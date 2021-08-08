package com.example.health.Classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Doctor extends User {
    private AppointmentManager appointmentManager;
    private List<Integer> schedule;

    public Doctor() {
        info = new DoctorInfo();
        appointmentManager = new AppointmentManager();
        schedule = new ArrayList<>();
    }

    public Doctor(String email, String firstName, String lastName, String gender, String password) {
        info = new DoctorInfo(email, firstName, lastName, gender, password);
        appointmentManager = new AppointmentManager();
        schedule = new ArrayList<>();
        addAppointment(new Appointment(2021, 8, 7, 13,
                (DoctorInfo) this.info,
                new PatientInfo("demail@gmail.com",
                        "Changhao",
                        "Wang",
                        "male",
                        "123456")
                ));
        addAppointment(new Appointment(2021, 8, 8, 13,
                (DoctorInfo) this.info,
                new PatientInfo("demail@gmail.com",
                        "Changhao",
                        "Wang",
                        "male",
                        "123456")
        ));
    }

    public List<Appointment> appointmentList() { return appointmentManager.appointmentList(); }

    public void addAppointment(Appointment appointment) {
        appointmentManager.addAppointment(appointment);
    }

    public Appointment getAppointment(int year, int month, int day, int time) {
        return appointmentManager.getAppointment(year, month, day, time);
    }

    public void addSchedule(int time) {
        schedule.add(time);
        Collections.sort(schedule);
    }

    public void removeSchedule(int index) {
        schedule.remove(index);
    }

    public boolean inSchedule(int time) {
        return schedule.contains(time);
    }

    public String getIdentity() { return "doctor"; }

    public String getProficiency() { return ((DoctorInfo) info).getProficiency(); }

    public AppointmentManager getAppointmentManager() { return appointmentManager; }

    public List<Integer> getSchedule() { return schedule; }

    public void setProficiency(String proficiency) {
        ((DoctorInfo) info).setProficiency(proficiency);
    }

    public void setAppointmentManager(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }

    public void setSchedule(List<Integer> schedule) {
        this.schedule = schedule;
    }
}
