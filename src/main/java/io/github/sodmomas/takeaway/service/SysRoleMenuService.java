package io.github.sodmomas.takeaway.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.github.sodmomas.takeaway.model.entity.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuService extends IService<SysRoleMenu> {


    /**
     * 获取角色拥有的菜单ID集合
     *
     * @param roleId
     * @return
     */
    List<Long> listMenuIdsByRoleId(Long roleId);
}
