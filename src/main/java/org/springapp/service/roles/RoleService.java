package org.springapp.service.roles;

import org.springapp.entity.Role;

public interface RoleService {
    Role findRoleByRoleId(Integer id);

    Role findRoleByAuthority(String authority);
}
