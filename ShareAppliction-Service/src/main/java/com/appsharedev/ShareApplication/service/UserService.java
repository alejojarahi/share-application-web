package com.appsharedev.ShareApplication.service;

import com.appsharedev.ShareApplication.model.User;
import com.appsharedev.ShareApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
        return userDetails;

    }

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
        user.setPassword(encoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

}
