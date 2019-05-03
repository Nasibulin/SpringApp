package org.springapp.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import org.springapp.auth.AuthUser;
import org.springapp.auth.service.CustomUserAuthService;
import org.springapp.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBaseAPI {
    
    @Autowired
    private CustomUserAuthService userDetailsService;

    //
    // Build setting for Gson class accept NULL value
    //
    Gson gson = new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(Constant.API_FORMAT_DATE).create();

    // Mapper object is used to convert object and etc...
    public final static ObjectMapper mapper = new ObjectMapper();

    

    static {
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
                .setSerializationInclusion(JsonInclude.Include.ALWAYS)
                .setDateFormat(new SimpleDateFormat(Constant.API_FORMAT_DATE));
    }

    //
    // Write object as string using mapper
    //
    protected String writeObjectToJson(Object obj) {
        try {

            return mapper.writeValueAsString(obj);

        } catch (JsonProcessingException ex) {
            // Throw our exception
            throw new RuntimeException(ex.getCause());
        }
    }

    protected String writeObjectToJsonRemoveNullProperty(Object obj) throws RuntimeException
    {    
         try {
             // Set setting remove NULL property
             mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
             // map json
             String result=mapper.writeValueAsString(obj);
             // Reset default setting
             mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
             return  result;

        } catch (JsonProcessingException ex) {
            // Throw our exception
            throw new RuntimeException(ex.getCause());
        }        
    }
    
    // get current user authenticated
//    public User getcurrentUser() {
////        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
////        String authToken = request.getParameter("auth_token");
////        User currentUser = null;//userSessionDao.getUserBySessionID(authToken);
////        if (currentUser == null) {
////            throw new ApplicationException(APIStatus.INVALID_ACCESS_TOKEN);
////        }
////        return currentUser;
//      return null;
//    }
    
    public AuthUser getAuthUserFromSession(HttpServletRequest request) {
        String authToken = request.getHeader(Constant.HEADER_TOKEN);
        // try to load sessio
        AuthUser user = userDetailsService.loadUserByAccessToken(authToken);
        return user;
    }
}
