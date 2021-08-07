package com.groupone.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

        Log.i("info", SingletonUserStore.getUser().getFirstName());
    }
}