package com.appsharedev.ShareApplication.service;

import com.appsharedev.ShareApplication.model.auth.JwtDTO;
import com.appsharedev.ShareApplication.others.CustomHttpResponse;
import com.appsharedev.ShareApplication.security.JwtProvider;
import com.appsharedev.ShareApplication.model.auth.LoginUser;
import com.appsharedev.ShareApplication.model.Rol;
import com.appsharedev.ShareApplication.model.User;
import com.appsharedev.ShareApplication.model.enums.RolName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    RolService rolService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    // Validation and create new user in the system
    public ResponseEntity<?> registerUser(User user) {
        if (this.userService.existUserByEmail(user.getEmail()))
            return new ResponseEntity<>(new String("There is already a user with this email"), HttpStatus.BAD_REQUEST);
        if (this.userService.existUserByDocument(user.getDocument()))
            return new ResponseEntity<>(new String("There is already a user with this document"), HttpStatus.BAD_REQUEST);
        Set<Rol> roles = new HashSet<>();
        Rol rolUser = rolService.getByRolName(RolName.ROLE_USER).get();
        roles.add(rolUser);
        user.setRoles(roles);
        user.setPassword(encoder.encode(user.getPassword()));
        this.userService.addUser(user);

        // The HttpStatus CREATED and OK must create a object with message response
        CustomHttpResponse customHttpResponse = new CustomHttpResponse();
        customHttpResponse.setMessage("The user was created successfully");
        return new ResponseEntity<>(customHttpResponse, HttpStatus.CREATED);
    }

    // Validate login user in the system
    // This method generate a token if credentials are correct and return to client the token
    /* This method return a JwtDTO. Check the  authentication, create the new token with jwtProvider, get username(email)
       and privileges with userDetails and it is packaged in the object JwtDTO and it is send client */
    public ResponseEntity<JwtDTO> loginUserValidate(LoginUser user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        CustomHttpResponse customHttpResponse = new CustomHttpResponse();
        customHttpResponse.setMessage("The user was created successfully");
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }

    // Check if exists in database a email registed
    public Boolean existsEmail(String email) {
        if(this.userService.getUserByEmail(email) != null)
            return true;
        return false;
    }

    // Check if exists in database a document registed
    public Boolean existsDocument(Integer document) {
        if(this.userService.getUserByDocument(document) != null)
            return true;
        return false;
    }
}
