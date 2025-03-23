package main.controller;

import main.repository.UserRepository;
import main.repository.SecurityRepository;
import main.security.UserSecurity;
import main.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityRepository securityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (securityRepository.findByLogin(login).isPresent()) {
            model.addAttribute("error", "Пользователь уже существует!");
            return "register";
        }

        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        user.setSex(sex);
        user.setDeleted(false);

        UserSecurity security = new UserSecurity();
        security.setLogin(login);
        security.setPassword(passwordEncoder.encode(password));
        security.setRole("USER");
        security.setUser(user);

        user.setSecurity(security);

        userRepository.save(user);

        return "redirect:/users";
    }
}
