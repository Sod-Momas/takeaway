package io.github.sodmomas.takeaway.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.sodmomas.takeaway.model.entity.DrugEntity;
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
Page<DrugEntity> list(@RequestBody DrugListQuery query) {
    Wrapper<DrugEntity> wrapper = Wrappers.<DrugEntity>lambdaQuery()
            // id 查询
            .in(DrugEntity::getId, query.getId())
            // 厂商查询
            .in(DrugEntity::getManufacturer, query.getManufacturer())
            // 产品名查询
            .in(DrugEntity::getProductName, query.getProductName())
            // 上架下架状态查询
            .in(DrugEntity::getPublished, query.getPublished());
    return drugService.page(Page.of(query.getPage(), query.getSize()), wrapper);
}

    @GetMapping("/drug/{id}")
    DrugEntity get(@PathVariable("id") Long id) {
        return drugService.getById(id);
    }

    @PostMapping("/add")
    Long add(@RequestBody DrugEntity drug) {
        drugService.add(drug);
        return drug.getId();
    }

    @PostMapping("/edit")
    Long edit(@RequestBody DrugEntity drug) {
        drugService.edit(drug);
        return drug.getId();
    }

    @PostMapping("/up")
    void up(@RequestBody @NotEmpty(message = "id为空") List<Long> ids) {
        drugService.up(ids);
    }

    @PostMapping("/down")
    void down(@RequestBody @NotEmpty(message = "id为空") List<Long> ids) {
        drugService.down(ids);
    }
}
