package com.tms.controller;

import com.tms.model.User;
import com.tms.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String getUserCreatePage() {
        return "createUser";
    }

    @GetMapping("/update-page/{id}")
    public String getUserUpdatePage(@PathVariable("id") Long userId, Model model, HttpServletResponse response) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            model.addAttribute("message", "User not found: id=" + userId);
            return "innerError";
        }
        model.addAttribute("user", user.get());
        return "edit";
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, HttpServletResponse response, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "createUser";
        }

        Optional<User> createdUser = userService.createUser(user);
        if (createdUser.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not created");
            return "innerError";
        }
        model.addAttribute("user", createdUser.get());
        return "user";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model, HttpServletResponse response) {
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("message", "User not found: id=" + id);
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("user", user.get());
        return "user";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "edit";
        }

        Optional<User> userUpdated = userService.updateUser(user);
        if (userUpdated.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not updated.");
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("user", userUpdated.get());
        return "user";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId, Model model, HttpServletResponse response) {
        Optional<User> userDeleted = userService.deleteUser(userId);
        if (userDeleted.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not deleted.");
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("user", userDeleted.get());
        return "user";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model, HttpServletResponse response) {
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "There are no users in the database.");
            return "innerError";
        }

        model.addAttribute("users", users);
        response.setStatus(HttpServletResponse.SC_OK);
        return "userlist";
    }

}
