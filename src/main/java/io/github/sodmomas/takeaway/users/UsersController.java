package io.github.sodmomas.takeaway.users;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sod-Momas
 * @since 2023/6/27
 */
@Controller
@RequestMapping("users")
public class UsersController {
    private static final Logger log = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private UsersService usersService;


    @GetMapping("profile")
    String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(anAuthentication);
//        SecurityContextHolder.setContext(context);
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("auth : {} user:{}", authentication, userDetails);
        model.addAttribute("user", userDetails);
        return "users/profile";
    }

    @GetMapping("profile/{username}")
    String profile(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", usersService.loadUserByUsername(username));
        return "users/profile";
    }

//    @GetMapping("groups")
//    String groups(Model model) {
//        userDetailsManager.loadUserByUsername()
//        model.addAttribute("groups", userDetailsManager.findAllGroups());
//        return "users/groups";
//    }
//
//    @GetMapping("groups/{group}")
//    String groupUsers(@PathVariable("group") String group, Model model) {
//        model.addAttribute("users", userDetailsManager.findUsersInGroup(group));
//        return "users/groups";
//    }

    @PostMapping("list")
    Page<UsersEntity> list(@RequestBody UsersListQuery query) {
        final QueryWrapper<UsersEntity> wrapper = Wrappers.<UsersEntity>query().like(StringUtils.hasText(query.getUsername()), "username", query.getUsername());
        return usersService.page(new Page<>(query.getCurrent(), query.getSize()), wrapper);
    }

}
