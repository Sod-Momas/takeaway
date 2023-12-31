package io.github.sodmomas.takeaway.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.github.sodmomas.takeaway.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sod-Momas
 * @since 2023/10/21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_rel_account_role")
@KeySequence(dbType = DbType.POSTGRE_SQL, value = "takeaway_seq")
public class RelAccountRole extends BaseEntity {
    @TableId(type = IdType.INPUT)
    private Integer id;
    private Integer accountId;
    private Integer roleId;
}
