package ru.tinkoff.edu.java.t2_seminar2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.t2_seminar2.dto.UserList;
import ru.tinkoff.edu.java.t2_seminar2.service.UserService;

@RestController
@RequestMapping("/api/1.0/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserList getUsers() {
        return userService.getAllUsers();
    }

}
