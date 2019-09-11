package com.appsharedev.ShareApplication.service;

import com.appsharedev.ShareApplication.model.User;
import com.appsharedev.ShareApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    // Query select all users
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // Query select user with the document
    public User getUserByDocument(int document) {
        return userRepository.findUserByDocument(document);
    }

    // Insert new row in the table user
    public User addUser(User user) {
        return this.userRepository.save(user);
    }

}
