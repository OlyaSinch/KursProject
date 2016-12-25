package edu.sinchuk.kurs.models.properties;

/**
 * Created by rad10k1tty on 25.12.2016.
 */
public class KursProperties {

    public static String getHost() {
        return new ApplicationProperties().getHost();
    }

    public static String getPort() {
        return new ApplicationProperties().getPort();
    }

    public static String getLogin() {
        return new ApplicationProperties().getLogin();
    }

    public static String getPassword() {
        return new ApplicationProperties().getPassword();
    }

    public static String getSid() {
        return new ApplicationProperties().getSid();
    }
}
