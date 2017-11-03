
package controllers;

import dao.RentDao;
import dao.RentDaoImpl;
import dao.OfferDao;
import dao.OfferDaoImpl;

import entities.RentPojo;
import entities.OfferPojo;


import dao.UserHasRentDao;
import dao.UserHasRentDaoImpl;
import entities.UserHasRentPojo;

import javax.servlet.http.HttpServletRequest;

import validation.Validation;


public class CreateRentController {

    public static Long create_rent(HttpServletRequest request, Long renter_id) {
        try {
            String date_from = request.getParameter("from");
            String date_to = request.getParameter("to");
            String id = request.getParameter("id");
            Long room_id = Long.parseLong(id);

            RentPojo rent = new RentPojo();

            rent.setRoom_id(room_id);
            rent.setDate_from(date_from);
            rent.setDate_to(date_to);

            RentDao dao = new RentDaoImpl(true);
            Long rent_id = dao.insert(rent);

            UserHasRentPojo userrent = new UserHasRentPojo();
            userrent.setRent_id(rent_id);
            userrent.setUser_id(renter_id);

            UserHasRentDao dao2 = new UserHasRentDaoImpl(true);
            dao2.insert(userrent);

            return rent_id;

        } catch (Exception e) {
            return null;
        }

    }
    
    
    public static boolean loadOffer(HttpServletRequest request) {
        try {
            Long id = Long.parseLong(request.getParameter("id"));

            OfferDao dao = new OfferDaoImpl(true);

            OfferPojo existingOffer = dao.find(id);
            if (existingOffer == null) {
                Validation.assignError(request, "ID", "offer does not exist");
                return false;
            } else {
                request.setAttribute("OFFER", existingOffer);

                return true;
            }
        } catch (Exception e) {
            validation.Validation.assignError(request, "ID", e.getMessage());
            return false;
        }
    }

}
