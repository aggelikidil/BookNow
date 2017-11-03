package dao;

import entities.RatingPojo;
import java.util.List;


public interface RatingDao {
    
    public List<RatingPojo> list();
    public RatingPojo find(Long id);
    public List<RatingPojo> find_by_user_id(Long user_id);
    public List<RatingPojo> find_by_room_id(Long room_id);
    public Long insert(RatingPojo pojo);
    
}
