package com.example.health;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.health.Classes.InputChecker;
import com.example.health.Classes.Patient;
import com.example.health.Classes.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class profileFragment extends dashboardFragment {
    Patient p = (Patient)tag;

    private EditText firstname;
    private EditText lastname;
    private TextView birthday;
    private EditText password;
    private EditText healthCard;
    private Button btn;
    private Button btn_logout;
    private Button btn_birthday;
    private TextInputLayout firstnameLayout;
    private com.google.android.material.textfield.TextInputLayout lastnameLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout healthcardLayout;

    public profileFragment() {
        // Required empty public constructor
    }

    @Override
    public void updateFocus() {
        return;
    }

    @Override
    public void update(User user) {
        if (view == null) { tag = user; return; }
        p = (Patient) user;
        initialState();
        firstname.setText(p.getFirstName());
        lastname.setText(p.getLastName());
        if (!p.getBirthday().equals("")) birthday.setText(p.getBirthday());
        healthCard.setText(p.getHealthCard());
    }

    public void initialState() {
        firstname = view.findViewById(R.id.p_editTextTextFirstName);
        lastname = view.findViewById(R.id.p_editTextTextLastName);
        birthday = view.findViewById(R.id.p_textViewShowBirthday);
        password = view.findViewById(R.id.p_editTextTextPassword);
        healthCard = view.findViewById(R.id.p_editTextTextHealthcard);
        firstnameLayout = view.findViewById(R.id.p_firstNameTextField);
        lastnameLayout = view.findViewById(R.id.p_lastNameTextField);
        passwordLayout = view.findViewById(R.id.p_passwordTextField);
        healthcardLayout = view.findViewById(R.id.p_healthCardTextField);
        btn = view.findViewById(R.id.p_buttonSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstnameLayout.setError(null);
                lastnameLayout.setError(null);
                healthcardLayout.setError(null);
                passwordLayout.setError(null);
                if (!InputChecker.checkName(firstname.getText().toString())) {
                    firstnameLayout.setError("First name format incorrect!");
                    return;
                }
                if (!InputChecker.checkName(lastname.getText().toString())) {
                    lastnameLayout.setError("Last name format incorrect!");
                    return;
                }
                if (!InputChecker.checkHealthCard(healthCard.getText().toString())) {
                    healthcardLayout.setError("Health Card format incorrect!");
                    return;
                }
                if (!password.getText().toString().equals("") &&
                        !InputChecker.checkPassword(password.getText().toString())) {
                    passwordLayout.setError("Password format incorrect!");
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure to change the profile?")
                        .setTitle("Message")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                                        .child("Users").child(User.getID(p.getEmail()));
                                p.setFirstName(firstname.getText().toString());
                                p.setLastName(lastname.getText().toString());
                                if (!password.getText().toString().equals(""))
                                    p.setPassword(User.hashPassword(password.getText().toString()));
                                p.setBirthday(birthday.getText().toString());
                                p.setHealthCard(healthCard.getText().toString());
                                ref.setValue(p, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError error,
                                                           DatabaseReference ref) {
                                        if (error == null) {
                                            password.setText("");
                                            showMessage("Success!");
                                        }
                                        else {
                                            System.out.println("Database Error: " +
                                                    error.getCode());
                                        }
                                    }
                                });
                            }
                        }).setNegativeButton("Cancel", null);
                builder.create().show();
            }
        });
        btn_logout = view.findViewById(R.id.p_buttonLogout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        btn_birthday = view.findViewById(R.id.p_buttonBirthday);
        btn_birthday.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String bd = birthday.getText().toString();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view,
                                                  int year, int month, int dayOfMonth) {
                                birthday.setText(
                                        String.format("%04d", year) + "/"
                                        + String.format("%02d", month + 1) + "/"
                                        + String.format("%02d", dayOfMonth)
                                );
                            }
                        },
                        Integer.parseInt(bd.substring(0, 4)),
                        Integer.parseInt(bd.substring(5, 7)) - 1,
                        Integer.parseInt(bd.substring(8, 10)));
                datePickerDialog.show();
            }
        });
    }

    public void logout() {
        SharedPreferences settings = getActivity().getSharedPreferences("setting", 0);
        settings.edit().putString("email", "").commit();
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage(message)
                .setTitle("Message")
                .setPositiveButton("OK", null);
        builder.create().show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        if (tag != null) {
            update(tag);
            tag = null;
        }
        return view;
    }
}