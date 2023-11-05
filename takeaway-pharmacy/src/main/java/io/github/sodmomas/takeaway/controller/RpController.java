package io.github.sodmomas.takeaway.controller;

import io.github.sodmomas.takeaway.model.AddRpRequest;
import io.github.sodmomas.takeaway.service.RpService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    Integer addRp(@RequestBody AddRpRequest request) {
        return rpService.addRp(request);
    }
}
