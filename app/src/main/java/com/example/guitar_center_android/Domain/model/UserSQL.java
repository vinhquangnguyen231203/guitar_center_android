package com.example.guitar_center_android.Domain.model;

public class UserSQL {
    private String userName;
    private String fullName;

    public UserSQL(String userName, String fullName) {
        this.userName = userName;
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "UserSQL{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
