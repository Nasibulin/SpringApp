package org.springapp.service.role;

import org.springapp.entity.Role;
import org.springapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Role findRoleByName(String name) {
        return repository.findRoleByName(name);
    }
}