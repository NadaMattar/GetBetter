package com.example.getbetter.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.getbetter.R;
import com.example.getbetter.databinding.ActivityMainBinding;
import com.example.getbetter.fragments.HomeFragment;
import com.example.getbetter.fragments.MoreFragment;
import com.example.getbetter.fragments.NotificationFragment;
import com.example.getbetter.fragments.SettingFragment;
import com.example.getbetter.fragments.StatisticsFragment;
import com.example.getbetter.storage.UserPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding ;
    public UserPreference userPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userPreference = new UserPreference(this);

        binding.mainUserName.setText(userPreference.getUserData().getName());


        binding.mainBottomNavigation.setSelectedItemId(R.id.main_menu_home); //هان خليت الأيقونة تاعت الهوم محددة
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new HomeFragment()).commit();


        binding.mainBottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.main_menu_home:
                        fragment = new HomeFragment();// home
                        break;
                    case R.id.main_menu_statistics:
                        fragment = new StatisticsFragment();// Statistics
                        break;
                    case R.id.main_menu_notification:
                        fragment = new NotificationFragment(); //Notification
                        break;
                    case R.id.main_menu_more:
                        fragment = new MoreFragment();//More
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();

                return true;
            }
        });


        binding.mainSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new SettingFragment()).commit();

            }
        });

//        updateFcmToken();


    }

//    private void updateFcmToken(){ //FCM  .. Firebase Cloud Message
//        FirebaseMessaging.getInstance().getToken() // أخذت التوكين الحالي لأستخدمه بعدين في
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w("Fetching_failed", "Fetching FCM registration token failed", task.getException());
//                            return;
//                        }
//
//                        // Get new FCM registration token
//                        String token = task.getResult();
//                        userPreference.setFCMToken(token);
//
//
//                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

}