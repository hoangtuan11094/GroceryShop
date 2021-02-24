package com.example.groceryshop.activities.entity;

public class UserEntity {
    public int idUser;
    public String fullName;
    public String email;
    public String passwordUser;

    public UserEntity() {
    }

    public UserEntity(  String email, String passwordUser) {
        this.email = email;
        this.passwordUser = passwordUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }
}
