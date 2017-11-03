package dao;

import entities.UserHasOfferPojo;
import java.util.List;


public interface UserHasOfferDao {
    
    public List<UserHasOfferPojo> list();
    public UserHasOfferPojo find(Long offer_id);
    public Long insert(UserHasOfferPojo pojo);
}
