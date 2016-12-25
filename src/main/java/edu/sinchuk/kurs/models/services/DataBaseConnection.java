package edu.sinchuk.kurs.models.services;

import edu.sinchuk.kurs.models.properties.KursProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by rad10k1tty on 25.12.2016.
 */
public class DataBaseConnection {

    private Connection connection;
    final private String driver = "oracle.jdbc.driver.OracleDriver";

    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        //connection = DriverManager.getConnection("jdbc:oracle:thin:@" + KursProperties.getHost() + ":" +
            //    KursProperties.getPort() + ":" + KursProperties.getSid(),KursProperties.getLogin(),KursProperties.getPassword());
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","olyadb","olyadb");
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return connection;
    }

    public void disconnect() throws SQLException {
        connection.close();
    }
}
