
package controllers;

import dao.MessageDao;
import dao.MessageDaoImpl;
import entities.MessagePojo;
import javax.servlet.http.HttpServletRequest;


public class CreateMessageController {
    
    public static Long create_message(HttpServletRequest request, Long user_sender_id) {
        try {
            String text = request.getParameter("text");
            String date = request.getParameter("date");
            String id = request.getParameter("id");
            
            Long user_receiver_id = Long.parseLong(id);

            

            MessagePojo message = new MessagePojo();            

            message.setUser_receiver(user_receiver_id);
            message.setDate(date);
            message.setText(text);

            MessageDao dao = new MessageDaoImpl(true);
            dao.insert(message);
      
            
            return user_sender_id;

        } catch (Exception e) {
            return null;
        }

    }

    
}
