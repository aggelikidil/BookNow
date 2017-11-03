
package controllers;

import dao.RoomDao;
import dao.RoomDaoImpl;
import entities.RoomPojo;

import dao.RoomDetailsDao;
import dao.RoomDetailsDaoImpl;
import entities.RoomDetailsPojo;

import dao.OfferDao;
import dao.OfferDaoImpl;
import dao.UserHasOfferDao;
import dao.UserHasOfferDaoImpl;
import entities.OfferPojo;
import entities.UserHasOfferPojo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class CreateRoomController {

    public static Long create_room(HttpServletRequest request, Long room_details_id) {
        try {
            HttpSession session = request.getSession();

            String room_type = request.getParameter("ROOM_TYPE");
            String max_cost_str = request.getParameter("MAX_COST");
            String address = request.getParameter("ADDRESS");
            String area_str = request.getParameter("AREA");
            String latitude = request.getParameter("LATITUDE");
            String longitude = request.getParameter("LONGITUDE");
            String max_people_str = request.getParameter("MAX_PEOPLE");
            String min_cost_str = request.getParameter("MIN_COST");
            String extra_cost_per_person_str = request.getParameter("EXTRA_COST_PER_PERSON");
            String thumbnail_url = request.getParameter("THUMBNAIL_URL");
            String description = request.getParameter("DESCRIPTION");
            String bathrooms_str = request.getParameter("BATHROOMS");
            String bedrooms_str = request.getParameter("BEDROOMS");
            String living_room = request.getParameter("LIVING_ROOM");
            String kitchen = request.getParameter("KITCHEN");

            Long owner_id = (Long) session.getAttribute("my_id");
            Integer max_cost = Integer.parseInt(max_cost_str);
            Integer min_cost = Integer.parseInt(min_cost_str);
            Integer area = Integer.parseInt(area_str);
            Integer bathrooms = Integer.parseInt(bathrooms_str);
            Integer bedrooms = Integer.parseInt(bedrooms_str);
            Integer max_people = Integer.parseInt(max_people_str);
            Integer extra_cost_per_person = Integer.parseInt(extra_cost_per_person_str);

            RoomPojo room = new RoomPojo();

            room.setOwner_id(owner_id);
            room.setRoom_details(room_details_id);
            room.setRoom_type(room_type);
            room.setMax_cost(max_cost);
            room.setAddress(address);
            room.setArea(area);
            room.setLatitude(latitude);
            room.setLongitude(longitude);
            room.setMax_people(max_people);
            room.setMin_cost(min_cost);
            room.setExtra_cost_per_person(extra_cost_per_person);
            room.setThumbnail_url(thumbnail_url);
            room.setDescription(description);
            room.setBathrooms(bathrooms);
            room.setBedrooms(bedrooms);

            if (living_room != null) {
                room.setLiving_room(true);
            } else {
                room.setLiving_room(false);
            }

            if (kitchen != null) {
                room.setKitchen(true);
            } else {
                room.setKitchen(false);
            }

            RoomDao dao = new RoomDaoImpl(true);

            Long id = dao.insert(room);
            return id;

        } catch (Exception e) {
            return null;
        }

    }

    public static Long create_details(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();

            String beds_str = request.getParameter("BEDS");
            String smoking = request.getParameter("SMOKING");
            String pets = request.getParameter("PETS");
            String event = request.getParameter("EVENT");
            String tv = request.getParameter("TV");
            String wifi = request.getParameter("WIFI");
            String heat = request.getParameter("HEAT");
            String aircondition = request.getParameter("AIRCONDITION");
            String parking = request.getParameter("PARKING");
            String elevator = request.getParameter("ELEVATOR");

            String minimum_dates_str = request.getParameter("MINIMUM_DATES");
            String neighbourhood = request.getParameter("NEIGHBOURHOOD");
            String public_transport = request.getParameter("PUBLIC_TRANSPORT");
            String host_review = request.getParameter("HOST_REVIEW");
            String confirmation = request.getParameter("CONFIRMATION");

            Integer minimum_dates = Integer.parseInt(minimum_dates_str);
            Integer beds = Integer.parseInt(beds_str);

            RoomDetailsPojo roomDetails = new RoomDetailsPojo();

            roomDetails.setBeds(beds);
            roomDetails.setMinimum_dates(minimum_dates);
            roomDetails.setNeighbourhood(neighbourhood);
            roomDetails.setPublic_transport(public_transport);
            roomDetails.setHost_review(host_review);

            if (smoking != null) {
                roomDetails.setSmoking(true);
            } else {
                roomDetails.setSmoking(false);
            }

            if (pets != null) {
                roomDetails.setPets(true);
            } else {
                roomDetails.setPets(false);
            }

            if (event != null) {
                roomDetails.setEvent(true);
            } else {
                roomDetails.setEvent(false);
            }

            if (tv != null) {
                roomDetails.setTv(true);
            } else {
                roomDetails.setTv(false);
            }

            if (wifi != null) {
                roomDetails.setWifi(true);
            } else {
                roomDetails.setWifi(false);
            }

            if (heat != null) {
                roomDetails.setHeat(true);
            } else {
                roomDetails.setHeat(false);
            }

            if (aircondition != null) {
                roomDetails.setAircondition(true);
            } else {
                roomDetails.setAircondition(false);
            }

            if (parking != null) {
                roomDetails.setParking(true);
            } else {
                roomDetails.setParking(false);
            }

            if (elevator != null) {
                roomDetails.setElevator(true);
            } else {
                roomDetails.setElevator(false);
            }

            if (confirmation != null) {
                roomDetails.setConfirmation(true);
            } else {
                roomDetails.setConfirmation(false);
            }

            RoomDetailsDao dao = new RoomDetailsDaoImpl(true);

            Long id = dao.insert(roomDetails);

            return id;
        } catch (Exception e) {
            return null;
        }

    }

    public static Boolean create_offer(HttpServletRequest request, Long room_id) {
        try {
            String date_from = request.getParameter("from");    //lathos onomata
            String date_to = request.getParameter("to");        //lathos onomata
            String cost_per_day_str = request.getParameter("COST_PER_DAY");

            Integer cost_per_day = Integer.parseInt(cost_per_day_str);

            OfferPojo offer = new OfferPojo();

            offer.setRoom_id(room_id);
            offer.setDate_from(date_from);
            offer.setDate_to(date_to);
            offer.setCost_per_day(cost_per_day);

            OfferDao dao = new OfferDaoImpl(true);
            
            //den gemizame ton pinaka UserHasOffer 
            Long offer_id = dao.insert(offer);
            
            HttpSession session = request.getSession();
            
            Long user_id = (Long) session.getAttribute("my_id");
            
            UserHasOfferPojo useroffer = new UserHasOfferPojo();
            useroffer.setOffer_id(offer_id);
            useroffer.setUser_id(user_id);
            
            UserHasOfferDao dao2 = new UserHasOfferDaoImpl(true);
            dao2.insert(useroffer);
            
            return true;

        } catch (Exception e) {
            return false;
        }

    }

}
