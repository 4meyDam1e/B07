package com.groupone.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.groupone.projectapp.Classes.User;
import com.groupone.projectapp.Classes.Patient;
import com.groupone.projectapp.Classes.Doctor;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void submitRequest(View view)
    {
        EditText emailEdit = (EditText) findViewById(R.id.r_editTextTextEmailAddress);
        EditText firstNameEdit = (EditText) findViewById(R.id.r_editTextTextFirstName);
        EditText lastNameEdit = (EditText) findViewById(R.id.r_editTextTextLastName);
        RadioButton maleRadio = (RadioButton) findViewById(R.id.r_radioButtonMale);
        EditText passwordEdit = (EditText) findViewById(R.id.r_editTextTextPassword);
        EditText confirmPasswordEdit = (EditText) findViewById(R.id.r_editTextTextConfirmPassword);
        RadioButton patientRadio = (RadioButton) findViewById(R.id.r_radioButtonPatient);
        String email = emailEdit.getText().toString();
        String first = firstNameEdit.getText().toString();
        String last = lastNameEdit.getText().toString();
        String gender = "female";
        if (maleRadio.isChecked()) gender = "male";
        String password = passwordEdit.getText().toString();
        String c_password = confirmPasswordEdit.getText().toString();
        User user;
        if (patientRadio.isChecked()) user = new Patient(email, first, last, gender, password);
        else user = new Doctor(email, first, last, gender, password);
        user.writeDB();
    }
}