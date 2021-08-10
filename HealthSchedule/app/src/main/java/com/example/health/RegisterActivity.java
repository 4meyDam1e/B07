package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.health.Classes.Doctor;
import com.example.health.Classes.InputChecker;
import com.example.health.Classes.Patient;
import com.example.health.Classes.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailEdit;
    private EditText firstNameEdit;
    private EditText lastNameEdit;
    private RadioButton maleRadio;
    private EditText passwordEdit;
    private EditText confirmPasswordEdit;
    private RadioButton patientRadio;
    private TextInputLayout emailLayout;
    private TextInputLayout firstnameLayout;
    private TextInputLayout lastnameLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout confirmPasswordLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEdit = findViewById(R.id.r_editTextTextEmailAddress);
        firstNameEdit = findViewById(R.id.r_editTextTextFirstName);
        lastNameEdit = findViewById(R.id.r_editTextTextLastName);
        maleRadio = findViewById(R.id.r_radioButtonMale);
        passwordEdit = findViewById(R.id.r_editTextTextPassword);
        confirmPasswordEdit = findViewById(R.id.r_editTextTextConfirmPassword);
        patientRadio = findViewById(R.id.r_radioButtonPatient);
        emailLayout = findViewById(R.id.r_emailTextField);
        firstnameLayout = findViewById(R.id.r_firstNameTextField);
        lastnameLayout = findViewById(R.id.r_lastNameTextField);
        passwordLayout = findViewById(R.id.r_passwordTextField);
        confirmPasswordLayout = findViewById(R.id.r_confirmPasswordTextField);
    }

    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle("Message")
                .setPositiveButton("OK", null);
        builder.create().show();
    }

    public void showMessageExit(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle("Message")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        builder.create().show();
    }

    public void registerNewUser(User user) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.child(User.getID(user.getEmail()))
                .setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error == null) {
                    showMessageExit("Registered successfully, please log in.");
                }
                else {
                    System.out.println("Sign up failed: " + error.getCode());
                }
            }
        });
    }

    public void submitRequest(View view)
    {
        emailLayout.setError(null);
        firstnameLayout.setError(null);
        lastnameLayout.setError(null);
        passwordLayout.setError(null);
        confirmPasswordLayout.setError(null);
        String email = emailEdit.getText().toString();
        String first = firstNameEdit.getText().toString();
        String last = lastNameEdit.getText().toString();
        String gender = "female";
        if (maleRadio.isChecked()) gender = "male";
        String password = passwordEdit.getText().toString();
        String c_password = confirmPasswordEdit.getText().toString();
        if (!InputChecker.checkEmail(email)) {
            emailLayout.setError("Email format incorrect!");
            return;
        }
        if (!InputChecker.checkName(first)) {
            firstnameLayout.setError("First name format incorrect!");
            return;
        }
        if (!InputChecker.checkName(last)) {
            lastnameLayout.setError("Last name format incorrect!");
            return;
        }
        if (!InputChecker.checkPassword(password)) {
            passwordLayout.setError("Password format incorrect!");
            return;
        }
        if (!password.equals(c_password)) {
            confirmPasswordLayout.setError("The two passwords entered do not match!");
            return;
        }
        User user;
        if (patientRadio.isChecked())
            user = new Patient(email, first, last, gender, User.hashPassword(password));
        else user = new Doctor(email, first, last, gender, User.hashPassword(password));
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(User.getID(email));
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() == null)
                    registerNewUser(user);
                else emailLayout.setError("User already exists!");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Database read failed: " + error.getCode());
            }
        });
    }
}