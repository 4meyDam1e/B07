package com.groupone.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.groupone.projectapp.Classes.Doctor;
import com.groupone.projectapp.Classes.Patient;
import com.groupone.projectapp.Classes.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void signup(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        User d1 = new Doctor("d1","abc@123.com","charlie", "male", "123456");
        ref.child("Users").child("Doctors").child(d1.getUsername()).setValue(d1);
        User p1 = new Patient("p1","abc@123.com","jack", "male", "123456", "789");
        ref.child("Users").child("Patients").child(p1.getUsername()).setValue(p1);
    }

    public void testAppointments(View view) {
        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }

}