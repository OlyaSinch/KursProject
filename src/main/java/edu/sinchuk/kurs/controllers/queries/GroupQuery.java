package edu.sinchuk.kurs.controllers.queries;

import edu.sinchuk.kurs.models.entities.GroupEntity;
import edu.sinchuk.kurs.models.entities.UserEntity;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.*;

/**
 * Created by admin on 25.12.2016.
 */
public class GroupQuery {

    private static Statement statement = null;
    private static Connection connection = null;
    
    public GroupQuery(Connection connection) {
        this.connection = connection;
    }

    public static GroupEntity selectUser(String login, String pass) throws SQLException {
        GroupEntity group = new GroupEntity();
        String selectUserSQL = "SELECT r_group "
                + "FROM users WHERE pk_user_stud_id = '" + login + "' AND user_pass='" + pass + "'";
        PreparedStatement prst = connection.prepareStatement(selectUserSQL);
        ResultSet rs = prst.executeQuery();

        if (rs.next()) {
            group.setGroupId(Integer.parseInt(rs.getString(1)));
            group.setGroupName(rs.getString(2));
            return group;
        } else {
            return null;
        }
    }
}
