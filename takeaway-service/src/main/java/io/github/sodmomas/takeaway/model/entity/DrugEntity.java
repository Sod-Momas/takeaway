package io.github.sodmomas.takeaway.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Sod-Momas
 * @since 2023/7/2
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("drug")
@KeySequence(dbType = DbType.POSTGRE_SQL, value = "drug_id_sequence")
public class DrugEntity extends Model<DrugEntity> {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 英文名称
     */
    private String englishName;

    /**
     * 商品名
     */
    private String brandName;

    /**
     * 剂型
     */
    private String dosageForm;

    /**
     * 规格
     */
    private String specification;

    /**
     * 上市许可持有人
     */
    private String licenseHolder;

    /**
     * 上市许可持有人地址
     */
    private String licenseHolderAddress;

    /**
     * 生产单位
     */
    private String manufacturer;

    /**
     * 批准日期
     */
    private Date approvalDate;

    /**
     * 生产地址
     */
    private String manufacturingAddress;

    /**
     * 产品类别
     */
    private String productCategory;

    /**
     * 原批准文号
     */
    private String originalApprovalNumber;

    /**
     * 药品本位码
     */
    private String drugStandardCode;

    /**
     * 药品本位码备注
     */
    private String drugStandardCodeRemark;
    /**
     * 是否发布, 1-上架, 0-下架
     */
    private Integer published;

    @TableLogic
    @JsonIgnore
    private Integer deleted;
}
