package com.example.getbetter.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.getbetter.R;
import com.example.getbetter.databinding.ActivitySignInBinding;
import com.example.getbetter.model.User;
import com.example.getbetter.storage.UserPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding binding ;
    public FirebaseAuth firebaseAuth;
    public FirebaseFirestore firebaseFirestore;

    public UserPreference userPreference;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userPreference = new UserPreference( this );

        binding.signInForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( SignInActivity.this , ForgetPasswordActivity.class) );
            }
        });

        binding.signInSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( SignInActivity.this , SignUpActivity.class) );
            }
        });

        binding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.signInEmail.getText().toString();
                String password = binding.signInPassword.getText().toString();

                if (email.trim().length() != 0 && password.trim().length() != 0){
                    login(email ,password);
                }else {
                    if (email.trim().length() == 0){
                        binding.signInEmail.setError(getString(R.string.error_email_not_entry));
                    }else {
                        binding.signInEmail.setError(null);
                    }

                    if (password.trim().length() == 0){
                        binding.signInPassword.setError(getString(R.string.error_password_not_entry));
                    }else {
                        binding.signInPassword.setError(null);
                    }
                }

            }
        });

    }

    private void login (String email , String password){
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            firebaseFirestore
                                    .collection("users")
                                    .whereEqualTo("email", email)
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    user = new User();
                                                    user.setId(document.getString("id"));
                                                    user.setEmail(document.getString("email"));
                                                    user.setName(document.getString("name"));
                                                }
                                                userPreference.setUserData( user );
                                                userPreference.setSignIn(); //هان صار في مستخدم عشان بعدين أفحص في السبلاش
                                                startActivity( new Intent( SignInActivity.this , SuccessActivity.class));
                                                finish();
                                            } else {
                                                Log.d("USER_DATA", "Error getting documents: ", task.getException());
                                            }
                                        }
                                    });
                        }
                        else {
                            Toast.makeText(SignInActivity.this, "Please verify that the data entered is correct", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}

//DONE