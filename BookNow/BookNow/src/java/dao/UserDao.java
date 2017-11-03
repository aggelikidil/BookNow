package dao;

import entities.UserPojo;
import java.util.List;


public interface UserDao {

    public List<Long> listIDs();
    public List<UserPojo> list();
    public List<UserPojo> list_active();
    public List<UserPojo> list_inactive();
    public UserPojo find(Long id);
    public UserPojo find(String username);
    public Long insert(UserPojo pojo);
    public void update(UserPojo pojo);
    public UserPojo updateActivation(UserPojo pojo);
    

    public UserPojo findByEmail(String email);
}
