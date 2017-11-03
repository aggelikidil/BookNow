package controllers;

import dao.OfferDao;
import dao.OfferDaoImpl;
import dao.RecommendationDao;
import dao.RecommendationDaoImpl;
import entities.OfferPojo;

import dao.RoomDao;
import dao.RoomDaoImpl;
import entities.RoomPojo;

import dao.RoomDetailsDao;
import dao.RoomDetailsDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import entities.RoomDetailsPojo;
import entities.UserPojo;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class RoomController {

    public static boolean loadRents(HttpServletRequest request) { // load details of a room
        try {
            HttpSession session = request.getSession();

            Long my_id = (Long) session.getAttribute("my_id");

            RoomDao dao = new RoomDaoImpl(true);
            List<RoomPojo> rooms = dao.listRentedRooms(my_id);
            request.setAttribute("rooms", rooms);

            return true;
        } catch (Exception e) {
            validation.Validation.assignError(request, "ID", e.getMessage());
            return false;
        }
    }
    public static boolean load(HttpServletRequest request, boolean active) { // load details of a room
        try {
            HttpSession session = request.getSession();

            Long my_id = (Long) session.getAttribute("my_id");

            RoomDao dao = new RoomDaoImpl(true);
            List<RoomPojo> rooms = dao.list(my_id, active);
            request.setAttribute("rooms", rooms);

            return true;
        } catch (Exception e) {
            validation.Validation.assignError(request, "ID", e.getMessage());
            return false;
        }
    }

    public static boolean load(HttpServletRequest request) { // load details of a room
        try {
            String string_id = (String) request.getParameter("id");
            Long id = Long.parseLong(string_id);

            RoomDao dao = new RoomDaoImpl(true);
            RoomDetailsDao rddao = new RoomDetailsDaoImpl(true);
            OfferDao ofdao = new OfferDaoImpl(true);
            UserDao udao = new UserDaoImpl(true);

            // load room
            RoomPojo existingRoom = dao.find(id);
            request.setAttribute("ROOM", existingRoom);
            
            // load room details
            Long room_id = existingRoom.getId();
            RoomDetailsPojo existingDetails = rddao.find(room_id);
            request.setAttribute("DETAILS", existingDetails);
            
            // offer
            Long room_details_id = existingRoom.getRoom_details();
            OfferPojo existingOffer = ofdao.find(room_id);
            request.setAttribute("OFFER", existingOffer);
            
            // load owner
            UserPojo owner = udao.find(existingRoom.getOwner_id());
            request.setAttribute("OWNER", owner);
            
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean save_room(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();

            String string_id = (String) session.getAttribute("room_id");
            Long id = Long.parseLong(string_id);
            
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
            
            Integer max_cost = Integer.parseInt(max_cost_str);
            Integer min_cost = Integer.parseInt(min_cost_str);
            Integer area = Integer.parseInt(area_str);
            Integer bathrooms = Integer.parseInt(bathrooms_str);
            Integer bedrooms = Integer.parseInt(bedrooms_str);
            Integer max_people = Integer.parseInt(max_people_str);
            Integer extra_cost_per_person = Integer.parseInt(extra_cost_per_person_str);
            
            RoomPojo newRoom = new RoomPojo();

            newRoom.setRoom_type(room_type);
            newRoom.setMax_cost(max_cost);
            newRoom.setAddress(address);
            newRoom.setArea(area);
            newRoom.setLatitude(latitude);
            newRoom.setLongitude(longitude);
            newRoom.setMax_people(max_people);
            newRoom.setMin_cost(min_cost);
            newRoom.setExtra_cost_per_person(extra_cost_per_person);
            newRoom.setThumbnail_url(thumbnail_url);
            newRoom.setDescription(description);
            newRoom.setBathrooms(bathrooms);
            newRoom.setBedrooms(bedrooms);
            
            if (living_room != null) {
                newRoom.setLiving_room(true);
            } else {
                newRoom.setLiving_room(false);
            }
            
            if (kitchen != null) {
                newRoom.setKitchen(true);
            } else {
                newRoom.setKitchen(false);
            }

            RoomDao dao = new RoomDaoImpl(true);
            dao.update_room(newRoom);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public static boolean save_details(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();

            String string_id = (String) session.getAttribute("room_id");
            Long id = Long.parseLong(string_id);
            
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
            
            Integer beds = Integer.parseInt(beds_str);
            Integer minimum_dates = Integer.parseInt(minimum_dates_str);
            
            
            RoomDetailsPojo newRoomDetails = new RoomDetailsPojo();

            newRoomDetails.setBeds(beds);
            newRoomDetails.setMinimum_dates(minimum_dates);
            newRoomDetails.setNeighbourhood(neighbourhood);
            newRoomDetails.setPublic_transport(public_transport);
            newRoomDetails.setHost_review(host_review);
            
            if (smoking != null) {
                newRoomDetails.setSmoking(true);
            } else {
                newRoomDetails.setSmoking(false);
            }
            
            if (pets != null) {
                newRoomDetails.setPets(true);
            } else {
                newRoomDetails.setPets(false);
            }
            
            if (event != null) {
                newRoomDetails.setEvent(true);
            } else {
                newRoomDetails.setEvent(false);
            }
            
            if (tv != null) {
                newRoomDetails.setTv(true);
            } else {
                newRoomDetails.setTv(false);
            }
            
            if (wifi != null) {
                newRoomDetails.setWifi(true);
            } else {
                newRoomDetails.setWifi(false);
            }
            
            if (heat != null) {
                newRoomDetails.setHeat(true);
            } else {
                newRoomDetails.setHeat(false);
            }
            
            if (aircondition != null) {
                newRoomDetails.setAircondition(true);
            } else {
                newRoomDetails.setAircondition(false);
            }
            
            if (parking != null) {
                newRoomDetails.setParking(true);
            } else {
                newRoomDetails.setParking(false);
            }
            
            if (elevator != null) {
                newRoomDetails.setElevator(true);
            } else {
                newRoomDetails.setElevator(false);
            }
            
            if (confirmation != null) {
                newRoomDetails.setConfirmation(true);
            } else {
                newRoomDetails.setConfirmation(false);
            }

            RoomDetailsDao dao = new RoomDetailsDaoImpl(true);
            dao.update_details(newRoomDetails);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean save_offer(HttpServletRequest request) {
        try {
//            HttpSession session = request.getSession();

            String room_id_str = request.getParameter("ROOM_ID");
            String date_from = request.getParameter("DATE_FROM");
            String date_to = request.getParameter("DATE_TO");
            String cost_per_day_str = request.getParameter("COST_PER_DAY");
            
            
            Long room_id = Long.parseLong(room_id_str);
            Integer cost_per_day = Integer.parseInt(cost_per_day_str);
            
            OfferPojo newOffer = new OfferPojo();

            newOffer.setRoom_id(room_id);
            newOffer.setDate_from(date_from);
            newOffer.setDate_to(date_to);
            newOffer.setCost_per_day(cost_per_day);
            
            
            OfferDao dao = new OfferDaoImpl(true);
            dao.update_offer(newOffer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public static boolean loadRecommended(HttpServletRequest request) { // load details of a room
        try {
            HttpSession session = request.getSession();

            Long my_id = (Long) session.getAttribute("my_id");

            RecommendationDao dao = new RecommendationDaoImpl(true);
            List<RoomPojo> rooms = dao.list(my_id);
            request.setAttribute("rooms", rooms);

            return true;
        } catch (Exception e) {
            validation.Validation.assignError(request, "ID", e.getMessage());
            return false;
        }
    }
}
