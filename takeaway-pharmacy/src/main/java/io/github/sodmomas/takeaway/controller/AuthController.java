package io.github.sodmomas.takeaway.controller;

import io.github.sodmomas.takeaway.common.constant.SecurityConstants;
import io.github.sodmomas.takeaway.common.enums.RoleEnum;
import io.github.sodmomas.takeaway.common.result.Result;
import io.github.sodmomas.takeaway.model.dto.LoginResult;
import io.github.sodmomas.takeaway.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "01.认证中心")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<LoginResult> login(
            @Parameter(description = "用户名", example = "admin") @RequestParam String username,
            @Parameter(description = "密码", example = "123456") @RequestParam String password
    ) {
        LoginResult result = authService.login(username, password, RoleEnum.PHARMACY);
        return Result.success(result);
    }

    @Operation(summary = "注销", security = {@SecurityRequirement(name = SecurityConstants.TOKEN_KEY)})
    @DeleteMapping("/logout")
    public Result<?> logout(@RequestHeader("Authorization") String accessToken) {
        authService.logout(accessToken);
        return Result.success();
    }
}
