package servlets;

import controllers.RoomController;
import controllers.CreateRentController;
import controllers.ProfileController;
import dao.OfferDao;
import dao.OfferDaoImpl;
import dao.RoomDao;
import dao.RoomDaoImpl;
import dao.RoomDetailsDao;
import dao.RoomDetailsDaoImpl;
import entities.OfferPojo;
import entities.RoomDetailsPojo;
import entities.RoomPojo;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import validation.Validation;


@WebServlet(name = "RoomServlet", urlPatterns = {"/search", "/search/results", "/search/advanced_search", "/search/results/details", "/search/results/details/host","/rentings", "/rentings/create","/rentings/my_rents","/rentings/my_rents/details"})
public class SearchServlet extends HttpServlet {

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

        if (url.equalsIgnoreCase("/search")) {
            page = "/WEB-INF/rooms/search.jsp";
        }

        if (url.equalsIgnoreCase("/search/results")) {
            RoomDao dao = new RoomDaoImpl(true);                
            List<RoomPojo> rooms = dao.list();
            request.setAttribute("rooms", rooms);
            RoomController.load(request, true);
            page = "/WEB-INF/rooms/search_results.jsp";
        }
        
        if (url.equalsIgnoreCase("/rentings")) {
            page = "/WEB-INF/rooms/rentings.jsp";
        }
        
        if (url.equalsIgnoreCase("/rentings/create")) {
            forwardParameter(request, "id");
            CreateRentController.loadOffer(request);
            page = "/WEB-INF/rooms/create_rent.jsp";
        }
        
        if (url.equalsIgnoreCase("/rentings/my_rents/details")) {
            RoomController.load(request);
            page = "/WEB-INF/rooms/rent_details.jsp";
        }
        
        if (url.equalsIgnoreCase("/rentings/my_rents")) {
            RoomDao dao = new RoomDaoImpl(true);
            List<RoomPojo> rooms = dao.list();
            request.setAttribute("rooms", rooms);
            RoomController.loadRents(request);
            page = "/WEB-INF/rooms/my_rents.jsp";
        }

        if (url.equalsIgnoreCase("/search/results/details")) {
            RoomController.load(request);
            page = "/WEB-INF/rooms/room_details.jsp";
        }

        if (url.equalsIgnoreCase("/search/advanced_search")) {
            page = "/WEB-INF/rooms/advanced_search.jsp";
        }

        if (url.equalsIgnoreCase("/search/results/details/host")) {
            ProfileController.loadOther(request);
            page = "/WEB-INF/host/profile_host.jsp";
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher(page);
        disp.forward(request, response);
    }

    
    private void forwardParameter(HttpServletRequest request, String key) {
        String value = request.getParameter(key);
        if (value != null) {
            request.setAttribute(key, value);
        }
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

        if (url.equalsIgnoreCase("/search")) {
            forwardParameter(request, "from");
            forwardParameter(request, "to");
            
            if (search(request, response) == true) {
                page = "/WEB-INF/rooms/search_results.jsp";
            } else {
                page = "/WEB-INF/rooms/search.jsp";
            }
        }

        if (url.equalsIgnoreCase("/search/advanced_search")) {
            forwardParameter(request, "from");
            forwardParameter(request, "to");
            forwardParameter(request, "maxcost");
            
            
            if (advanced_search(request, response) == true) {
                page = "/WEB-INF/rooms/search_results.jsp";
            } else {
                page = "/WEB-INF/rooms/advanced_search.jsp";
            }
        }

        if (url.equalsIgnoreCase("/search/results/details/host")) {
            page = "/WEB-INF/host/profile_host.jsp";
        }
        
        if (url.equalsIgnoreCase("/rentings")) {
            page = "/WEB-INF/rooms/rentings.jsp";
        }
        
        if (url.equalsIgnoreCase("/rentings/create")) {            
            if (rent_create(request, response) == true) {
                page = "/WEB-INF/rooms/rent_ok.jsp";
            } else {
                forwardParameter(request, "id");
                forwardParameter(request, "from");
                forwardParameter(request, "to");
                page = "/WEB-INF/rooms/create_rent.jsp";
            }
        }
        
        if (url.equalsIgnoreCase("/rentings/my_rents")) {
            page = "/WEB-INF/rooms/rent_ok.jsp";
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

    private boolean search(HttpServletRequest request, HttpServletResponse response) {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String members = request.getParameter("members");
        String keyword = request.getParameter("location");

        RoomDao dao = new RoomDaoImpl(true);
        RoomDetailsDao rddao = new RoomDetailsDaoImpl(true);
        OfferDao ofdao = new OfferDaoImpl(true);
                
        try {
            int total_members = Integer.parseInt(members);
            
            List<RoomPojo> rooms = dao.search(from, to, total_members, keyword);
            request.setAttribute("rooms", rooms);
            
            for (RoomPojo room : rooms) {
                RoomDetailsPojo details = rddao.find(room.getRoom_details());                
                room.setDetails(details);
                
                OfferPojo offerdetails = ofdao.find(room.getId());
                room.setOfferdetails(offerdetails);
            }            
            
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

   
    private boolean advanced_search(HttpServletRequest request, HttpServletResponse response) {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String members = request.getParameter("members");
        String keyword = request.getParameter("location");
        
        String tv = request.getParameter("tv");
        String wifi = request.getParameter("wifi");
        String heat = request.getParameter("heat");
        String aircondition = request.getParameter("aircondition");
        String kitchen = request.getParameter("kitchen");
        String parking = request.getParameter("parking");
        String elevator = request.getParameter("elevator");
        String roomtype= request.getParameter("roomtype");
        String maxcost = request.getParameter("maxcost");
        

        RoomDao dao = new RoomDaoImpl(true);
        RoomDetailsDao rddao = new RoomDetailsDaoImpl(true);
        OfferDao ofdao = new OfferDaoImpl(true);
                
        boolean tv_needed = (tv != null);
        boolean wifi_needed = (wifi != null);
        boolean heat_needed = (heat != null);
        boolean aircondition_needed = (aircondition != null);
        boolean kitchen_needed = (kitchen != null);
        boolean parking_needed = (parking != null);
        boolean elevator_needed = (elevator != null);
      
        
        try {
            int total_members = Integer.parseInt(members);
            int max_cost;
            
            
            try {
                max_cost = Integer.parseInt(maxcost);
            } catch (Exception ex) {
                max_cost = Integer.MAX_VALUE;
            }
            List<RoomPojo> rooms = dao.advancedsearch(from, to, total_members, keyword, roomtype, max_cost, tv_needed, wifi_needed, heat_needed, aircondition_needed, kitchen_needed, parking_needed, elevator_needed);
            request.setAttribute("rooms", rooms);
            
            for (RoomPojo room : rooms) {
                RoomDetailsPojo details = rddao.find(room.getRoom_details());                
                room.setDetails(details);
                
                OfferPojo offerdetails = ofdao.find(room.getId());
                room.setOfferdetails(offerdetails);
            }            
            
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    
    private boolean rent_create(HttpServletRequest request, HttpServletResponse response){
        boolean error = Validation.isEmpty(request, "DATE_FROM");
        
        error = Validation.isEmpty(request, "DATE_TO") || error;
        
        error = false;
        if (error) {
            return false;
        }
        
        try {
            HttpSession session = request.getSession();
            Long id = (Long) session.getAttribute("my_id");


            CreateRentController.create_rent(request, id);
            
            if (id != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }

        
    }

}
