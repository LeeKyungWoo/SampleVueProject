package com.example.login.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.model.LoginUser;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping({"/", ""})
    public String loginForm(){
        return "loginForm";
    }
	
}
