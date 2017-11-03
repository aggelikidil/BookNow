package validation;

import entities.UserPojo;


public class Security {
    public static boolean credentialsMatch(String form_username, String form_password, UserPojo dbUser) {
        try {
            if (form_username.equals(dbUser.getUsername()) && 
                    form_password.equals(dbUser.getPassword())) {
                return true;
            } else {
                return false;
            } 
        } catch (Exception x) {
            return false;
        }
    }
}
