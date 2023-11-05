package io.github.sodmomas.takeaway.controller;

import io.github.sodmomas.takeaway.common.constant.SecurityConstants;
import io.github.sodmomas.takeaway.common.enums.RoleEnum;
import io.github.sodmomas.takeaway.model.dto.LoginResult;
import io.github.sodmomas.takeaway.model.query.LoginQuery;
import io.github.sodmomas.takeaway.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "01.认证中心")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public LoginResult login(@RequestBody LoginQuery query) {
        return authService.login(query.getUsername(), query.getPassword(), RoleEnum.DOCTOR);
    }

    @Operation(summary = "注销", security = {@SecurityRequirement(name = SecurityConstants.TOKEN_KEY)})
    @DeleteMapping("/logout")
    public void logout(@RequestHeader("Authorization") String accessToken) {
        authService.logout(accessToken);
    }
}
