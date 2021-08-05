package com.groupone.projectapp.Classes;

public class Appointment {
    private int timeslotStartTime;
    private Doctor doctor;
    private Patient patient;
    private boolean completed;

    public Appointment(int timeslotStartTime, Doctor doctor, Patient patient){
        this.timeslotStartTime = timeslotStartTime;
        this.doctor = doctor;
        this.patient = patient;
        this.completed = false;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public boolean getCompleted(){
        return completed;
    }

    public int getTimeslotStartTime() {
        return timeslotStartTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTimeslotStartTime(int timeslotStartTime) {
        this.timeslotStartTime = timeslotStartTime;
    }

    public void completeAppointment(){
        this.completed = true;
    }
}
