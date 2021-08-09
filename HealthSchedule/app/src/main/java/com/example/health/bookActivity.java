package com.example.health;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.example.health.Classes.Appointment;
import com.example.health.Classes.Doctor;
import com.example.health.Classes.Patient;
import com.example.health.Classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class bookActivity extends AppCompatActivity {
    Patient patient;
    Doctor doctor;
    TextView genderText;
    TextView proficiencyText;
    TextView dateText;
    Button dateBtn;
    ListView listView;
    int sYear, sMonth, sDay;
    List<Integer> timeList;
    int successMessageTrigger = 0;
    ValueEventListener patientListener, doctorListener;
    DatabaseReference patientDbRef, doctorDbRef;

    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle("Message")
                .setPositiveButton("OK", null);
        builder.create().show();
    }

    public void successMessage() {
        if (successMessageTrigger == 1)
            showMessage("Success!");
        successMessageTrigger ^= 1;
    }

    public void attemptMakeAppointment(int time) {
        if (patient.getAppointment(sYear, sMonth, sDay, time) != null) {
            showMessage("You already have an appointment at this time!");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to make this appointment?")
                .setTitle("Message")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Appointment a = new Appointment(
                                sYear, sMonth, sDay, time,
                                doctor.info(),
                                patient.info()
                        );
                        patient.addAppointment(a);
                        doctor.addAppointment(a);
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                                .child("Users");
                        ref.child(User.getID(patient.getEmail())).setValue(
                                patient,
                                new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError error,
                                                           DatabaseReference ref) {
                                        if (error == null) {
                                            successMessage();
                                        }
                                        else {
                                            System.out.println("Database Error: " +
                                                    error.getCode());
                                        }
                                    }
                                }
                        );
                        ref.child(User.getID(doctor.getEmail())).setValue(
                                doctor,
                                new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError error,
                                                           DatabaseReference ref) {
                                        if (error == null) {
                                            successMessage();
                                        }
                                        else {
                                            System.out.println("Database Error: " +
                                                    error.getCode());
                                        }
                                    }
                                }
                        );
                    }
                }).setNegativeButton("Cancel", null);
        builder.create().show();
    }

    public void updateUI() {
        setTitle("Dr." + doctor.name());
        genderText.setText(doctor.getGender());
        proficiencyText.setText(doctor.getProficiency());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                                android.R.layout.simple_list_item_1);
        List<Integer> schedule = doctor.getSchedule();
        timeList = new ArrayList<>();
        for (int t : schedule)
            if (doctor.getAppointment(sYear, sMonth, sDay, t) == null) {
                adapter.add(Appointment.Convert24To12(t) + " - "
                        + Appointment.Convert24To12(t + 1));
                timeList.add(t);
            }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                attemptMakeAppointment(timeList.get(i));
            }
        });
    }

    public void showDatePicker(View view) {
        String bd = dateText.getText().toString();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            this,
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view,
                                      int year, int month, int dayOfMonth) {
                    Date date = new Date(System.currentTimeMillis());
                    if (year < date.getYear() + 1900
                            || month < date.getMonth()
                            || dayOfMonth < date.getDate())
                        showMessage("Cannot select a date before today!");
                    else {
                        sYear = year;
                        sMonth = month + 1;
                        sDay = dayOfMonth;
                        dateText.setText(
                                String.format("%04d", sYear) + "/"
                                        + String.format("%02d", sMonth) + "/"
                                        + String.format("%02d", sDay)
                        );
                        updateUI();
                    }
                }
            },
            Integer.parseInt(bd.substring(0, 4)),
            Integer.parseInt(bd.substring(5, 7)) - 1,
            Integer.parseInt(bd.substring(8, 10)));
        datePickerDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, DashboardActivity.class);
                intent.putExtra("email", patient.getEmail());
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        patientDbRef.removeEventListener(patientListener);
        doctorDbRef.removeEventListener(doctorListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        genderText = findViewById(R.id.b_textViewShowGender);
        proficiencyText = findViewById(R.id.b_textViewShowProficiency);
        dateText = findViewById(R.id.b_textViewShowDate);
        dateBtn = findViewById(R.id.b_buttonDate);
        listView = findViewById(R.id.b_listView);

        Date date = new Date(System.currentTimeMillis());
        sYear = date.getYear() + 1900;
        sMonth = date.getMonth() + 1;
        sDay = date.getDate();
        dateText.setText(String.format("%04d", sYear) + "/"
                + String.format("%02d", sMonth) + "/"
                + String.format("%02d", sDay));

        Intent intent = getIntent();
        String patientEmail = intent.getStringExtra("patientEmail");
        String doctorEmail = intent.getStringExtra("doctorEmail");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        patientDbRef = ref.child(User.getID(patientEmail));
        doctorDbRef = ref.child(User.getID(doctorEmail));
        patientDbRef.addValueEventListener(
                patientListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        patient = snapshot.getValue(Patient.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        System.out.println("Database read failed: " + error.getCode());
                    }
                }
        );
        doctorDbRef.addValueEventListener(
                doctorListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        doctor = snapshot.getValue(Doctor.class);
                        updateUI();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        System.out.println("Database read failed: " + error.getCode());
                    }
                }
        );
    }
}