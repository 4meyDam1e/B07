package com.groupone.projectapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.groupone.projectapp.Classes.Doctor;
import com.groupone.projectapp.Classes.Patient;
import com.groupone.projectapp.Classes.User;

public class MainActivity extends AppCompatActivity {

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void getInput() {
        EditText emailEt = findViewById(R.id.email_input);
        EditText passwordEt = findViewById(R.id.password_input);
        String emailInput = emailEt.getText().toString();
        String passwordInput = passwordEt.getText().toString();
        email = emailInput;
        password = passwordInput;
    }

    public void signup(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        getInput();
        verifyUserAndPassword();
    }

    private void verifyUserAndPassword() {
        DatabaseReference refDoc = FirebaseDatabase.getInstance().getReference().child("Users").child("Doctors");
        DatabaseReference refP = FirebaseDatabase.getInstance().getReference().child("Users").child("Patients");

        refP.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    Patient patient = child.getValue(Patient.class);
                    if(patient.getEmail().equals(email)) {
                        Log.i("info", "email equals");
                        if(patient.getPassword().equals(password)) {
                            Log.i("info", "pass equals");
                            //resultPatient = true;
                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            return;
                        }
                    }
                }
                //Toast.makeText(MainActivity.this,"Incorrect email or password!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled", databaseError.toException());
            }
        });
        Log.i("info", "---------");
        refDoc.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    Doctor doctor = child.getValue(Doctor.class);
                    if(doctor.getEmail().equals(email)) {
                        Log.i("info", "email equals");
                        if(doctor.getPassword().equals(password)) {
                            Log.i("info", "pass equals");
                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            return;
                        }
                    }
                }
                Toast.makeText(MainActivity.this,"Incorrect email or password!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    public void testAppointments(View view) {
        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }

}