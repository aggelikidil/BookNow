package dao;

import entities.UserHasRentPojo;
import java.util.List;


public interface UserHasRentDao {
    
     
    public List<UserHasRentPojo> list();
    public UserHasRentPojo find(Long user_id);
    public Long insert(UserHasRentPojo pojo);
    
}
