package edu.sinchuk.kurs.views;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import edu.sinchuk.kurs.controllers.queries.MessageQuery;
import edu.sinchuk.kurs.models.entities.MessageEntity;
import edu.sinchuk.kurs.models.entities.OrderEntity;
import edu.sinchuk.kurs.models.entities.UserEntity;
import edu.sinchuk.kurs.models.services.DataBaseConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button;

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
public class MailView extends VerticalLayout implements View {

    final static public String NAME = "mailView";

    private static final Logger logger = Logger.getLogger("MailView");

    protected VerticalLayout messagesLayout;
    protected Button back;
    protected TextArea messageText;
    protected Button sendBtn;

    public MailView(OrderEntity orderEntity) {
        Design.read(this);

        DataBaseConnection connection = new DataBaseConnection();
        try {
            MessageQuery messageQuery = new MessageQuery(connection.connect());
            List<MessageEntity> messages = messageQuery.selectByOrder(orderEntity.getOrderId());

            messages.forEach(messageEntity -> {
                if (messageEntity.getFkSenderId() == orderEntity.getFkDeveloperId()) {
                    Label message = new Label(messageEntity.getMessageText());
                    message.setCaption("Developr:");
                    messagesLayout.addComponent(message);
                } else if (messageEntity.getFkSenderId() == orderEntity.getFkCreatorId()) {
                    Label message = new Label(messageEntity.getMessageText());
                    message.setCaption("Creator:");
                    messagesLayout.addComponent(message);
                }
            });
        } catch (ClassNotFoundException e) {
            logger.warning("Ошибка получения сообщений!!!");
        } catch (SQLException e) {
            logger.warning("Ошибка получения сообщений!!!");
        }


        back.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().getNavigator().addView(MainView.NAME,new MainView());
                UI.getCurrent().getNavigator().navigateTo(MainView.NAME);
            }
        });


        sendBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                MessageEntity messageEntity = new MessageEntity();
                messageEntity.setFkOrderId(orderEntity.getOrderId());
                int userId = ((UserEntity) VaadinSession.getCurrent().getAttribute("user")).getUserId();
                if (userId == orderEntity.getFkCreatorId()) {
                    messageEntity.setFkSenderId(userId);
                    messageEntity.setFkReceiverId(orderEntity.getFkDeveloperId());
                } else if (userId == orderEntity.getFkDeveloperId()) {
                    messageEntity.setFkSenderId(userId);
                    messageEntity.setFkReceiverId(orderEntity.getFkCreatorId());
                }
                messageEntity.setMessageText(messageText.getValue());

                DataBaseConnection connection = new DataBaseConnection();
                try {
                    MessageQuery messageQuery = new MessageQuery(connection.connect());
                    messageQuery.insertMessage(messageEntity);

                    messagesLayout.removeAllComponents();

                    messageQuery = new MessageQuery(connection.connect());
                    List<MessageEntity> messages = messageQuery.selectByOrder(orderEntity.getOrderId());

                    messages.forEach(messageEnt -> {
                        if (messageEnt.getFkSenderId() == orderEntity.getFkDeveloperId()) {
                            Label message = new Label(messageEnt.getMessageText());
                            message.setCaption("Developr:");
                            messagesLayout.addComponent(message);
                        } else if (messageEnt.getFkSenderId() == orderEntity.getFkCreatorId()) {
                            Label message = new Label(messageEnt.getMessageText());
                            message.setCaption("Creator:");
                            messagesLayout.addComponent(message);
                        }
                    });
                } catch (ClassNotFoundException e) {
                    logger.warning("Ошибка отправки сообщения!!!");
                } catch (SQLException e) {
                    logger.warning("Ошибка отправки сообщения!!!");
                }
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
