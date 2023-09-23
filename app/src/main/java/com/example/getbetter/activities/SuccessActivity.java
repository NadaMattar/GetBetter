package com.example.getbetter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.getbetter.R;
import com.example.getbetter.databinding.ActivitySuccessBinding;

public class SuccessActivity extends AppCompatActivity {
    ActivitySuccessBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.successGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( SuccessActivity.this , MainActivity.class);
                startActivity( intent );
                finish();
            }
        });
    }
}