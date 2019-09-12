package com.appsharedev.ShareApplication.controller;

import com.appsharedev.ShareApplication.model.auth.JwtDTO;
import com.appsharedev.ShareApplication.model.auth.LoginUser;
import com.appsharedev.ShareApplication.model.User;
import com.appsharedev.ShareApplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.POST}) // REST CORS
@RequestMapping("/auth")


public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/register")
    public User userRegister(@Valid @RequestBody User user) {
        return this.authService.registerUser(user);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtDTO> userLogin(@Valid @RequestBody LoginUser user) {
        return this.authService.loginUserValidate(user);
    }
}
