package io.github.sodmomas.takeaway.users;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sod-Momas
 * @since 2023/6/27
 */
@Controller
@RequestMapping("users")
public class UsersController {
    @RequestMapping("profile")
    String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(anAuthentication);
//        SecurityContextHolder.setContext(context);
        model.addAttribute("user", userDetails);
        return "users/profile";
    }
}
