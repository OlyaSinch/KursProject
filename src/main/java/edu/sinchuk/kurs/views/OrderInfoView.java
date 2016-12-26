package edu.sinchuk.kurs.views;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import edu.sinchuk.kurs.controllers.queries.OrderQuery;
import edu.sinchuk.kurs.models.entities.OrderEntity;
import edu.sinchuk.kurs.models.entities.UserEntity;
import edu.sinchuk.kurs.models.services.DataBaseConnection;

import java.sql.SQLException;

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
public class OrderInfoView extends FormLayout implements View {

    final static public String NAME = "order";

    private OrderEntity orderEntity;
    private UserEntity userEntity;

    protected Label title;
    protected TextArea description;
    protected TextField price;
    protected Button setOrder;
    protected Button delete;
    protected Button finished;
    protected Button back;

    public OrderInfoView(OrderEntity orderEntity) {
        Design.read(this);

        this.orderEntity = orderEntity;
        this.userEntity = (UserEntity) VaadinSession.getCurrent().getAttribute("user");
        if ((this.orderEntity.getFkDeveloperId() < 1) && (this.userEntity.getFkGroupId() == 2)) {
            setOrder.setEnabled(true);
        }
        if (this.orderEntity.getFkCreatorId() == this.userEntity.getUserId()) {
            delete.setEnabled(true);
        }
        if ((this.orderEntity.getFkDeveloperId() == this.userEntity.getUserId()) &&
                (this.orderEntity.getFkStatusId() == 2)) {
            finished.setEnabled(true);
        }

        this.title.setValue(orderEntity.getOrderTitle());
        this.description.setValue(orderEntity.getOrderTask());
        this.price.setValue(String.valueOf(orderEntity.getOrderPrice()));

        setOrder.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                DataBaseConnection connection = new DataBaseConnection();
                try {
                    OrderQuery orderQuery = new OrderQuery(connection.connect());
                    orderQuery.update(orderEntity.getOrderId(),userEntity.getUserId());
                    addComponent(new Label("Заказ принят!!!"));
                    setOrder.setEnabled(false);
                } catch (ClassNotFoundException e) {
                    addComponent(new Label("Ошибка принятия заказа!!!"));
                } catch (SQLException e) {
                    addComponent(new Label("Ошибка принятия заказа!!!"));
                }
            }
        });

        finished.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                DataBaseConnection connection = new DataBaseConnection();
                try {
                    OrderQuery orderQuery = new OrderQuery(connection.connect());
                    orderQuery.updateStatusToFinished(orderEntity.getOrderId());
                    addComponent(new Label("Заказ завершен!!!"));
                    finished.setEnabled(false);
                } catch (ClassNotFoundException e) {
                    addComponent(new Label("Ошибка завершения заказа!!!"));
                } catch (SQLException e) {
                    addComponent(new Label("Ошибка завершения заказа!!!"));
                }
            }
        });

        delete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                DataBaseConnection connection = new DataBaseConnection();
                try {
                    OrderQuery orderQuery = new OrderQuery(connection.connect());
                    orderQuery.deleteOrder(orderEntity.getOrderId());
                    UI.getCurrent().getNavigator().addView(MainView.NAME,new MainView());
                    UI.getCurrent().getNavigator().navigateTo(MainView.NAME);
                } catch (ClassNotFoundException e) {
                    addComponent(new Label("Ошибка удаления заказа!!!"));
                } catch (SQLException e) {
                    addComponent(new Label("Ошибка удаления заказа!!!"));
                }
            }
        });

        back.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().getNavigator().addView(MainView.NAME,new MainView());
                UI.getCurrent().getNavigator().navigateTo(MainView.NAME);
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
