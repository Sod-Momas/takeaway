package io.github.sodmomas.takeaway.service;

import cn.hutool.json.JSONUtil;
import io.github.sodmomas.takeaway.common.constant.RedisKeyConstants;
import io.github.sodmomas.takeaway.common.constant.SecurityConstants;
import io.github.sodmomas.takeaway.common.enums.RoleEnum;
import io.github.sodmomas.takeaway.common.exception.BusinessException;
import io.github.sodmomas.takeaway.model.dto.LoginResult;
import io.github.sodmomas.takeaway.model.dto.UserAuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author Sod-Momas
 * @since 2023/7/6
 */
@Service
public class AuthService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public LoginResult login(String username, String password,RoleEnum role) {
        // 获取登录账号信息
        final UserAuthInfo user = sysUserService.getUserAuthInfo(username);
        if (user == null) {
            // 账号不存在
            throw new BusinessException("登录失败，请检查用户名或密码");
        }
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            // 密码不正确
            throw new BusinessException("登录失败，请检查用户名或密码");
        }
        if (!user.getRoles().contains(role.getDesc())) {
            // 角色错误
            throw new BusinessException("登录失败，请检查用户角色");
        }
        // 登录凭证
        final String accessToken = UUID.randomUUID().toString().replaceAll("-", "");
        // 如果accessToken过期，则使用refreshToken来验证身份并生成新的accessToken
        final String refreshToken = UUID.randomUUID().toString().replaceAll("-", "");
        // 把token保存到缓存里
        redisTemplate.opsForValue().set(RedisKeyConstants.ACCESS_TOKEN_PREFIX + ":" + accessToken, JSONUtil.toJsonStr(user));
        redisTemplate.opsForValue().set(RedisKeyConstants.REFRESH_TOKEN_PREFIX + ":" + accessToken, refreshToken);
        // 把登录凭证返回给前端
        return LoginResult.builder()
                .tokenType(SecurityConstants.ACCESS_TOKEN_PREFIX)
                .refreshToken(refreshToken)
                .accessToken(SecurityConstants.ACCESS_TOKEN_PREFIX + accessToken)
                .expires(SecurityConstants.TIMEOUT)
                .build();
    }

    public void logout(String accessToken) {
        redisTemplate.delete(RedisKeyConstants.ACCESS_TOKEN_PREFIX + ":" + accessToken);
        redisTemplate.delete(RedisKeyConstants.REFRESH_TOKEN_PREFIX + ":" + accessToken);
    }
}
