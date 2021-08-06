package com.groupone.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.groupone.projectapp.Classes.Appointment;
import com.groupone.projectapp.Classes.Doctor;
import com.groupone.projectapp.Classes.User;

public class DashboardDoctorActivity extends AppCompatActivity {

    private Doctor currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String email = intent.getStringExtra(MainActivity.EMAIL);

        DatabaseReference refDoc = FirebaseDatabase.getInstance().getReference().child("Users").child("Doctors").child(User.getID(email));

        refDoc.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentUser = dataSnapshot.getValue(Doctor.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled", databaseError.toException());
            }
        });

        TextView textView = findViewById(R.id.textView4);
        textView.setText("Welcome, " + currentUser.getFirstName());

        Appointment latestApp = currentUser.getLatestAppointment();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void goToProfile(View view) {

    }


}