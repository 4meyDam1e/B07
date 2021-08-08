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

import com.example.health.Classes.InputChecker;
import com.example.health.Classes.Patient;
import com.example.health.Classes.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class profileFragment extends dashboardFragment {
    User tag;
    Patient p = (Patient)tag;

    private EditText firstname;
    private EditText lastname;
    private EditText birthday;
    private EditText password;
    private EditText healthcard;
    private Button btn;
    private Button btn_logout;



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
        birthday.setText(p.getBirthday());
        healthcard.setText(p.getHealthCard());
    }

    public void initialState() {
        firstname = view.findViewById(R.id.p_editTextTextFirstName);
        lastname = view.findViewById(R.id.p_editTextTextLastName);
        birthday = view.findViewById(R.id.p_editTextTextBirthday);
        password = view.findViewById(R.id.p_editTextTextPassword);
        healthcard = view.findViewById(R.id.p_editTextTextHealthcard);
        btn = view.findViewById(R.id.p_buttonSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
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
                if (!InputChecker.checkBirthday(birthday.getText().toString())) {
                    showMessage("Birthday format incorrect!");
                    return;
                }
                if (!InputChecker.checkHealthCard(healthcard.getText().toString())) {
                    showMessage("Health Card format incorrect!");
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
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                                        .child("Users").child(User.getID(p.getEmail()));
                                p.setFirstName(firstname.getText().toString());
                                p.setLastName(lastname.getText().toString());
                                if (!password.getText().toString().equals(""))
                                    p.setPassword(password.getText().toString());
                                p.setBirthday(birthday.getText().toString());
                                p.setHealthCard(healthcard.getText().toString());
                                ref.setValue(p, new DatabaseReference.CompletionListener() {
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
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        if (tag != null) {
            update(tag);
            tag = null;
        }
        return view;
    }
}