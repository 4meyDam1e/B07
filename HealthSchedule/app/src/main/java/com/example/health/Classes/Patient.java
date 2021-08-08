package com.example.health.Classes;

import java.util.ArrayList;
import java.util.List;

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
        /*addAppointment(new Appointment(2021, 8, 7, 13,
                new DoctorInfo("demail@gmail.com",
                        "Changhao",
                        "Wang",
                        "male",
                        "123456"),
                (PatientInfo) this.info));
        addAppointment(new Appointment(2021, 8, 8, 13,
                new DoctorInfo("demail@gmail.com",
                        "Changhao",
                        "Wang",
                        "male",
                        "123456"),
                (PatientInfo) this.info));*/
    }

    public List<DoctorInfo> previousDoctorList() {
        List<DoctorInfo> res = new ArrayList<>();
        List<Appointment> appointments = appointmentList();
        for (Appointment a : appointments)
            if (a.done())
                res.add(a.getDoctorInfo());
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
