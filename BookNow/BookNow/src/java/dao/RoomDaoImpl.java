package dao;

import db.ConnectionFactory;
import db.DAOUtil;
import entities.RoomPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RoomDaoImpl implements RoomDao {

    // --------------------------------------------------                                                                                                                                                           
    //                  SQL Statements
    // --------------------------------------------------
    private static final String SQL_LIST_IDS = "SELECT id FROM Room";
    private static final String SQL_SEARCH = "SELECT * FROM Room where ";
    private static final String SQL_BASIC_SEARCH = "SELECT * FROM Room r inner join Offer o on r.id = o.room_id where o.date_from <= ? and o.date_to >= ? and (r.description like ? or r.address like ?) and (r.max_people >= ?) and not exists (select * from Offer o2 inner join Rent re on o2.room_id = re.room_id where re.room_id = r.id  and ((re.date_from >= ? and re.date_from <= ?) or (re.date_to >= ? and re.date_to <= ?))) order by r.max_cost desc";
    private static final String SQL_ADVANCED_SEARCH = "SELECT * FROM Room r inner join RoomDetails rd on rd.id = r.room_details_id inner join Offer o on r.id = o.room_id where o.date_from <= ? and o.date_to >= ? and (r.description like ? or r.address like ?) and (r.max_people >= ?) and not exists (select * from Offer o2 inner join Rent re on o2.room_id = re.room_id where re.room_id = r.id  and ((re.date_from >= ? and re.date_from <= ?) or (re.date_to >= ? and re.date_to <= ?))) and r.max_cost <= ? ";
    private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM Room ORDER BY id";
    private static final String SQL_LIST_ACTIVE_BY_OWNER_ID = "SELECT * FROM Room r where r.owner_id = ? and exists (select * from Rent re where re.room_id = r.id and (re.date_from >= now() or re.date_to >= now())) order by r.area desc";
    private static final String SQL_LIST_INACTIVE_BY_OWNER_ID = "SELECT * FROM Room r where r.owner_id = ? and not exists (select * from Rent re where re.room_id = r.id and (re.date_from >= now() or re.date_to >= now())) order by r.area desc";
    private static final String SQL_LIST_BY_RENTER_ID = "SELECT * FROM Room r where r.id in (select re.room_id from User u inner join UserHasRent h on u.id = h.user_id inner join Rent re on h.rent_id = re.id where u.id = ?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM Room WHERE id = ?";
    private static final String SQL_REC_FIND_BY_ID = "SELECT * FROM Room r inner join RoomDetails rd on rd.id = r.room_details_id inner join Offer o on r.id = o.room_id WHERE r.id = ?";
    
    private static final String SQL_INSERT = "INSERT INTO rentals.Room ("
            + "owner_id,"
            + "room_details_id,"
            + "room_type,"
            + "max_cost,"
            + "address,"
            + "area,"
            + "latitude,"
            + "longitude,"
            + "max_people,"
            + "min_cost,"
            + "extra_cost_per_person,"
            + "thumbnail_url,"
            + "description,"
            + "bathrooms,"
            + "bedrooms,"
            + "living_room,"
            + "kitchen"
            + ") VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

    private static final String SQL_UPDATE_MAX_COST = "UPDATE Room set max_cost = ? WHERE id = ?";
    private static final String SQL_UPDATE_MAX_PEOPLE = "UPDATE Room set max_people = ? WHERE id = ?";
    private static final String SQL_UPDATE_MIN_COST = "UPDATE Room set min_cost = ? WHERE id = ?";
    private static final String SQL_UPDATE_EXTRA_COST_PER_PERSON = "UPDATE Room set extra_cost_per_person = ? WHERE id = ?";
    private static final String SQL_UPDATE_THUMBNAIL_URL = "UPDATE Room set thumbnail_url = ? WHERE id = ?";
    private static final String SQL_UPDATE_DESCRIPTION = "UPDATE Room set description = ? WHERE id = ?";
    private static final String SQL_UPDATE_BATHROOMS = "UPDATE Room set bathrooms = ? WHERE id = ?";
    private static final String SQL_UPDATE_BEDROOMS = "UPDATE Room set bedrooms = ? WHERE id = ?";
    private static final String SQL_UPDATE_LIVING_ROOM = "UPDATE Room set living_room = ? WHERE id = ?";
    private static final String SQL_UPDATE_KITCHEN = "UPDATE Room set kitchen = ? WHERE id = ?";

    protected ConnectionFactory factory;

    public RoomDaoImpl(boolean pool) {
        factory = ConnectionFactory.getInstance(pool);
    }

    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // convert database row to database entity
    private static RoomPojo map(ResultSet resultSet) throws SQLException {
        RoomPojo room = new RoomPojo();
        room.setId(resultSet.getLong("id"));
        room.setOwner_id(resultSet.getLong("owner_id"));
        room.setRoom_details(resultSet.getLong("room_details_id"));
        room.setRoom_type(resultSet.getString("room_type"));

        if (resultSet.getString("max_cost") != null) {
            room.setMax_cost(resultSet.getInt("max_cost"));
        }

        room.setAddress(resultSet.getString("address"));

        if (resultSet.getString("area") != null) {
            room.setArea(resultSet.getInt("area"));
        }

        room.setLatitude(resultSet.getString("latitude"));
        room.setLongitude(resultSet.getString("longitude"));

        if (resultSet.getString("max_people") != null) {
            room.setMax_people(resultSet.getInt("max_people"));
        }

        if (resultSet.getString("min_cost") != null) {
            room.setMin_cost(resultSet.getInt("min_cost"));
        }

        if (resultSet.getString("extra_cost_per_person") != null) {
            room.setExtra_cost_per_person(resultSet.getInt("extra_cost_per_person"));
        }

        room.setThumbnail_url(resultSet.getString("thumbnail_url"));
        room.setDescription(resultSet.getString("description"));

        if (resultSet.getString("bathrooms") != null) {
            room.setBathrooms(resultSet.getInt("bathrooms"));
        }

        if (resultSet.getString("bedrooms") != null) {
            room.setBedrooms(resultSet.getInt("bedrooms"));
        }

        room.setLiving_room(resultSet.getBoolean("living_room"));
        room.setKitchen(resultSet.getBoolean("kitchen"));

        return room;
    }

    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // select all lines
    public List<RoomPojo> list() {
        List<RoomPojo> rooms = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                RoomPojo room = map(resultSet);
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rooms;
    }

    // select by primary key
    public RoomPojo find(Long id) {
        RoomPojo room = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, id);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                room = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return room;
    }

    @Override
    public RoomPojo recfind(Long id) {
        RoomPojo room = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_REC_FIND_BY_ID, false, id);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                room = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return room;
    }
    
    // --------------------------------------------------
    //                      INSERT 
    // --------------------------------------------------
    public Long insert(RoomPojo pojo) {

        Object[] values = new Object[]{
            pojo.getOwner_id(),
            pojo.getRoom_details(),
            pojo.getRoom_type(),
            pojo.getMax_cost(),
            pojo.getAddress(),
            pojo.getArea(),
            pojo.getLatitude(),
            pojo.getLongitude(),
            pojo.getMax_people(),
            pojo.getMin_cost(),
            pojo.getExtra_cost_per_person(),
            pojo.getThumbnail_url(),
            pojo.getDescription(),
            pojo.getBathrooms(),
            pojo.getBedrooms(),
            pojo.getLiving_room(),
            pojo.getKitchen()
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
    //                      UPADATE 
    // --------------------------------------------------
    public void update_room(RoomPojo room) {
        RoomPojo previousRoom = null;
        Long id = room.getId();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement_select = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, id);
                ResultSet resultSet = statement_select.executeQuery();) {

            if (resultSet.next()) {
                previousRoom = map(resultSet);
            } else {
                return;
            }

            if (!previousRoom.getMax_cost().equals(room.getMax_cost())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_MAX_COST, false, room.getMax_cost(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoom.getMax_people().equals(room.getMax_people())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_MAX_PEOPLE, false, room.getMax_people(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoom.getMin_cost().equals(room.getMin_cost())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_MIN_COST, false, room.getMin_cost(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoom.getExtra_cost_per_person().equals(room.getExtra_cost_per_person())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_EXTRA_COST_PER_PERSON, false, room.getExtra_cost_per_person(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoom.getThumbnail_url().equals(room.getThumbnail_url())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_THUMBNAIL_URL, false, room.getThumbnail_url(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoom.getDescription().equals(room.getDescription())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_DESCRIPTION, false, room.getDescription(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoom.getBathrooms().equals(room.getBathrooms())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_BATHROOMS, false, room.getBathrooms(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoom.getBedrooms().equals(room.getBedrooms())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_BEDROOMS, false, room.getBedrooms(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoom.getLiving_room().equals(room.getLiving_room())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_LIVING_ROOM, false, room.getLiving_room(), id)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousRoom.getKitchen().equals(room.getKitchen())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_KITCHEN, false, room.getKitchen(), id)) {
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

    // --------------------------------------------------
    //                      SEARCH 
    // --------------------------------------------------
    
    @Override

    public List<RoomPojo> search(String from, String to, int members, String keyword) {
        List<RoomPojo> rooms = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");

        Date datefrom;
        Date dateto;

        keyword = "%" + keyword + "%";

        try {
            datefrom = sdf.parse(from);
            dateto = sdf.parse(to);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return new ArrayList<>();
        }

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_BASIC_SEARCH, false, datefrom, dateto, keyword, keyword, members, datefrom, dateto, datefrom, dateto);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                RoomPojo room = map(resultSet);
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rooms;
    }

    @Override
    public List<RoomPojo> list(Long owner_id, boolean active) {
        List<RoomPojo> rooms = new ArrayList<>();

        try (Connection connection = factory.getConnection();) {

            PreparedStatement statement;
            if (active) {
                statement = DAOUtil.prepareStatement(connection, SQL_LIST_ACTIVE_BY_OWNER_ID, false, owner_id);
            } else {
                statement = DAOUtil.prepareStatement(connection, SQL_LIST_INACTIVE_BY_OWNER_ID, false, owner_id);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RoomPojo room = map(resultSet);
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rooms;
    }

    @Override
    public List<RoomPojo> advancedsearch(String from, String to, int total_members, String keyword, String roomtype, int max_cost, boolean tv_needed, boolean wifi_needed, boolean heat_needed, boolean aircondition_needed, boolean kitchen_needed, boolean parking_needed, boolean elevator_needed) {
        List<RoomPojo> rooms = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");

        Date datefrom;
        Date dateto;

        if (keyword != null && !keyword.isEmpty()) {
            keyword = "%" + keyword + "%";
        } else {
            keyword = "%";
        }

        if (roomtype != null && !roomtype.equals("any")) {
            roomtype = "%" + roomtype + "%";
        } else {
            roomtype = "%";
        }

        String dynamic_query = SQL_ADVANCED_SEARCH;

        try {
            datefrom = sdf.parse(from);
            dateto = sdf.parse(to);

            if (tv_needed) {
                dynamic_query += " and rd.tv = true";
            }
            if (wifi_needed) {
                dynamic_query += " and rd.wifi = true";
            }
            if (heat_needed) {
                dynamic_query += " and rd.heat = true";
            }
            if (aircondition_needed) {
                dynamic_query += " and rd.aircondition = true";
            }
            if (kitchen_needed) {
                dynamic_query += " and rd.kitchen = true";
            }
            if (parking_needed) {
                dynamic_query += " and rd.parking = true";
            }
            if (elevator_needed) {
                dynamic_query += " and rd.elevator = true";
            }
            if (roomtype != null) {
                dynamic_query += " and room_type like '" + roomtype + "'";
            }

            dynamic_query += " order by r.max_cost desc";
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return new ArrayList<>();
        }

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, dynamic_query, false, datefrom, dateto, keyword, keyword, total_members, datefrom, dateto, datefrom, dateto, max_cost);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                RoomPojo room = map(resultSet);
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rooms;
    }

    @Override
    public List<RoomPojo> listRentedRooms(Long renter_id) {
        List<RoomPojo> rooms = new ArrayList<>();

        try (Connection connection = factory.getConnection();) {

            PreparedStatement statement;
            statement = DAOUtil.prepareStatement(connection, SQL_LIST_BY_RENTER_ID, false, renter_id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RoomPojo room = map(resultSet);
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rooms;
    }

    @Override
    public List<Long> listIDs() {
        List<Long> room_ids = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_IDS);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                room_ids.add(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return room_ids;
    }

}
