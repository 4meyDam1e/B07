package com.groupone.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.groupone.projectapp.Classes.Appointment;
import com.groupone.projectapp.Classes.Doctor;
import com.groupone.projectapp.Classes.Patient;

import java.util.ArrayList;

public class AppointmentsActivity extends AppCompatActivity {

    private static final String TAG = "AppointmentsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
//        Log.d(TAG, "onCreate: Started");
//        ListView myListView = (ListView) findViewById(R.id.listView);
//
//        Doctor d1 = new Doctor("doctor1", "doc1@gmail.com", "Doctor1", new ArrayList<String>(), new ArrayList<String>(), new ArrayList<Integer>());
//        Patient p1 = new Patient("patient1", "pat1@gmail.com",  "Patient1", 1234567890, new ArrayList<String>());
//        Patient p2 = new Patient("patient2", "pat2@gmail.com",  "Patient2", 1234567890, new ArrayList<String>());
//        Patient p3 = new Patient("patient3", "pat3@gmail.com",  "Patient3", 1234567890, new ArrayList<String>());
//        Patient p4 = new Patient("patient4", "pat4@gmail.com",  "Patient4", 1234567890, new ArrayList<String>());
//        Patient p5 = new Patient("patient5", "pat5@gmail.com",  "Patient5", 1234567890, new ArrayList<String>());
//        Patient p6 = new Patient("patient6", "pat6@gmail.com",  "Patient6", 1234567890, new ArrayList<String>());
//        Patient p7 = new Patient("patient7", "pat7@gmail.com",  "Patient7", 1234567890, new ArrayList<String>());
//        Patient p8 = new Patient("patient8", "pat8@gmail.com",  "Patient8", 1234567890, new ArrayList<String>());
//        Patient p9 = new Patient("patient9", "pat9@gmail.com",  "Patient9", 1234567890, new ArrayList<String>());
//        Patient p10 = new Patient("patient10", "pat10@gmail.com",  "Patient10", 1234567890, new ArrayList<String>());
//        Patient p11 = new Patient("patient11", "pat11@gmail.com",  "Patient11", 1234567890, new ArrayList<String>());
//        Patient p12 = new Patient("patient12", "pat12@gmail.com",  "Patient12", 1234567890, new ArrayList<String>());
//
//        Appointment a1 = new Appointment(1, d1, p1);
//        Appointment a2 = new Appointment(2, d1, p2);
//        Appointment a3 = new Appointment(3, d1, p3);
//        Appointment a4 = new Appointment(4, d1, p4);
//        Appointment a5 = new Appointment(5, d1, p5);
//        Appointment a6 = new Appointment(6, d1, p6);
//        Appointment a7 = new Appointment(7, d1, p7);
//        Appointment a8 = new Appointment(8, d1, p8);
//        Appointment a9 = new Appointment(9, d1, p9);
//        Appointment a10 = new Appointment(10, d1, p10);
//        Appointment a11 = new Appointment(11, d1, p11);
//        Appointment a12 = new Appointment(12, d1, p12);
//
//        ArrayList<Appointment> appointmentsList = new ArrayList<Appointment>();
//        appointmentsList.add(a1);
//        appointmentsList.add(a2);
//        appointmentsList.add(a3);
//        appointmentsList.add(a4);
//        appointmentsList.add(a5);
//        appointmentsList.add(a6);
//        appointmentsList.add(a7);
//        appointmentsList.add(a8);
//        appointmentsList.add(a9);
//        appointmentsList.add(a10);
//        appointmentsList.add(a11);
//        appointmentsList.add(a12);
//
//        AppointmentsListAdapter adapter = new AppointmentsListAdapter(this, R.layout.adapter_appointments, appointmentsList);
//        myListView.setAdapter(adapter);
    }
}