package com.groupone.projectapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ProfileActivity extends AppCompatActivity {

    ListView specializationListView;
    Button btnAdd, btnRemove;
    EditText editText;
    ArrayList<String> specializationsList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        specializationListView = (ListView) findViewById(R.id.specializationListView);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        editText = (EditText) findViewById(R.id.et_specialization);

        specializationsList = new ArrayList<String>();

        //Create an array adapter to show our specializations list in a certain way.
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, specializationsList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String specializationToBeAdded = editText.getText().toString();

                //Don't allow duplicate specializations.
                if (specializationsList.contains(specializationToBeAdded)) {
                    Toast.makeText(ProfileActivity.this, "specialization already exists",
                            Toast.LENGTH_SHORT).show();
                }
                //Don't allow strings that are empty or only consist of whitespaces.
                else if (specializationToBeAdded.trim().isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "A specialization cannot be an empty string",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    specializationsList.add(specializationToBeAdded);
                    specializationListView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                }

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String specializationToBeDeleted = editText.getText().toString();

                if(!specializationsList.contains(specializationToBeDeleted)) {
                    Toast.makeText(ProfileActivity.this, "No such specialization found",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    specializationsList.remove(specializationToBeDeleted);
                    specializationListView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
