package com.example.health.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AppointmentManager {
    private Map<String, Map<String, Appointment>> appointments;

    public static String dateCode(int year, int month, int day) {
        return String.valueOf(year * 10000 + month * 100 + day);
    }

    public static String timeCode(int time) {
        return String.format("%02d", time);
    }

    public AppointmentManager() {
        appointments = new TreeMap<>();
    }

    public List<Appointment> appointmentList() {
        List<Appointment> res = new ArrayList<>();
        for (Map.Entry<String, Map<String, Appointment> > i : appointments.entrySet())
            for (Map.Entry<String, Appointment> j : i.getValue().entrySet())
                res.add(j.getValue());
        return res;
    }

    public void addAppointment(Appointment appointment) {
        String dc = dateCode(appointment.getYear(), appointment.getMonth(), appointment.getDay());
        String tc = timeCode(appointment.getTime());
        if (!appointments.containsKey(dc))
            appointments.put(dc, new TreeMap<>());
        appointments.get(dc).put(tc, appointment);
    }

    public Appointment getAppointment(int year, int month, int day, int time) {
        String dc = dateCode(year, month, day);
        String tc = timeCode(time);
        if (!appointments.containsKey(dc)) return null;
        return appointments.get(dc).get(tc);
    }

    public Map<String, Map<String, Appointment>> getAppointments() { return appointments; }

    public void setAppointments(Map<String, Map<String, Appointment>> appointments) {
        this.appointments = appointments;
    }
}
