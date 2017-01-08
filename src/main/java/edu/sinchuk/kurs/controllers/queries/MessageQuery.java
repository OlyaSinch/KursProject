package edu.sinchuk.kurs.controllers.queries;

import edu.sinchuk.kurs.models.entities.MessageEntity;
import edu.sinchuk.kurs.models.entities.OrderEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 25.12.2016.
 */
public class MessageQuery {

    static Connection conn = null;
    static Statement statement = null;

    public MessageQuery(Connection connection) {
        this.conn = connection;
    }

    public void insertMessage(String text, int fkSenderId, int fkReceiverId) throws SQLException {
        statement = conn.createStatement();
        String insertMessageSQL = "INSERT INTO message_table"
                + "(message_id, message_text, fk_sender_id, fk_reciever_id) " + "VALUES"
                + "(message_sequence.NEXTVAL,'" + text + "'," + fkSenderId + "," + fkReceiverId + ")";
        statement.executeUpdate(insertMessageSQL);
        conn.close();
    }

    public List<MessageEntity> selectAll() throws SQLException {
        List<MessageEntity> messages = new ArrayList<>();
        String selectOrderSQL = "SELECT * "
                + "FROM message_table";
        PreparedStatement prst = conn.prepareStatement(selectOrderSQL);
        ResultSet rs = prst.executeQuery();

        MessageEntity message;
        while (rs.next()) {
            message = new MessageEntity();
            message.setMessageId(rs.getInt(1));
            message.setMessageText(rs.getString(2));
            message.setFkOrderId(rs.getInt(3));
            message.setFkSenderId(rs.getInt(4));
            message.setFkReceiverId(rs.getInt(5));
            messages.add(message);
        }

        return messages;
    }

    public List<MessageEntity> selectBySender(int fkOrderId, int fkSenderId) throws SQLException {
        List<MessageEntity> messages = new ArrayList<>();
        String selectOrderSQL = "SELECT * "
                + "FROM message_table WHERE fk_order_id = " + fkOrderId + " AND fk_sender_id = " + fkSenderId;
        PreparedStatement prst = conn.prepareStatement(selectOrderSQL);
        ResultSet rs = prst.executeQuery();

        MessageEntity message;
        while (rs.next()) {
            message = new MessageEntity();
            message.setMessageId(rs.getInt(1));
            message.setMessageText(rs.getString(2));
            message.setFkOrderId(rs.getInt(3));
            message.setFkSenderId(rs.getInt(4));
            message.setFkReceiverId(rs.getInt(5));
            messages.add(message);
        }

        return messages;
    }

    public List<MessageEntity> selectByReciever(int fkOrderId, int fkReceiverId) throws SQLException {
        List<MessageEntity> messages = new ArrayList<>();
        String selectOrderSQL = "SELECT * "
                + "FROM message_table WHERE fk_order_id = " + fkOrderId + " AND fk_reciever_id = " + fkReceiverId;
        PreparedStatement prst = conn.prepareStatement(selectOrderSQL);
        ResultSet rs = prst.executeQuery();

        MessageEntity message;
        while (rs.next()) {
            message = new MessageEntity();
            message.setMessageId(rs.getInt(1));
            message.setMessageText(rs.getString(2));
            message.setFkOrderId(rs.getInt(3));
            message.setFkSenderId(rs.getInt(4));
            message.setFkReceiverId(rs.getInt(5));
            messages.add(message);
        }

        return messages;
    }

    public List<MessageEntity> selectBySenderAndReciever(int fkOrderId, int fkSenderId, int fkReceiverId) throws SQLException {
        List<MessageEntity> messages = new ArrayList<>();
        String selectOrderSQL = "SELECT * "
                + "FROM message_table WHERE fk_order_id = " + fkOrderId + " AND fk_sender_id = " + fkSenderId + " AND fk_reciever_id = " + fkReceiverId;
        PreparedStatement prst = conn.prepareStatement(selectOrderSQL);
        ResultSet rs = prst.executeQuery();

        MessageEntity message;
        while (rs.next()) {
            message = new MessageEntity();
            message.setMessageId(rs.getInt(1));
            message.setMessageText(rs.getString(2));
            message.setFkOrderId(rs.getInt(3));
            message.setFkSenderId(rs.getInt(4));
            message.setFkReceiverId(rs.getInt(5));
            messages.add(message);
        }

        return messages;
    }
}
