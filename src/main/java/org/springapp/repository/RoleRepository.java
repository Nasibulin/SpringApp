package org.springapp.repository;

import org.springapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByRoleId(Integer id);

    Role findRoleByAuthority(String authority);
}
