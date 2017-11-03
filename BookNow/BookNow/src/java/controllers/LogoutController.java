package controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LogoutController {
    public static boolean logout(HttpServletRequest request) {
        boolean logout_complete = true;
        try {
            HttpSession session = request.getSession();            
            session.invalidate();

        } catch (Exception ex) {
            
        }
        return logout_complete;
    }
}
