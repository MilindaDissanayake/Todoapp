package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginScreen extends AppCompatActivity {

    TextView Signup;
    EditText Email;
    EditText Password;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Signup=findViewById(R.id.textSignup);
        Email =findViewById(R.id.editTextEmail);
        Password =findViewById(R.id.editTextPw);
        Login = findViewById(R.id.Login);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginScreen.this,signUpScreen.class);
                startActivity(intent);
                finish();
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString();
                String pass = Password.getText().toString();

                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(loginScreen.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                String registeredUser = sharedPreferences.getString("Email", "");
                String registeredPass = sharedPreferences.getString("Password", "");
                String registerUsername = sharedPreferences.getString("Username", "");

                if (email.equals(registeredUser) && pass.equals(registeredPass)) {
                    Toast.makeText(loginScreen.this, "Login successful", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("LoggedIn", true);
                    editor.apply();

                    Intent intent=new Intent(loginScreen.this, main_screen.class);
                    intent.putExtra("Email", email);
                    intent.putExtra("Username", registerUsername);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(loginScreen.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}