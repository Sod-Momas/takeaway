package io.github.sodmomas.takeaway.users;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Sod-Momas
 * @since 2023/6/29
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDetailConverter {

    default UserDetails toDetail(UsersEntity user) {
        return User.withUsername(user.getUsername())
                .roles("USER", "ADMIN")
                .password(user.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .authorities(AuthorityUtils.NO_AUTHORITIES).build();
    }
}
