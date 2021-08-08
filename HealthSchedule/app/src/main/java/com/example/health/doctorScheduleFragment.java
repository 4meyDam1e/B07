package com.example.health;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.health.Classes.Appointment;
import com.example.health.Classes.Doctor;
import com.example.health.Classes.Patient;
import com.example.health.Classes.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class doctorScheduleFragment extends dashboardFragment {
    final int workStartTime = 10, workEndTime = 17;

    User tag;
    Doctor user;
    Spinner spinner;
    Button button;
    ListView listView;

    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage(message)
                .setTitle("Message")
                .setPositiveButton("OK", null);
        builder.create().show();
    }

    public void setNewSchedule() {
        int time = workStartTime + spinner.getSelectedItemPosition();
        if (user.inSchedule(time)) {
            showMessage("This timeslot is already in your schedule!");
            return;
        }
        user.addSchedule(time);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                                .child("Users").child(User.getID(user.getEmail()));
        ref.setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error == null) {
                    showMessage("Success!");
                }
                else {
                    System.out.println("Database Error: " + error.getCode());
                }
            }
        });
    }

    public doctorScheduleFragment() {
        // Required empty public constructor
    }

    public void attemptRemoveSchedule(int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Are you sure to remove this timeslot?")
                .setTitle("Message")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        user.removeSchedule(index);
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                                .child("Users").child(User.getID(user.getEmail()));
                        ref.setValue(user, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError error,
                                                   DatabaseReference ref) {
                                if (error == null) {
                                    showMessage("Success!");
                                }
                                else {
                                    System.out.println("Database Error: " +
                                            error.getCode());
                                }
                            }
                        });
                    }
                }).setNegativeButton("Cancel", null);
        builder.create().show();
    }

    @Override
    public void update(User user)
    {
        if (view == null) { tag = user; return; }
        this.user = (Doctor) user;
        List<Integer> s = this.user.getSchedule();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1);
        for (int i : s)
            adapter.add(Appointment.Convert24To12(i) + " - " +
                    Appointment.Convert24To12(i + 1));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                attemptRemoveSchedule(i);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_doctor_schedule, container, false);

        spinner = view.findViewById(R.id.ds_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item);
        for (int i = workStartTime; i < workEndTime; i += 1)
            adapter.add(Appointment.Convert24To12(i) + " - " +
                    Appointment.Convert24To12(i + 1));
        spinner.setAdapter(adapter);

        button = view.findViewById(R.id.ds_buttonSet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNewSchedule();
            }
        });

        listView = view.findViewById(R.id.ds_listViewSchedule);

        if (tag != null) {
            update(tag);
            tag = null;
        }
        return view;
    }
}