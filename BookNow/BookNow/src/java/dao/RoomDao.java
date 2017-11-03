package dao;


import entities.RoomPojo;
import java.util.List;


public interface RoomDao {
    
    public List<RoomPojo> search(String from, String to, int members, String location);
    public List<Long> listIDs();
    public List<RoomPojo> list();
    public List<RoomPojo> list(Long owner_id, boolean active);
    public RoomPojo find(Long id);
    public RoomPojo recfind(Long id);
    public Long insert(RoomPojo pojo);
    public void update_room(RoomPojo pojo);

    public List<RoomPojo> advancedsearch(String from, String to, int total_members, String keyword, String roomtype, int max_cost_int, boolean tv_needed, boolean wifi_needed,boolean heat_needed, boolean aircondition_needed, boolean kitchen_needed, boolean parking_needed, boolean elevator_needed);

    public List<RoomPojo> listRentedRooms(Long my_id);

}
