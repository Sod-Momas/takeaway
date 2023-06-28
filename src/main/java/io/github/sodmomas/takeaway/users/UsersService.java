package io.github.sodmomas.takeaway.users;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Sod-Momas
 * @since 2023/6/29
 */
@Service
public class UsersService extends ServiceImpl<UsersMapper, UserEntity> implements IService<UserEntity> {
}
