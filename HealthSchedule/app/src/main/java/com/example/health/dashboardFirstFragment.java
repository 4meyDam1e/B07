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

public class dashboardFirstFragment extends dashboardFragment {
    ListView listView;
    ListView listViewPast;
    List<Appointment> appointments;

    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage(message)
                .setTitle("Message")
                .setPositiveButton("OK", null);
        builder.create().show();
    }

    public dashboardFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void update(User user)
    {
        if (view == null) { tag = user; return; }
        List<Integer> upcomingID = new ArrayList<>();
        List<Integer> pastID = new ArrayList<>();
        if (user.getIdentity() == "patient")
            appointments = ((Patient) user).appointmentList();
        else
            appointments = ((Doctor) user).appointmentList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1);
        ArrayAdapter<String> adapterPast = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1);
        for (int i = 0; i < appointments.size(); i += 1) {
            Appointment a = appointments.get(i);
            ArrayAdapter<String> x;
            List<Integer> y;
            if (a.done()) {
                x = adapterPast;
                y = pastID;
            }
            else {
                x = adapter;
                y = upcomingID;
            }
            y.add(i);
            if (user.getIdentity() == "patient")
                x.add("Dr." + a.getDoctorInfo().name() + "    " + a.timeToString() +
                        "\n" + a.dateToString());
            else
                x.add(a.getPatientInfo().name() + "    " + a.timeToString() +
                        "\n" + a.dateToString());
        }
        listView.setAdapter(adapter);
        listViewPast.setAdapter(adapterPast);
        if (user.getIdentity() == "doctor") {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int j = upcomingID.get(i);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                            .child("Users")
                            .child(User.getID(appointments.get(j).getPatientInfo().getEmail()));
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.getValue() != null) {
                                Patient p = snapshot.getValue(Patient.class);
                                String msg = "Name: " + p.name() + "\n"
                                        + "Gender: " + p.getGender() + "\n"
                                        + "Birthday: " + p.getBirthday() + "\n"
                                        + "Health Card: " + p.getHealthCard() + "\n"
                                        + "Previous Doctor list:\n";
                                List<String> dl = p.previousDoctorList();
                                for (int i = 0; i < dl.size() - 1; i += 1)
                                    msg = msg + dl.get(i) + ", ";
                                if (dl.size() > 0)
                                    msg = msg + dl.get(dl.size() - 1);
                                showMessage(msg);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            System.out.println("Database read failed: " + error.getCode());
                        }
                    });
                }
            });
            listViewPast.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int j = pastID.get(i);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                            .child("Users")
                            .child(User.getID(appointments.get(j).getPatientInfo().getEmail()));
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.getValue() != null) {
                                Patient p = snapshot.getValue(Patient.class);
                                String msg = "Name: " + p.name() + "\n"
                                        + "Gender: " + p.getGender() + "\n"
                                        + "Birthday: " + p.getBirthday() + "\n"
                                        + "Health Card: " + p.getHealthCard() + "\n"
                                        + "Previous Doctor list:\n";
                                List<String> dl = p.previousDoctorList();
                                for (int i = 0; i < dl.size() - 1; i += 1)
                                    msg = msg + dl.get(i) + ", ";
                                if (dl.size() > 0)
                                    msg = msg + dl.get(dl.size() - 1);
                                showMessage(msg);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            System.out.println("Database read failed: " + error.getCode());
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dashboard_first, container, false);
        listView = view.findViewById(R.id.df_listViewAppointments);
        listViewPast = view.findViewById(R.id.df_listViewPastAppointments);
        if (tag != null) {
            update(tag);
            tag = null;
        }
        return view;
    }
}