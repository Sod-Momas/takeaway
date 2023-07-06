package io.github.sodmomas.takeaway.service;

import io.github.sodmomas.takeaway.common.constant.SecurityConstants;
import io.github.sodmomas.takeaway.common.exception.BusinessException;
import io.github.sodmomas.takeaway.model.dto.LoginResult;
import io.github.sodmomas.takeaway.model.dto.UserAuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Sod-Momas
 * @since 2023/7/6
 */
@Service
public class AuthService {
    @Autowired
    private SysUserService sysUserService;
    private static final Map<String, UserAuthInfo> loginUserMap = Collections.synchronizedMap(new HashMap<>());
    private static final Map<String, String> refreshTokenMap = Collections.synchronizedMap(new HashMap<>());

    public LoginResult login(String username, String password) {
        final UserAuthInfo user = sysUserService.getUserAuthInfo(username);
        if (user.getPassword().equals(password)) {
            throw new BusinessException("登录失败，请检查用户名或密码");
        }
        final String token = UUID.randomUUID().toString().replaceAll("-", "");
        loginUserMap.put(token, user);
        refreshTokenMap.put(token, token);
        return LoginResult.builder()
                .tokenType(SecurityConstants.TOKEN_PREFIX)
                .refreshToken(token)
                .accessToken("Bearer " + token)
                .expires(1000 * 30 * 30L)
                .build();
    }

    public void logout(String accessToken) {
        if (accessToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            accessToken = accessToken.replace(SecurityConstants.TOKEN_PREFIX, "");
        }
        loginUserMap.remove(accessToken);
    }
}
