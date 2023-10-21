package io.github.sodmomas.takeaway.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import io.github.sodmomas.takeaway.common.result.Result;
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
    Result<Long> addAccount(@RequestBody Account account) {
        accountService.addAccount(account);
        return Result.success(account.getId());
    }

    @PostMapping("page")
    Result<Page<Account>> page(@RequestBody PageDTO<Account> query) {
        return Result.success(accountService.page(query));
    }
}
