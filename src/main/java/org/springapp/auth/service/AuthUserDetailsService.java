package org.springapp.auth.service;

import org.springapp.auth.AuthUser;
import org.springapp.entity.RoleEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Service
public class AuthUserDetailsService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthUser authUser = new AuthUser();
        authUser.setAccountNonExpired(true);
        authUser.setAuthorities(Collections.unmodifiableList(Arrays.asList(RoleEnum.NORMAL_USER)));
        authUser.setAccountNonLocked(true);
        authUser.setCredentialsNonExpired(true);
        authUser.setEnabled(true);
        authUser.setPassword(bcryptPasswordEncoder().encode("password"));
        authUser.setUsername("user");
        return authUser;
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
