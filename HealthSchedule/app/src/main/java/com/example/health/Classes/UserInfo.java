package com.example.health.Classes;

public abstract class UserInfo {
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String password;

    public UserInfo() {
        email = "";
        firstName = "";
        lastName = "";
        gender = "";
        password = "";
    }

    public UserInfo(String email, String firstName, String lastName,
                    String gender, String password){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String name() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
