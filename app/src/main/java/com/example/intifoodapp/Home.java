package com.example.intifoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.intifoodapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        if(auth.getCurrentUser() !=null){
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(Home.this, MainActivity.class));
            finish();
        }



    }

    public void login(View view) {
        startActivity(new Intent(Home.this, Login.class));
    }

    public void registration(View view) {

        startActivity(new Intent(Home.this, Register.class));
    }


}