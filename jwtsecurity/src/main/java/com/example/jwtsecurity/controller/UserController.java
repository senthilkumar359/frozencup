package com.example.jwtsecurity.controller;

import com.example.jwtsecurity.entity.User;
import com.example.jwtsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void createInit(){
        userService.inituserAndRoles();
    }

    @RequestMapping(value = "/registerNewUser",method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) throws Exception {
        return userService.registerNewUser(user);
    }

    @RequestMapping(value="/usercode",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public String userCode(){
        return "accessed only by user:)";
    }

    @RequestMapping(value="/admincode",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String adminCode(){
        return "accessed only by admin:)";
    }

}
