package dao;

import db.ConnectionFactory;
import db.DAOUtil;
import entities.UserHasOfferPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserHasOfferDaoImpl implements UserHasOfferDao {
    // --------------------------------------------------
    //                  SQL Statements
    // --------------------------------------------------

    private static final String SQL_LIST_ORDER_BY_OFFER_ID = "SELECT * FROM UserHasOffer ORDER BY offer_id";
    private static final String SQL_FIND_BY_OFFER_ID = "SELECT * FROM UserHasOffer WHERE offer_id = ?";
    private static final String SQL_INSERT = "INSERT INTO rentals.UserHasOffer ("
            + "offer_id,"
            + "user_id"
            + ") VALUES ( ?,? ) ";
     
    
    private ConnectionFactory factory;

    public UserHasOfferDaoImpl(boolean pool) {
        factory = ConnectionFactory.getInstance(pool);
    }
    
    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // convert database row to database entity
    private static UserHasOfferPojo map(ResultSet resultSet) throws SQLException {
        UserHasOfferPojo userHasOffer = new UserHasOfferPojo();
        userHasOffer.setOffer_id(resultSet.getLong("offer_id"));
        userHasOffer.setUser_id(resultSet.getLong("user_id"));
        
        return userHasOffer;
    }
    
    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // select all lines
    public List<UserHasOfferPojo> list() {
        List<UserHasOfferPojo> userHasOffers = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_OFFER_ID);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                UserHasOfferPojo userHasOffer = map(resultSet);
                userHasOffers.add(userHasOffer);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return userHasOffers;
    }
    
    // select by primary key
    public UserHasOfferPojo find(Long offer_id) {
        UserHasOfferPojo userHasOffer = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_OFFER_ID, false, offer_id);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                userHasOffer = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return userHasOffer;
    }
    
     
    // --------------------------------------------------
    //                      INSERT 
    // --------------------------------------------------
    
    public Long insert(UserHasOfferPojo pojo) {
        Object[] values = new Object[]{
            pojo.getOffer_id(),
            pojo.getUser_id()
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
