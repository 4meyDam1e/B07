package com.example.health.Classes;

public abstract class User {
    UserInfo info;

    public static String getID(String email) {
        String res = "";
        for (int i = 0; i < email.length(); i += 1)
            res = res + Integer.toHexString((int) email.charAt(i));
        return res;
    }

    public abstract String getIdentity();

    public String getEmail() {
        return info.getEmail();
    }

    public String name() {
        return info.name();
    }

    public String getFirstName() {
        return info.getFirstName();
    }

    public String getLastName() {
        return info.getLastName();
    }

    public String getGender() {
        return info.getGender();
    }

    public String getPassword() { return info.getPassword(); }

    public void setEmail(String email) {
        info.setEmail(email);
    }

    public void setFirstName(String firstName) {
        info.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        info.setLastName(lastName);
    }

    public void setGender(String gender) {
        info.setGender(gender);
    }

    public void setPassword(String password) {
        info.setPassword(password);
    }
}
