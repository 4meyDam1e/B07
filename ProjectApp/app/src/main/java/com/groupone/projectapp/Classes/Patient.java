package com.groupone.projectapp.Classes;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User{
    private int healthcardNumber;
    private List<Appointment> upcomingAppointments;
    private List<Doctor> previousDoctors;

    public Patient(String username, String email, String name, int healthcardNumber, List<String> upcomingAppointments) {
        super(username, email, name);
        this.healthcardNumber = healthcardNumber;
        this.upcomingAppointments = new ArrayList<Appointment>();
        this.previousDoctors = new ArrayList<Doctor>();

    }

    public int getHealthcardNumber() {
        return healthcardNumber;
    }

    public List<Appointment> getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public List<Doctor> getPreviousDoctors() {
        return previousDoctors;
    }

    public void setHealthcardNumber(int healthcardNumber) {
        this.healthcardNumber = healthcardNumber;
    }

    public void setUpcomingAppointments(List<Appointment> upcomingAppointments) {
        this.upcomingAppointments = upcomingAppointments;
    }

    public void setPreviousDoctors(List<Doctor> previousDoctors) {
        this.previousDoctors = previousDoctors;
    }

    public void createAppointment(Doctor doctor, int timeslot) throws  IllegalArgumentException{
        Appointment appointment = new Appointment(timeslot, doctor, this);
        this.upcomingAppointments.add(appointment);
        doctor.createAppointment(this, timeslot);
    }

    public void removeAppointment(Appointment appointment){
        if(appointment.getCompleted()){
            previousDoctors.add(appointment.getDoctor());
        }

        this.upcomingAppointments.remove(appointment);
        Doctor doctor = appointment.getDoctor();
        doctor.removeAppointment(appointment);

    }

}
