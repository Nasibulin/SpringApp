package org.springapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springapp.api.response.util.ResponseUtil;
import org.springapp.auth.AuthUser;
import org.springapp.auth.service.CustomUserAuthService;
import org.springapp.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBaseController {
    
    @Autowired
    private CustomUserAuthService userDetailsService;

//    @Autowired
//    AppConfig appConfig;
    
    @Autowired
    protected ResponseUtil responseUtil;
    
    public AuthUser getAuthUserFromSession(HttpServletRequest request) {
        String authToken = request.getHeader(Constant.HEADER_TOKEN);
        // try to load session
        AuthUser user = userDetailsService.loadUserByAccessToken(authToken);
        return user;
    }
}
