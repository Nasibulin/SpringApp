package org.springapp.api.user;

import org.springapp.api.APIName;
import org.springapp.api.controller.AbstractBaseController;
import org.springapp.api.request.model.AuthRequestModel;
import org.springapp.api.request.model.UserRequestModel;
import org.springapp.api.response.model.APIResponse;
import org.springapp.api.response.util.APIStatus;
import org.springapp.entity.User;
import org.springapp.entity.UserAddress;
import org.springapp.entity.UserToken;
import org.springapp.service.auth.AuthService;
import org.springapp.service.users.UserAddressService;
import org.springapp.service.users.UserServiceIf;
import org.springapp.service.users.UserTokenService;
import org.springapp.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = APIName.USERS, method = RequestMethod.POST)
public class UserAPI extends AbstractBaseController {

    @Autowired
    private UserServiceIf userServiceIf;
    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private AuthService authService;

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value = APIName.USERS_LOGIN, method = RequestMethod.POST, produces = APIName.CHARSET)
    public ResponseEntity<APIResponse> login(
            @RequestBody AuthRequestModel authRequestModel
    ) {

        if ("".equals(authRequestModel.getUsername()) || "".equals(authRequestModel.getPassword())) {
            // invalid paramaters
            throw new RuntimeException("INVALID_PARAMETER");
        } else {
            User userLogin = userServiceIf.findByEmail(authRequestModel.getUsername());

            if (userLogin != null) {

                if (bcryptPasswordEncoder().matches(authRequestModel.getPassword(),userLogin.getPassword())) {
//                if (passwordHash.equals(userLogin.getPassword())) {
                    UserToken userToken = authService.createUserToken(userLogin, authRequestModel.isKeepMeLogin());
                    // Create Auth User -> Set to filter config
                    // Perform the security
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            userLogin.getEmail(),
                            userLogin.getPassword()
                    );
                    //final Authentication authentication = authenticationManager.authenticate();
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    return responseUtil.successResponse(userToken.getToken());
                } else {
                    // wrong password
                    throw new RuntimeException("ERR_USER_NOT_VALID");
                }

            } else {
                // can't find user by email address in database
                throw new RuntimeException("ERR_USER_NOT_EXIST");
            }
        }
    }

    @RequestMapping(value = APIName.USERS_LOGOUT, method = RequestMethod.POST, produces = APIName.CHARSET)
    public ResponseEntity<APIResponse> logout(
            HttpServletRequest request) {

        String authToken = request.getHeader(Constant.HEADER_TOKEN);
        UserToken userToken = userTokenService.getTokenById(authToken);
        if (userToken != null) {
            userTokenService.invalidateToken(userToken);
            return responseUtil.successResponse(APIStatus.OK);
        } else {
            throw new RuntimeException("ERR_UNAUTHORIZED");
        }

    }

    @RequestMapping(path = APIName.USER_REGISTER, method = RequestMethod.POST, produces = APIName.CHARSET)
    public ResponseEntity<APIResponse> register(
            @RequestBody UserRequestModel user
    ) {
        // check user already exists
        User existedUser = userServiceIf.findByEmail(user.getEmail());
        if (existedUser == null) {
            // email is valid to create user
            String email = user.getEmail();

            if (email != null && !email.equals("")) {

                Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email);

                if (!matcher.matches()) {
                    throw new RuntimeException("ERR_INVALID_DATA");
                }

                User userSignUp = new User();
                userSignUp.setEmail(email);
                userSignUp.setFirstName(user.getFirstName());
                userSignUp.setLastName(user.getLastName());
//                userSignUp.setRoleId(Constant.USER_ROLE.NORMAL_USER.getRoleId());
                //                    String generatedString = RandomStringUtils.randomAlphabetic(6);
                String generatedString = "123456";
                String password = bcryptPasswordEncoder().encode(generatedString);
                userSignUp.setPassword(password);

//                userSignUp.setRoleId(Constant.USER_ROLE.NORMAL_USER.getRoleId());
                userSignUp.setStatus(Constant.USER_STATUS.ACTIVE.getStatus());

                userServiceIf.saveUser(userSignUp);

                UserAddress userAddress = new UserAddress();
                userAddress.setUser(userSignUp);
                userAddress.setAddress(user.getAddress());
                userAddress.setCity(user.getCity());
                userAddress.setCountry(user.getCountry());
                userAddress.setFax(user.getFax());
                userAddress.setPhone(user.getPhone());
//                userAddress.setStatus(Constant.STATUS.ACTIVE_STATUS.getValue());
                userAddressService.save(userAddress);
                // do send mail notify...
                return responseUtil.successResponse(userSignUp);
            } else {
                throw new RuntimeException("ERR_INVALID_DATA");
            }

        } else {
            // notify user already exists
            throw new RuntimeException("USER_ALREADY_EXIST");
        }

    }
}
