package edu.sinchuk.kurs.views;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import edu.sinchuk.kurs.controllers.queries.UserQuery;
import edu.sinchuk.kurs.models.entities.OrderEntity;
import edu.sinchuk.kurs.models.entities.UserEntity;
import edu.sinchuk.kurs.models.services.DataBaseConnection;
import edu.sinchuk.kurs.views.components.UserComponent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * !! DO NOT EDIT THIS FILE !!
 * <p>
 * This class is generated by Vaadin Designer and will be overwritten.
 * <p>
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class AdminView extends VerticalLayout implements View {

    final static public String NAME = "admin";

    private static final Logger logger = Logger.getLogger("AdminView");

    protected Button back;

    public AdminView() {
        Design.read(this);

        setWidthUndefined();
        setHeightUndefined();

        DataBaseConnection connection = new DataBaseConnection();
        try {
            UserQuery userQuery = new UserQuery(connection.connect());
            List<UserEntity> ordersEntities = userQuery.selectAll();
            ordersEntities.forEach(orderEntity -> {
                addComponent(new UserComponent(orderEntity));
            });
        } catch (ClassNotFoundException e) {
            addComponent(new Label("Ошибка соединения с базой!!!"));
        } catch (SQLException e) {
            addComponent(new Label("Ошибка запроса к базе!!!"));
        }

        back.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().getNavigator().addView(LoginView.NAME,new LoginView());
                UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }
}
