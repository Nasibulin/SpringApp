package org.springapp.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
   SYS_ADMIN, STORE_ADMIN,NORMAL_USER, GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
