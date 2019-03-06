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
//
//        if (repository.findByFirstName("guest") == null) {
//            User user = new User();
//            user.setPasswordHash(new BCryptPasswordEncoder().encode("guest"));
//            user.setRole(roleRepository.findRoleByAuthority(USER_ROLE.GUEST.name()));
//            user.setFirstName("guest");
//            repository.save(user);
//        }
//        if (repository.findByFirstName("customer") == null) {
//            User user = new User();
//            user.setPasswordHash(new BCryptPasswordEncoder().encode("customer"));
//            user.setRole(roleRepository.findRoleByAuthority(USER_ROLE.NORMAL_USER.name()));
//            user.setFirstName("customer");
//            repository.save(user);
//        }
//        if (repository.findByFirstName("admin") == null) {
//            User user = new User();
//            user.setPasswordHash(new BCryptPasswordEncoder().encode("admin"));
//            user.setRole(roleRepository.findRoleByAuthority(USER_ROLE.SYS_ADMIN.name()));
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