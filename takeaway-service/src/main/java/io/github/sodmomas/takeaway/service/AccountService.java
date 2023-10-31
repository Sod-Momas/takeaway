package io.github.sodmomas.takeaway.service;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.sodmomas.takeaway.mapper.AccountMapper;
import io.github.sodmomas.takeaway.model.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author Sod-Momas
 * @since 2023/10/21
 */
@Service
public class AccountService extends ServiceImpl<AccountMapper, Account> {

    public void addAccount(Account account) {
        Assert.notBlank(account.getNickname(), "用户昵称为空");
        Assert.notBlank(account.getUsername(), "用户名称为空");
        Assert.notBlank(account.getPassword(), "用户密码为空");

        // 检查用户昵称
        final Account sameNickName = super.getOne(Wrappers.<Account>lambdaQuery().eq(Account::getNickname, account.getNickname()).last("LIMIT 1"));
        Assert.isNull(sameNickName, "用户昵称重复");

        // 检查用户名称
        final Account sameUsername = super.getOne(Wrappers.<Account>lambdaQuery().eq(Account::getNickname, account.getNickname()).last("LIMIT 1"));
        Assert.isNull(sameUsername, "用户名称重复");

        account.setPassword(DigestUtils.md5DigestAsHex(account.getPassword().getBytes(StandardCharsets.UTF_8)));
        super.save(account);
    }

    public void edit(Account account) {
        Assert.notNull(account.getId(), "用户id为空");
        Assert.notBlank(account.getNickname(), "用户昵称为空");
        Assert.notBlank(account.getUsername(), "用户名称为空");
        Assert.notBlank(account.getPassword(), "用户密码为空");

        // 检查用户昵称
        final Account sameNickName = super.getOne(Wrappers.<Account>lambdaQuery()
                .eq(Account::getNickname, account.getNickname())
                .ne(Account::getId, account.getId())
                .last("LIMIT 1"));
        Assert.isNull(sameNickName, "用户昵称重复");

        // 检查用户名称
        final Account sameUsername = super.getOne(Wrappers.<Account>lambdaQuery()
                .eq(Account::getNickname, account.getNickname())
                .ne(Account::getId, account.getId())
                .last("LIMIT 1"));
        Assert.isNull(sameUsername, "用户名称重复");

        account.setPassword(DigestUtils.md5DigestAsHex(account.getPassword().getBytes(StandardCharsets.UTF_8)));
        super.updateById(account);
    }

    public void disable(Long id) {
        Assert.notNull(id, "id为空");
        // 禁用账号，enabled设置为false
        super.update(Wrappers.<Account>lambdaUpdate().eq(Account::getId, id).set(Account::getEnabled, false));
    }

    public void enable(Long id) {
        Assert.notNull(id, "id为空");
        // 启用账号，enabled设置为true
        super.update(Wrappers.<Account>lambdaUpdate().eq(Account::getId, id).set(Account::getEnabled, true));
    }
}
