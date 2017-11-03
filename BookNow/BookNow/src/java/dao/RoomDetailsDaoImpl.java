package dao;

import db.ConnectionFactory;
import db.DAOUtil;
import entities.RoomDetailsPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RoomDetailsDaoImpl implements RoomDetailsDao {

    // --------------------------------------------------
    //                  SQL Statements
    // --------------------------------------------------
    private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM RoomDetails ORDER BY id";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM RoomDetails WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO rentals.RoomDetails ("
            + "beds,"
            + "smoking,"
            + "pets,"
            + "event,"
            + "tv,"
            + "wifi,"
            + "heat,"
            + "aircondition,"
            + "parking,"
            + "elevator,"
            + "minimum_dates,"
            + "neighbourhood,"
            + "public_transport,"
            + "host_review,"
            + "confirmation"
            + ") VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ";

    private static final String SQL_UPDATE_BEDS = "UPDATE RoomDetails set beds = ? WHERE id = ?";
    private static final String SQL_UPDATE_SMOKING = "UPDATE RoomDetails set smoking = ? WHERE id = ?";
    private static final String SQL_UPDATE_PETS = "UPDATE RoomDetails set pets = ? WHERE id = ?";
    private static final String SQL_UPDATE_EVENT = "UPDATE RoomDetails set event = ? WHERE id = ?";
    private static final String SQL_UPDATE_TV = "UPDATE RoomDetails set tv = ? WHERE id = ?";
    private static final String SQL_UPDATE_WIFI = "UPDATE RoomDetails set wifi = ? WHERE id = ?";
    private static final String SQL_UPDATE_HEAT = "UPDATE RoomDetails set heat = ? WHERE id = ?";
    private static final String SQL_UPDATE_AIRCONDITION = "UPDATE RoomDetails set aircondition = ? WHERE id = ?";
    private static final String SQL_UPDATE_PARKING = "UPDATE RoomDetails set parking = ? WHERE id = ?";
    private static final String SQL_UPDATE_ELEVATOR = "UPDATE RoomDetails set elevator = ? WHERE id = ?";
    private static final String SQL_UPDATE_MINIMUM_DATES = "UPDATE RoomDetails set minimum_dates = ? WHERE id = ?";
    private static final String SQL_PUBLIC_TRANSPORT = "UPDATE RoomDetails set public_transport = ? WHERE id = ?";
    private static final String SQL_UPDATE_HOST_REVIEW = "UPDATE RoomDetails set host_review = ? WHERE id = ?";
    private static final String SQL_UPDATE_CONFIRMATION = "UPDATE RoomDetails set confirmation = ? WHERE id = ?";

    private ConnectionFactory factory;

    public RoomDetailsDaoImpl(boolean pool) {
        factory = ConnectionFactory.getInstance(pool);
    }

    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // convert database row to database entity
    private static RoomDetailsPojo map(ResultSet resultSet) throws SQLException {
        RoomDetailsPojo roomDetails = new RoomDetailsPojo();
        roomDetails.setId(resultSet.getLong("id"));

        if (resultSet.getString("beds") != null) {
            roomDetails.setBeds(resultSet.getInt("beds"));
        }

        roomDetails.setSmoking(resultSet.getBoolean("smoking"));
        roomDetails.setPets(resultSet.getBoolean("pets"));
        roomDetails.setEvent(resultSet.getBoolean("event"));
        roomDetails.setTv(resultSet.getBoolean("tv"));
        roomDetails.setWifi(resultSet.getBoolean("wifi"));
        roomDetails.setHeat(resultSet.getBoolean("heat"));
        roomDetails.setAircondition(resultSet.getBoolean("aircondition"));
        roomDetails.setParking(resultSet.getBoolean("parking"));
        roomDetails.setElevator(resultSet.getBoolean("elevator"));

        if (resultSet.getString("minimum_dates") != null) {
            roomDetails.setMinimum_dates(resultSet.getInt("minimum_dates"));
        }

        if (resultSet.getString("neighbourhood") != null) {
            roomDetails.setNeighbourhood(resultSet.getString("neighbourhood"));
        }

        if (resultSet.getString("public_transport") != null) {
            roomDetails.setPublic_transport(resultSet.getString("public_transport"));
        }

        if (resultSet.getString("host_review") != null) {
            roomDetails.setHost_review(resultSet.getString("host_review"));
        }

        roomDetails.setConfirmation(resultSet.getBoolean("confirmation"));

        return roomDetails;
    }

    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // select all lines
    public List<RoomDetailsPojo> list() {
        List<RoomDetailsPojo> roomsDetails = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                RoomDetailsPojo roomDetails = map(resultSet);
                roomsDetails.add(roomDetails);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return roomsDetails;
    }

    // select by primary key
    public RoomDetailsPojo find(Long id) {
        RoomDetailsPojo roomDetails = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, id);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                roomDetails = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return roomDetails;
    }

    // --------------------------------------------------
    //                      INSERT 
    // --------------------------------------------------
    public Long insert(RoomDetailsPojo pojo) {
        pojo.setConfirmation(true);

        Object[] values = new Object[]{
            pojo.getBeds(),
            pojo.getSmoking(),
            pojo.getPets(),
            pojo.getEvent(),
            pojo.getTv(),
            pojo.getWifi(),
            pojo.getHeat(),
            pojo.getAircondition(),
            pojo.getParking(),
            pojo.getElevator(),
            pojo.getMinimum_dates(),
            pojo.getNeighbourhood(),
            pojo.getPublic_transport(),
            pojo.getHost_review(),
            pojo.getConfirmation()};

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
    public void update_details(RoomDetailsPojo roomDetails) {
        RoomDetailsPojo previousRoomDetails = null;
        Long id = roomDetails.getId();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement_select = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, id);
                ResultSet resultSet = statement_select.executeQuery();) {

            if (resultSet.next()) {
                previousRoomDetails = map(resultSet);
            } else {
                return;
            }

            if (!previousRoomDetails.getBeds().equals(roomDetails.getBeds())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_BEDS, false, roomDetails.getBeds(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoomDetails.getSmoking().equals(roomDetails.getSmoking())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_SMOKING, false, roomDetails.getSmoking(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoomDetails.getPets().equals(roomDetails.getPets())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_PETS, false, roomDetails.getPets(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoomDetails.getEvent().equals(roomDetails.getEvent())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_EVENT, false, roomDetails.getEvent(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoomDetails.getTv().equals(roomDetails.getTv())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_TV, false, roomDetails.getTv(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoomDetails.getWifi().equals(roomDetails.getWifi())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_WIFI, false, roomDetails.getWifi(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoomDetails.getHeat().equals(roomDetails.getHeat())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_HEAT, false, roomDetails.getHeat(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoomDetails.getAircondition().equals(roomDetails.getAircondition())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_AIRCONDITION, false, roomDetails.getAircondition(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoomDetails.getParking().equals(roomDetails.getParking())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_PARKING, false, roomDetails.getParking(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoomDetails.getElevator().equals(roomDetails.getElevator())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_ELEVATOR, false, roomDetails.getElevator(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoomDetails.getMinimum_dates().equals(roomDetails.getMinimum_dates())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_MINIMUM_DATES, false, roomDetails.getMinimum_dates(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoomDetails.getPublic_transport().equals(roomDetails.getPublic_transport())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_PUBLIC_TRANSPORT, false, roomDetails.getPublic_transport(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoomDetails.getHost_review().equals(roomDetails.getHost_review())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_HOST_REVIEW, false, roomDetails.getHost_review(), id)) {
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

    public RoomDetailsPojo updateConfirmation(RoomDetailsPojo newRoomDetails) {
        RoomDetailsPojo previousRoomDetails = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement_select = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, newRoomDetails.getId());
                ResultSet resultSet = statement_select.executeQuery();) {

            if (resultSet.next()) {
                previousRoomDetails = map(resultSet);
            } else {
                return newRoomDetails;
            }

            if (!previousRoomDetails.getConfirmation().equals(newRoomDetails.getConfirmation())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_CONFIRMATION, false, newRoomDetails.getConfirmation(), newRoomDetails.getId())) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }
            previousRoomDetails.setConfirmation(newRoomDetails.getConfirmation());
            return previousRoomDetails;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return newRoomDetails;
        }
    }

}
