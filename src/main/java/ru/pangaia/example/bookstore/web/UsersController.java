package ru.pangaia.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.pangaia.example.bookstore.entity.User;
import ru.pangaia.example.bookstore.repository.UserRepository;

import java.util.List;

@Controller
public class UsersController
{
    @Autowired
    UserRepository userRepository;
    @GetMapping("/users/")
    String users(Model model)
    {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    @PostMapping("/users/")
    String addUser(
            @RequestParam("name") String name,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            Model model)
    {
        User user = new User();
        user.name = name;
        if (email != null)
        {
            user.email = email;
        }
        user.login = login;
        user.setPassword(password);
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    @PostMapping("/users/delete")
    String deleteUser(@RequestParam("id") Long userId)
    {
        User user = userRepository.getOne(userId);
        userRepository.delete(user);
        return "redirect:/users/";
    }
    @GetMapping("/user/{userId}")
    String user(@PathVariable Long userId, Model model)
    {
        User user = userRepository.getOne(userId);
        model.addAttribute("user", user);
        return "userDetails";
    }
}
