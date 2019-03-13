package org.springapp.util;

public class Constant {

    // API FORMAT DATE
    public static final String API_FORMAT_DATE = "yyyy/MM/dd HH:mm:ss";

    // LANG
    public static final String LANG_DEFAULT = "en"; // Lang default
    public static final String LANG_AUTO = "auto";

    public static enum STATUS {
        ACTIVE_STATUS(0, "Active"),
        DELETED_STATUS(1, "Deleted"),
        REVOKE_STATUS(2, "Revoke"),
        DISABLED_STATUS(3, "Disable"),
        DELETED_FOREVER_STATUS(4, "Deleted forever"),
        PENDING(5, "Pending"),
        TRIAL_ACCOUNT_STATUS(6, "Trial");

        private final int value;
        private final String type;

        private STATUS(int value, String type) {
            this.value = value;
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public String getType() {
            return type;
        }
    }

    public static enum USER_ROLE {
        SYS_ADMIN(1, "System Admin"),
        STORE_ADMIN(2, "Store Admin"),
        STORE_MANAGER(3, "Store Manager"),
        NORMAL_USER(4, "Normal User"),
        GUEST(5, "Guest");
        

        private final int roleId;
        private final String roleName;

        private USER_ROLE(int id, String name) {
            this.roleId = id;
            this.roleName = name;
        }

        public int getRoleId() {
            return roleId;
        }

        public String getRoleName() {
            return roleName;
        }
    }
    
    public static enum USER_STATUS {
        INACTIVE(-1),
        PENDING(0),
        ACTIVE(1);
        
        private final int status;
        
        private USER_STATUS(int status) {
            this.status = status;
        }
        
        public int getStatus() {
            return status;
        }
    }
    
    public static enum ORDER_STATUS {
        PENDING(0),
        SHIPPING(1),
        COMPLETED(2);
        
        private final int status;
        
        private ORDER_STATUS(int status) {
            this.status = status;
        }
        
        public int getStatus() {
            return status;
        }
    }

    public enum ParamError {

        MISSING_USERNAME_AND_EMAIL("accountName", "Missing both user name and email address"),
        USER_NAME("userName", "Invalid user name"),
        EMAIL_ADDRESS("email", "error.user",
                "There is already a user registered with the email provided"),
        PASSWORD("passwordHash", "Invalid password hash"),
        PHONE_NUMBER("phone", "Invalid phone number"),
        FIRST_NAME("firstName", "Invalid first name"),
        LAST_NAME("lastName", "Invalid last name"),
        APP_NAME("appName", "Invalid app name"),
        APP_DOMAIN("appDomain", "Invalid app domain"),
        SERVER_KEY("serverKey", "Invalid server key"),
        TOKEN_EXPIRE_DURATION("tokenExpireDuration", "Invalid token expiry duration"),
        REDIRECT_URL("redirectUrl", "Invalid redirect URL"),
        ROLE_NAME("roleName", "Invalid role name"),
        ROLE_DESC("roleDescription", "Invalid role description");

        private final String name;
        private final String type;
        private final String desc;

        private ParamError(String name, String desc) {
            this.name = name;
            this.desc = desc;
            this.type = null;
        }

        private ParamError(String name, String type, String desc) {
            this.name = name;
            this.type = type;
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return name;
        }

        public String getDesc() {
            return desc;
        }
    }
}
