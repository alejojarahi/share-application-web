package com.appsharedev.ShareApplication.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// This class check the credentials in the login
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    private static  final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class); // print errors  and method relationship with the error

    // Inject method commence of class AuthenticationEntryPoint for initialize the login authentication
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        logger.error("Fail in the method commence for JwtEntryPoint"); // print in console error
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized for this action"); // print the http response with connection for authentication no permission
    }
}