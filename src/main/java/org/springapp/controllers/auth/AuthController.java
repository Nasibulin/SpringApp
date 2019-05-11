package org.springapp.controllers.auth;

import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;

import org.springapp.api.APIName;
import org.springapp.api.request.model.AuthRequestModel;
import org.springapp.api.response.model.APIResponse;
import org.springapp.api.response.model.UserDetailResponseModel;
import org.springapp.api.response.util.APIStatus;
import org.springapp.auth.AuthUserFactory;
import org.springapp.controllers.AbstractBaseController;
import org.springapp.entity.User;
import org.springapp.entity.UserAddress;
import org.springapp.entity.UserToken;
import org.springapp.exception.ApplicationException;
import org.springapp.service.auth.AuthService;
import org.springapp.service.users.UserAddressService;
import org.springapp.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIName.AUTH_API)
public class AuthController extends AbstractBaseController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthUserFactory authUserFactory;

    @Autowired
    UserAddressService userAddressService;


    @RequestMapping(path = APIName.SESSION_DATA, method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getSessionData(
            HttpServletRequest request
    ) {
        String userId = getAuthUserFromSession(request).getUsername();
        //System.err.println(userId);
        if (userId != null && !"".equals(userId)) {
            User user = authService.getUserByEmail(userId);
            UserAddress userAddress = userAddressService.getAddressByUser(user);
            if (user != null) {
                UserDetailResponseModel userResponse = new UserDetailResponseModel();
                userResponse.setUserId(user.getUserId());
                userResponse.setEmail(user.getEmail());
                userResponse.setFirstName(user.getFirstName());
                userResponse.setLastName(user.getLastName());
                userResponse.setRoleId(user.getRole().getRoleId());
                if (userAddress != null) {
                    userResponse.setAddress(userAddress.getAddress());
                    userResponse.setCity(userAddress.getCity());
                    userResponse.setCountry(userAddress.getCountry());
                    userResponse.setPhone(userAddress.getPhone());
                }
                return responseUtil.successResponse(userResponse);
            } else {
                throw new ApplicationException(APIStatus.ERR_USER_NOT_EXIST);
            }
        } else {
            throw new ApplicationException(APIStatus.INVALID_PARAMETER);
        }
    }

    @RequestMapping(path = APIName.USERS_LOGOUT, method = RequestMethod.POST)
    public ResponseEntity<APIResponse> logout(
            @RequestHeader(value = Constant.HEADER_TOKEN) String tokenId
    ) {
        if (tokenId != null && !"".equals(tokenId)) {
            UserToken userToken = authService.getUserTokenById(tokenId);
            if (userToken != null) {
                authService.deleteUserToken(userToken);
                return responseUtil.successResponse("OK");
            } else {
                throw new ApplicationException(APIStatus.ERR_SESSION_NOT_FOUND);
            }
        } else {
            throw new ApplicationException(APIStatus.INVALID_PARAMETER);
        }
    }
}
