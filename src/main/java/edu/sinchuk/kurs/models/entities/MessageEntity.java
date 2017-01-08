package edu.sinchuk.kurs.models.entities;

/**
 * Created by rad10k1tty on 25.12.2016.
 */
public class MessageEntity {

    private int messageId;
    private String messageText;
    private int fkOrderId;
    private int fkSenderId;
    private int fkReceiverId;

    public MessageEntity() {}

    public MessageEntity(int messageId, String messageText, int fkOrderId, int fkSenderId, int fkReceiverId) {
        this.messageId = messageId;
        this.messageText = messageText;
        this.fkOrderId = fkOrderId;
        this.fkSenderId = fkSenderId;
        this.fkReceiverId = fkReceiverId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getFkOrderId() {
        return fkOrderId;
    }

    public void setFkOrderId(int fkOrderId) {
        this.fkOrderId = fkOrderId;
    }

    public int getFkSenderId() {
        return fkSenderId;
    }

    public void setFkSenderId(int fkSenderId) {
        this.fkSenderId = fkSenderId;
    }

    public int getFkReceiverId() {
        return fkReceiverId;
    }

    public void setFkReceiverId(int fkReceiverId) {
        this.fkReceiverId = fkReceiverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (messageId != that.messageId) return false;
        if (fkOrderId != that.fkOrderId) return false;
        if (fkSenderId != that.fkSenderId) return false;
        if (fkReceiverId != that.fkReceiverId) return false;
        return messageText.equals(that.messageText);
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + messageText.hashCode();
        result = 31 * result + fkOrderId;
        result = 31 * result + fkSenderId;
        result = 31 * result + fkReceiverId;
        return result;
    }
}
