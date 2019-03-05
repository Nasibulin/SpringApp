package org.springapp.service.user;

import org.springapp.entity.Role;
import org.springapp.entity.User;
import org.springapp.repository.RoleRepository;
import org.springapp.repository.UserRepository;
import static org.springapp.util.Constant.USER_ROLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private RoleRepository roleRepository;

    @Autowired
    public void setUserRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository repository) {
        this.roleRepository = repository;
    }

//    @PostConstruct
//    public void init() {
//        repository.findByFirstName("user").ifPresent((user -> {
//            user.setPasswordHash(new BCryptPasswordEncoder().encode("user"));
//            repository.save(user);}));
//
//        if (repository.findByFirstName("user") == null) {
//            User user = new User();
//            user.setPasswordHash(new BCryptPasswordEncoder().encode("password"));
//            user.setRole(roleRepository.findRoleByName(USER_ROLE.NORMAL_USER.name()));
//            user.setFirstName("user");
//            repository.save(user);
//        }
//        if (repository.findByFirstName("admin") == null) {
//            User user = new User();
//            user.setPasswordHash(new BCryptPasswordEncoder().encode("admin"));
//            user.setRole(roleRepository.findRoleByName(USER_ROLE.SYS_ADMIN.name()));
//            user.setFirstName("admin");
//            repository.save(user);
//        }
//
//    }

    @Override
    public User findByUserIdEquals(Integer id) {
        return repository.findByUserIdEquals(id);
    }

    @Override
    public Optional<User> findByFirstName(String firstname) {
        return repository.findByFirstName(firstname);
    }

    @Override
    public List<User> findByRole(Role role) {
        return repository.findByRole(role);
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }
}