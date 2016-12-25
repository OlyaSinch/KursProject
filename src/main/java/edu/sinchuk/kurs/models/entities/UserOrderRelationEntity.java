package edu.sinchuk.kurs.models.entities;

/**
 * Created by rad10k1tty on 25.12.2016.
 */
public class UserOrderRelationEntity {

    private int uorId;
    private int fkCreatorId;
    private int fkDeveloperId;
    private int fkOrderId;

    public UserOrderRelationEntity() {}

    public UserOrderRelationEntity(int uorId, int fkCreatorId, int fkDeveloperId, int fkOrderId) {
        this.uorId = uorId;
        this.fkCreatorId = fkCreatorId;
        this.fkDeveloperId = fkDeveloperId;
        this.fkOrderId = fkOrderId;
    }

    public int getUorId() {
        return uorId;
    }

    public void setUorId(int uorId) {
        this.uorId = uorId;
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

    public int getFkOrderId() {
        return fkOrderId;
    }

    public void setFkOrderId(int fkOrderId) {
        this.fkOrderId = fkOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOrderRelationEntity that = (UserOrderRelationEntity) o;

        if (uorId != that.uorId) return false;
        if (fkCreatorId != that.fkCreatorId) return false;
        if (fkDeveloperId != that.fkDeveloperId) return false;
        return fkOrderId == that.fkOrderId;
    }

    @Override
    public int hashCode() {
        int result = uorId;
        result = 31 * result + fkCreatorId;
        result = 31 * result + fkDeveloperId;
        result = 31 * result + fkOrderId;
        return result;
    }
}
