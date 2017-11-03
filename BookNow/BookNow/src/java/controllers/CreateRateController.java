
package controllers;

import dao.RatingDao;
import dao.RatingDaoImpl;
import entities.RatingPojo;

import javax.servlet.http.HttpServletRequest;

public class CreateRateController {
    
    public static Long create_rate(HttpServletRequest request, Long rater_id) {
        try {
                        
            String id = request.getParameter("id");
            String value_str = request.getParameter("value");
           
            
            Integer value = Integer.parseInt(value_str);
            Long room_id = Long.parseLong(id);

            RatingPojo rating = new RatingPojo();

            rating.setUser_rate(rater_id);
            rating.setRoom_id(room_id);
            rating.setValue(value);
            

            RatingDao dao = new RatingDaoImpl(true);
            Long rate_id = dao.insert(rating);


            return rate_id;

        } catch (Exception e) {
            return null;
        }

    }
    
}
