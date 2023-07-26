package com.geekster.expensetracker11.controller;


import com.geekster.expensetracker11.model.User;
import com.geekster.expensetracker11.model.dto.SignInInput;
import com.geekster.expensetracker11.model.dto.SignUpOutput;
import com.geekster.expensetracker11.service.AuthService;
import com.geekster.expensetracker11.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;


    @PostMapping("user/signup")
    public SignUpOutput signUpUser(@RequestBody User user)
    {

        return userService.signUpUser(user);
    }
    @PostMapping("user/signIn")
    public String signInUser(@RequestBody @Valid SignInInput signInInput)
    {
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String signOutUser(String email, String token)
    {
        if(authService.authenticate(email,token)) {
            return userService.signOutUser(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }

    }

    @GetMapping("users")
    List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }
}
