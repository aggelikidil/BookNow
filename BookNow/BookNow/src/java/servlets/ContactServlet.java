package servlets;

import controllers.MessageController;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ContactServlet", urlPatterns = {"/contact", "/contact/inbox", "/contact/outbox", "/contact/create", "/contact/create_ok", "/contact/inbox/details", "/contact/outbox/details", "/contact/delete", "/contact/reply"})
public class ContactServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void forwardParameter(HttpServletRequest request, String key) {
        String value = request.getParameter(key);
        if (value != null) {
            request.setAttribute(key, value);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getServletPath();
        String page = "/WEB-INF/js_fragments/index.jsp";

        if (url.equalsIgnoreCase("/contact")) {
            page = "/WEB-INF/messages/contact.jsp";
        }

        if (url.equalsIgnoreCase("/contact/inbox")) {
            HttpSession session = request.getSession();
            Long my_id = (Long) session.getAttribute("my_id");

            MessageController.load_inbox(request, my_id);
            page = "/WEB-INF/messages/inbox.jsp";
        }

        if (url.equalsIgnoreCase("/contact/outbox")) {
            HttpSession session = request.getSession();
            Long my_id = (Long) session.getAttribute("my_id");

            MessageController.load_outbox(request, my_id);
            page = "/WEB-INF/messages/outbox.jsp";
        }

        if (url.equalsIgnoreCase("/contact/create")) {
            forwardParameter(request, "email");     //kanei load to email
            page = "/WEB-INF/messages/create_message.jsp";
        }

        if (url.equalsIgnoreCase("/contact/inbox/details")) {
            MessageController.load(request);
            page = "/WEB-INF/messages/message_details_inbox.jsp";
        }

        if (url.equalsIgnoreCase("/contact/outbox/details")) {
            MessageController.load(request);
            page = "/WEB-INF/messages/message_details_outbox.jsp";
        }

        if (url.equalsIgnoreCase("/contact/delete")) {
            page = "/WEB-INF/messages/delete_message.jsp";
        }

        if (url.equalsIgnoreCase("/contact/reply")) {
            forwardParameter(request, "email");
            page = "/WEB-INF/messages/create_message.jsp";
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

        if (url.equalsIgnoreCase("/contact")) {
            page = "/WEB-INF/messages/contact.jsp";
        }


        if (url.equalsIgnoreCase("/contact/create")) {
            if (MessageController.save_message(request)) {
                page = "/WEB-INF/messages/message_ok.jsp";
            } else {
                page = "/WEB-INF/messages/message_error.jsp";
            }
        }


        if (url.equalsIgnoreCase("/contact/delete")) {
            page = "/WEB-INF/messages/delete_message.jsp";
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

}
