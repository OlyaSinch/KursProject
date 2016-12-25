package edu.sinchuk.kurs.controllers.queries;

import edu.sinchuk.kurs.models.entities.UserEntity;

import java.sql.*;

/**
 * Created by admin on 25.12.2016.
 */
public class UserQuery {

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
}
