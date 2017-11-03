package servlets;

import controllers.RoomController;
import controllers.CreateRoomController;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import validation.Validation;


@WebServlet(name = "HostServlet", urlPatterns = {"/host/rooms", "/host/rooms/create", "/host/rooms/active", "/host/rooms/inactive", "/host/rooms/properties", "/host/rooms/inactive/delete"})
public class HostServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getServletPath();
        String page = "/WEB-INF/js_fragments/index.jsp";

        if (url.equalsIgnoreCase("/host/rooms")) {
            page = "/WEB-INF/host/rooms.jsp";
        }

        if (url.equalsIgnoreCase("/host/rooms/create")) {
            page = "/WEB-INF/host/create_room.jsp";
        }

        if (url.equalsIgnoreCase("/host/rooms/active")) {
            RoomController.load(request, true);
            page = "/WEB-INF/host/active_rooms.jsp";
        }

        if (url.equalsIgnoreCase("/host/rooms/inactive")) {
            RoomController.load(request, false);
            page = "/WEB-INF/host/inactive_rooms.jsp";
        }

        if (url.equalsIgnoreCase("/host/rooms/properties")) {
            RoomController.load(request);
            page = "/WEB-INF/host/room_properties.jsp";
        }

        

        if (url.equalsIgnoreCase("/host/rooms/inactive/delete")) {
            page = "/WEB-INF/host/delete_ok.jsp";
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher(page);
        disp.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        String page = "/WEB-INF/index.jsp";

        if (url.equalsIgnoreCase("/host/rooms")) {
            page = "/WEB-INF/host/rooms.jsp";
        }

        if (url.equalsIgnoreCase("/host/rooms/create")) {
            if (room_create(request, response) == true) {
                page = "/WEB-INF/host/room_ok.jsp";
            } else {
                page = "/WEB-INF/host/create_room.jsp";
            }
        }


        if (url.equalsIgnoreCase("/host/rooms/properties")) {
            if (update_room(request, response) == true) {
                page = "/WEB-INF/host/room_update.jsp";
            } else {
                page = "/WEB-INF/host/room_properties.jsp";
            }

        }


        if (url.equalsIgnoreCase("/host/rooms/inactive/delete")) {
            page = "/WEB-INF/host/delete_ok.jsp";
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher(page);
        disp.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean update_room(HttpServletRequest request, HttpServletResponse response) {
        RoomController.save_room(request);
        return true;
    }

    private boolean update_offer(HttpServletRequest request, HttpServletResponse response) {
        RoomController.save_offer(request);
        return true;
    }

    private boolean room_create(HttpServletRequest request, HttpServletResponse response) {
        
        /// validate room
        boolean error = Validation.isEmpty(request, "ROOM_TYPE");
        
        error = Validation.isEmpty(request, "MAX_COST") || error;
        error = Validation.isEmpty(request, "ADDRESS") || error;
        error = Validation.isEmpty(request, "AREA") || error;
        error = Validation.isEmpty(request, "LATITUDE") || error;
        error = Validation.isEmpty(request, "LONGITUDE") || error;
        error = Validation.isEmpty(request, "MAX_PEOPLE") || error;

        error = Validation.isEmpty(request, "MIN_COST") || error;
        error = Validation.isEmpty(request, "EXTRA_COST_PER_PERSON") || error;
        error = Validation.isEmpty(request, "THUMBNAIL_URL") || error;
        error = Validation.isEmpty(request, "DESCRIPTION") || error;
        error = Validation.isEmpty(request, "BATHROOMS") || error;
        error = Validation.isEmpty(request, "BEDROOMS") || error;

        error = Validation.isEmpty(request, "LIVING_ROOM") || error;
        error = Validation.isEmpty(request, "KITCHEN") || error;

        error = false;
        if (error) {
            return false;
        }

        // validate details
        error = Validation.isEmpty(request, "BEDS");

        error = Validation.isEmpty(request, "SMOKING") || error;
        error = Validation.isEmpty(request, "PETS") || error;
        error = Validation.isEmpty(request, "EVENT") || error;
        error = Validation.isEmpty(request, "TV") || error;
        error = Validation.isEmpty(request, "WIFI") || error;
        error = Validation.isEmpty(request, "HEAT") || error;
        error = Validation.isEmpty(request, "AIRCONDITION") || error;
        error = Validation.isEmpty(request, "PARKING") || error;
        error = Validation.isEmpty(request, "ELEVATOR") || error;

        error = Validation.isEmpty(request, "MINIMUM_DATES") || error;
        error = Validation.isEmpty(request, "NEIGHBOURHOOD") || error;
        error = Validation.isEmpty(request, "PUBLIC_TRANSPORT") || error;
        error = Validation.isEmpty(request, "HOST_REVIEW") || error;
        error = Validation.isEmpty(request, "CONFIRMATION") || error;

        error = false;
        if (error) {
            return false;
        }

        // validate offers
        error = Validation.isEmpty(request, "DATE_TO") || error;
        error = Validation.isEmpty(request, "DATE_FROM") || error;
        error = Validation.isEmpty(request, "COST_PER_DAY") || error;

        error = false;
        if (error) {
            return false;
        }

        
        try {
            Long room_details_id = CreateRoomController.create_details(request);
            Long room_id = CreateRoomController.create_room(request, room_details_id);
            CreateRoomController.create_offer(request, room_id);

            if (room_details_id != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    
    

    

    private boolean update_details(HttpServletRequest request, HttpServletResponse response) {
        RoomController.save_details(request);
        return true;
    }
}
