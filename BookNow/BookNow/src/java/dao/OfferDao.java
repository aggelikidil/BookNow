package dao;

import entities.OfferPojo;
import java.util.List;


public interface OfferDao {
    
    public List<OfferPojo> list();
    public List<OfferPojo> list_for_room(Long room_id);
    public OfferPojo find(Long room_id);
    public Long insert(OfferPojo pojo);
    public void update_offer(OfferPojo pojo);
}
