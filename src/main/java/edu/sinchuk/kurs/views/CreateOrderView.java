package edu.sinchuk.kurs.views;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.data.Property;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import edu.sinchuk.kurs.controllers.queries.OrderQuery;
import edu.sinchuk.kurs.models.entities.UserEntity;
import edu.sinchuk.kurs.models.services.DataBaseConnection;

import java.sql.SQLException;
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
public class CreateOrderView extends VerticalLayout implements View {

    final static public String NAME = "newOrder";

    private static final Logger logger = Logger.getLogger("CreateOrderView");

    protected Label mainTitle;
    protected TextField title;
    protected TextArea info;
    protected TextField price;
    protected Button create;
    protected Button cancel;

    public CreateOrderView(String lastView) {
        Design.read(this);

        price.addValidator(new RegexpValidator("([0-9])\\w+","Error price format..."));

        create.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                DataBaseConnection connection = new DataBaseConnection();
                try {
                    OrderQuery orderQuery = new OrderQuery(connection.connect());
                    orderQuery.insertOder(title.getValue(), info.getValue(), Integer.parseInt(price.getValue()), 1,
                            ((UserEntity) VaadinSession.getCurrent().getAttribute("user")).getUserId());
                    logger.warning("Подключение установлено!!!");
                    UI.getCurrent().getNavigator().navigateTo(lastView);
                } catch (ClassNotFoundException e) {
                    addComponent(new Label("Ошибка создания заказа!!!"));
                } catch (SQLException e) {
                    addComponent(new Label("Ошибка создания заказа!!!"));
                }
            }
        });

        cancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().getNavigator().navigateTo(lastView);
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
