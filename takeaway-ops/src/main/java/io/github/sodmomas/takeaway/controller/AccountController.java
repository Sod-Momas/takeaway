package io.github.sodmomas.takeaway.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import io.github.sodmomas.takeaway.model.entity.Account;
import io.github.sodmomas.takeaway.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sod-Momas
 * @since 2023/10/21
 */
@RestController
@RequestMapping("account")
public class AccountController {
    private @Autowired AccountService accountService;

    @PostMapping("add")
    Integer addAccount(@RequestBody Account account) {
        accountService.addAccount(account);
        return (account.getId());
    }

    @PostMapping("page")
    Page<Account> page(@RequestBody PageDTO<Account> query) {
        return (accountService.page(query));
    }

    @PostMapping("edit")
    Integer edit(@RequestBody Account account) {
        accountService.edit(account);
        return (account.getId());
    }

    @PostMapping("enable")
    Integer enable(@RequestBody Account account) {
        accountService.enable(account.getId());
        return (account.getId());
    }

    @PostMapping("disable")
    Integer disable(@RequestBody Account account) {
        accountService.disable(account.getId());
        return (account.getId());
    }
}
