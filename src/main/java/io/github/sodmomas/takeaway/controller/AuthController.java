package io.github.sodmomas.takeaway.controller;

import io.github.sodmomas.takeaway.common.result.Result;
import io.github.sodmomas.takeaway.model.dto.CaptchaResult;
import io.github.sodmomas.takeaway.security.captcha.EasyCaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "01.认证中心")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final EasyCaptchaService easyCaptchaService;

    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public Result getCaptcha() {
        CaptchaResult captcha = easyCaptchaService.getCaptcha();
        return Result.success(captcha);
    }

}
