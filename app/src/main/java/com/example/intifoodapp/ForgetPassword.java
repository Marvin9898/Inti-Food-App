package com.example.intifoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    EditText mEmailReset;
    Button mButtonReset;
    ProgressBar mProgressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mEmailReset = findViewById(R.id.editTextTextEmailAddress3);
        mButtonReset = findViewById(R.id.buttonReset);
        mProgressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword(){
        String email = mEmailReset.getText().toString().trim();

        if(email.isEmpty()){
            mEmailReset.setError("Email is required!");
            mEmailReset.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmailReset.setError("Please provide valid email!");
            mEmailReset.requestFocus();
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);
        fAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgetPassword.this,"Check your email to reset password!", Toast.LENGTH_LONG).show();
                    mProgressBar.setVisibility(View.GONE);
                }else{
                    Toast.makeText(ForgetPassword.this,"Try again! Something wrong happened!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}