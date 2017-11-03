package controllers;

import dao.UserDao;
import dao.UserDaoImpl;
import entities.UserPojo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import validation.Security;
import validation.Validation;


public class LoginController {

    public static boolean login(HttpServletRequest request) {
        try {
            String username = request.getParameter("USERNAME");
            String password = request.getParameter("PASSWORD");

            UserDao dao = new UserDaoImpl(true);

            UserPojo existingUser = dao.find(username);
            if (existingUser == null) {
                Validation.assignError(request, "USERNAME", "account does not exist");
                return false;
            } else {
                boolean login_complete = Security.credentialsMatch(username, password, existingUser);
                if (login_complete) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("my_id", existingUser.getId());
                    session.setAttribute("my_username", existingUser.getUsername());
                    session.setAttribute("my_name", existingUser.getFirstname() + " " + existingUser.getLastname());
                    
                    if (existingUser.getIs_admin()) {
                        session.setAttribute("i_am_admin", true);
                    } else {
                        session.setAttribute("i_am_admin", false);
                    }
                    if (existingUser.getIs_host()) {
                        session.setAttribute("i_am_host", true);
                    } else {
                        session.setAttribute("i_am_host", false);
                    }
                    if (existingUser.getIs_renter()) {
                        session.setAttribute("i_am_renter", true);
                    } else {
                        session.setAttribute("i_am_renter", false);
                    }
                } else {
                    Validation.assignError(request, "USERNAME", "invalid password");
                }
                return login_complete;
            }
        } catch (Exception e) {
            validation.Validation.assignError(request, "USERNAME", e.getMessage());
            return false;
        }
    }
    
}
