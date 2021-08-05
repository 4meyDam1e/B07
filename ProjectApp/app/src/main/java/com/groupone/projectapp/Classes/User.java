package com.groupone.projectapp.Classes;

public class User {
    private String username;
    private String email;
    private String name;
    private String gender;

    User(String username, String email, String name){
        this.username = username;
        this.email = email;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNames(String name) {
        this.name = name;
    }

    public void setUsernames(String username) {
        this.username = username;
    }
}