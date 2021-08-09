package com.example.health.Classes;

public abstract class UserInfo {
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private int password; // hashed

    public UserInfo() {
        email = "";
        firstName = "";
        lastName = "";
        gender = "";
        password = 0;
    }

    public UserInfo(String email, String firstName, String lastName,
                    String gender, int password){
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

    public int getPassword() {
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

    public void setPassword(int password) {
        this.password = password;
    }
}
