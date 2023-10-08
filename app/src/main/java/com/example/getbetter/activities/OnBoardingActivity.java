package com.example.getbetter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.getbetter.R;
import com.example.getbetter.adapter.OnBoardingSliderPagerAdapter;
import com.example.getbetter.databinding.ActivityOnBoardingBinding;
import com.example.getbetter.model.OnBoarding;

import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity {
    ActivityOnBoardingBinding binding ;
    private final ArrayList<OnBoarding> onBoardingArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onBoardingArrayList.add( new OnBoarding( getString(R.string.on_boarding_title_one) , getString(R.string.on_boarding_description_one) , R.drawable.im_on_boarding));
        onBoardingArrayList.add( new OnBoarding( getString(R.string.on_boarding_title_two) , getString(R.string.on_boarding_description_two) , R.drawable.im_on_boarding));
        onBoardingArrayList.add( new OnBoarding( getString(R.string.on_boarding_title_three) , getString(R.string.on_boarding_description_thirty) , R.drawable.im_on_boarding));

        OnBoardingSliderPagerAdapter onBoardingSliderPagerAdapter = new OnBoardingSliderPagerAdapter(onBoardingArrayList);
        binding.onBoardingPager.setAdapter(onBoardingSliderPagerAdapter);
        binding.onBoardingIndicator.setViewPager(binding.onBoardingPager); // هان ربطت الفيو بيجر مع الاندكيتور


        binding.onBoardingContinue.setOnClickListener(new View.OnClickListener() { // الضغط على زر الاستمرار
            @Override
            public void onClick(View view) {
                startActivity( new Intent( OnBoardingActivity.this , SignInActivity.class) );
                finish();
            }
        });


    }
}

//DONE