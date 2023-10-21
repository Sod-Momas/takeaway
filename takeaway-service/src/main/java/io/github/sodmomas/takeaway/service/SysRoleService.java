package io.github.sodmomas.takeaway.service;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.sodmomas.takeaway.common.model.Option;
import io.github.sodmomas.takeaway.converter.RoleConverter;
import io.github.sodmomas.takeaway.mapper.SysRoleMapper;
import io.github.sodmomas.takeaway.model.entity.SysRole;
import io.github.sodmomas.takeaway.model.entity.SysRoleMenu;
import io.github.sodmomas.takeaway.model.entity.SysUserRole;
import io.github.sodmomas.takeaway.model.form.RoleForm;
import io.github.sodmomas.takeaway.model.query.RolePageQuery;
import io.github.sodmomas.takeaway.model.vo.RolePageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色业务接口层
 *
 * @author haoxr
 * @since 2022/6/3
 */
@Service
@RequiredArgsConstructor
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> implements IService<SysRole> {

    private final SysRoleMenuService sysRoleMenuService;
    private final SysUserRoleService sysUserRoleService;
    private final RoleConverter roleConverter;

    /**
     * 角色分页列表
     *
     * @param queryParams
     * @return
     */

    public Page<RolePageVO> getRolePage(RolePageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();

        // 查询数据
        Page<SysRole> rolePage = this.page(new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<SysRole>()
                        .and(StrUtil.isNotBlank(keywords),
                                wrapper ->
                                        wrapper.like(StrUtil.isNotBlank(keywords), SysRole::getName, keywords)
                                                .or()
                                                .like(StrUtil.isNotBlank(keywords), SysRole::getCode, keywords)
                        )
        );

        // 实体转换
        Page<RolePageVO> pageResult = roleConverter.entity2Page(rolePage);
        return pageResult;
    }

    /**
     * 角色下拉列表
     *
     * @return
     */

    public List<Option> listRoleOptions() {
        // 查询数据
        List<SysRole> roleList = this.list(new LambdaQueryWrapper<SysRole>()
                .select(SysRole::getId, SysRole::getName)
                .orderByAsc(SysRole::getSort)
        );

        // 实体转换
        List<Option> list = roleConverter.entities2Options(roleList);
        return list;
    }

    /**
     * 保存角色
     *
     * @param roleForm
     * @return
     */

    public boolean saveRole(RoleForm roleForm) {

        Long roleId = roleForm.getId();
        String roleCode = roleForm.getCode();

        long count = this.count(new LambdaQueryWrapper<SysRole>()
                .ne(roleId != null, SysRole::getId, roleId)
                .and(wrapper ->
                        wrapper.eq(SysRole::getCode, roleCode).or().eq(SysRole::getName, roleCode)
                ));
        Assert.isTrue(count == 0, "角色名称或角色编码重复，请检查！");

        // 实体转换
        SysRole role = roleConverter.form2Entity(roleForm);

        boolean result = this.saveOrUpdate(role);
        return result;
    }

    /**
     * 获取角色表单数据
     *
     * @param roleId 角色ID
     * @return {@link RoleForm} – 角色表单数据
     */

    public RoleForm getRoleForm(Long roleId) {
        SysRole entity = this.getById(roleId);
        RoleForm roleForm = roleConverter.entity2Form(entity);
        return roleForm;
    }

    /**
     * 修改角色状态
     *
     * @param roleId 角色ID
     * @param status 角色状态(1:启用；0:禁用)
     * @return {@link Boolean}
     */

    public boolean updateRoleStatus(Long roleId, Integer status) {
        boolean result = this.update(new LambdaUpdateWrapper<SysRole>()
                .eq(SysRole::getId, roleId)
                .set(SysRole::getStatus, status));
        return result;
    }

    /**
     * 批量删除角色
     *
     * @param ids 角色ID，多个使用英文逗号(,)分割
     * @return
     */

    public boolean deleteRoles(String ids) {
        List<Long> roleIds = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id)).collect(Collectors.toList());
        Optional.ofNullable(roleIds)
                .orElse(new ArrayList<>())
                .forEach(id -> {
                    long count = sysUserRoleService.count(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, id));
                    Assert.isTrue(count <= 0, "该角色已分配用户，无法删除");
                    sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, id));
                });


        boolean result = this.removeByIds(roleIds);
        return result;
    }

    /**
     * 获取角色的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合(包括按钮权限ID)
     */

    public List<Long> getRoleMenuIds(Long roleId) {
        List<Long> menuIds = sysRoleMenuService.listMenuIdsByRoleId(roleId);
        return menuIds;
    }

    /**
     * 修改角色的资源权限
     *
     * @param roleId
     * @param menuIds
     * @return
     */

    @Transactional
    @CacheEvict(cacheNames = "io/github/sodmomas/system", key = "'routes'")
    public boolean updateRoleMenus(Long roleId, List<Long> menuIds) {
        // 删除角色菜单
        sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        // 新增角色菜单
        if (CollectionUtil.isNotEmpty(menuIds)) {
            List<SysRoleMenu> roleMenus = menuIds.stream()
                    .map(menuId -> new SysRoleMenu(roleId, menuId))
                    .collect(Collectors.toList());
            sysRoleMenuService.saveBatch(roleMenus);
        }
        return true;
    }

    /**
     * 获取最大范围的数据权限
     *
     * @param roles
     * @return
     */

    public Integer getMaximumDataScope(Set<String> roles) {
        Integer dataScope = this.baseMapper.getMaximumDataScope(roles);
        return dataScope;
    }

}