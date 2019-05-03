package org.springapp.service.auth;

import org.springapp.entity.User;
import org.springapp.entity.UserToken;

public interface AuthService {
    
    public User getUserByEmailAndCompanyIdAndStatus (String email, Long companyId, int status);

    public UserToken createUserToken(User adminUser, boolean keepMeLogin);
    
    public User getUserByUserIdAndCompanyIdAndStatus (String userId, Long companyId, int status);
    
    public UserToken getUserTokenById (String id);
    
    public void deleteUserToken (UserToken userToken);
}
