package io.github.sodmomas.system.common.util;

import cn.hutool.core.util.StrUtil;
import io.github.sodmomas.system.common.constant.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 请求工具类
 *
 * @author haoxr
 */
public class RequestUtils {

    /**
     * 请求头解析获取 Token
     */
    public static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(SecurityConstants.TOKEN_KEY);
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return bearerToken.substring(SecurityConstants.TOKEN_PREFIX.length());
        }
        return null;
    }
}
