package io.github.sodmomas.takeaway.model.dto;

import lombok.Data;

import java.util.Set;

/**
 * 用户认证信息
 *
 * @author haoxr
 * @since 2022/10/22
 */
@Data
public class UserAuthInfo {
    private Long accountId;
    private String username;
    private String nickname;
    private String password;
    private Boolean enabled;
    private Set<Integer> roles;
}
