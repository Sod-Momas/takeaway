package io.github.sodmomas.takeaway.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.sodmomas.takeaway.model.entity.DrugEntity;
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
public class DrugController {
    @Autowired
    private DrugService drugService;

    @PostMapping("/page")
    Page<DrugEntity> list(Page<DrugEntity> page, DrugEntity drug) {
        return drugService.page(page, Wrappers.lambdaQuery(drug));
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
