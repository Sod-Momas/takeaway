package io.github.sodmomas.takeaway.service;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.sodmomas.takeaway.common.enums.DrugPublishEnum;
import io.github.sodmomas.takeaway.mapper.DrugMapper;
import io.github.sodmomas.takeaway.model.entity.DrugEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sod-Momas
 * @since 2023/7/5
 */
@Service
public class DrugService extends ServiceImpl<DrugMapper, DrugEntity> {

    /**
     * 添加药品
     *
     * @param drug 药品信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(DrugEntity drug) {
        Assert.notBlank(drug.getProductName(), "产品名称为空");
        Assert.notBlank(drug.getBrandName(), "商品名为空");
        Assert.notBlank(drug.getApprovalNumber(), "批准文号为空");
        Assert.notBlank(drug.getDrugStandardCode(), "药品本位码为空");

        super.save(drug);
    }

    /**
     * 编辑药品信息
     *
     * @param drug 药品信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void edit(DrugEntity drug) {
        Assert.notNull(drug.getId(), "id为空");
        Assert.notBlank(drug.getProductName(), "产品名称为空");
        Assert.notBlank(drug.getBrandName(), "商品名为空");
        Assert.notBlank(drug.getApprovalNumber(), "批准文号为空");
        Assert.notBlank(drug.getDrugStandardCode(), "药品本位码为空");

        super.updateById(drug);
    }

    /**
     * 上架药品
     *
     * @param ids 药品列表
     */
    @Transactional(rollbackFor = Exception.class)
    public void up(List<Long> ids) {
        super.update(Wrappers.<DrugEntity>lambdaUpdate().in(DrugEntity::getId, ids).set(DrugEntity::getPublished, DrugPublishEnum.UP.getCode()));
    }

    /**
     * 下架
     *
     * @param ids 药品id列表
     */
    @Transactional(rollbackFor = Exception.class)
    public void down(List<Long> ids) {
        super.update(Wrappers.<DrugEntity>lambdaUpdate().in(DrugEntity::getId, ids).set(DrugEntity::getPublished, DrugPublishEnum.DOWN.getCode()));
    }
}
