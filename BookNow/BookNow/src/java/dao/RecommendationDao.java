package dao;

import entities.RoomPojo;
import java.util.List;


public interface RecommendationDao {
    public List<RoomPojo> list(Long user_id);
}
