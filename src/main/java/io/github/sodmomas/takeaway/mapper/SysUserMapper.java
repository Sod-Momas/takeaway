package io.github.sodmomas.takeaway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.sodmomas.system.common.annotation.DataPermission;
import io.github.sodmomas.system.model.bo.UserBO;
import io.github.sodmomas.system.model.bo.UserFormBO;
import io.github.sodmomas.system.model.dto.UserAuthInfo;
import io.github.sodmomas.system.model.entity.SysUser;
import io.github.sodmomas.system.model.query.UserPageQuery;
import io.github.sodmomas.system.model.vo.UserExportVO;
import io.github.sodmomas.takeaway.model.bo.UserBO;
import io.github.sodmomas.takeaway.model.bo.UserFormBO;
import io.github.sodmomas.takeaway.model.dto.UserAuthInfo;
import io.github.sodmomas.takeaway.model.entity.SysUser;
import io.github.sodmomas.takeaway.model.query.UserPageQuery;
import io.github.sodmomas.takeaway.model.vo.UserExportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户持久层
 *
 * @author haoxr
 * @since 2022/1/14
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    @DataPermission(deptAlias = "u")
    Page<UserBO> getUserPage(Page<UserBO> page, UserPageQuery queryParams);

    /**
     * 获取用户表单详情
     *
     * @param userId 用户ID
     * @return
     */
    UserFormBO getUserDetail(Long userId);

    /**
     * 根据用户名获取认证信息
     *
     * @param username
     * @return
     */
    UserAuthInfo getUserAuthInfo(String username);

    /**
     * 获取导出用户列表
     *
     * @param queryParams
     * @return
     */
    @DataPermission(deptAlias = "u")
    List<UserExportVO> listExportUsers(UserPageQuery queryParams);
}
