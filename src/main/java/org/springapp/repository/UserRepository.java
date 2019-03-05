package org.springapp.repository;

import org.springapp.entity.Role;
import org.springapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserIdEquals(Integer id);
    Optional<User> findByFirstName(String firstname);
    List<User> findByRole (Role role);

}
