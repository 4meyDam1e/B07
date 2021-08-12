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

public class MainPresenter {
    private MainActivity view;
    private MainModel model;

    public MainPresenter() {
        this.model = new MainModel();
        this.model.setPresenter(this);
    }

    public void setView(MainActivity view) { this.view = view; }

    public void setModel(MainModel model) { this.model = model; }

    public void checkLoginRecord() {
        SharedPreferences settings = view.getSharedPreferences("setting", 0);
        String email = settings.getString("email", "");
        if (!email.equals(""))
            successfullyLogin(email);
    }

    public void setLoginRecord(String email) {
        SharedPreferences settings = view.getSharedPreferences("setting", 0);
        settings.edit().putString("email", email).commit();
    }

    public void successfullyLogin(String email) {
        setLoginRecord(email);
        view.Login(email);
    }

    public void loginEmailError() {
        view.setEmailLayoutError("User doesn't exist!");
    }

    public void loginPasswordError() {
        view.setPasswordLayoutError("Password incorrect!");
    }

    public void attemptLogin() {
        String email = this.view.getEmail();
        String password = this.view.getPassword();
        view.setEmailLayoutError(null);
        view.setPasswordLayoutError(null);
        if (!InputChecker.checkEmail(email)) {
            view.setEmailLayoutError("Please Enter a Valid Email!");
            return;
        }
        if (!InputChecker.checkPassword(password)) {
            view.setPasswordLayoutError("Please Enter a Valid Password!");
            return;
        }
        model.checkEmailPassword(email, password);
    }
}
