package org.springapp.service.users;

import org.springapp.entity.Role;
import org.springapp.entity.User;
import org.springapp.repository.RoleRepository;
import org.springapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;


//    @PostConstruct
//    public void init() {
//
//        if (repository.findByFirstName("guest") == null) {
//            User user = new User();
//            user.setPassword(new BCryptPasswordEncoder().encode("guest"));
//            user.setRole(roleRepository.findRoleByAuthority(USER_ROLE.GUEST.name()));
//            user.setFirstName("guest");
//            repository.save(user);
//        }
//        if (repository.findByFirstName("customer") == null) {
//            User user = new User();
//            user.setPassword(new BCryptPasswordEncoder().encode("customer"));
//            user.setRole(roleRepository.findRoleByAuthority(USER_ROLE.NORMAL_USER.name()));
//            user.setFirstName("customer");
//            repository.save(user);
//        }
//        if (repository.findByFirstName("admin") == null) {
//            User user = new User();
//            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
//            user.setRole(roleRepository.findRoleByAuthority(USER_ROLE.SYS_ADMIN.name()));
//            user.setFirstName("admin");
//            repository.save(user);
//        }
//
//    }

    @Override
    public User findByUserId(Integer id) {
        return repository.findByUserId(id);
    }

    @Override
    public User findByFirstName(String firstname) {
        return repository.findByFirstName(firstname);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<User> findByRole(Role role) {
        return repository.findByRole(role);
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
}