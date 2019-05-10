package com.sprint.data.controller;

import com.sprint.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller
{
    @Autowired
    private UserService userService;
    
    // http://localhost:2019/users/mine
    // get active user and their todos
    @GetMapping(value = "/users/mine", produces = {"application/json"})
    private ResponseEntity<?> getActiveUser(Authentication authentication)
    {
        String currentUserName = authentication.getName();
        System.out.println(currentUserName);
        
        return new ResponseEntity<>(userService.findUserByName(currentUserName), HttpStatus.OK);
    }
}
