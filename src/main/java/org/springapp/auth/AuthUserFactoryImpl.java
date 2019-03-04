package org.springapp.auth;

import org.springapp.entity.Role;
import org.springapp.entity.RoleEnum;
import org.springapp.entity.User;
import org.springapp.repository.RoleRepository;
import org.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class AuthUserFactoryImpl implements AuthUserFactory{
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    
    @Override
    public AuthUser createAuthUser(User user) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(user.getFirstName());
        authUser.setPassword(user.getPasswordHash());
        authUser.setEnabled(true);
        authUser.setCredentialsNonExpired(true);
        authUser.setAccountNonExpired(true);
        authUser.setAccountNonLocked(true);
        authUser.setAuthorities(Collections.unmodifiableList(Arrays.asList(RoleEnum.NORMAL_USER)));
        return authUser;
    }
    
    private Role getUserRole (int roleId){
        try {
            return roleRepository.findRoleByRoleId(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
