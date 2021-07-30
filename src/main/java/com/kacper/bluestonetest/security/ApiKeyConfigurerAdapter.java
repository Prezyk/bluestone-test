package com.kacper.bluestonetest.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApiKeyConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Value("${holiday.security.apiKey.value}")
    private String apiKey;

    @Value("${holiday.security.authentication.header}")
    private String header;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApiKeyAuthenticationFilter apiKeyAuthenticationFilter = new ApiKeyAuthenticationFilter(header);
        apiKeyAuthenticationFilter.setAuthenticationManager(prepareAuthenticationManager());
        http.csrf().disable()
                .antMatcher("/api/**")
                .addFilter(apiKeyAuthenticationFilter)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    private AuthenticationManager prepareAuthenticationManager() {
        return authentication -> {
            String principal = (String) authentication.getPrincipal();
            if(!apiKey.equals(principal)) {
                throw new BadCredentialsException("Wrong API Key provided.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        };
    }
}
