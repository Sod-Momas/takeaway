package io.github.sodmomas.takeaway.controller;

import io.github.sodmomas.takeaway.common.annotation.PreventDuplicateSubmit;
import io.github.sodmomas.takeaway.common.model.Option;
import io.github.sodmomas.takeaway.common.result.Result;
import io.github.sodmomas.takeaway.model.form.MenuForm;
import io.github.sodmomas.takeaway.model.query.MenuQuery;
import io.github.sodmomas.takeaway.model.vo.MenuVO;
import io.github.sodmomas.takeaway.model.vo.RouteVO;
import io.github.sodmomas.takeaway.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单控制器
 *
 * @author haoxr
 * @since 2020/11/06
 */
@Tag(name = "04.菜单接口")
@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
@Slf4j
public class SysMenuController {

    private final SysMenuService menuService;

    @Operation(summary = "菜单列表",security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping
    public Result<List<MenuVO>> listMenus(@ParameterObject MenuQuery queryParams) {
        List<MenuVO> menuList = menuService.listMenus(queryParams);
        return Result.success(menuList);
    }

    @Operation(summary = "菜单下拉列表",security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/options")
    public Result listMenuOptions() {
        List<Option> menus = menuService.listMenuOptions();
        return Result.success(menus);
    }

    @Operation(summary = "路由列表",security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/routes")
    public Result<List<RouteVO>> listRoutes() {
        List<RouteVO> routeList = menuService.listRoutes();
        return Result.success(routeList);
    }

    @Operation(summary = "菜单表单数据",security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{id}/form")
    public Result<MenuForm> getMenuForm(
            @Parameter(description =  "菜单ID") @PathVariable Long id
    ) {
        MenuForm menu = menuService.getMenuForm(id);
        return Result.success(menu);
    }

    @Operation(summary = "新增菜单",security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping
    @PreventDuplicateSubmit
    @CacheEvict(cacheNames = "io/github/sodmomas/system", key = "'routes'")
    public Result addMenu(@RequestBody MenuForm menuForm) {
        boolean result = menuService.saveMenu(menuForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改菜单",security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping(value = "/{id}")
    @CacheEvict(cacheNames = "io/github/sodmomas/system", key = "'routes'")
    public Result updateMenu(
            @RequestBody MenuForm menuForm
    ) {
        boolean result = menuService.saveMenu(menuForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除菜单",security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "io/github/sodmomas/system", key = "'routes'")
    public Result deleteMenu(
            @Parameter(description ="菜单ID，多个以英文(,)分割") @PathVariable("id") Long id
    ) {
        boolean result = menuService.deleteMenu(id);
        return Result.judge(result);
    }

    @Operation(summary = "修改菜单显示状态",security = {@SecurityRequirement(name = "Authorization")})
    @PatchMapping("/{menuId}")
    public Result updateMenuVisible(
            @Parameter(description =  "菜单ID") @PathVariable Long menuId,
            @Parameter(description =  "显示状态(1:显示;0:隐藏)") Integer visible

    ) {
        boolean result =menuService.updateMenuVisible(menuId, visible);
        return Result.judge(result);
    }


}
