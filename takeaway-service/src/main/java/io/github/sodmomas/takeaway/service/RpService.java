package io.github.sodmomas.takeaway.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.sodmomas.takeaway.common.enums.RpStatusEnum;
import io.github.sodmomas.takeaway.config.TakeawayFilter;
import io.github.sodmomas.takeaway.mapper.RpMapper;
import io.github.sodmomas.takeaway.model.PharmacyAddRpRequest;
import io.github.sodmomas.takeaway.model.PharmacyRpDetail;
import io.github.sodmomas.takeaway.model.PharmacyRpListRequest;
import io.github.sodmomas.takeaway.model.entity.Rp;
import io.github.sodmomas.takeaway.model.entity.RpItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Sod-Momas
 * @since 2023/11/5
 */
@Service
public class RpService extends ServiceImpl<RpMapper, Rp> {
    private @Autowired RpItemService rpItemService;

    /**
     * 开处方
     *
     * @param request 处方信息
     * @return 处方id
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer pharmacyAddRp(PharmacyAddRpRequest request) {
        final Integer accountId = TakeawayFilter.LOGIN_USER.get().getAccountId();

        final Rp rp = new Rp();
        rp.setPharmacyId(accountId);
        rp.setPatientId(request.getPatientId());
        rp.setStatus(RpStatusEnum.WAIT_CHECK.getCode());
        rp.setDoctorAdvice(request.getDoctorAdvice());
        Assert.isTrue(super.save(rp), "处方保存失败");

        final List<RpItem> list = request.getItemList().stream()
                .map(e -> {
                    final RpItem rpItem = new RpItem();
                    rpItem.setRpId(rp.getId());
                    rpItem.setDrugName(e.getDrugName());
                    rpItem.setDrugCount(e.getDrugCount());
                    rpItem.setDrugType(e.getDrugType());
                    return rpItem;
                }).toList();
        Assert.isTrue(rpItemService.saveBatch(list), "处方明细保存失败");

        return rp.getId();
    }

    public Page<PharmacyRpDetail> pharmacyRpListPage(PharmacyRpListRequest request) {
        final Integer accountId = TakeawayFilter.LOGIN_USER.get().getAccountId();
        Wrapper<Rp> wrapper = Wrappers.<Rp>lambdaQuery().eq(Rp::getPharmacyId, accountId);
        final Page<Rp> rpPage = super.page(Page.of(request.getPage(), request.getSize()), wrapper);
        final List<Rp> rpList = rpPage.getRecords();
        if (rpList.isEmpty()) {
            // 没数据
            return Page.of(request.getPage(), request.getSize());
        }
        // 使用处方id查找处方明细
        final Set<Integer> rpIds = rpList.stream().map(Rp::getId).collect(Collectors.toSet());
        final List<RpItem> rpItemList = rpItemService.list(Wrappers.<RpItem>lambdaQuery().in(RpItem::getRpId, rpIds));
        // 按处方id分组
        final Map<Integer, List<PharmacyRpDetail.Item>> rpItemMap = rpItemList.stream()
                .map(e -> BeanUtil.copyProperties(e, PharmacyRpDetail.Item.class))
                .collect(Collectors.groupingBy(PharmacyRpDetail.Item::getRpId));

        final List<PharmacyRpDetail> outs = new ArrayList<>(rpList.size());
        for (Rp rp : rpList) {
            final PharmacyRpDetail out = BeanUtil.copyProperties(rp, PharmacyRpDetail.class);
            // 按处方id获取明细列表
            out.setItemList(rpItemMap.get(out.getId()));
            outs.add(out);
        }

        final Page<PharmacyRpDetail> ret = Page.of(request.getPage(), request.getSize());
        ret.setRecords(outs);
        return ret;
    }

    public PharmacyRpDetail pharmacyRpDetail(Integer id) {
        // 根据id查找处方
        Wrapper<Rp> wrapper = Wrappers.<Rp>lambdaQuery()
                .eq(Rp::getId, id)
                .last("LIMIT 1");
        final Rp one = super.getOne(wrapper);
        // 没查到
        if (one == null) return null;
        // 查找处方明细
        final List<PharmacyRpDetail.Item> list = rpItemService.list(Wrappers.<RpItem>lambdaQuery().in(RpItem::getRpId, one.getId())).stream()
                .map(e -> BeanUtil.copyProperties(e, PharmacyRpDetail.Item.class))
                .toList();
        // 转换为出参格式
        final PharmacyRpDetail out = BeanUtil.copyProperties(one, PharmacyRpDetail.class);
        out.setItemList(list);
        return out;
    }
}
