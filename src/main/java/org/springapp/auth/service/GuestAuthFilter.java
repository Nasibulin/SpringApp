package org.springapp.auth.service;

import org.springapp.auth.AuthUser;
import org.springapp.auth.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

//@Component
//public class GuestAuthFilter extends GenericFilterBean {
//    @Autowired
//    private AuthUserDetailsService  authUserDetailsService;
//
//    private AuthUser guest;
//
//    @PostConstruct
//    public void init() {
//        guest = (AuthUser) authUserDetailsService.loadUserByUsername("guest@gmail.com");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(guest));
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
