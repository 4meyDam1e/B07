package com.groupone.projectapp.Classes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {
    private List<String> proficiencies;
    private List<Patient> previousPatients;
    private List<Appointment> upcomingAppointments;
    private List<Integer> availableTimeslots;


    public Doctor(String email, String firstName, String lastName, String gender, String password) {
        super(email, firstName, lastName, gender, password);
        this.proficiencies = new ArrayList<String>();
        this.previousPatients = new ArrayList<Patient>();
        this.upcomingAppointments = new ArrayList<Appointment>();
        this.availableTimeslots = new ArrayList<Integer>();
    }

    public List<Patient> getPreviousPatients() {
        return previousPatients;
    }

    public List<String> getProficiencies() {
        return proficiencies;
    }

    public List<Appointment> getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public void setPreviousPatients(List<Patient> previousPatients) {
        this.previousPatients = previousPatients;
    }

    public void setProficiencies(List<String> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public void setUpcomingAppointments(List<Appointment> upcomingAppointments) {
        this.upcomingAppointments = upcomingAppointments;
    }

    public void createAppointment(Patient patient, int timeslot) throws IllegalArgumentException{
        if(!availableTimeslots.contains(timeslot)){
            throw new IllegalArgumentException("timeslot not available");
        }

        Appointment appointment = new Appointment(timeslot, this, patient);
        this.upcomingAppointments.add(appointment);
        this.availableTimeslots.remove(timeslot);
    }


    public void removeAppointment(Appointment appointment){
        if (appointment.getCompleted()){
            this.previousPatients.add(appointment.getPatient());
        }

        int timeslot = appointment.getTimeslotStartTime();
        this.upcomingAppointments.remove(appointment);
        this.availableTimeslots.add(timeslot);
    }

    @Override
    public void writeDB()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Users").child("Doctors").child(getID()).setValue(this);
    }
}
