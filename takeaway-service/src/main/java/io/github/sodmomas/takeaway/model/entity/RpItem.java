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
@TableName("t_rp_item")
@KeySequence(dbType = DbType.POSTGRE_SQL, value = "takeaway_seq")
public class RpItem extends BaseEntity {
    @TableId(type = IdType.INPUT)
    private Integer id;
    private Integer rpId;
    private String drugName;
    private String drugType;
    private Integer drugCount;
}
