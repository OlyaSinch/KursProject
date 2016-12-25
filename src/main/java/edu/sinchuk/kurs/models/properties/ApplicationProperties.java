package edu.sinchuk.kurs.models.properties;

import com.vaadin.server.VaadinService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by rad10k1tty on 25.12.2016.
 */
public class ApplicationProperties extends Properties {

    private static final Logger logger = Logger.getLogger("ApplicationProperties");

    public ApplicationProperties() {
        String settingsPath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()  + "\\WEB-INF\\classes\\application.properties";
        logger.warning(settingsPath);
        try {
            load(new FileInputStream(new File(settingsPath)));
        } catch (IOException e) {
            logger.warning("Не удалось загрузить файл classpath:application.properties");
        }
    }

    public String getHost() {
        return getProperty("host");
    }

    public String getPort() {
        return getProperty("port");
    }

    public String getLogin() {
        return getProperty("login");
    }

    public String getPassword() {
        return getProperty("password");
    }

    public String getSid() {
        return getProperty("sid");
    }
}
