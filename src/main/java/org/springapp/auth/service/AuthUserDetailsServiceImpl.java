
package org.springapp.auth.service;

import com.google.gson.Gson;
import org.springapp.auth.AuthUser;
import org.springapp.entity.UserToken;
import org.springapp.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsServiceImpl implements CustomUserAuthService {
    
    Gson gson = new Gson();
    
    @Autowired
    private UserTokenRepository userTokenRepository;
    
    @Override
    public UserDetails loadUserByUsername(String userId) {
        // Not implement
        return null;
    }
    
    
    @Override
    public AuthUser loadUserByAccessToken(String token) {
        UserToken session = userTokenRepository.findByTokenEquals(token);
        if (session != null){
            if (session.getSessionData() != null && !"".equals(session.getSessionData())){
                AuthUser authUser = gson.fromJson(session.getSessionData(), AuthUser.class);
                return authUser;
            }else{
                throw new IllegalArgumentException("Session data invalid");
            }
        }else{
            throw new IllegalArgumentException("Session not found");
        }
    }
}
