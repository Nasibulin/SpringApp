
package org.springapp.service.auth;


import org.springapp.auth.AuthUser;
import org.springapp.auth.AuthUserFactory;
import org.springapp.entity.User;
import org.springapp.entity.UserToken;
import org.springapp.repository.UserRepository;
import org.springapp.repository.UserTokenRepository;
import org.springapp.service.AbstractBaseService;
import org.springapp.util.Constant;
import org.springapp.util.DateUtil;
import org.springapp.util.UniqueID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthServiceImpl extends AbstractBaseService implements AuthService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private UserTokenRepository userTokenRepository;
    
    @Autowired
    AuthUserFactory authUserFactory;
    
    @Override
    public UserToken createUserToken (User userLogin, boolean keepMeLogin){
        try {
            UserToken userToken = new UserToken();
            userToken.setToken(UniqueID.getUUID());
//            userToken.setCompanyId(userLogin.getCompanyId());
            userToken.setUserId(userLogin.getUserId());
            Date currentDate = new Date();
            userToken.setLoginDate(DateUtil.convertToUTC(currentDate));
            Date expirationDate = keepMeLogin ? new Date(currentDate.getTime() + Constant.DEFAULT_REMEMBER_LOGIN_MILISECONDS) : new Date(currentDate.getTime() + Constant.DEFAULT_SESSION_TIME_OUT);
            userToken.setExpirationDate(DateUtil.convertToUTC(expirationDate));
            AuthUser authUser = authUserFactory.createAuthUser(userLogin);
            // Set authUser to session data...
            userToken.setSessionData(gson.toJson(authUser));
            userTokenRepository.save(userToken);
            return userToken;
        } catch (Exception e) {
//            LOGGER.error("Error create User token ", e);
            throw new RuntimeException("SQL_ERROR");
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByUserId(Integer userId) {
        return userRepository.findByUserIdEquals(userId);
    }

    @Override
    public UserToken getUserTokenById(String id) {
        return userTokenRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUserToken(UserToken userToken) {
        userTokenRepository.delete(userToken);
    }
    
}
