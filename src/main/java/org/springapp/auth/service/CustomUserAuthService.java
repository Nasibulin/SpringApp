package org.springapp.auth.service;

import org.springapp.auth.AuthUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserAuthService extends UserDetailsService{
    AuthUser loadUserByAccessToken(String token);
}
