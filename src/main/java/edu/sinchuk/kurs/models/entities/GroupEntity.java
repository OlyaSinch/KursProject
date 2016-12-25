package edu.sinchuk.kurs.models.entities;

/**
 * Created by rad10k1tty on 25.12.2016.
 */
public class GroupEntity {

    private int groupId;
    private String groupName;

    public GroupEntity() {}

    public GroupEntity(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupEntity that = (GroupEntity) o;

        if (groupId != that.groupId) return false;
        return groupName.equals(that.groupName);
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + groupName.hashCode();
        return result;
    }
}
