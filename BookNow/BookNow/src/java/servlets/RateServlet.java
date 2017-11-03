package servlets;

import controllers.CreateRateController;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import validation.Validation;


@WebServlet(name = "RateServlet", urlPatterns = {"/rating" , "/rating/rate_room" , "/rating/rate_host"  , "/rating/rate_host/rate_ok" , "/rating/rate_room/rate_ok"})
public class RateServlet extends HttpServlet {

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

        if (url.equalsIgnoreCase("/rating")) {
            page = "/WEB-INF/ratings/rate.jsp";
        }
        
        if (url.equalsIgnoreCase("/rating/rate_room")) {
            forwardParameter(request, "id");
            page = "/WEB-INF/ratings/rate_room.jsp";
        }
        
        if (url.equalsIgnoreCase("/rating/rate_host")) {
            page = "/WEB-INF/ratings/rate_host.jsp";
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

        
        if (url.equalsIgnoreCase("/rating")) {
            page = "/WEB-INF/ratings/rate.jsp";
        }
        
        if (url.equalsIgnoreCase("/rating/rate_room")) {
            if (rate_create(request, response) == true) {
                page = "/WEB-INF/ratings/rate_ok.jsp";
            } else {
                forwardParameter(request, "id");
                forwardParameter(request, "value");
                 page = "/WEB-INF/ratings/rate_room.jsp";
            }
        }
        
        if (url.equalsIgnoreCase("/rating/rate_host")) {
            page = "/WEB-INF/ratings/rate_host.jsp";
        }
        
   
        RequestDispatcher disp = getServletContext().getRequestDispatcher(page);
        disp.forward(request, response);
    }

  
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    private boolean rate_create(HttpServletRequest request, HttpServletResponse response){
        boolean error = Validation.isEmpty(request, "value");
        
        error = false;
        if (error) {
            return false;
        }
        
        try {
            HttpSession session = request.getSession();
            Long rater_id = (Long) session.getAttribute("my_id");

            if (rater_id != null) {
                CreateRateController.create_rate(request, rater_id);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }

    }
    
}

    