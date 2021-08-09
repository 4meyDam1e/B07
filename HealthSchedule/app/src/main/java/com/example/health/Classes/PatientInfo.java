package com.example.health.Classes;

import java.util.List;

public class PatientInfo extends UserInfo{
    private String birthday;
    private String healthCard;

    public PatientInfo() {
        super();
        birthday = "";
        healthCard = "";
    }

    public PatientInfo(String email, String firstName,String lastName,
                       String gender, int password) {
        super(email, firstName, lastName, gender, password);
        birthday = "";
        healthCard = "";
    }

    public String getBirthday() {
        return birthday;
    }

    public String getHealthCard() {
        return healthCard;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setHealthCard(String healthCard) {
        this.healthCard = healthCard;
    }
}
