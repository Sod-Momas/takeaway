package io.github.sodmomas.takeaway.drug;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @PostMapping("/drug-list")
    Page<DrugEntity> list(Page<DrugEntity> page, DrugEntity drug) {
        return drugService.page(page, Wrappers.query(drug));
    }

    @GetMapping("/drug/{id}")
    DrugEntity get(@PathVariable("id") Long id) {
        return drugService.getById(id);
    }

    @PostMapping("/add")
    Long add(@RequestBody DrugEntity drug) {
        drugService.save(drug);
        return drug.getId();
    }

    @DeleteMapping("/drug")
    void delete(@RequestParam List<Long> ids) {
        drugService.removeBatchByIds(ids);
    }
}
