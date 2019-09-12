package com.appsharedev.ShareApplication;

import com.appsharedev.ShareApplication.security.JwtEntryPoint;
import com.appsharedev.ShareApplication.security.JwtTokenFilter;
import com.appsharedev.ShareApplication.service.security.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// This class configure all the options of security
// Some options some of security are:
// Permissions for access resources
// That resources can be access
// Type of acess
// Password encrypt
// This is a sub-class of WebSecurityConfigurerAdapter
@Configuration
@EnableWebSecurity
// This tag EnableGlobalMethodSecurity, as use is so that in the methods of the controllers be indicated the privileges of roles for access to the resources
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigApp extends WebSecurityConfigurerAdapter {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // Encrypt the password
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sessionService).passwordEncoder(bcrypt);
    }

    // Configure all options of security of the application.
    // This method defines the resources can be accessed and with its privileges
    // .authorizeRequests() define authorization for access to a resource
    // .antMatchers() define to route
    // .permitAll() anyone can access
    // .anyRequest().authenticated() others routes for access is required to be authenticated
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
