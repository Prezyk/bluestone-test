package com.kacper.bluestonetest.security;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;


public class ApiKeyAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private String requestHeader;

    public ApiKeyAuthenticationFilter(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(requestHeader);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return "N/A";
    }
}
