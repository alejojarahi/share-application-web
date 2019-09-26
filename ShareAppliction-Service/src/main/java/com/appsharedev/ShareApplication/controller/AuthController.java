package com.appsharedev.ShareApplication.controller;

import com.appsharedev.ShareApplication.model.auth.JwtDTO;
import com.appsharedev.ShareApplication.model.auth.LoginUser;
import com.appsharedev.ShareApplication.model.User;
import com.appsharedev.ShareApplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.POST, RequestMethod.GET}) // REST CORS
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> userRegister(@Valid @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            // Get all errors found in the form
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            // Return status http error
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return this.authService.registerUser(user);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtDTO> userLogin(@Valid @RequestBody LoginUser user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            // Get all errors found in the form
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            // Return status http error
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        return this.authService.loginUserValidate(user);
    }

    @GetMapping(value = "/search", params = {"email"})
    public Boolean existsEmail(@RequestParam(name = "email") String email) {
        return authService.existsEmail(email);
    }

    @GetMapping(value = "/search", params = {"document"})
    public Boolean existsDocument(@RequestParam(name = "document") Integer document) {
        return authService.existsDocument(document);
    }
}
