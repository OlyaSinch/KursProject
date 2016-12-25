package edu.sinchuk.kurs.models.entities;

/**
 * Created by rad10k1tty on 25.12.2016.
 */
public class StatusEntity {

    private int statusId;
    private String statusName;

    public StatusEntity() {}

    public StatusEntity(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusEntity that = (StatusEntity) o;

        if (statusId != that.statusId) return false;
        return statusName.equals(that.statusName);
    }

    @Override
    public int hashCode() {
        int result = statusId;
        result = 31 * result + statusName.hashCode();
        return result;
    }
}
