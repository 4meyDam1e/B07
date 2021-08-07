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

    ListView proficiencyListView;
    Button btnAdd, btnRemove;
    EditText editText;
    ArrayList<String> proficienciesList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        proficiencyListView = (ListView) findViewById(R.id.proficiencyListView);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        editText = (EditText) findViewById(R.id.et_proficiency);

        proficienciesList = new ArrayList<String>();

        //Create an array adapter to show our proficiencies list in a certain way.
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, proficienciesList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String proficiencyToBeAdded = editText.getText().toString();

                //Don't allow duplicate proficiencies.
                if (proficienciesList.contains(proficiencyToBeAdded)) {
                    Toast.makeText(ProfileActivity.this, "Proficiency already exists",
                            Toast.LENGTH_SHORT).show();
                }
                //Don't allow strings that are empty or only consist of whitespaces.
                else if (proficiencyToBeAdded.trim().isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "A proficiency cannot be an empty string",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    proficienciesList.add(proficiencyToBeAdded);
                    proficiencyListView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                }

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String proficiencyToBeDeleted = editText.getText().toString();

                if(!proficienciesList.contains(proficiencyToBeDeleted)) {
                    Toast.makeText(ProfileActivity.this, "No such proficiency found",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    proficienciesList.remove(proficiencyToBeDeleted);
                    proficiencyListView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
