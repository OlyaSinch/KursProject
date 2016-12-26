package edu.sinchuk.kurs.models.entities;

/**
 * Created by rad10k1tty on 25.12.2016.
 */
public class OrderEntity {

    private int orderId;
    private String orderTitle;
    private String orderTask;
    private int orderPrice;
    private int fkStatusId;
    private int fkCreatorId;
    private int fkDeveloperId;

    public OrderEntity() {}

    public OrderEntity(int orderId, String orderTitle, String orderTask, int orderPrice, int fkStatusId, int fkCreatorId, int fkDeveloperId) {
        this.orderId = orderId;
        this.orderTitle = orderTitle;
        this.orderTask = orderTask;
        this.orderPrice = orderPrice;
        this.fkStatusId = fkStatusId;
        this.fkCreatorId = fkCreatorId;
        this.fkDeveloperId = fkDeveloperId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOrderTask() {
        return orderTask;
    }

    public void setOrderTask(String orderTask) {
        this.orderTask = orderTask;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(int fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public int getFkCreatorId() {
        return fkCreatorId;
    }

    public void setFkCreatorId(int fkCreatorId) {
        this.fkCreatorId = fkCreatorId;
    }

    public int getFkDeveloperId() {
        return fkDeveloperId;
    }

    public void setFkDeveloperId(int fkDeveloperId) {
        this.fkDeveloperId = fkDeveloperId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (orderId != that.orderId) return false;
        if (orderPrice != that.orderPrice) return false;
        if (fkStatusId != that.fkStatusId) return false;
        if (fkCreatorId != that.fkCreatorId) return false;
        if (fkDeveloperId != that.fkDeveloperId) return false;
        if (!orderTitle.equals(that.orderTitle)) return false;
        return orderTask.equals(that.orderTask);
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + orderTitle.hashCode();
        result = 31 * result + orderTask.hashCode();
        result = 31 * result + orderPrice;
        result = 31 * result + fkStatusId;
        result = 31 * result + fkCreatorId;
        result = 31 * result + fkDeveloperId;
        return result;
    }
}
