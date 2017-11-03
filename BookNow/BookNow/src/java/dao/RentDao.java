package dao;

import entities.RentPojo;
import java.util.List;


public interface RentDao {
    
    public List<RentPojo> list();
    public RentPojo find(Long id);
    public List<RentPojo> find_by_room_id(Long room_id);
    public Long insert(RentPojo pojo);
    
}
