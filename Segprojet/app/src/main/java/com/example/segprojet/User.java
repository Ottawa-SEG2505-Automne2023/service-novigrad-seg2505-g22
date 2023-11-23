package com.example.segprojet;

public class User {

    private String username;
    private String email;
    private String password;
    private String TypeOfAccount;
    private String name;
    private String SuccursaleName;

    public String getSuccursaleName() {
        return SuccursaleName;
    }

    public void setSuccursaleName(String succursaleName) {
        SuccursaleName = succursaleName;
    }

    public User(String username, String email, String password, String typeOfAccount, String name, String succursaleName) {
        this.username = username;
        this.email = email;
        this.password = password;
        TypeOfAccount = typeOfAccount;
        this.name = name;
        SuccursaleName = succursaleName;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeOfAccount() {
        return TypeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        TypeOfAccount = typeOfAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
