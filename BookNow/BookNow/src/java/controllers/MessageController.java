
package controllers;

import dao.MessageDao;
import dao.MessageDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import entities.MessagePojo;
import entities.UserPojo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class MessageController {
    
    public static boolean load_outbox(HttpServletRequest request, long id) { 
        try {
            HttpSession session = request.getSession();

            Long my_id = (Long) session.getAttribute("my_id");

            MessageDao dao = new MessageDaoImpl(true);
            List<MessagePojo> messages = dao.list_outbox(my_id);
            request.setAttribute("messages", messages);

            return true;
        } catch (Exception e) {
            validation.Validation.assignError(request, "ID", e.getMessage());
            return false;
        }
    }
    
    public static boolean load_inbox(HttpServletRequest request, long id) { 
        try {
            HttpSession session = request.getSession();

            Long my_id = (Long) session.getAttribute("my_id");

            MessageDao dao = new MessageDaoImpl(true);
            List<MessagePojo> messages = dao.list_inbox(my_id);
            request.setAttribute("messages", messages);

            return true;
        } catch (Exception e) {
            validation.Validation.assignError(request, "ID", e.getMessage());
            return false;
        }
    }
    
    
     public static boolean load(HttpServletRequest request) { 
        try {
            String string_id = (String) request.getParameter("id");
            Long id = Long.parseLong(string_id);

            MessageDao dao = new MessageDaoImpl(true);
            
            
            MessagePojo existingMessage = dao.find(id);
            request.setAttribute("MESSAGE", existingMessage);
            
            return true;

        } catch (Exception e) {
            return false;
        }
    }
     
     
     public static boolean save_message(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();

            String text = request.getParameter("TEXT");
            
            // find sender id
            Long sender_id = (Long) session.getAttribute("my_id");
            
            // find receiver id                        
            String email = request.getParameter("email");
            
            UserDao dao = new UserDaoImpl(true);
            UserPojo u = dao.findByEmail(email);
            
            if (u == null) {
                return false;
            }
                    
            Long receivers_id = u.getId();
            
            
            MessagePojo newMessage = new MessagePojo();

            newMessage.setText(text);
            newMessage.setUser_sender(sender_id);
            newMessage.setUser_receiver(receivers_id);
            newMessage.setDate(null);
            
            MessageDao mdao = new MessageDaoImpl(true);
            mdao.insert(newMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
