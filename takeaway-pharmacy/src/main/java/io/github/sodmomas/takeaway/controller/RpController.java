package io.github.sodmomas.takeaway.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.sodmomas.takeaway.model.PharmacyAddRpRequest;
import io.github.sodmomas.takeaway.model.PharmacyRpDetail;
import io.github.sodmomas.takeaway.model.PharmacyRpListRequest;
import io.github.sodmomas.takeaway.service.RpService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sod-Momas
 * @since 2023/11/4
 */
@RestController
@RequestMapping("/rp")
public class RpController {
    private @Autowired RpService rpService;

    @PostMapping("/add")
    @Operation(description = "添加处方")
    Integer addRp(@RequestBody PharmacyAddRpRequest request) {
        return rpService.pharmacyAddRp(request);
    }

    @PostMapping("/page")
    Page<PharmacyRpDetail> pharmacyRpListPage(@RequestBody PharmacyRpListRequest request) {
        return rpService.pharmacyRpListPage(request);
    }

    @GetMapping("/detail/{id}")
    PharmacyRpDetail pharmacyRpDetail(@PathVariable("id") Integer id) {
        return rpService.pharmacyRpDetail(id);
    }
}
