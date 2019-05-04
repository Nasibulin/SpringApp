package org.springapp.service.users;


import org.springapp.entity.User;
import org.springapp.entity.UserToken;
import org.springapp.repository.UserRepository;
import org.springapp.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTokenRepository userTokenRepository;
    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User users) {
        return userRepository.save(users);
    }

    public User getUserByUserIdAndComIdAndStatus(Integer userId) {
        return userRepository.findByUserIdEquals(userId);
    }

    public User getUserByActivationCode(String token) {
        UserToken userToken = userTokenRepository.getOne(token);

        if (userToken != null) {
            return userRepository.findByUserIdEquals(userToken.getUserId());
        } else {
            return null;
        }
    }
    
}
