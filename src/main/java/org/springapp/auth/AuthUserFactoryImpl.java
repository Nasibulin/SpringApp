package org.springapp.auth;

import org.springapp.entity.User;
import org.springframework.stereotype.Service;

@Service
public class AuthUserFactoryImpl implements AuthUserFactory {

    @Override
    public AuthUser createAuthUser(User user) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(user.getEmail());
        authUser.setPassword(user.getPassword());
        authUser.setEnabled(true);
        authUser.setCredentialsNonExpired(true);
        authUser.setAccountNonExpired(true);
        authUser.setAccountNonLocked(true);
        authUser.setAuthorities(user.getRole());
        return authUser;
    }


}
