package ru.pangaia.example.bookstore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pangaia.example.bookstore.entity.User;
import ru.pangaia.example.bookstore.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    UserRepository userRepository;
    @RequestMapping(value = "/users/")
    List<User> getUsers()
    {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user/{userId}/")
    User getUser(@PathVariable Long userId)
    {
        return userRepository.findById(userId).get();

    }
    @RequestMapping(value = "/users/create", method = RequestMethod.POST, consumes = "application/json")
    User createUser(@RequestBody User user)
    {
        userRepository.saveAndFlush(user);
        return user;
    }

}
