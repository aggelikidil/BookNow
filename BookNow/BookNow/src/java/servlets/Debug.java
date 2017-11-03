package servlets;

import dao.MessageDaoImpl;
import dao.OfferDaoImpl;
import dao.RatingDaoImpl;
import dao.RentDaoImpl;
import dao.RoomDaoImpl;
import dao.RoomDetailsDaoImpl;
import dao.UserDaoImpl;
import dao.UserHasOfferDaoImpl;
import dao.UserHasRentDaoImpl;
import dao.UserRatingDaoImpl;
import entities.MessagePojo;
import entities.OfferPojo;
import entities.RentPojo;
import entities.RoomPojo;
import entities.RoomDetailsPojo;
import entities.UserPojo;
import entities.UserHasOfferPojo;
import entities.UserHasRentPojo;
import entities.UserRatingPojo;
import entities.RatingPojo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aggeliki
 */
@WebServlet(name = "Debug", urlPatterns = {"/Debug"})
public class Debug extends HttpServlet {

    void print(PrintWriter out, String str) {
        out.println("<p>" + str + "</p>");
    }

    void printTitle(PrintWriter out, String str) {
        out.println("<h1>" + str + "</h1>");
    }

    private void testMessageDao(PrintWriter out){
        printTitle(out, "Testing MessageDao");

        MessageDaoImpl db = new MessageDaoImpl(true);
        
        // ---------------------------------
//        printTitle(out, "Testing List");
//        //List<MessagePojo> list = db.list();
//        for (MessagePojo pojo : list) {
//            print(out, pojo.getUser_sender() + " " + pojo.getUser_receiver() + " " + pojo.getText());
//        }

        // ---------------------------------
        printTitle(out, "Testing Find 1");
        MessagePojo find = db.find(1L);
        print(out, find.toHTML());


    }
    private void testOfferDao(PrintWriter out) {
        printTitle(out, "Testing OfferDao");

        OfferDaoImpl db = new OfferDaoImpl(true);

        // ---------------------------------
        printTitle(out, "Testing List");
        List<OfferPojo> list = db.list();
        for (OfferPojo pojo : list) {
            print(out, pojo.getRoom_id() + " " + pojo.getDate_from() + " " + pojo.getDate_to());
        }

        // ---------------------------------
        printTitle(out, "Testing Find 1");
        OfferPojo find = db.find(1L);
        print(out, find.toHTML());

    }

    private void testRatingDao(PrintWriter out) {
        printTitle(out, "Testing RatingDao");

        RatingDaoImpl db = new RatingDaoImpl(true);

        // ---------------------------------
        printTitle(out, "Testing List");
        List<RatingPojo> list = db.list();
        for (RatingPojo pojo : list) {
            print(out, pojo.getRoom_id() + " " + pojo.getUser_rate() + " " + pojo.getValue() + " ");
        }

        // ---------------------------------
        printTitle(out, "Testing Find 1");
        RatingPojo find = db.find(1L);
        print(out, find.toHTML());
    }

    private void testRentDao(PrintWriter out) {
        printTitle(out, "Testing RentDao");

        RentDaoImpl db = new RentDaoImpl(true);

        // ---------------------------------
        printTitle(out, "Testing List");
        List<RentPojo> list = db.list();
        for (RentPojo pojo : list) {
            print(out, pojo.getRoom_id() + " " + pojo.getDate_from() + " " + pojo.getDate_to());
        }

        // ---------------------------------
        printTitle(out, "Testing Find 4");
        RentPojo find = db.find(4L);
        print(out, find.toHTML());
    }

    private void testRoomDao(PrintWriter out) {
        printTitle(out, "Testing RoomDao");

        RoomDaoImpl db = new RoomDaoImpl(true);

        // ---------------------------------
        printTitle(out, "Testing List");
        List<RoomPojo> list = db.list();
        for (RoomPojo pojo : list) {
            print(out, pojo.getId() + " " + pojo.getOwner_id() + " " + pojo.getAddress());
        }

        // ---------------------------------
        printTitle(out, "Testing Find 4");
        RoomPojo find = db.find(4L);
        print(out, find.toHTML());
    }

    private void testRoomDetailsDao(PrintWriter out) {
        printTitle(out, "Testing RoomDetailsDao");

        RoomDetailsDaoImpl db = new RoomDetailsDaoImpl(true);

        // ---------------------------------
        printTitle(out, "Testing List");
        List<RoomDetailsPojo> list = db.list();
        for (RoomDetailsPojo pojo : list) {
            print(out, pojo.getId() + " " + pojo.getBeds() + " " + pojo.getWifi());
        }

        // ---------------------------------
        printTitle(out, "Testing Find 3");
        RoomDetailsPojo find = db.find(3L);
        print(out, find.toHTML());
    }

    private void testUserDao(PrintWriter out) {
        printTitle(out, "Testing UserDao");

        UserDaoImpl db = new UserDaoImpl(true);

        // ---------------------------------
        printTitle(out, "Testing List");
        List<UserPojo> list = db.list();
        for (UserPojo pojo : list) {
            print(out, pojo.getUsername() + " " + pojo.getFirstname() + " " + pojo.getLastname());
        }

        // ---------------------------------
        printTitle(out, "Testing Find 1");
        UserPojo find = db.find(1L);
        print(out, find.toHTML());

        // ---------------------------------
        printTitle(out, "Testing Find aggelikidil");
        UserPojo find2 = db.find("aggelikidil");
        print(out, find2.toHTML());
    }

    private void testUserHasOfferDao(PrintWriter out) {
        printTitle(out, "Testing UserHasOfferDao");

        UserHasOfferDaoImpl db = new UserHasOfferDaoImpl(true);

        // ---------------------------------
        printTitle(out, "Testing List");
        List<UserHasOfferPojo> list = db.list();
        for (UserHasOfferPojo pojo : list) {
            print(out, pojo.getOffer_id() + " " + pojo.getUser_id() + " ");
        }

        // ---------------------------------
        printTitle(out, "Testing Find 5");
        UserHasOfferPojo find = db.find(5L);
        print(out, find.toHTML());

    }

    private void testUserHasRentDao(PrintWriter out) {
        printTitle(out, "Testing UserHasRentDao");

        UserHasRentDaoImpl db = new UserHasRentDaoImpl(true);

        // ---------------------------------
        printTitle(out, "Testing List");
        List<UserHasRentPojo> list = db.list();
        for (UserHasRentPojo pojo : list) {
            print(out, pojo.getUser_id() + " " + pojo.getRent_id() + " ");
        }

        // ---------------------------------
        printTitle(out, "Testing Find 2");
        UserHasRentPojo find = db.find(2L);
        print(out, find.toHTML());

    }

    private void testUserRatingDao(PrintWriter out) {
        printTitle(out, "Testing UserRatingDao");

        UserRatingDaoImpl db = new UserRatingDaoImpl(true);

        // ---------------------------------
        printTitle(out, "Testing List");
        List<UserRatingPojo> list = db.list();
        for (UserRatingPojo pojo : list) {
            print(out, pojo.getId() + " " + pojo.getUser_rate() + " " + pojo.getUser_rated() + " " + pojo.getValue());
        }

        // ---------------------------------
        printTitle(out, "Testing Find 6");
        UserRatingPojo find = db.find(6L);
        print(out, find.toHTML());

    }


    private void testPaginationUser(PrintWriter out) {
        int pagesize = 2;
        UserDaoImpl db = new UserDaoImpl(true);

        List<UserPojo> all = db.list();
        printTitle(out, "Testing PaginationUser");

        printTitle(out, "Testing Page 0");
        List<UserPojo> page1 = db.paginationUser(all, 1, pagesize);
        for (UserPojo pojo : page1) {
            print(out, pojo.getUsername() + " " + pojo.getFirstname() + " " + pojo.getLastname());
        }

        printTitle(out, "Testing Page 1");
        List<UserPojo> page2 = db.paginationUser(all, 2, pagesize);
        for (UserPojo pojo : page2) {
            print(out, pojo.getUsername() + " " + pojo.getFirstname() + " " + pojo.getLastname());
        }

        printTitle(out, "Testing Page 2");
        List<UserPojo> page3 = db.paginationUser(all, 3, pagesize);
        for (UserPojo pojo : page3) {
            print(out, pojo.getUsername() + " " + pojo.getFirstname() + " " + pojo.getLastname());
        }

    }

    private void testPaginationOffer(PrintWriter out) {
        int pagesize = 2;
        OfferDaoImpl db = new OfferDaoImpl(true);

        List<OfferPojo> all = db.list();
        printTitle(out, "Testing PaginationOffer");

        printTitle(out, "Testing Page 0");
        List<OfferPojo> page1 = db.paginationOffer(all, 1, pagesize);
        for (OfferPojo pojo : page1) {
            print(out, pojo.getRoom_id() + " " + pojo.getDate_from() + " " + pojo.getDate_to());
        }

        printTitle(out, "Testing Page 1");
        List<OfferPojo> page2 = db.paginationOffer(all, 2, pagesize);
        for (OfferPojo pojo : page2) {
            print(out, pojo.getRoom_id() + " " + pojo.getDate_from() + " " + pojo.getDate_to());
        }

        //--------------------------------------------------------
        printTitle(out, "Testing Page 2");
        List<OfferPojo> page3 = db.paginationOffer(all, 3, pagesize);
        for (OfferPojo pojo : page3) {
            print(out, pojo.getRoom_id() + " " + pojo.getDate_from() + " " + pojo.getDate_to());
        }

    }

    private void testPaginationRating(PrintWriter out) {
        int pagesize = 2;
        RatingDaoImpl db = new RatingDaoImpl(true);

        List<RatingPojo> all = db.list();
        printTitle(out, "Testing PaginationRating");

        printTitle(out, "Testing Page 0");
        List<RatingPojo> page1 = db.paginationRating(all, 1, pagesize);
        for (RatingPojo pojo : page1) {
            print(out, pojo.getRoom_id() + " " + pojo.getValue() + " ");
        }

        printTitle(out, "Testing Page 1");
        List<RatingPojo> page2 = db.paginationRating(all, 2, pagesize);
        for (RatingPojo pojo : page2) {
            print(out, pojo.getRoom_id() + " " + pojo.getValue() + " ");
        }

        printTitle(out, "Testing Page 2");
        List<RatingPojo> page3 = db.paginationRating(all, 3, pagesize);
        for (RatingPojo pojo : page3) {
            print(out, pojo.getRoom_id() + " " + pojo.getValue() + " ");
        }

    }

    private void testPaginationRent(PrintWriter out) {
        int pagesize = 2;
        RentDaoImpl db = new RentDaoImpl(true);

        List<RentPojo> all = db.list();
        printTitle(out, "Testing PaginationRent");

        printTitle(out, "Testing Page 0");
        List<RentPojo> page1 = db.paginationRent(all, 1, pagesize);
        for (RentPojo pojo : page1) {
            print(out, pojo.getRoom_id() + " " + pojo.getDate_from() + " " + pojo.getDate_to());
        }

        printTitle(out, "Testing Page 1");
        List<RentPojo> page2 = db.paginationRent(all, 2, pagesize);
        for (RentPojo pojo : page2) {
            print(out, pojo.getRoom_id() + " " + pojo.getDate_from() + " " + pojo.getDate_to());
        }

        printTitle(out, "Testing Page 2");
        List<RentPojo> page3 = db.paginationRent(all, 3, pagesize);
        for (RentPojo pojo : page3) {
            print(out, pojo.getRoom_id() + " " + pojo.getDate_from() + " " + pojo.getDate_to());
        }
    }

    private void testUserDaoInsert(PrintWriter out) {
        UserPojo pojo = new UserPojo(1L, "bob", "bob", "bob", "bob", "bob@bob.com", "123", true, true, true, "http://google.com", "athens", "Greece", "attica", "10", "10", true);

        UserDaoImpl db = new UserDaoImpl(true);

        Long id = db.insert(pojo);

        print(out, "id = " + id);
    }

    private void testMessageDaoInsert(PrintWriter out) {
        MessagePojo pojo = new MessagePojo(1L, 1L, 1L, "HELLOOOOOOOOO!!!", "2017-05-07");

        MessageDaoImpl db = new MessageDaoImpl(true);

        Long id = db.insert(pojo);

        print(out, "id = " + id);
    }

    private void testOfferDaoInsert(PrintWriter out) {
        OfferPojo pojo = new OfferPojo(1L, 2L, "2016-03-05", "2017-07-12", 200);

        OfferDaoImpl db = new OfferDaoImpl(true);

        Long id = db.insert(pojo);

        print(out, "id = " + id);
    }

    private void testRatingDaoInsert(PrintWriter out) {
        RatingPojo pojo = new RatingPojo(3L, 4L, 4L, 6);

        RatingDaoImpl db = new RatingDaoImpl(true);

        Long id = db.insert(pojo);

        print(out, "id = " + id);
    }

    private void testRentDaoInsert(PrintWriter out) {
        RentPojo pojo = new RentPojo(3L, 4L, "2017-02-13", "2017-09-13");

        RentDaoImpl db = new RentDaoImpl(true);

        Long id = db.insert(pojo);

        print(out, "id = " + id);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Testing DB operations</title>");
            out.println("</head>");
            out.println("<body>");

            testUserDao(out);
            testOfferDao(out);
            testRatingDao(out);
            testRentDao(out);
            testRoomDao(out);
            testRoomDetailsDao(out);
            testUserHasOfferDao(out);
            testUserHasRentDao(out);
            testUserRatingDao(out);

            testMessageDaoInsert(out);
            testOfferDaoInsert(out);
            testUserDaoInsert(out);
            testRatingDaoInsert(out);
            testRentDaoInsert(out);

            testPaginationOffer(out);
            testPaginationUser(out);
            testPaginationRating(out);
            testPaginationRent(out);

            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
