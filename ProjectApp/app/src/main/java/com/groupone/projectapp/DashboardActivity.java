package com.groupone.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;

import com.groupone.projectapp.Classes.Appointment;

import android.widget.Button;
import android.widget.TextView;


import com.groupone.projectapp.Classes.Doctor;
import com.groupone.projectapp.Classes.Patient;
import com.groupone.projectapp.Classes.SingletonUserStore;
import com.groupone.projectapp.Classes.User;

public class DashboardActivity extends AppCompatActivity {
    User globalUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView textView = (TextView) findViewById(R.id.welcomeTitle);
        textView.setText("Welcome, " + SingletonUserStore.getUser().getFirstName() + " " +SingletonUserStore.getUser().getLastName());

        Appointment latestApp = ((Doctor) SingletonUserStore.getUser()).getLatestAppointment();

        TextView appTime = (TextView) findViewById(R.id.textView7);
        TextView patEmail = (TextView) findViewById(R.id.textView8);
        TextView patFullname = (TextView) findViewById(R.id.textView9);

        //appTime.setText((String)latestApp.getTimeslotStartTime());
        patFullname.setText(latestApp.getPatient().getFirstName() + " " + latestApp.getPatient().getLastName());
        patEmail.setText(latestApp.getPatient().getEmail());
        globalUser = SingletonUserStore.getUser();


        //Patients/Doctors (History) button
        Button historyButton = (Button) findViewById(R.id.historyButton);

        if (globalUser instanceof Doctor)
            historyButton.setText("Past Patients");
        else if (globalUser instanceof Patient)
            historyButton.setText("Past Doctors");

        Log.i("info", SingletonUserStore.getUser().getFirstName());
    }
}