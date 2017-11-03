package dao;

import db.ConnectionFactory;
import db.DAOUtil;
import entities.UserRatingPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserRatingDaoImpl implements UserRatingDao {
    // --------------------------------------------------
    //                  SQL Statements
    // --------------------------------------------------

    private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM UserRating ORDER BY id";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM UserRating WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO rentals.UserRating ("
            + "user_rate,"
            + "user_rated,"
            + "value"
            + ") VALUES ( ?,?,?) ";
     
    
    private ConnectionFactory factory;
    
    public UserRatingDaoImpl(boolean pool) {
        factory = ConnectionFactory.getInstance(pool);
    }
    
    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // convert database row to database entity
    private static UserRatingPojo map(ResultSet resultSet) throws SQLException {
        UserRatingPojo userRating = new 
        UserRatingPojo();
        userRating.setId(resultSet.getLong("id"));
        userRating.setUser_rate(resultSet.getLong("user_rate"));
        userRating.setUser_rated(resultSet.getLong("user_rated"));
        userRating.setValue(resultSet.getInt("value"));
        
        return userRating;
    }
    
    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // select all lines
    public List<UserRatingPojo> list() {
        List<UserRatingPojo> userRatings = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                UserRatingPojo userRating = map(resultSet);
                userRatings.add(userRating);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return userRatings;
    }
    
    // select by primary key
    public UserRatingPojo find(Long id) {
        UserRatingPojo userRating = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, id);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                userRating = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return userRating;
        
    }
    
    
    
    
    // --------------------------------------------------
    //                      INSERT 
    // --------------------------------------------------
    
     public Long insert(UserRatingPojo pojo) {
        Object[] values = new Object[]{
            pojo.getUser_rate(),
            pojo.getUser_rated(),
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

