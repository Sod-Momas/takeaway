package io.github.sodmomas.takeaway.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.sodmomas.takeaway.model.dto.UserAuthInfo;
import io.github.sodmomas.takeaway.model.entity.SysUser;
import io.github.sodmomas.takeaway.model.form.UserForm;
import io.github.sodmomas.takeaway.model.query.UserPageQuery;
import io.github.sodmomas.takeaway.model.vo.UserExportVO;
import io.github.sodmomas.takeaway.model.vo.UserInfoVO;
import io.github.sodmomas.takeaway.model.vo.UserPageVO;

import java.util.List;

/**
 * 用户业务接口
 *
 * @author haoxr
 * @since 2022/1/14
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 用户分页列表
     *
     * @return
     */
    IPage<UserPageVO> getUserPage(UserPageQuery queryParams);


    /**
     * 获取用户表单数据
     *
     * @param userId
     * @return
     */
    UserForm getUserFormData(Long userId);


    /**
     * 新增用户
     *
     * @param userForm 用户表单对象
     * @return
     */
    boolean saveUser(UserForm userForm);

    /**
     * 修改用户
     *
     * @param userId   用户ID
     * @param userForm 用户表单对象
     * @return
     */
    boolean updateUser(Long userId, UserForm userForm);


    /**
     * 删除用户
     *
     * @param idsStr 用户ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteUsers(String idsStr);


    /**
     * 修改用户密码
     *
     * @param userId   用户ID
     * @param password 用户密码
     * @return
     */
    boolean updatePassword(Long userId, String password);

    /**
     * 根据用户名获取认证信息
     *
     * @param username 用户名
     * @return {@link UserAuthInfo}
     */

    UserAuthInfo getUserAuthInfo(String username);


    /**
     * 获取导出用户列表
     *
     * @param queryParams
     * @return
     */
    List<UserExportVO> listExportUsers(UserPageQuery queryParams);


    /**
     * 获取登录用户信息
     *
     * @return
     */
    UserInfoVO getUserLoginInfo();
}
