package com.example.health;

import android.app.AlertDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.health.Classes.Appointment;
import com.example.health.Classes.Doctor;
import com.example.health.Classes.DoctorInfo;
import com.example.health.Classes.Patient;
import com.example.health.Classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class newAppointmentFragment extends dashboardFragment {

    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage(message)
                .setTitle("Message")
                .setPositiveButton("OK", null);
        builder.create().show();
    }

    public newAppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void update(User user)
    {
        if (view == null) { tag = user; return; }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_appointment, container, false);
        if (tag != null) {
            update(tag);
            tag = null;
        }
        return view;
    }
}