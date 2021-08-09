package com.example.health;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.health.Classes.Doctor;
import com.example.health.Classes.InputChecker;
import com.example.health.Classes.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profileDoctorFragment extends dashboardFragment {
    Doctor d = (Doctor) tag;

    private EditText firstname;
    private EditText lastname;
    private EditText password;
    private TextView specialist;
    private Button btn_signup;
    private Button btn_logout;
    private Button btn_delete_account;


    //private EditText specialist;


    public profileDoctorFragment() {
        // Required empty public constructor
    }

    @Override
    public void updateFocus() {
        return;
    }

    @Override
    public void update(User user) {
        if (view == null) { tag = user; return; }
        d = (Doctor) user;
        initialState(user);
        firstname.setText(d.getFirstName());
        lastname.setText(d.getLastName());
        specialist.setText(d.getProficiency());
    }

    public void initialState(User user) {
        firstname = view.findViewById(R.id.p_editTextTextFirstName);
        lastname = view.findViewById(R.id.p_editTextTextLastName);
        password = view.findViewById(R.id.p_editTextTextPassword);
        specialist = view.findViewById(R.id.p_editTextTextSpecialist);
        btn_signup = view.findViewById(R.id.p_buttonSignUp);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!InputChecker.checkName(firstname.getText().toString())) {
                    showMessage("First name format incorrect!");
                    return;
                }
                if (!InputChecker.checkName(lastname.getText().toString())) {
                    showMessage("Last name format incorrect!");
                    return;
                }
                if (!InputChecker.checkProficiency(specialist.getText().toString())) {
                    showMessage("Birthday format incorrect!");
                    return;
                }
                if (!password.getText().toString().equals("") &&
                        !InputChecker.checkPassword(password.getText().toString())) {
                    showMessage("Password format incorrect!");
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure to change the profile?")
                        .setTitle("Message")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                                        .child("Users").child(User.getID(d.getEmail()));
                                d.setFirstName(firstname.getText().toString());
                                d.setLastName(lastname.getText().toString());
                                if (!password.getText().toString().equals(""))
                                    d.setPassword(password.getText().toString());
                                d.setProficiency(specialist.getText().toString());
                                ref.setValue(d, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError error,
                                                           DatabaseReference ref) {
                                        if (error == null) {
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
        btn_delete_account = view.findViewById(R.id.p_buttonDeleteAccount);
        btn_delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                        .child("Users").child(User.getID(user.getEmail()));
                ref.removeValue();
                logout();
            }
        });
    }

    public void logout() {
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
        view = inflater.inflate(R.layout.fragment_profile_doctor, container, false);
        if (tag != null) {
            update(tag);
            tag = null;
        }
        return view;
    }
}