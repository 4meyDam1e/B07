package com.groupone.projectapp.Classes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Doctor extends User {
    private ArrayList<String> proficiencies;
    private ArrayList<Patient> previousPatients;
    private ArrayList<Appointment> upcomingAppointments;
    private ArrayList<Integer> availableTimeslots;

    public Doctor()
    {
        // Default constructor required for calls to DataSnapshot.getValue(Patient.class)
    }


    public Doctor(String email, String firstName, String lastName, String gender, String password) {
        super(email, firstName, lastName, gender, password);
        this.proficiencies = new ArrayList<String>();
        this.previousPatients = new ArrayList<Patient>();
        this.upcomingAppointments = new ArrayList<Appointment>();
        this.availableTimeslots = new ArrayList<Integer>();
    }

    public ArrayList<Patient> getPreviousPatients() {
        return previousPatients;
    }

    public ArrayList<String> getProficiencies() {
        return proficiencies;
    }

    public ArrayList<Appointment> getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public void setPreviousPatients(ArrayList<Patient> previousPatients) {
        this.previousPatients = previousPatients;
    }

    public void setProficiencies(ArrayList<String> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public void setAvailableTimeslots(ArrayList<Integer> availableTimeslots) {
        this.availableTimeslots = availableTimeslots;
    }

    public List<Integer> getAvailableTimeslots() {
        return availableTimeslots;
    }

    public void setUpcomingAppointments(ArrayList<Appointment> upcomingAppointments) {
        this.upcomingAppointments = upcomingAppointments;
    }

    public void createAppointment(Patient patient, int timeslot) throws IllegalArgumentException{
        if(!availableTimeslots.contains(timeslot)){
            throw new IllegalArgumentException("timeslot not available");
        }

        Appointment appointment = new Appointment(timeslot, this, patient);
        this.upcomingAppointments.add(appointment);
        this.availableTimeslots.remove(Integer.valueOf(timeslot));
    }


    public void removeAppointment(Appointment appointment){
        if (appointment.getCompleted()){
            this.previousPatients.add(appointment.getPatient());
        }

        int timeslot = appointment.getTimeslotStartTime();
        this.upcomingAppointments.remove(appointment);
        this.availableTimeslots.add(timeslot);
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

    @Override
    public void writeDB()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Users").child("Doctors").child(User.getID(getEmail())).setValue(this);
    }


}
