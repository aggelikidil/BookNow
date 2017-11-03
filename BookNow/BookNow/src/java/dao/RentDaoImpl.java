package dao;

import db.ConnectionFactory;
import db.DAOUtil;
import entities.RentPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentDaoImpl implements RentDao {
    // --------------------------------------------------
    //                  SQL Statements
    // --------------------------------------------------

    private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM Rent ORDER BY id";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM Rent WHERE id = ?";
    private static final String SQL_FIND_BY_ROOM_ID = "SELECT * FROM Rent WHERE room_id = ?";
    private static final String SQL_INSERT = "INSERT INTO rentals.Rent ("
            + "room_id,"
            + "date_from,"
            + "date_to"
            + ") VALUES (?,?,? ) ";

    private ConnectionFactory factory;

    public RentDaoImpl(boolean pool) {
        factory = ConnectionFactory.getInstance(pool);
    }

    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // convert database row to database entity
    private static RentPojo map(ResultSet resultSet) throws SQLException {
        RentPojo rent = new RentPojo();
        rent.setId(resultSet.getLong("id"));
        rent.setRoom_id(resultSet.getLong("room_id"));

        if (resultSet.getString("date_from") != null) {
            rent.setDate_from(resultSet.getString("date_from"));
        }

        if (resultSet.getString("date_to") != null) {
            rent.setDate_to(resultSet.getString("date_to"));
        }

        return rent;

    }

    public List<RentPojo> paginationRent(List<RentPojo> all, int pageno, int pagesize) {
        List<RentPojo> page = new ArrayList<>();

        for (int i = pageno * pagesize; i <= (pageno + 1) * pagesize - 1; i++) {
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
    public List<RentPojo> list() {
        List<RentPojo> rents = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                RentPojo rent = map(resultSet);
                rents.add(rent);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rents;
    }

    // select by primary key
    public RentPojo find(Long id) {
        RentPojo rent = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, id);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                rent = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rent;
    }

    @Override
    public List<RentPojo> find_by_room_id(Long room_id) {

        List<RentPojo> rents = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ROOM_ID, false, room_id);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                RentPojo rent = map(resultSet);
                rents.add(rent);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rents;
    }

    // --------------------------------------------------
    //                      INSERT 
    // --------------------------------------------------
    public Long insert(RentPojo pojo) {

        SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");

        Date datefrom = null;
        Date dateto = null;

        try {
            datefrom = sdf.parse(pojo.getDate_from());
            dateto = sdf.parse(pojo.getDate_to());
        } catch (Exception ex) {
            return -1L;
        }

        Object[] values = new Object[]{
            pojo.getRoom_id(),
            datefrom,
            dateto
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
