package io.github.sodmomas.takeaway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.sodmomas.takeaway.model.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色持久层
 *
 * @author haoxr
 * @since 2022/1/15
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}
