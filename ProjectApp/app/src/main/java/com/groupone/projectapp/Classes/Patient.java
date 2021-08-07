package com.groupone.projectapp.Classes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patient extends User {
<<<<<<< HEAD
    private String healthCardNumber;
    private ArrayList<Appointment> upcomingAppointments;
    private ArrayList<Doctor> previousDoctors;
=======
    private String dob;
    private List<Appointment> previousAppointments;
    private List<Appointment> upcomingAppointments;
    private List<Doctor> previousDoctors;
>>>>>>> a690cd2241d6711878304124ae2a18e10c07df14

    public Patient()
    {
        // Default constructor required for calls to DataSnapshot.getValue(Patient.class)
    }

    public Patient(String email, String firstName, String lastName, String gender, String password, String dob)
    {
        super(email, firstName, lastName, gender, password);
        this.previousAppointments = new ArrayList<>();
        this.upcomingAppointments = new ArrayList<>();
        this.previousDoctors = new ArrayList<>();

        Pattern dobPattern = Pattern.compile("[0-3][0-9]/[0-1][0-9]/[0-2][0-9]{3}");
        Matcher dobMatcher = dobPattern.matcher(dob);
        if (dobMatcher.matches())
            this.dob = dob;
    }

    public List<Appointment> getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public List<Doctor> getPreviousDoctors() {
        return previousDoctors;
    }

<<<<<<< HEAD
    public void setHealthCardNumber(String healthCardNumber) {
        this.healthCardNumber = healthCardNumber;
    }

    public void setUpcomingAppointments(ArrayList<Appointment> upcomingAppointments) {
=======
   public void setUpcomingAppointments(List<Appointment> upcomingAppointments) {
>>>>>>> a690cd2241d6711878304124ae2a18e10c07df14
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
