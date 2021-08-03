package com.example.projectapp.Classes;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User{
    private int healthcardNumber;
    private List<String> upcomingAppointments;

    Patient(String username, String email, String name, int healthcardNumber, List<String> upcomingAppointments) {
        super(username, email, name);
        this.healthcardNumber = healthcardNumber;
        this.upcomingAppointments = new ArrayList<String>();

    }

    public int getHealthcardNumber() {
        return healthcardNumber;
    }

    public List<String> getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public void setHealthcardNumber(int healthcardNumber) {
        this.healthcardNumber = healthcardNumber;
    }

    public void setUpcomingAppointments(List<String> upcomingAppointments) {
        this.upcomingAppointments = upcomingAppointments;
    }
}
