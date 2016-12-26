package edu.sinchuk.kurs.controllers.queries;

import com.vaadin.ui.Label;
import edu.sinchuk.kurs.models.entities.OrderEntity;
import edu.sinchuk.kurs.models.entities.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by admin on 25.12.2016.
 */
public class UserQuery {

    private static final Logger logger = Logger.getLogger("UserQuery");

    static Connection conn = null;
    static Statement statement = null;

    public UserQuery(Connection connection) {
        this.conn = connection;
    }

    public void insertUser(String login, String password, String name, String lastName, int fkGroupId) throws SQLException {
        statement = conn.createStatement();
        String insertUserSQL = "INSERT INTO user_table"
                + "(user_id, user_login, user_password, user_name, user_lastname, fk_group_id) " + "VALUES"
                + "(user_sequence.NEXTVAL,'" + login + "','" + password + "','" + name + "','" + lastName + "',"
                + fkGroupId + ")";
        statement.executeUpdate(insertUserSQL);
        conn.close();
    }

    public List<UserEntity> selectAll() throws SQLException {
        List<UserEntity> users = new ArrayList<>();
        String selectUserSQL = "SELECT * "
                + "FROM user_table";
        PreparedStatement prst = conn.prepareStatement(selectUserSQL);
        ResultSet rs = prst.executeQuery();

        UserEntity user;
        while (rs.next()) {
            user = new UserEntity();
            user.setUserId(rs.getInt(1));
            user.setUserLogin(rs.getString(2));
            user.setUserPassword(rs.getString(3));
            user.setUserName(rs.getString(4));
            user.setUserLastName(rs.getString(5));
            user.setFkGroupId(rs.getInt(6));
            users.add(user);
        }
        return users;
    }

    public UserEntity selectUser(String login, String password) throws SQLException {
        UserEntity user = new UserEntity();
        String selectUserSQL = "SELECT user_id, user_login, user_password, user_name, user_lastname, fk_group_id "
                + "FROM user_table WHERE user_login = '" + login + "' AND user_password='" + password + "'";
        PreparedStatement prst = conn.prepareStatement(selectUserSQL);
        ResultSet rs = prst.executeQuery();

        if (rs.next()) {
            user.setUserId(rs.getInt(1));
            user.setUserLogin(rs.getString(2));
            user.setUserPassword(rs.getString(3));
            user.setUserName(rs.getString(4));
            user.setUserLastName(rs.getString(5));
            user.setFkGroupId(rs.getInt(6));
            return user;
        } else {
            return null;
        }
    }

    public void updateUser(int id, String login, String password, String name, String lastName, int fkGroupId) throws SQLException {
        statement = conn.createStatement();
        String updateUserSQL = "UPDATE user_table SET user_login = '" + login + "' WHERE user_id = " + id + "";
        statement.execute(updateUserSQL);
        updateUserSQL = "UPDATE user_table SET user_password = '" + password + "' WHERE user_id = " + id + "";
        statement.execute(updateUserSQL);
        updateUserSQL = "UPDATE user_table SET user_name = '" + name + "' WHERE user_id = " + id + "";
        statement.execute(updateUserSQL);
        updateUserSQL = "UPDATE user_table SET user_lastname = '" + lastName + "' WHERE user_id = " + id + "";
        statement.execute(updateUserSQL);
        updateUserSQL = "UPDATE user_table SET fk_group_id = " + fkGroupId + " WHERE user_id = " + id + "";
        statement.execute(updateUserSQL);
    }

    public Connection deleteUser(int userId) throws SQLException {
        statement = conn.createStatement();
        conn.setAutoCommit(false);
        String delOrderSQL = "DELETE FROM user_table WHERE user_id = " + userId;
        statement.execute(delOrderSQL);
        return conn;
    }

    public Connection deleteUserWithTransation(int userId, int fkGroupId) {
        try {
            statement = conn.createStatement();
            conn.setAutoCommit(false);
            conn.setSavepoint();
            OrderQuery orderQuery = new OrderQuery(conn);
            List<OrderEntity> ordersEntities = new ArrayList<OrderEntity>();
            if (fkGroupId == 1) {
                ordersEntities = orderQuery.selectByCreator(userId);
                for (int i = 0; i < ordersEntities.size(); i++) {
                    try {
                        orderQuery.deleteOrder(ordersEntities.get(i).getOrderId());
                    } catch (SQLException e) {
                        logger.warning("Ошибка удаления!!!");
                        try {
                            conn.rollback();
                        } catch (SQLException e1) {
                            logger.warning("Ошибка отката!!!");
                        }
                    }
                }
            }
            String delOrderSQL = "DELETE FROM user_table WHERE user_id = " + userId;
            statement.execute(delOrderSQL);
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception ex) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                logger.warning("Ошибка отката!!!");
            }
        }
        return conn;
    }
}
