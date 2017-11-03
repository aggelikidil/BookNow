package dao;

import db.ConnectionFactory;
import db.DAOUtil;
import entities.OfferPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OfferDaoImpl implements OfferDao {
    // --------------------------------------------------
    //                  SQL Statements
    // --------------------------------------------------
    
    private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM Offer ORDER BY id";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM Offer WHERE room_id = ?";
    private static final String SQL_FIND_BY_ROOM_ID = "SELECT * FROM Offer WHERE room_id = ?";
    private static final String SQL_INSERT = "INSERT INTO rentals.Offer ("
            + "room_id,"
            + "date_from,"
            + "date_to,"
            + "cost_per_day"
            + ") VALUES ( ?,?,?,?) ";

    
    private static final String SQL_UPDATE_DATE_FROM = "UPDATE Offer set date_from = ? WHERE id = ?";
    private static final String SQL_UPDATE_DATE_TO = "UPDATE Offer set date_to = ? WHERE id = ?";
    private static final String SQL_UPDATE_COST_PER_DAY = "UPDATE Offer set cost_per_day = ? WHERE id = ?";
    
    private ConnectionFactory factory;
    
    public OfferDaoImpl(boolean pool) {
        factory = ConnectionFactory.getInstance(pool);
    }
    
    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // convert database row to database entity
    private static OfferPojo map(ResultSet resultSet) throws SQLException {
        OfferPojo offer = new OfferPojo();
        offer.setId(resultSet.getLong("id"));
        offer.setRoom_id(resultSet.getLong("room_id"));
        offer.setDate_from(resultSet.getString("date_from"));
        offer.setDate_to(resultSet.getString("date_to"));
        
        if (resultSet.getString("cost_per_day") != null) {
            offer.setCost_per_day(resultSet.getInt("cost_per_day"));
        }

        return offer;
    }
    
    public List<OfferPojo> paginationOffer(List<OfferPojo> all, int pageno, int pagesize) {
        List<OfferPojo> page = new ArrayList<>();
                
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
    public List<OfferPojo> list() {
        List<OfferPojo> offers = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                OfferPojo offer = map(resultSet);
                offers.add(offer);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return offers;
    }
    
    public OfferPojo find(Long room_id) {
        OfferPojo offer = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, room_id);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                offer = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return offer;
    }
    
    
    // --------------------------------------------------
    //                      INSERT 
    // --------------------------------------------------
    @Override
    public Long insert(OfferPojo pojo) {
        //eleipe to simple date format
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
        
        Date datefrom = null ;
        Date dateto = null ;
        
        try {
            datefrom = sdf.parse(pojo.getDate_from());
            dateto = sdf.parse(pojo.getDate_to());
        } catch (Exception ex){
            return -1L;
        }
        
        
        
        Object[] values = new Object[]{
            pojo.getRoom_id(),
            datefrom,
            dateto,
            pojo.getCost_per_day()
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
    
    
    // --------------------------------------------------
    //                      UPDATE 
    // --------------------------------------------------
    
    public void update_offer(OfferPojo newOffer) {
        OfferPojo previousOffer = null;
        Long id = newOffer.getId();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement_select = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, id);
                ResultSet resultSet = statement_select.executeQuery();) {

            if (resultSet.next()) {
                previousOffer = map(resultSet);
            } else {
                return;
            }

            if (!previousOffer.getDate_from().equals(newOffer.getDate_from())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_DATE_FROM, false, newOffer.getDate_from(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousOffer.getDate_to().equals(newOffer.getDate_to())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_DATE_TO, false, newOffer.getDate_to(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousOffer.getCost_per_day().equals(newOffer.getCost_per_day())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_COST_PER_DAY, false, newOffer.getCost_per_day(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }


    
    @Override
    public List<OfferPojo> list_for_room(Long room_id) {
         List<OfferPojo> offers = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ROOM_ID, false, room_id);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                OfferPojo offer = map(resultSet);
                offers.add(offer);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return offers;
    }
}

