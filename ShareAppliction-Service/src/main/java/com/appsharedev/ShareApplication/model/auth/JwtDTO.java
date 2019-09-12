package com.appsharedev.ShareApplication.model.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

// This class use the pattern design DTO (Data Transfer Object) for agility the data traffic between the service and clients
// The class JwtDTO package the jwp and go from service to client or client to service
public class JwtDTO {
    private String token;
    private static String bearer = "Bearer";
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDTO(String token, String email, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.email = email;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
