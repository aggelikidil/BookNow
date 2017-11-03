package dao;

import entities.UserRatingPojo;
import java.util.List;


public interface UserRatingDao {
    
    public List<UserRatingPojo> list();
    public UserRatingPojo find(Long id);
    public Long insert(UserRatingPojo pojo);
}
