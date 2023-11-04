package io.github.sodmomas.takeaway.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.sodmomas.takeaway.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sod-Momas
 * @since 2023/10/21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_account")
public class Account extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String phone;
    private Boolean enabled;
}
