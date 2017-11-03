package dao;

import db.ConnectionFactory;
import db.DAOUtil;
import entities.UserHasRentPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserHasRentDaoImpl implements UserHasRentDao {
    // --------------------------------------------------
    //                  SQL Statements
    // --------------------------------------------------

    private static final String SQL_LIST_ORDER_BY_USER_ID = "SELECT * FROM UserHasRent ORDER BY user_id";
    private static final String SQL_FIND_BY_USER_ID = "SELECT * FROM UserHasRent WHERE user_id = ?";
     private static final String SQL_INSERT = "INSERT INTO rentals.UserHasRent ("
            + "user_id,"
            + "rent_id"
            + ") VALUES ( ?,? ) ";
     
    
    private ConnectionFactory factory;

    public UserHasRentDaoImpl(boolean pool) {
        factory = ConnectionFactory.getInstance(pool);
    }
    
    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // convert database row to database entity
    private static UserHasRentPojo map(ResultSet resultSet) throws SQLException {
        UserHasRentPojo userHasRent = new UserHasRentPojo();
        userHasRent.setUser_id(resultSet.getLong("user_id"));
        userHasRent.setRent_id(resultSet.getLong("rent_id"));
        
        return userHasRent;
    }
    
    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // select all lines
    public List<UserHasRentPojo> list() {
        List<UserHasRentPojo> userHasRents = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_USER_ID);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                UserHasRentPojo userHasRent = map(resultSet);
                userHasRents.add(userHasRent);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return userHasRents;
    }
    
    // select by primary key
    public UserHasRentPojo find(Long user_id) {
        UserHasRentPojo userHasRent = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_USER_ID, false, user_id);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                userHasRent = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return userHasRent;
    }
    
    
    // --------------------------------------------------
    //                      INSERT 
    // --------------------------------------------------
    
    public Long insert(UserHasRentPojo pojo) {
        Object[] values = new Object[]{
            pojo.getUser_id(),
            pojo.getRent_id()
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
