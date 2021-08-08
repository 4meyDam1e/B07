package com.groupone.projectapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.projectapp.Classes.Appointment;
import com.groupone.projectapp.Classes.Doctor;
import com.groupone.projectapp.Classes.Patient;
import com.groupone.projectapp.Classes.SingletonUserStore;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsListAdapter extends ArrayAdapter<Appointment> {
    private static final String TAG = "AppointmentsListAdapter";
    private Context mContext;
    private int mResource;

    public AppointmentsListAdapter(Context context, int resource, ArrayList<Appointment> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Create the appointment object at the given position.
        Appointment appointment = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvTimeSlotStartTime = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvPersonName = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvPatientGender = (TextView) convertView.findViewById(R.id.textView3);

        tvTimeSlotStartTime.setText("Start time: " + String.valueOf(appointment.getTimeslotStartTime()));
        //Get the info about the appointment that we want to view.
        if (SingletonUserStore.isDoctor()) {
            tvPersonName.setText("Patient Name: " + appointment.getPatient().getName());
            tvPatientGender.setText("Gender: " + appointment.getPatient().getGender());
        }
        if (SingletonUserStore.isPatient()) {
            tvPersonName.setText("Doctor Name: " + appointment.getDoctor().getName());
            tvPatientGender.setText("Gender: " + appointment.getDoctor().getGender());
        }

        return convertView;
    }
}