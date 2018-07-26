package com.kartikeya.firebasetest;

public class users {

    String userId;
    String userName;
    String password;
    String permissionType;

    public users(){

    }

    public users(String userId, String userName, String password, String permissionType) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.permissionType = permissionType;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPermissionType() {
        return permissionType;
    }
}
