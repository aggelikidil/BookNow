package controllers;

import dao.UserDao;
import dao.UserDaoImpl;
import entities.UserPojo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import validation.Validation;


public class ProfileController {

    public static boolean load(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();

            String username = (String) session.getAttribute("my_username");

            UserDao dao = new UserDaoImpl(true);

            UserPojo existingUser = dao.find(username);
            if (existingUser == null) {
                Validation.assignError(request, "USERNAME", "account does not exist");
                return false;
            } else {
                request.setAttribute("USER", existingUser);

                return true;
            }
        } catch (Exception e) {
            validation.Validation.assignError(request, "USERNAME", e.getMessage());
            return false;
        }
    }
    
    public static boolean loadOther(HttpServletRequest request) {
        try {
            Long id = Long.parseLong(request.getParameter("id"));

            UserDao dao = new UserDaoImpl(true);

            UserPojo existingUser = dao.find(id);
            if (existingUser == null) {
                Validation.assignError(request, "ID", "account does not exist");
                return false;
            } else {
                request.setAttribute("USER", existingUser);

                return true;
            }
        } catch (Exception e) {
            validation.Validation.assignError(request, "USERNAME", e.getMessage());
            return false;
        }
    }

    public static boolean save(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();

            String username = (String) session.getAttribute("my_username");
            String password = request.getParameter("PASSWORD");
            String firstname = request.getParameter("FIRSTNAME");
            String lastname = request.getParameter("LASTNAME");
            String email = request.getParameter("EMAIL");
            String phonenumber = request.getParameter("PHONENUMBER");
            String photo_url = request.getParameter("PHOTO_URL");
            String city = request.getParameter("CITY");
            String country = request.getParameter("COUNTRY");
            String location = request.getParameter("LOCATION");

            UserPojo newUser = new UserPojo();

            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setFirstname(firstname);
            newUser.setLastname(lastname);
            newUser.setEmail(email);
            newUser.setPhonenumber(phonenumber);
            newUser.setPhoto_url(photo_url);
            newUser.setCity(city);
            newUser.setCountry(country);
            newUser.setLocation(location);

            UserDao dao = new UserDaoImpl(true);
            dao.update(newUser);
            return true;
        } catch (Exception e) {
            validation.Validation.assignError(request, "USERNAME", e.getMessage());
            return false;
        }
    }
}
