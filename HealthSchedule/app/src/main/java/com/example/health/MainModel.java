package com.example.health;

import android.content.Intent;
import android.content.SharedPreferences;

import com.example.health.Classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainModel {
    MainPresenter presenter;

    public MainModel() {}

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    void checkEmailPassword(String email, String password) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(User.getID(email));
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() == null)
                    presenter.loginEmailError();
                else if (!snapshot.child("password").getValue(Integer.class)
                        .equals(User.hashPassword(password)))
                    presenter.loginPasswordError();
                else
                    presenter.successfullyLogin(email);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Database read failed: " + error.getCode());
            }
        });
    }
}
