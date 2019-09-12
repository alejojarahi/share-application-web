package com.appsharedev.ShareApplication.service;

import com.appsharedev.ShareApplication.model.User;
import com.appsharedev.ShareApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

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

    // Query select user with the document
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    // Insert new row in the table user
    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    // Query for check that exist to user with parameter email
    public boolean existUserByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    // Query for check that exist to user with parameter document
    public boolean existUserByDocument(Integer document) {
        return this.userRepository.existsByDocument(document);
    }

}
