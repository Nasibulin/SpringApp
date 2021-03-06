package org.springapp.service.roles;

import org.springapp.entity.Role;
import org.springapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository repository;

    @Autowired
    public void setProductRepository(RoleRepository repository) {
        this.repository = repository;
    }


    @Override
    public Role findRoleByRoleId(Integer id) {
        return repository.findRoleByRoleId(id);
    }

    @Override
    public Role findRoleByAuthority(String authority) {
        return repository.findRoleByAuthority(authority);
    }

}