package edu.sinchuk.kurs.controllers.queries;

import edu.sinchuk.kurs.models.entities.OrderEntity;
import edu.sinchuk.kurs.models.entities.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 25.12.2016.
 */
public class OrderQuery {

    static Connection conn = null;
    static Statement statement = null;

    public OrderQuery(Connection connection) {
        this.conn = connection;
    }

    public void insertOder(String title, String task, int price, int fkStatusId, int fkCreatorId) throws SQLException {
        statement = conn.createStatement();
        String insertOderSQL = "INSERT INTO order_table"
                + "(order_id, order_title, order_task, order_price, fk_status_id, fk_creator_id) " + "VALUES"
                + "(order_sequence.NEXTVAL,'" + title + "','" + task + "'," + price + "," + fkStatusId + "," + fkCreatorId + ")";
        statement.executeUpdate(insertOderSQL);
        conn.close();
    }

    public List<OrderEntity> selectAll() throws SQLException {
        List<OrderEntity> orders = new ArrayList<>();
        String selectOrderSQL = "SELECT * "
                + "FROM order_table";
        PreparedStatement prst = conn.prepareStatement(selectOrderSQL);
        ResultSet rs = prst.executeQuery();

        OrderEntity order;
        while (rs.next()) {
            order = new OrderEntity();
            order.setOrderId(rs.getInt(1));
            order.setOrderTitle(rs.getString(2));
            order.setOrderTask(rs.getString(3));
            order.setOrderPrice(rs.getInt(4));
            order.setFkStatusId(rs.getInt(5));
            order.setFkCreatorId(rs.getInt(6));
            order.setFkDeveloperId(rs.getInt(7));
            orders.add(order);
        }

        return orders;
    }

    public OrderEntity selectOrder(int orderId) throws SQLException {
        OrderEntity order = new OrderEntity();
        String selectOrderSQL = "SELECT order_id, order_title, order_task, order_price, fk_status_id, fk_creator_id, fk_developer_id "
                + "FROM order_table WHERE order_id = " + orderId;
        PreparedStatement prst = conn.prepareStatement(selectOrderSQL);
        ResultSet rs = prst.executeQuery();

        if (rs.next()) {
            order.setOrderId(rs.getInt(1));
            order.setOrderTitle(rs.getString(2));
            order.setOrderTask(rs.getString(3));
            order.setOrderPrice(rs.getInt(4));
            order.setFkStatusId(rs.getInt(5));
            order.setFkCreatorId(rs.getInt(6));
            order.setFkDeveloperId(rs.getInt(7));
            return order;
        } else {
            return null;
        }
    }

    public void updateOrderByCreator(int orderId, String title, String task, int price, int fkStatusId) throws SQLException {
        statement = conn.createStatement();
        String updateOrderSQL = "UPDATE order_table SET order_title = '" + title + "' WHERE user_id = " + orderId + "";
        statement.execute(updateOrderSQL);
        updateOrderSQL = "UPDATE order_table SET order_task = '" + task + "' WHERE user_id = " + orderId + "";
        statement.execute(updateOrderSQL);
        updateOrderSQL = "UPDATE order_table SET order_price = " + price + " WHERE user_id = " + orderId + "";
        statement.execute(updateOrderSQL);
        updateOrderSQL = "UPDATE order_table SET fk_status_id = " + fkStatusId + " WHERE user_id = " + orderId + "";
        statement.execute(updateOrderSQL);
    }

    public void update(int orderId, int fkDeveloperId) throws SQLException {
        statement = conn.createStatement();
        String updateOrderSQL = "UPDATE order_table SET fk_developer_id = " + fkDeveloperId + " WHERE order_id = " + orderId + "";
        statement.execute(updateOrderSQL);
    }
}
