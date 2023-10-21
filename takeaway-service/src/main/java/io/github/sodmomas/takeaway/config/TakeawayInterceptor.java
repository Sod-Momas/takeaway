package io.github.sodmomas.takeaway.config;

import cn.hutool.json.JSONUtil;
import io.github.sodmomas.takeaway.common.constant.RedisKeyConstants;
import io.github.sodmomas.takeaway.common.constant.SecurityConstants;
import io.github.sodmomas.takeaway.common.result.Result;
import io.github.sodmomas.takeaway.common.result.ResultCode;
import io.github.sodmomas.takeaway.model.dto.UserAuthInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sod-Momas
 * @since 2023/10/7
 */
@Component
public class TakeawayInterceptor implements HandlerInterceptor {
    private static final ThreadLocal<UserAuthInfo> LOGIN_USER = new ThreadLocal<>();
    private static final Set<String> NO_LOGIN_URL_SET = new HashSet<>(Arrays.asList(
            // 登录接口
            "/auth/login",
            // 验证码接口
            "/auth/captcha"
    ));
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (NO_LOGIN_URL_SET.contains(request.getRequestURI())) {
            // 不需要验证身份，直接请求接口
            return true;
        } else {
            String token = request.getHeader(SecurityConstants.TOKEN_KEY);
            String redisKey = RedisKeyConstants.ACCESS_TOKEN_PREFIX + ":" + token;
            if (Boolean.TRUE.equals(redisTemplate.hasKey(redisKey))) {
                // token存在且有效
                String json = redisTemplate.opsForValue().get(redisKey);
                UserAuthInfo user = JSONUtil.toBean(json, UserAuthInfo.class);
                LOGIN_USER.set(user);
                return true;
            } else {
                // token过期或者不存在
                response.setStatus(403);
                response.setContentType("application/json");
                response.getWriter().write(JSONUtil.toJsonStr(Result.failed(ResultCode.TOKEN_INVALID)));
                return false;
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束的时候移除保存的用户信息，避免污染线程
        LOGIN_USER.remove();
    }
}