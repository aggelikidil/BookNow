package dao;

import db.ConnectionFactory;
import db.DAOUtil;
import entities.UserPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {
    // --------------------------------------------------
    //                  SQL Statements
    // --------------------------------------------------

    private static final String SQL_LIST_IDS = "SELECT id FROM User";
    private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM User ORDER BY id";
    private static final String SQL_LIST_ACTIVE_ORDER_BY_ID = "SELECT * FROM User where is_approved=1 ORDER BY id";
    private static final String SQL_LIST_INACTIVE_ORDER_BY_ID = "SELECT * FROM User where is_approved=0 ORDER BY id";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM User WHERE id = ?";
    private static final String SQL_FIND_BY_USERNAME = "SELECT * FROM User WHERE username = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM User WHERE email = ?";
    private static final String SQL_INSERT = "INSERT INTO rentals.User ("
            + "username,"
            + "password,"
            + "firstname,"
            + "lastname,"
            + "email,"
            + "phonenumber,"
            + "is_admin,"
            + "is_host,"
            + "is_renter,"
            + "photo_url,"
            + "city,"
            + "country,"
            + "location,"
            + "latitude,"
            + "longitude,"
            + "is_approved"
            + ") VALUES ( ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ";

    private static final String SQL_UPDATE_FIRSTNAME = "UPDATE User set firstname = ? WHERE username = ?";
    private static final String SQL_UPDATE_LASTNAME = "UPDATE User set lastname = ? WHERE username = ?";
    private static final String SQL_UPDATE_EMAIL = "UPDATE User set email = ? WHERE username = ?";
    private static final String SQL_UPDATE_PHONENUMBER = "UPDATE User set phonenumber = ? WHERE username = ?";
    private static final String SQL_UPDATE_PHOTO_URL = "UPDATE User set photo_url = ? WHERE username = ?";
    private static final String SQL_UPDATE_CITY = "UPDATE User set city = ? WHERE username = ?";
    private static final String SQL_UPDATE_COUNTRY = "UPDATE User set country = ? WHERE username = ?";
    private static final String SQL_UPDATE_LOCATION = "UPDATE User set location = ? WHERE username = ?";
    private static final String SQL_UPDATE_IS_APPROVED = "UPDATE User set is_approved = ? WHERE id = ?";

    private ConnectionFactory factory;

    public UserDaoImpl(boolean pool) {
        factory = ConnectionFactory.getInstance(pool);
    }

    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // convert database row to database entity
    private static UserPojo map(ResultSet resultSet) throws SQLException {
        UserPojo user = new UserPojo();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstname(resultSet.getString("firstname"));
        user.setLastname(resultSet.getString("lastname"));

        if (resultSet.getString("email") != null) {
            user.setEmail(resultSet.getString("email"));
        }

        if (resultSet.getString("phonenumber") != null) {
            user.setPhonenumber(resultSet.getString("phonenumber"));
        }

        user.setIs_admin(resultSet.getBoolean("is_admin"));
        user.setIs_host(resultSet.getBoolean("is_host"));
        user.setIs_renter(resultSet.getBoolean("is_renter"));

        if (resultSet.getString("photo_url") != null) {
            user.setPhoto_url(resultSet.getString("photo_url"));
        }

        if (resultSet.getString("city") != null) {
            user.setCity(resultSet.getString("city"));
        }

        if (resultSet.getString("country") != null) {
            user.setCountry(resultSet.getString("country"));
        }

        if (resultSet.getString("location") != null) {
            user.setLocation(resultSet.getString("location"));
        }

        user.setLatitude(resultSet.getString("latitude"));
        user.setLongitude(resultSet.getString("longitude"));
        user.setIs_approved(resultSet.getBoolean("is_approved"));

        return user;
    }

    public List<UserPojo> paginationUser(List<UserPojo> all, int pageno, int pagesize) {
        List<UserPojo> page = new ArrayList<>();

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
    public List<UserPojo> list() {
        List<UserPojo> users = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                UserPojo user = map(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return users;
    }
    
    // get only user ids
    @Override
     public List<Long> listIDs() {
        List<Long> user_ids = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_IDS);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                user_ids.add(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return user_ids;
    }
    
    

    public List<UserPojo> list_active() {
        List<UserPojo> users = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ACTIVE_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                UserPojo user = map(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return users;
    }

    public List<UserPojo> list_inactive() {
        List<UserPojo> users = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_INACTIVE_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                UserPojo user = map(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return users;
    }

    // select by primary key
    public UserPojo find(Long id) {
        UserPojo user = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, id);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                user = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return user;
    }

    // add additional selects as needed
    public UserPojo find(String username) {
        UserPojo user = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_USERNAME, false, username);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                user = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return user;
    }
    
     // add additional selects as needed
    public UserPojo findByEmail(String email) {
        UserPojo user = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_EMAIL, false, email);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                user = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return user;
    }

    // --------------------------------------------------
    //                      INSERT 
    // --------------------------------------------------
    public Long insert(UserPojo pojo) {
        Object[] values = new Object[]{
            pojo.getUsername(),
            pojo.getPassword(),
            pojo.getFirstname(),
            pojo.getLastname(),
            pojo.getEmail(),
            pojo.getPhonenumber(),
            pojo.getIs_admin(),
            pojo.getIs_host(),
            pojo.getIs_renter(),
            pojo.getPhoto_url(),
            pojo.getCity(),
            pojo.getCountry(),
            pojo.getLocation(),
            pojo.getLatitude(),
            pojo.getLongitude(),
            pojo.getIs_approved()
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

    public void update(UserPojo newUser) {
        UserPojo previousUser = null;
        String username = newUser.getUsername();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement_select = DAOUtil.prepareStatement(connection, SQL_FIND_BY_USERNAME, false, username);
                ResultSet resultSet = statement_select.executeQuery();) {

            if (resultSet.next()) {
                previousUser = map(resultSet);
            } else {
                return;
            }

            if (!previousUser.getFirstname().equals(newUser.getFirstname())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_FIRSTNAME, false, newUser.getFirstname(), username)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousUser.getLastname().equals(newUser.getLastname())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_LASTNAME, false, newUser.getLastname(), username)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousUser.getEmail().equals(newUser.getEmail())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_EMAIL, false, newUser.getEmail(), username)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousUser.getPhonenumber().equals(newUser.getPhonenumber())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_PHONENUMBER, false, newUser.getPhonenumber(), username)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousUser.getPhoto_url().equals(newUser.getPhoto_url())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_PHOTO_URL, false, newUser.getPhoto_url(), username)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousUser.getCity().equals(newUser.getCity())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_CITY, false, newUser.getCity(), username)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousUser.getCountry().equals(newUser.getCountry())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_COUNTRY, false, newUser.getCountry(), username)) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }

            if (!previousUser.getLocation().equals(newUser.getLocation())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_LOCATION, false, newUser.getLocation(), username)) {
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

    public UserPojo updateActivation(UserPojo newUser) {
        UserPojo previousUser = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement_select = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, newUser.getId());
                ResultSet resultSet = statement_select.executeQuery();) {

            if (resultSet.next()) {
                previousUser = map(resultSet);
            } else {
                return newUser;
            }

            if (!previousUser.getIs_approved().equals(newUser.getIs_approved())) {
                try (
                        PreparedStatement statement_update = DAOUtil.prepareStatement(connection, SQL_UPDATE_IS_APPROVED, false, newUser.getIs_approved(), newUser.getId())) {
                    statement_update.executeUpdate();
                } catch (Exception ex) {
                    throw ex;
                }
            }
            previousUser.setIs_approved(newUser.getIs_approved());
            return previousUser;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return newUser;
        }
    }
}
