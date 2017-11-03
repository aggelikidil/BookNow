package dao;

import db.ConnectionFactory;
import db.DAOUtil;
import entities.RatingPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RatingDaoImpl implements RatingDao {
    // --------------------------------------------------
    //                  SQL Statements
    // --------------------------------------------------

    private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM Rating ORDER BY id";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM Rating WHERE id = ?";
    private static final String SQL_FIND_BY_USER_ID = "SELECT * FROM Rating WHERE user_rate = ?";
    private static final String SQL_FIND_BY_ROOM_ID = "SELECT * FROM Rating WHERE room_id = ?";
    private static final String SQL_INSERT = "INSERT INTO rentals.Rating ("
            + "room_id,"
            + "user_rate,"
            + "value"
            + ") VALUES ( ?,?,?) ";

    
    private ConnectionFactory factory;
    
    public RatingDaoImpl(boolean pool) {
        factory = ConnectionFactory.getInstance(pool);
    }
    
    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // convert database row to database entity
    private static RatingPojo map(ResultSet resultSet) throws SQLException {
        RatingPojo rating = new RatingPojo();
        rating.setId(resultSet.getLong("id"));
        rating.setRoom_id(resultSet.getLong("room_id"));
        rating.setUser_rate(resultSet.getLong("user_rate"));
        rating.setValue(resultSet.getInt("value"));
        
        return rating;
    }
    
     
    public List<RatingPojo> paginationRating(List<RatingPojo> all, int pageno, int pagesize) {
        List<RatingPojo> page = new ArrayList<>();
                
        for (int i=pageno*pagesize;i<=(pageno+1)*pagesize-1;i++) {
            if (all.size() > i) {
                page.add(all.get(i));
            }
        }
                
        return page;
    }
    
    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // select all lines
    public List<RatingPojo> list() {
        List<RatingPojo> ratings = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                RatingPojo rating = map(resultSet);
                ratings.add(rating);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return ratings;
    }
    
    // select by primary key
    public RatingPojo find(Long id) {
        RatingPojo rating = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, id);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                rating = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rating;
        
    }
    
    @Override
    public List<RatingPojo> find_by_room_id(Long room_id) {
        
        List<RatingPojo> ratings = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ROOM_ID, false, room_id);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                RatingPojo rating = map(resultSet);
                ratings.add(rating);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return ratings;
    }
    
    @Override
    public List<RatingPojo> find_by_user_id(Long user_id) {
         List<RatingPojo> ratings = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_USER_ID, false, user_id);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                RatingPojo rating = map(resultSet);
                ratings.add(rating);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return ratings;
    }
     
    // --------------------------------------------------
    //                      INSERT 
    // --------------------------------------------------
    public Long insert(RatingPojo pojo) {
        Object[] values = new Object[]{
            pojo.getRoom_id(),
            pojo.getUser_rate(),
            pojo.getValue()
        };

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);) {

            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys();) {
                if (rs != null && rs.next()) {
                    Long key = rs.getLong(1);
                    return key;
                }
            }

            return -1L;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

        return -1L;
    }
    
    
    
}
