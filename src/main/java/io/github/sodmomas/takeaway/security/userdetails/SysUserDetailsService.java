package io.github.sodmomas.takeaway.security.userdetails;

import io.github.sodmomas.takeaway.model.dto.UserAuthInfo;
import io.github.sodmomas.takeaway.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 系统用户认证
 *
 * @author haoxr
 */
@Service
@RequiredArgsConstructor
public class SysUserDetailsService implements UserDetailsService, AuthenticationProvider {

    private final SysUserService sysUserService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthInfo userAuthInfo = sysUserService.getUserAuthInfo(username);
        if (userAuthInfo == null) {
            throw new UsernameNotFoundException(username);
        }
        return new SysUserDetails(userAuthInfo);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取用户提供的凭据，如用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 使用自定义的 UserDetailsService 加载用户信息
        UserDetails userDetails = this.loadUserByUsername(username);

        // 执行你的身份验证逻辑，比较用户提供的密码与加载的用户密码是否匹配
        if (passwordMatches(password, userDetails.getPassword())) {
            // 如果密码匹配，创建认证成功的 Authentication 对象，包含用户权限等信息
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            // 如果密码不匹配，抛出 AuthenticationException 异常表示认证失败
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class == authentication;
    }

    private boolean passwordMatches(String fromDb, String fromUser) {
        return passwordEncoder.encode(fromUser).equals(fromDb);
    }
}
