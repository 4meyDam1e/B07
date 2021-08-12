package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.health.Classes.Doctor;
import com.example.health.Classes.User;
import com.example.health.Classes.InputChecker;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    private EditText emailEdit;
    private EditText passwordEdit;

    private MainPresenter presenter;
    private MainModel model;

    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;
    private Intent dashboardIntent;
    private Intent registerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEdit = findViewById(R.id.m_editTextEmail);
        passwordEdit = findViewById(R.id.m_editTextPassword);

        //presenter = new MainPresenter();
        model = new MainModel();
        presenter = model.presenter;
        presenter.setView(this);

        emailLayout = findViewById(R.id.emailTextField);
        passwordLayout = findViewById(R.id.passwordTextField);
        dashboardIntent = new Intent(this, DashboardActivity.class);
        registerIntent = new Intent(this, RegisterActivity.class);

        presenter.checkLoginRecord();
    }

    public String getEmail(){
        return emailEdit.getText().toString();
    }

    public String getPassword(){
        return passwordEdit.getText().toString();
    }

    public void handleLogin(View view){
        presenter.attemptLogin();
    }

    public void handleSignup(View view){
        openRegisterPage();
    }

    public void setEmailLayoutError(String s) {
        emailLayout.setError(s);
    }

    public void setPasswordLayoutError(String s) {
        passwordLayout.setError(s);
    }

    public void Login(String email) {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra("email", email);
        this.startActivity(intent);
        this.finish();
    }

    public void openRegisterPage() {
        Intent intent = new Intent(this, RegisterActivity.class);
        this.startActivity(intent);
    }
}