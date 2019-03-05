package org.springapp.service.role;

import org.springapp.entity.Category;
import org.springapp.entity.Product;
import org.springapp.entity.Role;

import java.util.List;

public interface RoleService {
    Role findRoleByRoleId(Integer id);
    Role findRoleByAuthority(String authority);
}
