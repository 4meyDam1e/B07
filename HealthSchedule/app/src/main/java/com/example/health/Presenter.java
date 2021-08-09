package com.example.health;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import com.example.health.Classes.InputChecker;
import com.example.health.Classes.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Presenter {
    private MainActivity view;
    //private FirebaseDatabase db;

    public Presenter(MainActivity view){
        this.view = view;
        //db = FirebaseDatabase.getInstance();
    }

//    public Presenter(MainActivity view, FirebaseDatabase db){
//        this.view = view;
//        this.db = db;
//    }

    public void successfullyLogin(String email)
    {
        SharedPreferences settings = view.getSharedPreferences("setting", 0);
        settings.edit().putString("email", email).commit();
        Intent intent = new Intent(this.view, DashboardActivity.class);
        intent.putExtra("email", email);
        this.view.startActivity(intent);
        this.view.finish();
    }

    public void attemptLogin() {
        String email = this.view.getEmail();
        String password = this.view.getPassword();
        view.getEmailLayout().setError(null);
        view.getPasswordLayout().setError(null);
        if (!InputChecker.checkEmail(email)) {
            view.getEmailLayout().setError("Please Enter a Valid Email!");
            return;
        }
        if (!InputChecker.checkPassword(password)) {
            view.getPasswordLayout().setError("Please Enter a Valid Password!");
            return;
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(User.getID(email));
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() == null
                        || !snapshot.child("password").getValue(Integer.class)
                            .equals(User.hashPassword(password))) {
                    view.getEmailLayout().setError("Incorrect Email or Password!");
                    view.getPasswordLayout().setError("Incorrect Email or Password!");
                }

                else {
                    successfullyLogin(email);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Database read failed: " + error.getCode());
            }
        });
    }

    public void openRegisterPage() {
        Intent intent = new Intent(this.view, RegisterActivity.class);
        this.view.startActivity(intent);
    }

}
