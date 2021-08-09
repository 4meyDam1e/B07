package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.health.Classes.Doctor;
import com.example.health.Classes.User;
import com.example.health.Classes.InputChecker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    EditText emailEdit;
    EditText passwordEdit;
    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEdit = findViewById(R.id.m_editTextEmail);
        passwordEdit = findViewById(R.id.m_editTextPassword);

        presenter = new Presenter(this);
    }

    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle("Message")
                .setPositiveButton("OK", null);
        builder.create().show();
    }

   /* public void successfullyLogin(String email)
    {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }*/

    public String getEmail(){
        return emailEdit.getText().toString();
    }

    public String getPassword(){
        return passwordEdit.getText().toString();
    }

    public void handleLogin(View view){
        presenter.attemptLogin();
    }

    /*public void attemptLogin(View view) {
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        if (!InputChecker.checkEmail(email) || !InputChecker.checkPassword(password)) {
            showMessage("The input format is wrong!");
            return;
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                                .child("Users").child(User.getID(email));
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() == null
                        || !snapshot.child("password").getValue(String.class).equals(password))
                    showMessage("Incorrect Email or Password!");
                else successfullyLogin(email);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Database read failed: " + error.getCode());
            }
        });
    }

    public void openRegisterPage(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }*/
}