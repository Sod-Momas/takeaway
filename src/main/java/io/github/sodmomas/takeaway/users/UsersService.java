package io.github.sodmomas.takeaway.users;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Sod-Momas
 * @since 2023/6/29
 */
@Service
public class UsersService extends ServiceImpl<UsersMapper, UsersEntity> implements IService<UsersEntity>/*, UserDetailsService*/, AuthenticationProvider {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserDetailConverter userDetailConverter;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UsersEntity user = usersMapper.selectOne(Wrappers.<UsersEntity>query().eq("username", username));
        if (user == null) throw new UsernameNotFoundException("用户不存在:" + username);
        return userDetailConverter.toDetail(user);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取用户提供的凭据，如用户名和密码
        // 执行自定义的身份验证逻辑，校验凭据是否有效
        // 如果认证成功，返回一个带有用户权限的 Authentication 对象
        // 如果认证失败，抛出 AuthenticationException 异常
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
        // 指定支持的认证类型，在这里一般返回 UsernamePasswordAuthenticationToken.class
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

    // 比较密码是否匹配的自定义方法
    private boolean passwordMatches(String rawPassword, String encodedPassword) {
        // 根据需要选择适合的密码比较方式，如使用 BCryptPasswordEncoder 进行比较
        // 返回密码是否匹配的结果
        return passwordEncoder.encode(encodedPassword).equals(rawPassword);
    }
}
