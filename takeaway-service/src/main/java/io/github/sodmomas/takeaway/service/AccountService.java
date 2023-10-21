package io.github.sodmomas.takeaway.service;

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
        account.setPassword(DigestUtils.md5DigestAsHex(account.getPassword().getBytes(StandardCharsets.UTF_8)));
        super.save(account);
    }
}
