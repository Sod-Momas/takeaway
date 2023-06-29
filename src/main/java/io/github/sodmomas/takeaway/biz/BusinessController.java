package io.github.sodmomas.takeaway.biz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sod-Momas
 * @since 2023/6/30
 */
@Controller
public class BusinessController {
    /**
     * 药品管理
     */
    @GetMapping("/emp/drug")
    String drug() {
        return "employee";
    }

    /**
     * 患者管理
     */
    @GetMapping("/emp/patient")
    String patient() {
        return "employee";
    }

    /**
     * 医生管理
     */
    @GetMapping("/emp/doctor")
    String doctor() {
        return "employee";
    }

    /**
     * 处方管理
     */
    @GetMapping("/emp/rx")
    String rx() {
        return "employee";
    }
}
