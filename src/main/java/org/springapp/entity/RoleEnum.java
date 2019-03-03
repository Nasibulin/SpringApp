package org.springapp.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    ADMIN, POWER_USER, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
