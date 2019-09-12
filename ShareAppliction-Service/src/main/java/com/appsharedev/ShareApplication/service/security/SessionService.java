package com.appsharedev.ShareApplication.service.security;

import com.appsharedev.ShareApplication.model.User;
import com.appsharedev.ShareApplication.model.security.Session;
import com.appsharedev.ShareApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SessionService implements UserDetailsService {

    @Autowired
    UserService userService;

    // Get a user with the email for login and created a user session
    @Override
    // This tag allows the method to create operations on the database with the service UserService
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(username); // get a user of the database with the name
        return Session.build(user); // create session with the user
    }
}
