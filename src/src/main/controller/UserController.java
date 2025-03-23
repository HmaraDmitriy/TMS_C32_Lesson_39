package main.controller;

import main.repository.UserRepository;
import main.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            String username;

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }

            Optional<User> user = userRepository.findBySecurity_Login(username);

            if (user.isPresent()) {
                model.addAttribute("user", user.get());
                return "user-info"; // Страница с информацией о пользователе после входа
            } else {
                model.addAttribute("error", "Пользователь не найден!");
            }
        }

        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editUserForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        Optional<User> user = userRepository.findBySecurity_Login(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user-edit";
        }
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        Optional<User> user = userRepository.findBySecurity_Login(username);
        user.ifPresent(userRepository::delete);

        return "redirect:/";
    }
}
