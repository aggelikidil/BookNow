package servlets;

import dao.RentDao;
import dao.RentDaoImpl;
import dao.RoomDao;
import dao.RoomDaoImpl;
import dao.RoomDetailsDao;
import dao.RoomDetailsDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import dao.RatingDao;
import dao.RatingDaoImpl;
import entities.RentPojo;
import entities.RatingPojo;
import entities.RoomDetailsPojo;
import entities.RoomPojo;
import entities.UserPojo;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import xml.ExportClass;
import xml.RentModel;
import xml.RoomDetailsModel;
import xml.RoomModel;
import xml.RatingModel;


@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/users", "/admin/export", "/admin/users/active", "/admin/users/inactive", "/admin/users/all", "/admin/users/activate",  "/admin/users/details"})
public class AdminServlet extends HttpServlet {

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

        if (url.equalsIgnoreCase("/admin/users")) {
            page = "/WEB-INF/admin/users.jsp";
        }

        if (url.equalsIgnoreCase("/admin/export")) {
            page = "/WEB-INF/admin/export.jsp";
        }

        if (url.equalsIgnoreCase("/admin/users/active")) {
            UserDao dao = new UserDaoImpl(true);
            List<UserPojo> users = dao.list_active();
            request.setAttribute("users", users);
            page = "/WEB-INF/admin/active_users.jsp";
        }

        if (url.equalsIgnoreCase("/admin/users/inactive")) {
            UserDao dao = new UserDaoImpl(true);
            List<UserPojo> users = dao.list_inactive();
            request.setAttribute("users", users);
            page = "/WEB-INF/admin/inactive_users.jsp";
        }

        if (url.equalsIgnoreCase("/admin/users/all")) {
            UserDao dao = new UserDaoImpl(true);
            List<UserPojo> users = dao.list();
            request.setAttribute("users", users);
            page = "/WEB-INF/admin/all_users.jsp";
        }

        if (url.equalsIgnoreCase("/admin/users/details")) {
            UserDao dao = new UserDaoImpl(true);
            try {
                String value = request.getParameter("id");
                Long id = Long.parseLong(value);
                UserPojo user = dao.find(id);
                request.setAttribute("USER", user);
                page = "/WEB-INF/admin/user_details.jsp";
            } catch (Exception e) {

            }
        }

        if (url.equalsIgnoreCase("/admin/users/activate")) {
            page = "/WEB-INF/admin/activate.jsp";
        }

        if (url.equalsIgnoreCase("/admin/users/deactivate")) {
            page = "/WEB-INF/admin/deactivate.jsp";
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

        if (url.equalsIgnoreCase("/admin/export")) {
            export(request, response); 
        }


        if (url.equalsIgnoreCase("/logout")) {
            page = "/WEB-INF/account/logout_ok.jsp";
        }

        if (url.equalsIgnoreCase("/profile")) {
            if (updateprofile(request, response) == true) {
                page = "/WEB-INF/account/profile_ok.jsp";
            } else {
                page = "/WEB-INF/account/profile.jsp";
            }
        }

        if (url.equalsIgnoreCase("/admin/users/activate")) {
            if (activate(request, response) == true) {
                page = "/WEB-INF/admin/user_details.jsp";
            } else {
                page = "/WEB-INF/admin/user_details.jsp";
            }
        }

        
        if (url.equalsIgnoreCase("/admin/users")) {
            page = "/WEB-INF/admin/users.jsp";
        }

        if (url.equalsIgnoreCase("/admin/users/active")) {
            UserDao dao = new UserDaoImpl(true);
            List<UserPojo> users = dao.list_active();
            request.setAttribute("users", users);
            page = "/WEB-INF/admin/active_users.jsp";
        }
        
        if (url.equalsIgnoreCase("/admin/users/inactive")) {
            UserDao dao = new UserDaoImpl(true);
            List<UserPojo> users = dao.list_inactive();
            request.setAttribute("users", users);
            page = "/WEB-INF/admin/inactive_users.jsp";
        }
        
        if (url.equalsIgnoreCase("/admin/users/all")) {
            UserDao dao = new UserDaoImpl(true);
            List<UserPojo> users = dao.list();
            request.setAttribute("users", users);
            page = "/WEB-INF/admin/all_users.jsp";
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

    private boolean export(HttpServletRequest request, HttpServletResponse response) {
        try {
            ExportClass ec = new ExportClass();

            RoomDao roomDao = new RoomDaoImpl(true);
            RoomDetailsDao roomdDao = new RoomDetailsDaoImpl(true);
            RentDao rentDao = new RentDaoImpl(true);
            RatingDao ratingDao = new RatingDaoImpl(true);

            for (RoomPojo pojo : roomDao.list()) {
                Long room_id = pojo.getId();
                Long details_id = pojo.getRoom_details();

                RoomModel model = new RoomModel(pojo);

                // add room details element to room model
                RoomDetailsPojo d_pojo = roomdDao.find(details_id);
                model.roomDetails = new RoomDetailsModel(d_pojo);

                // add room rents element to room model
                for (RentPojo rent : rentDao.find_by_room_id(room_id)) {
                    RentModel m = new RentModel(rent);
                    model.rents.add(m);
                }

                // add room rating element to room model
                for (RatingPojo rating : ratingDao.find_by_room_id(room_id)) {
                    RatingModel k = new RatingModel(rating);
                    model.ratings.add(k);
                }

                // add room to list of export class
                ec.rooms.add(model);
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(ExportClass.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(ec, response.getOutputStream());
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    private boolean updateprofile(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }


    private boolean activate(HttpServletRequest request, HttpServletResponse response) {
        UserDao dao = new UserDaoImpl(true);
        try {
            String value = request.getParameter("id");
            Long id = Long.parseLong(value);
            UserPojo user = new UserPojo();
            user.setId(id);
            user.setIs_approved(true);
            user = dao.updateActivation(user);
            request.setAttribute("USER", user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
