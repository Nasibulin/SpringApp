
package org.springapp.auth;

import org.springapp.entity.User;


public interface AuthUserFactory {
    
    AuthUser createAuthUser (User user);
}
