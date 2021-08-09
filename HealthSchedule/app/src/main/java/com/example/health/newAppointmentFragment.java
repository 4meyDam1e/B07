package com.example.health;

import android.app.AlertDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.health.Classes.Appointment;
import com.example.health.Classes.Doctor;
import com.example.health.Classes.DoctorInfo;
import com.example.health.Classes.Patient;
import com.example.health.Classes.User;
import com.example.health.Classes.InputChecker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class newAppointmentFragment extends dashboardFragment {
    Spinner spinner;
    EditText editProficiency;
    ListView listView;
    Button listBtn;
    Button searchBtn;

    final List<String> spinnerContent = Arrays.asList("male", "female");

    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage(message)
                .setTitle("Message")
                .setPositiveButton("OK", null);
        builder.create().show();
    }

    public void getFilteredList() {
        String gender = spinner.getSelectedItem().toString();
        String proficiency = editProficiency.getText().toString();
        if (!InputChecker.checkSingleProficiency(proficiency)) {
            showMessage("Proficiency format incorrect!");
            return;
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Doctor> doctorList = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren())
                    if ("doctor".equals(child.child("identity").getValue(String.class))) {
                        Doctor d = child.getValue(Doctor.class);
                        if (!gender.equals(d.getGender())) continue;
                        if (!Doctor.proficiencyList(d.getProficiency()).contains(proficiency))
                            continue;
                        doctorList.add(d);
                    }
                for (int i = 0 ; i < doctorList.size() - 1; i += 1)
                    for (int j = i + 1; j < doctorList.size(); j += 1)
                        if (doctorList.get(i).name().compareTo(doctorList.get(j).name()) > 0)
                            Collections.swap(doctorList, i, j);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        getContext(),
                        android.R.layout.simple_list_item_1
                );
                for (Doctor d : doctorList)
                    adapter.add("Dr." + d.name());
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Database read failed: " + error.getCode());
            }
        });
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

        spinner = view.findViewById(R.id.na_spinner);
        editProficiency = view.findViewById(R.id.na_editTextProficiency);
        listView = view.findViewById(R.id.na_listView);
        listBtn = view.findViewById(R.id.na_buttonProficiencyList);
        searchBtn = view.findViewById(R.id.na_buttonSearch);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                spinnerContent);
        spinner.setAdapter(spinnerAdapter);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFilteredList();
            }
        });

        if (tag != null) {
            update(tag);
            tag = null;
        }
        return view;
    }
}