package dao;

import entities.RoomDetailsPojo;
import java.util.List;


public interface RoomDetailsDao {
     
    public List<RoomDetailsPojo> list();
    public RoomDetailsPojo find(Long id);
    public Long insert(RoomDetailsPojo pojo);
    public void update_details(RoomDetailsPojo pojo);
}
