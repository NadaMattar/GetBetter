package com.example.getbetter.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.getbetter.R;
import com.example.getbetter.databinding.ActivityForgetPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgetPasswordActivity extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;
    public FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        binding.forgetPasswordSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ForgetPasswordActivity.this , SignInActivity.class);
                startActivity( intent );
            }
        });

        binding.recoveryPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.forgetPasswordEmail.getText().toString();
                if (email.trim().length() != 0){
                    recovery(email);
                }else{
                    if (email.length() == 0){
                        binding.forgetPasswordEmail.setError(getString(R.string.error_email_not_entry));
                    }else {
                        binding.forgetPasswordEmail.setError(null);
                    }
                }

            }
        });

    }


    private void recovery (String email ){
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(ForgetPasswordActivity.this , SignInActivity.class));
                        }
                        else {
                            Toast.makeText(ForgetPasswordActivity.this, "Please verify that the email entered is correct", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}