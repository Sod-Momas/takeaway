package io.github.sodmomas.takeaway.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sod-Momas
 * @since 2023/6/27
 */
@Controller
@RequestMapping("users")
public class UsersController {

//    @Autowired
//    private UserDetailsManager userDetailsManager;
//    @Autowired
//    private GroupManager groupManager;
    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;
    @Autowired
    private UsersService usersService;

    @GetMapping("profile")
    String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(anAuthentication);
//        SecurityContextHolder.setContext(context);
        model.addAttribute("user", userDetails);
        return "users/profile";
    }

    @GetMapping("profile/{username}")
    String profile(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userDetailsManager.loadUserByUsername(username));
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

}
