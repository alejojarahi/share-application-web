package com.appsharedev.ShareApplication.controller;

import com.appsharedev.ShareApplication.model.User;
import com.appsharedev.ShareApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST}) // REST CORS
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/search", params = "/{document}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@RequestParam(name = "document") Integer document) {
        return userService.getUserByDocument(document);
    }

    @GetMapping(value = "/search", params = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@RequestParam(name = "email") String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping(value = "/adduser", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User addNewUser(@Valid @RequestBody User user) {
        return this.userService.addUser(user);
    }

}
