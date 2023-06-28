package io.github.sodmomas.takeaway.users;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * @author Sod-Momas
 * @since 2023/6/29
 */
@TableName("users")
public class UsersEntity extends Model<UsersEntity> {
    @TableId
    private String username;
    @TableField
    private String password;
    @TableField
    private Boolean enabled;
}
