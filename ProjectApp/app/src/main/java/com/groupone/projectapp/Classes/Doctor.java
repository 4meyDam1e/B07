package com.groupone.projectapp.Classes;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User{
    private List<String> proficiencies;
    private List<String> previousPatients;
    private List<String> upcomingAppointments;
    private List<String> availableTimeslots;


    Doctor(String username, String email, String name, List<String> proficiencies, List<String> previousPatients, List<String> upcomingAppointment, List<String> availableTimeslots) {
        super(username, email, name);
        this.proficiencies = new ArrayList<String>();
        this.previousPatients = new ArrayList<String>();
        this.upcomingAppointments = new ArrayList<String>();
        this.availableTimeslots = new ArrayList<String>();
    }

    public List<String> getPreviousPatients() {
        return previousPatients;
    }

    public List<String> getProficiencies() {
        return proficiencies;
    }

    public List<String> getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public void setPreviousPatients(List<String> previousPatients) {
        this.previousPatients = previousPatients;
    }

    public void setProficiencies(List<String> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public void setUpcomingAppointments(List<String> upcomingAppointments) {
        this.upcomingAppointments = upcomingAppointments;
    }
}
