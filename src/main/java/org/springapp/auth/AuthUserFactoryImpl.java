
package org.springapp.auth;

import org.springapp.entity.User;
import org.springapp.repository.RoleRepository;
import org.springapp.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserFactoryImpl implements AuthUserFactory{
    @Autowired
    RoleRepository roleRepository;
    
    @Override
    public AuthUser createAuthUser(User user) {
        return new AuthUser(
                    user.getUserId(),
                    user.getEmail(),
                    user.getPasswordHash(),
                    getUserRoleString(user.getRole().getRoleId()),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getStatus() == Constant.STATUS.ACTIVE_STATUS.getValue()
            );
    }
    
    private String getUserRoleString (Integer roleId){
        try {
            return roleRepository.findById(roleId).get().getName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
