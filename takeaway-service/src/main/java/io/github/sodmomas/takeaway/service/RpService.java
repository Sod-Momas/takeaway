package io.github.sodmomas.takeaway.service;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.sodmomas.takeaway.common.enums.RpStatusEnum;
import io.github.sodmomas.takeaway.mapper.RpMapper;
import io.github.sodmomas.takeaway.model.AddRpRequest;
import io.github.sodmomas.takeaway.model.entity.Rp;
import io.github.sodmomas.takeaway.model.entity.RpItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Integer addRp(AddRpRequest request) {
        final Rp rp = new Rp();
        rp.setPharmacyId(request.getPharmacyId());
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
}
