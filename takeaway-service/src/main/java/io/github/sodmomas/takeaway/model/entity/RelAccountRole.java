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
@KeySequence(dbType = DbType.POSTGRE_SQL, value = "drug_id_sequence")
public class RelAccountRole extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    private Integer accountId;
    private Integer roleId;
}
