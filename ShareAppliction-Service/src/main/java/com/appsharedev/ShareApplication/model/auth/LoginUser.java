package com.appsharedev.ShareApplication.model.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

// This class is use for login check data username (email) and password
public class LoginUser {

    @NotBlank(message = "The email is a mandatory field")
    @Email(message = "The email field haven't the correct format")
    private String email;

    @NotBlank(message = "The password is a mandatory field")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
