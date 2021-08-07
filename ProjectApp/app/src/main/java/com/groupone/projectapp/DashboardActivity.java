package com.groupone.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
<<<<<<< HEAD
import android.widget.TextView;

import com.groupone.projectapp.Classes.Appointment;
=======
import android.widget.Button;
import android.widget.TextView;

>>>>>>> a690cd2241d6711878304124ae2a18e10c07df14
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

<<<<<<< HEAD
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
=======
        globalUser = SingletonUserStore.getUser();

        //Welcome title
        TextView textView = (TextView) findViewById(R.id.welcomeTitle);
        textView.setText("Welcome, " + globalUser.getFirstName());

        //Patients/Doctors (History) button
        Button historyButton = (Button) findViewById(R.id.historyButton);

        if (globalUser instanceof Doctor)
            historyButton.setText("Past Patients");
        else if (globalUser instanceof Patient)
            historyButton.setText("Past Doctors");
>>>>>>> a690cd2241d6711878304124ae2a18e10c07df14

        Log.i("info", SingletonUserStore.getUser().getFirstName());
    }
}