package com.groupone.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.groupone.projectapp.Classes.SingletonUserStore;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView textView = (TextView) findViewById(R.id.textView4);
        textView.setText("Welcome, " + SingletonUserStore.getUser().getFirstName());

        Log.i("info", SingletonUserStore.getUser().getFirstName());
    }
}