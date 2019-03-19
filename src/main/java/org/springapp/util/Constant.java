package org.springapp.util;

public class Constant {

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

        USER_EMAIL_ALREADY_EXIST("email", "There is already a user registered with the email provided"),
        EMAIL_ADDRESS("email", "E-mail address you entered is incorrect or invalid"),
        PASSWORD_NOT_MATCH("password", "Password does not match...");

        private final String name;
        private final String desc;

        private ParamError(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

        private ParamError(String name, String type, String desc) {
            this.name = name;
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
