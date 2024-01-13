package com.example.jwtsecurity.controller;

import com.example.jwtsecurity.entity.AuthRequest;
import com.example.jwtsecurity.entity.AuthResponse;
import com.example.jwtsecurity.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public AuthResponse createJwtToken(@RequestBody AuthRequest authRequest)throws Exception{
        return jwtService.createJwtToken(authRequest);
    }
}
