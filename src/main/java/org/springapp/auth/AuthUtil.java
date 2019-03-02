
package org.springapp.auth;

import java.util.Random;
import java.util.UUID;

public class AuthUtil {

    public static String generateRandomNumber(int charLength) {
        return String.valueOf(charLength < 1 ? 0 : new Random().nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1) + (int) Math.pow(10, charLength - 1));
    }

    public static String generateInvitationCode() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
