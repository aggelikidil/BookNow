package servlets;

import controllers.LoginController;
import controllers.LogoutController;
import controllers.ProfileController;
import controllers.RegistrationController;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import validation.FieldsManager;
import validation.Validation;

/**
 *
 * @author aggeliki
 */
@WebServlet(name = "AccountServlet", urlPatterns = {"/login", "/register", "/logout", "/profile"})
public class AccountServlet extends HttpServlet {

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

        if (url.equalsIgnoreCase("/register")) {
            page = "/WEB-INF/account/register.jsp";
        }

        if (url.equalsIgnoreCase("/login")) {
            page = "/WEB-INF/account/login.jsp";
        }

        if (url.equalsIgnoreCase("/logout")) {
            logout(request, response);
            page = "/WEB-INF/account/logout_ok.jsp";
        }

        if (url.equalsIgnoreCase("/profile")) {
            ProfileController.load(request);
            page = "/WEB-INF/account/profile.jsp";
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

        if (url.equalsIgnoreCase("/register")) {
            if (register(request, response) == true) {
                page = "/WEB-INF/account/register_ok.jsp";
            } else {
                FieldsManager.forward(request);
                page = "/WEB-INF/account/register.jsp";
            }
        }

        if (url.equalsIgnoreCase("/login")) {
            if (login(request, response) == true) {
                page = "/WEB-INF/account/login_ok.jsp";
            } else {
                page = "/WEB-INF/account/login.jsp";
            }
        }

        if (url.equalsIgnoreCase("/logout")) {
            logout(request, response);
            page = "/WEB-INF/account/logout_ok.jsp";
        }

        if (url.equalsIgnoreCase("/profile")) {
            if (updateprofile(request, response) == true) {
                page = "/WEB-INF/account/profile_ok.jsp";
            } else {
                page = "/WEB-INF/account/profile.jsp";
            }
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

    private boolean register(HttpServletRequest request, HttpServletResponse response) {
        boolean error = Validation.isEmpty(request, "USERNAME");
        error = Validation.isEmpty(request, "PASSWORD") || error;
        error = Validation.isEmpty(request, "PASSWORD_VERIFICATION") || error;
        error = Validation.isEmpty(request, "FIRSTNAME") || error;
        error = Validation.isEmpty(request, "LASTNAME") || error;
        error = Validation.isEmpty(request, "EMAIL") || error;
        error = Validation.isEmpty(request, "PHONENUMBER") || error;
        error = Validation.isEmpty(request, "PHOTO_URL") || error;
        error = Validation.isEmpty(request, "CITY") || error;
        error = Validation.isEmpty(request, "COUNTRY") || error;
        error = Validation.isEmpty(request, "LOCATION") || error;

        if (error) {
            return false;
        }

        String p1 = request.getParameter("PASSWORD");
        String p2 = request.getParameter("PASSWORD_VERIFICATION");

        if (!p1.equals(p2)) {
            Validation.assignError(request, "PASSWORD", "field does not match");
            Validation.assignError(request, "PASSWORD_VERIFICATION", "field does not match");
            return false;
        }

        return RegistrationController.register(request);
    }

    private boolean login(HttpServletRequest request, HttpServletResponse response) {
        boolean error = Validation.isEmpty(request, "USERNAME");
        error = Validation.isEmpty(request, "PASSWORD") || error;

        if (error) {
            return false;
        }

        return LoginController.login(request);
    }

    private boolean logout(HttpServletRequest request, HttpServletResponse response) {
        LogoutController.logout(request);
        return true;
    }

    private boolean updateprofile(HttpServletRequest request, HttpServletResponse response) {
        ProfileController.save(request);
        return true;
    }

}
