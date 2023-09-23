package com.example.getbetter.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.getbetter.databinding.ActivitySplashBinding;
import com.example.getbetter.model.User;
import com.example.getbetter.storage.UserPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding ;
    public UserPreference userPreference;
    public FirebaseFirestore firebaseFirestore;
    public User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userPreference = new UserPreference(this);
        firebaseFirestore = FirebaseFirestore.getInstance();
        if (userPreference.isSignIn()){ // هان ازا في مستخدم
            updateData(); // حيجيب بيانات المستخدم الحالي
        }
        else {
            // ازا لاء حيروح ع الأونبوردينج
            Intent intent = new Intent( SplashActivity.this , OnBoardingActivity.class);
            startActivity( intent );
        }

    }


    public void updateData(){
        firebaseFirestore.collection("users")
                .whereEqualTo("id", userPreference.getUserData().getId()) // هان بطلب منه يجيبلي بيانات المستخدم يلي الآي دي تاعه بيساوي يلي في الشيرد
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

                            Intent intent = new Intent( SplashActivity.this , MainActivity.class);
                            startActivity( intent );

                        } else {
                            Log.d("USER_DATA", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

}

// DONE