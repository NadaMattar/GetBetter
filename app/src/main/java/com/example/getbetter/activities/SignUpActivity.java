package com.example.getbetter.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.getbetter.R;
import com.example.getbetter.databinding.ActivitySignUpBinding;
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

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding ;
    public FirebaseAuth firebaseAuth;
    public UserPreference userPreference;
    public FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        userPreference = new UserPreference(this);
        firebaseFirestore = FirebaseFirestore.getInstance();

        binding.signUpSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( SignUpActivity.this , SignInActivity.class) );
            }
        });


        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.signUpName.getText().toString();
                String email = binding.signUpEmail.getText().toString();
                String password = binding.signUpPassword.getText().toString();

                if (name.trim().length() != 0 && email.trim().length() != 0 && password.trim().length() != 0){ // validation
                    register(name ,email ,password);
                } else {
                    if (name.trim().length() == 0){ // يعني فارغة أو .isEmpty
                        binding.signUpName.setError(getString(R.string.error_name_not_entry));
                    }else {
                        binding.signUpName.setError(null);
                    }

                    if (email.trim().length() == 0){
                        binding.signUpEmail.setError(getString(R.string.error_email_not_entry));
                    }else {
                        binding.signUpEmail.setError(null);
                    }

                    if (password.trim().length() == 0){
                        binding.signUpPassword.setError(getString(R.string.error_password_not_entry));
                    }else {
                        binding.signUpPassword.setError(null);
                    }
                    
                }

            }
        });


    }

    private void register (String name ,String email , String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // Success ...
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser(); //هان صار عندي مستخدم جديد رجعلي فيربيز يوزر

                            // Shared Preference
                            User userModel = new User();
                            userModel.setId(firebaseUser.getUid());
                            userModel.setName(name);
                            userModel.setEmail(email);

                            userPreference.setUserData(userModel);


                            Map<String, Object> user = new HashMap<>();
                            user.put("id", firebaseUser.getUid());
                            user.put("name", name);
                            user.put("email", email);

                            firebaseFirestore.collection("users") // add to collection users
                                    .add(user)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            startActivity( new Intent(SignUpActivity.this , SignInActivity.class) );
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SignUpActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }else {
                            // Failed ...
                            Toast.makeText(SignUpActivity.this, "Please verify this user", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }



}

//DONE