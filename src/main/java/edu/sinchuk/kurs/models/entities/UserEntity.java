package edu.sinchuk.kurs.models.entities;

/**
 * Created by rad10k1tty on 25.12.2016.
 */
public class UserEntity {

    private int userId;
    private String userLogin;
    private String userPassword;
    private String userName;
    private String userLastName;
    private int fkGroupId;

    public UserEntity() {}

    public UserEntity(int userId, String userLogin, String userPassword, String userName, String userLastName, int fkGroupId) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userLastName = userLastName;
        this.fkGroupId = fkGroupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public int getFkGroupId() {
        return fkGroupId;
    }

    public void setFkGroupId(int fkGroupId) {
        this.fkGroupId = fkGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (fkGroupId != that.fkGroupId) return false;
        if (!userLogin.equals(that.userLogin)) return false;
        if (!userPassword.equals(that.userPassword)) return false;
        if (!userName.equals(that.userName)) return false;
        return userLastName.equals(that.userLastName);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + userLogin.hashCode();
        result = 31 * result + userPassword.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + userLastName.hashCode();
        result = 31 * result + fkGroupId;
        return result;
    }
}
