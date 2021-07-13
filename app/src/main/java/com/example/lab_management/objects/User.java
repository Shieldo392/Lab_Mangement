package com.example.lab_management.objects;

public class User {
    private int id_user;
    private String userName;
    private String password;
    private String codeUser; // Ma giang vien
    private String email;
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String address;
    private int type; //0: quan ly, 1: giang vien

    public User(int id_user, String userName, String password, String codeUser, String email, String fullName, String gender, String dateOfBirth, String address, int type) {
        this.id_user = id_user;
        this.userName = userName;
        this.password = password;
        this.codeUser = codeUser;
        this.email = email;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.type = type;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodeUser() {
        return codeUser;
    }

    public void setCodeUser(String codeUser) {
        this.codeUser = codeUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
