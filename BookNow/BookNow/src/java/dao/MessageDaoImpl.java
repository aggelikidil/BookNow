package dao;

import db.ConnectionFactory;
import db.DAOUtil;
import entities.MessagePojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDaoImpl implements MessageDao {

    // --------------------------------------------------
    //                  SQL Statements
    // --------------------------------------------------
    private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM Message ORDER BY id";
    private static final String SQL_LIST_BY_SENDER_ID = "SELECT m.*, u1.email as receiver_email, u2.email as sender_email, u1.firstname as receiver_firstname,u1.lastname as receiver_lastname, u2.firstname as sender_firstname, u2.lastname as sender_lastname FROM Message m inner join User u1 on m.user_receiver = u1.id inner join User u2 on m.user_sender = u2.id where u2.id = ?";
    private static final String SQL_LIST_BY_RECEIVER_ID = "SELECT m.*, u1.email as receiver_email, u2.email as sender_email, u1.firstname as receiver_firstname,u1.lastname as receiver_lastname, u2.firstname as sender_firstname, u2.lastname as sender_lastname FROM Message m inner join User u1 on m.user_receiver = u1.id inner join User u2 on m.user_sender = u2.id where u1.id = ?";
    private static final String SQL_FIND_BY_ID = "SELECT m.*, u1.email as receiver_email, u2.email as sender_email, u1.firstname as receiver_firstname,u1.lastname as receiver_lastname, u2.firstname as sender_firstname, u2.lastname as sender_lastname FROM Message m inner join User u1 on m.user_receiver = u1.id inner join User u2 on m.user_sender = u2.id where m.id = ?";
    private static final String SQL_FIND_BY_DATE = "SELECT * FROM Message WHERE date = ?";
    private static final String SQL_INSERT = "INSERT INTO rentals.Message ("
            + "user_sender,"
            + "user_receiver,"
            + "text,"
            + "date"
            + ") VALUES ( ?, ?,?,? ) ";

    private ConnectionFactory factory;

    public MessageDaoImpl(boolean pool) {
        factory = ConnectionFactory.getInstance(pool);
    }

    // --------------------------------------------------
    //                      SELECT 
    // --------------------------------------------------
    // convert database row to database entity
    private static MessagePojo map(ResultSet resultSet) throws SQLException {
        MessagePojo message = new MessagePojo();
        message.setId(resultSet.getLong("id"));
        message.setUser_sender(resultSet.getLong("user_sender"));
        message.setUser_receiver(resultSet.getLong("user_receiver"));
        message.setText(resultSet.getString("text"));

        String sfirstname = resultSet.getString("sender_firstname");
        String slastname = resultSet.getString("sender_lastname");
        String sfullname = sfirstname + " " + slastname;

        String rfirstname = resultSet.getString("receiver_firstname");
        String rlastname = resultSet.getString("receiver_lastname");
        String rfullname = rfirstname + " " + rlastname;

        String semail = resultSet.getString("sender_email");
        String remail = resultSet.getString("receiver_email");

        message.setSender_email(semail);
        message.setReceiver_email(remail);

        message.setReceiver_name(rfullname);
        message.setSender_name(sfullname);

        if (resultSet.getString("date") != null) {
            message.setDate(resultSet.getString("date"));
        }

        return message;
    }

    public List<MessagePojo> paginationMessage(List<MessagePojo> all, int pageno, int pagesize) {
        List<MessagePojo> page = new ArrayList<>();

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
    @Override
    public List<MessagePojo> list_inbox(Long user_id) {
        List<MessagePojo> messages = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_LIST_BY_RECEIVER_ID, false, user_id);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                MessagePojo message = map(resultSet);
                messages.add(message);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return messages;
    }

    public List<MessagePojo> list_outbox(Long user_id) {
        List<MessagePojo> messages = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_LIST_BY_SENDER_ID, false, user_id);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                MessagePojo message = map(resultSet);
                messages.add(message);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return messages;
    }

    // select by primary key
    public MessagePojo find(Long message_id) {
        MessagePojo message = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_ID, false, message_id);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                message = map(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return message;
    }

    // add additional selects as needed
    public MessagePojo find(String date) {
        MessagePojo message = null;

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_BY_DATE, false, date);
                ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {

                message = map(resultSet);

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return message;
    }

    // --------------------------------------------------
    //                      INSERT 
    // --------------------------------------------------
    public Long insert(MessagePojo pojo) {
        Object[] values = new Object[]{
            pojo.getUser_sender(),
            pojo.getUser_receiver(),
            pojo.getText(),
            new Date()
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
