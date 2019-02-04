package ru.koguts.enterprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.koguts.enterprise.model.User;
import ru.koguts.enterprise.service.UserServiceImpl;

@RestController
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user")
    public @ResponseBody
    User getUserByName(@RequestParam(name = "name") String name) {
        return userService.findByName(name);
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping("/addUser")
    public @ResponseBody
    User createUser(@RequestParam(name = "name") String name) {
        User user = new User();
        user.setName(name);
        userService.create(user);
        return user;
    }
}


