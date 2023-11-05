package io.github.sodmomas.takeaway.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.github.sodmomas.takeaway.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sod-Momas
 * @since 2023/11/5
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_rp")
@KeySequence(dbType = DbType.POSTGRE_SQL, value = "takeaway_seq")
public class Rp extends BaseEntity {
    @TableId(type = IdType.INPUT)
    private Integer id;
    private Integer pharmacyId;
    private Integer patientId;
    private Integer doctorId;
    private Integer checkPharmacistId;
    private Integer recheckPharmacistId;
    /**
     * @see io.github.sodmomas.takeaway.common.enums.RpStatusEnum
     */
    private Integer status;
    private String rejectReason;
    private String doctorAdvice;
}
