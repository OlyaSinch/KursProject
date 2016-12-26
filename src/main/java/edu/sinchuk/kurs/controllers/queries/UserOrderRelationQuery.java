package edu.sinchuk.kurs.controllers.queries;

import edu.sinchuk.kurs.models.entities.UserEntity;
import edu.sinchuk.kurs.models.entities.UserOrderRelationEntity;

import java.sql.*;

/**
 * Created by admin on 25.12.2016.
 */
public class UserOrderRelationQuery {

    static Connection conn = null;
    static Statement statement = null;

    public UserOrderRelationQuery(Connection connection) {
        this.conn = connection;
    }

    public void insertUserOrderRelation(int fkCreatorId, int fkOrderId) throws SQLException {
        statement = conn.createStatement();
        String insertUserOrderRelatioSQL = "INSERT INTO user_order_relation_table"
                + "(uor_id, fk_creator_id, fk_order_id) " + "VALUES"
                + "(user_order_relation_sequence.NEXTVAL," + fkCreatorId + "," + fkOrderId + ")";
        statement.executeUpdate(insertUserOrderRelatioSQL);
        conn.close();
    }

    public UserOrderRelationEntity selectUserOrderRelationById(int uorId) throws SQLException {
        UserOrderRelationEntity uor = new UserOrderRelationEntity();
        String selectUserOrderRelatioSQL = "SELECT uor_id, fk_creator_id, fk_developer_id, fk_order_id "
                + "FROM user_order_relation_table WHERE uor_id = " + uorId;
        PreparedStatement prst = conn.prepareStatement(selectUserOrderRelatioSQL);
        ResultSet rs = prst.executeQuery();

        if (rs.next()) {
            uor.setUorId(rs.getInt(1));
            uor.setFkCreatorId(rs.getInt(2));
            uor.setFkDeveloperId(rs.getInt(3));
            uor.setFkOrderId(rs.getInt(4));
            return uor;
        } else {
            return null;
        }
    }

    public UserOrderRelationEntity selectUserOrderRelationByOrderId(int fkOrderId) throws SQLException {
        UserOrderRelationEntity uor = new UserOrderRelationEntity();
        String selectUserOrderRelatioSQL = "SELECT uor_id, fk_creator_id, fk_developer_id, fk_order_id "
                + "FROM user_order_relation_table WHERE fk_order_id = " + fkOrderId;
        PreparedStatement prst = conn.prepareStatement(selectUserOrderRelatioSQL);
        ResultSet rs = prst.executeQuery();

        if (rs.next()) {
            uor.setUorId(rs.getInt(1));
            uor.setFkCreatorId(rs.getInt(2));
            uor.setFkDeveloperId(rs.getInt(3));
            uor.setFkOrderId(rs.getInt(4));
            return uor;
        } else {
            return null;
        }
    }

    public void updateUserOrderRelationByWithoutDeveloper(int uorId, int fkCreatorId, int fkOrderId) throws SQLException {
        statement = conn.createStatement();
        String updateUserOrderRelatioSQL = "UPDATE user_order_relation_table SET fk_creator_id = " + fkCreatorId + " WHERE uor_id = " + uorId + "";
        statement.execute(updateUserOrderRelatioSQL);
        updateUserOrderRelatioSQL = "UPDATE user_order_relation_table SET fk_order_id = " + fkOrderId + " WHERE uor_id = " + uorId + "";
        statement.execute(updateUserOrderRelatioSQL);
    }

    public void updateUserOrderRelation(int uorId, int fkCreatorId, int fkDeveloperId, int fkOrderId) throws SQLException {
        statement = conn.createStatement();
        String updateUserOrderRelatioSQL = "UPDATE user_order_relation_table SET fk_creator_id = " + fkCreatorId + " WHERE uor_id = " + uorId + "";
        statement.execute(updateUserOrderRelatioSQL);
        updateUserOrderRelatioSQL = "UPDATE user_order_relation_table SET fk_developer_id = " + fkDeveloperId + " WHERE uor_id = " + uorId + "";
        statement.execute(updateUserOrderRelatioSQL);
        updateUserOrderRelatioSQL = "UPDATE user_order_relation_table SET fk_order_id = " + fkOrderId + " WHERE uor_id = " + uorId + "";
        statement.execute(updateUserOrderRelatioSQL);
    }
}
