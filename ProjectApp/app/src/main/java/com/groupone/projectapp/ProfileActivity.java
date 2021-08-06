package com.groupone.projectapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ProfileActivity extends AppCompatActivity {
    ListView proficiencyListView;
    ArrayList<String> proficienciesSet;
    Button btnAdd;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        proficiencyListView = (ListView) findViewById(R.id.proficiencyListView);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        proficienciesSet =
    }
}
