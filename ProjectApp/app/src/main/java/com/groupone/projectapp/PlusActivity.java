package com.groupone.projectapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.groupone.projectapp.Classes.Doctor;

import java.util.ArrayList;

public class PlusActivity extends AppCompatActivity {

    private ArrayList<Doctor> allDoctors = new ArrayList<Doctor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);

       // ArrayList<Doctor> allDoctors = getAllDoctors();
        Log.v("Size", String.valueOf(allDoctors.size()));
    }


   /* private ArrayList<Doctor> getAllDoctors(){

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Doctors");
        // Read from the database

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                allDoctors.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    allDoctors.add(snapshot.getValue(Doctor.class));
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
            }
        });

        return allDoctors;*/
    }


