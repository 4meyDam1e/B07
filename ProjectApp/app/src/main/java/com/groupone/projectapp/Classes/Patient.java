package com.groupone.projectapp.Classes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Patient extends User {
    private String healthCardNumber;
    private ArrayList<Appointment> upcomingAppointments;
    private ArrayList<Doctor> previousDoctors;

    public Patient()
    {
        // Default constructor required for calls to DataSnapshot.getValue(Patient.class)
    }

    public Patient(String email, String firstName, String lastName, String gender, String password)
    {
        super(email, firstName, lastName, gender, password);
        this.healthCardNumber = "";
        this.upcomingAppointments = new ArrayList<>();
        this.previousDoctors = new ArrayList<>();
    }

    public Patient(String email, String firstName, String lastName, String gender, String password, String healthCardNumber) {
        super(email, firstName, lastName, gender, password);
        this.healthCardNumber = healthCardNumber;
        this.upcomingAppointments = new ArrayList<>();
        this.previousDoctors = new ArrayList<>();
    }

    public String getHealthCardNumber() {
        return healthCardNumber;
    }

    public List<Appointment> getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public List<Doctor> getPreviousDoctors() {
        return previousDoctors;
    }

    public void setHealthCardNumber(String healthCardNumber) {
        this.healthCardNumber = healthCardNumber;
    }

    public void setUpcomingAppointments(ArrayList<Appointment> upcomingAppointments) {
        this.upcomingAppointments = upcomingAppointments;
    }

    public void setPreviousDoctors(ArrayList<Doctor> previousDoctors) {
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

    @Override
    public void writeDB()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Users").child("Patients").child(getID(getEmail())).setValue(this);
    }

    public Appointment getLatestAppointment(){
        int earliestTimeslot = 24; // timeslots range from 0 - 23
        Appointment earliestAppointment = null;

        for(Appointment appointment : upcomingAppointments){
            if(appointment.getTimeslotStartTime() < earliestTimeslot ){
                earliestTimeslot = appointment.getTimeslotStartTime();
                earliestAppointment = appointment;
            }
            ;        }

        return earliestAppointment;
    }

    public void sortAppointments(){
        Collections.sort(upcomingAppointments);
    }
}
