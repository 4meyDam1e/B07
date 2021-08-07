package com.groupone.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.groupone.projectapp.Classes.Appointment;
import com.groupone.projectapp.Classes.Doctor;
import com.groupone.projectapp.Classes.Patient;
import com.groupone.projectapp.Classes.SingletonUserStore;

public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView textView = (TextView) findViewById(R.id.textView4);
        textView.setText("Welcome, " + SingletonUserStore.getUser().getFirstName() + " " +SingletonUserStore.getUser().getLastName());

        Appointment latestApp = ((Doctor) SingletonUserStore.getUser()).getLatestAppointment();
        Log.v("DSFASDFSFASDFl'l'''''AS", "timeslot: " + latestApp.getTimeslotStartTime());

        TextView appTime = (TextView) findViewById(R.id.textView7);
        TextView patEmail = (TextView) findViewById(R.id.textView8);
        TextView patFullname = (TextView) findViewById(R.id.textView9);

        //appTime.setText(latestApp.getTimeslotStartTime());
        patFullname.setText(latestApp.getPatient().getFirstName().toString() + " " + latestApp.getPatient().getLastName().toString());
        patEmail.setText(latestApp.getPatient().getEmail());

        Log.i("info", SingletonUserStore.getUser().getFirstName());
    }
}