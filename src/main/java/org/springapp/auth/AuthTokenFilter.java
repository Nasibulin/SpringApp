package org.springapp.auth;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springapp.auth.service.AuthUserDetailsService;
import org.springapp.auth.service.CustomUserAuthService;
import org.springapp.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    private final Log LOGGER = LogFactory.getLog(this.getClass());

    @Autowired
    private CustomUserAuthService userAuthService;

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    /**
     * Do filter all request, if any request don't have token => though error =>
     * Will enable it later when all API apply secure
     *
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String authToken = request.getHeader(Constant.HEADER_TOKEN);
        if (authToken != null) {
            try {
                // try to load sessio
//                AuthUser user = userAuthService.loadUserByAccessToken(authToken); temporary comment
                AuthUser user = (AuthUser) authUserDetailsService.loadUserByUsername("denzel@gmail.com");

                if (user != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (RuntimeException ex) {
                // token not found or expired
                LOGGER.debug("doFilterInternal", ex);
            }
        }

        // Allo cross domain 
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        // Allow set custom header token
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, X-Access-Token");

        chain.doFilter(request, response);
    }

}
