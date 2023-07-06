package io.github.sodmomas.takeaway.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.sodmomas.takeaway.common.annotation.PreventDuplicateSubmit;
import io.github.sodmomas.takeaway.common.model.Option;
import io.github.sodmomas.takeaway.common.result.PageResult;
import io.github.sodmomas.takeaway.common.result.Result;
import io.github.sodmomas.takeaway.model.form.DictForm;
import io.github.sodmomas.takeaway.model.form.DictTypeForm;
import io.github.sodmomas.takeaway.model.query.DictPageQuery;
import io.github.sodmomas.takeaway.model.query.DictTypePageQuery;
import io.github.sodmomas.takeaway.model.vo.DictPageVO;
import io.github.sodmomas.takeaway.model.vo.DictTypePageVO;
import io.github.sodmomas.takeaway.service.SysDictService;
import io.github.sodmomas.takeaway.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "06.字典接口")
@RestController
@RequestMapping("/api/v1/dict")
@RequiredArgsConstructor
public class SysDictController {

    private final SysDictService dictService;

    private final SysDictTypeService dictTypeService;

    @Operation(summary = "字典分页列表", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/page")
    public PageResult<DictPageVO> getDictPage(
            @ParameterObject DictPageQuery queryParams
    ) {
        Page<DictPageVO> result = dictService.getDictPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "字典数据表单数据", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{id}/form")
    public Result<DictForm> getDictForm(
            @Parameter(description ="字典ID") @PathVariable Long id
    ) {
        DictForm formData = dictService.getDictForm(id);
        return Result.success(formData);
    }

    @Operation(summary = "新增字典", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping
    @PreventDuplicateSubmit
    public Result saveDict(
            @RequestBody DictForm DictForm
    ) {
        boolean result = dictService.saveDict(DictForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改字典", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/{id}")
    public Result updateDict(
            @PathVariable Long id,
            @RequestBody DictForm DictForm
    ) {
        boolean status = dictService.updateDict(id, DictForm);
        return Result.judge(status);
    }

    @Operation(summary = "删除字典", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/{ids}")
    public Result deleteDict(
            @Parameter(description ="字典ID，多个以英文逗号(,)拼接") @PathVariable String ids
    ) {
        boolean result = dictService.deleteDict(ids);
        return Result.judge(result);
    }


    @Operation(summary = "字典下拉列表", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/options")
    public Result<List<Option>> listDictOptions(
            @Parameter(description ="字典类型编码") @RequestParam String typeCode
    ) {
        List<Option> list = dictService.listDictOptions(typeCode);
        return Result.success(list);
    }


    /*----------------------------------------------------*/
    @Operation(summary = "字典类型分页列表", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/types/page")
    public PageResult<DictTypePageVO> getDictTypePage(
            @ParameterObject DictTypePageQuery queryParams
    ) {
        Page<DictTypePageVO> result = dictTypeService.getDictTypePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "字典类型表单数据", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/types/{id}/form")
    public Result<DictTypeForm> getDictTypeForm(
            @Parameter(description ="字典ID") @PathVariable Long id
    ) {
        DictTypeForm dictTypeForm = dictTypeService.getDictTypeForm(id);
        return Result.success(dictTypeForm);
    }

    @Operation(summary = "新增字典类型", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping("/types")
    @PreventDuplicateSubmit
    public Result saveDictType(@RequestBody DictTypeForm dictTypeForm) {
        boolean result = dictTypeService.saveDictType(dictTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改字典类型", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/types/{id}")
    public Result updateDictType(@PathVariable Long id, @RequestBody DictTypeForm dictTypeForm) {
        boolean status = dictTypeService.updateDictType(id, dictTypeForm);
        return Result.judge(status);
    }

    @Operation(summary = "删除字典类型", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/types/{ids}")
    public Result deleteDictTypes(
            @Parameter(description ="字典类型ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = dictTypeService.deleteDictTypes(ids);
        return Result.judge(result);
    }

}
