package io.github.sodmomas.takeaway.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.sodmomas.takeaway.model.entity.Drug;
import io.github.sodmomas.takeaway.model.query.DrugListFilterQuery;
import io.github.sodmomas.takeaway.model.query.DrugListQuery;
import io.github.sodmomas.takeaway.model.vo.FilterVO;
import io.github.sodmomas.takeaway.service.DrugService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sod-Momas
 * @since 2023/7/5
 */
@RestController
@RequestMapping("/drug")
public class DrugController {
    @Autowired
    private DrugService drugService;

    @PostMapping("/filter")
    Page<FilterVO> filter(@RequestBody DrugListFilterQuery query) {
        return drugService.filter(query);
    }

    @PostMapping("/page")
    Page<Drug> list(@RequestBody DrugListQuery query) {
        Wrapper<Drug> wrapper = Wrappers.<Drug>lambdaQuery()
                // id 查询
                .in(CollectionUtil.isNotEmpty(query.getId()), Drug::getId, query.getId())
                // 厂商查询
                .eq(null != query.getManufacturer(), Drug::getManufacturer, query.getManufacturer())
                // 产品名查询
                .eq(null != query.getProductName(), Drug::getProductName, query.getProductName())
                // 上架下架状态查询
                .eq(null != query.getPublished(), Drug::getPublished, query.getPublished());
        return drugService.page(Page.of(query.getPage(), query.getSize()), wrapper);
    }

    @GetMapping("/drug/{id}")
    Drug get(@PathVariable("id") Long id) {
        return drugService.getById(id);
    }

    @PostMapping("/add")
    Integer add(@RequestBody Drug drug) {
        drugService.add(drug);
        return drug.getId();
    }

    @PostMapping("/edit")
    Integer edit(@RequestBody Drug drug) {
        drugService.edit(drug);
        return drug.getId();
    }

    @PostMapping("/up")
    void up(@RequestBody @NotEmpty(message = "id为空") List<Integer> ids) {
        drugService.up(ids);
    }

    @PostMapping("/down")
    void down(@RequestBody @NotEmpty(message = "id为空") List<Integer> ids) {
        drugService.down(ids);
    }
}
