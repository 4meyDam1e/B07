package com.example.health.Classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Patient extends User {
    private AppointmentManager appointmentManager;

    public Patient() {
        info = new PatientInfo();
        appointmentManager = new AppointmentManager();
    }

    public Patient(String email, String firstName,
                   String lastName, String gender, String password) {
        info = new PatientInfo(email, firstName, lastName, gender, password);
        appointmentManager = new AppointmentManager();
    }

    public PatientInfo info() {
        return (PatientInfo) info;
    }

    public List<String> previousDoctorList() {
        List<String> res = new LinkedList<>();
        TreeSet<String> h = new TreeSet<>();
        List<Appointment> appointments = appointmentList();
        for (Appointment a : appointments)
            if (a.done())
                h.add(a.getDoctorInfo().name());
        for (String s : h) res.add(s);
        return res;
    }

    public List<Appointment> appointmentList() { return appointmentManager.appointmentList(); }

    public void addAppointment(Appointment appointment) {
        appointmentManager.addAppointment(appointment);
    }

    public Appointment getAppointment(int year, int month, int day, int time) {
        return appointmentManager.getAppointment(year, month, day, time);
    }

    public String getIdentity()
    {
        return "patient";
    }

    public String getBirthday() { return ((PatientInfo) info).getBirthday(); }

    public String getHealthCard() { return ((PatientInfo) info).getHealthCard(); }

    public AppointmentManager getAppointmentManager() { return appointmentManager; }

    public void setBirthday(String birthday) { ((PatientInfo) info).setBirthday(birthday); }

    public void setHealthCard(String healthCard) { ((PatientInfo) info).setHealthCard(healthCard); }

    public void setAppointmentManager(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }
}
