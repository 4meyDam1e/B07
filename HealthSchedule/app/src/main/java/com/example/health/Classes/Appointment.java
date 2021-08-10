package com.example.health.Classes;

import java.util.Date;

public class Appointment implements Comparable<Appointment> {
    private int year, month, day, time;
    private DoctorInfo doctorInfo;
    private PatientInfo patientInfo;

    @Override
    public int compareTo(Appointment a) {
        if (year < a.year) return -1;
        if (year > a.year) return 1;
        if (month < a.month) return -1;
        if (month > a.month) return 1;
        if (day < a.day) return -1;
        if (day > a.day) return 1;
        if (time < a.time) return -1;
        if (time > a.time) return 1;
        return 0;
    }

    public static String Convert24To12(int time) {
        String tmp;
        if (time < 12 || time == 24) tmp = "AM";
        else tmp = "PM";
        return String.format("%02d", ((time + 11) % 12 + 1)) + tmp;
    }

    public Appointment() {
        year = month = day = time = 0;
        doctorInfo = new DoctorInfo();
        patientInfo = new PatientInfo();
    }

    public Appointment(int year, int month, int day, int time,
                       DoctorInfo doctorInfo, PatientInfo patientInfo) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.doctorInfo = doctorInfo;
        this.patientInfo = patientInfo;
    }

    public boolean done() {
        Date date = new Date(System.currentTimeMillis());
        if (year - 1900 < date.getYear()) return true;
        if (year - 1900 > date.getYear()) return false;
        if (month - 1 < date.getMonth()) return true;
        if (month - 1 > date.getMonth()) return false;
        if (day < date.getDate()) return true;
        if (day > date.getDate()) return false;
        return time < date.getHours();
    }

    public String dateToString() {
        return String.format("%04d", year) + "/"
                + String.format("%02d", month) + "/"
                + String.format("%02d", day);
    }

    public String timeToString() {
        return Convert24To12(time) + " - " + Convert24To12(time + 1);
    }

    public int getYear() { return year; }

    public int getMonth() { return month; }

    public int getDay() { return day; }

    public int getTime() { return time; }

    public DoctorInfo getDoctorInfo() { return doctorInfo; }

    public PatientInfo getPatientInfo() { return patientInfo; }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) { this.month = month; }

    public void setDay(int day) { this.day = day; }

    public void setTime(int time) {
        this.time = time;
    }

    public void setDoctorInfo(DoctorInfo doctorInfo) {
        this.doctorInfo = doctorInfo;
    }

    public void setPatientInfo(PatientInfo patientInfo) {
        this.patientInfo = patientInfo;
    }
}
