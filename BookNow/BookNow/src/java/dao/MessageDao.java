
package dao;

import entities.MessagePojo;
import java.util.List;


public interface MessageDao {
    public List<MessagePojo> list_inbox(Long id);
    public List<MessagePojo> list_outbox(Long id);
    public MessagePojo find(Long id);
    public MessagePojo find(String date);
    public Long insert(MessagePojo pojo);
            
    
}
