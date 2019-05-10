package org.springapp.service.auth;


import org.springapp.entity.User;
import org.springapp.entity.UserToken;

public interface AuthService {
    
    public User getUserByEmail (String email);

    public UserToken createUserToken(User user, boolean keepMeLogin);
    
    public User findByUserId (Integer userId);
    
    public UserToken getUserTokenById (String id);
    
    public void deleteUserToken (UserToken userToken);
}
