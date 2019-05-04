package org.springapp.service.auth;

import org.springapp.entity.User;
import org.springapp.entity.UserToken;

public interface AuthService {
    
    public User getUserByEmail (String email);

    public UserToken createUserToken(User adminUser, boolean keepMeLogin);
    
    public User getUserByUserId (Integer userId);
    
    public UserToken getUserTokenById (String id);
    
    public void deleteUserToken (UserToken userToken);
}
