package org.springapp.service.user;

import org.springapp.entity.Role;
import org.springapp.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByUserIdEquals(Integer id);
    Optional<User> findByFirstName(String firstname);
    List<User> findByRole (Role role);
    void saveUser(User user);
}
