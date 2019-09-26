package com.appsharedev.ShareApplication.controller;

import com.appsharedev.ShareApplication.model.User;
import com.appsharedev.ShareApplication.model.dto.UserDTO;
import com.appsharedev.ShareApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // This class is a service REST
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST}) // REST CORS
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // Get all users registered in the database
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get all users registered in the database for pagination, defined param page and size of page elements
    @GetMapping(value = "/list", params = {"page", "size"} )
    public ResponseEntity<Page<UserDTO>> getUsersPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
                @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<UserDTO> users = userService.getUsersPage(PageRequest.of(page, size));

        return new ResponseEntity<Page<UserDTO>>(users, HttpStatus.OK);
    }

    // Get user for the document
    @GetMapping(value = "/search", params = "/{document}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@RequestParam(name = "document") Integer document) {
        return userService.getUserByDocument(document);
    }

    // Get user for the email (username)
    @GetMapping(value = "/search", params = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@RequestParam(name = "email") String email) {
        return userService.getUserByEmail(email);
    }

    // Adds a new user in the database, this action is done by admin
    @PostMapping(value = "/adduser", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User addNewUser(@Valid @RequestBody User user) {
        return this.userService.addUser(user);
    }

}
