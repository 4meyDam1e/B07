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
    Button btnAdd;
    EditText editText;
    ArrayList<String> proficienciesList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        proficiencyListView = (ListView) findViewById(R.id.proficiencyListView);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        editText = (EditText) findViewById(R.id.et_proficiency);

        proficienciesList = new ArrayList<String>();

        //Create an array adapter to show our proficiencies list in a certain way.
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, proficienciesList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newProficiency = editText.getText().toString();

                //Don't allow duplicate proficiencies.
                if (proficienciesList.contains(newProficiency)) {
                    Toast.makeText(ProfileActivity.this, "Proficiency already exists",
                            Toast.LENGTH_LONG).show();
                }
                //Don't allow strings that are empty or only consist of whitespaces.
                else if (newProficiency.trim().isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "A proficiency cannot be an empty string",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    proficienciesList.add(newProficiency);
                    proficiencyListView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                }

            }
        });
    }
}
