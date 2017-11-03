
package controllers;

import dao.UserDao;
import dao.UserDaoImpl;
import entities.UserPojo;
import javax.servlet.http.HttpServletRequest;


public class RegistrationController {

    public static boolean register(HttpServletRequest request) {
        try {
            String username = request.getParameter("USERNAME");
            String password = request.getParameter("PASSWORD");
            String firstname = request.getParameter("FIRSTNAME");
            String lastname = request.getParameter("LASTNAME");
            String email = request.getParameter("EMAIL");
            String phonenumber = request.getParameter("PHONENUMBER");
            String photo_url = request.getParameter("PHOTO_URL");
            String city = request.getParameter("CITY");
            String country = request.getParameter("COUNTRY");
            String location = request.getParameter("LOCATION");
            String longitude = request.getParameter("LONGITUDE");
            String latitude = request.getParameter("LATITUDE");
            String role = request.getParameter("ROLES"); // eixame valei lathos metavliti sto anti gia ROLES eixame ROLE

            UserPojo user = new UserPojo();

            user.setUsername(username);
            user.setPassword(password);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setPhonenumber(phonenumber);        
            user.setPhoto_url(photo_url);
            user.setCity(city);
            user.setCountry(country);
            user.setLocation(location);
            user.setLongitude(longitude);
            user.setLatitude(latitude);
            user.setIs_admin(false);
            user.setIs_renter(true);
            user.setIs_approved(false);

            if (role != null) {
                user.setIs_host(true);
            } else {
                user.setIs_host(false);
                user.setIs_approved(true);  //paradoxi ekfonisis
            }

//            user.setLongitude("0");
//            user.setLatitude("0");

            UserDao dao = new UserDaoImpl(true);

            UserPojo existingUser = dao.find(username);
            if (existingUser != null) {
                validation.Validation.assignError(request, "USERNAME", "already exists");
                return false;
            } else {
                dao.insert(user);
                return true;
            }
        } catch (Exception e) {
            validation.Validation.assignError(request, "USERNAME", e.getMessage());
            return false;
        }
    }
    
}
