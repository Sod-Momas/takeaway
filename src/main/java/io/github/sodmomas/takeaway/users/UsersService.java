package io.github.sodmomas.takeaway.users;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Sod-Momas
 * @since 2023/6/29
 */
@Service
public class UsersService extends ServiceImpl<UsersMapper, UsersEntity> implements IService<UsersEntity>, UserDetailsService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserDetailConverter userDetailConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UsersEntity user = usersMapper.selectOne(Wrappers.<UsersEntity>query().eq("username", username));
        if (user == null) throw new UsernameNotFoundException("用户不存在:" + username);
        return userDetailConverter.toDetail(user);
    }
}
