package org.springapp.auth.service;

import org.springapp.entity.UserToken;
import org.springapp.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    public UserToken save(UserToken userToken) {
        return userTokenRepository.save(userToken);
    }

    public UserToken getTokenById(String token) {
        return userTokenRepository.findByToken(token);
    }

    public void invalidateToken(UserToken userToken) {
        userTokenRepository.delete(userToken);
    }

}
