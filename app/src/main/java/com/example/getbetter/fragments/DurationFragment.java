package com.example.getbetter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.getbetter.R;
import com.example.getbetter.databinding.FragmentDurationBinding;


import java.util.Calendar;

public class DurationFragment extends Fragment {
    FragmentDurationBinding binding ;

    private final String timestamp;

    public DurationFragment(String timestamp) {
        this.timestamp = timestamp;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDurationBinding.inflate(inflater,container,false) ;
        return binding.getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        TextView minutes = view.findViewById(R.id.fragment_duration_minutes);

        binding.fragmentDurationDays.setText(String.valueOf(getDay()));
        binding.fragmentDurationHours.setText(String.valueOf(getHour()));
        binding.fragmentDurationMinutes.setText(String.valueOf(getMinutes()));

        int[] colors = new int[3];
        final float[] stats = new float[3];

        if (getDay() <= 21){
            binding.fragmentDurationTitle.setText(R.string.will_become_a_habit);
            binding.fragmentDurationResidual.setText(String.valueOf(21 - getDay()) + "\nday left");
            colors[0] = getResources().getColor( R.color.gray);
            colors[1] = getResources().getColor( R.color.green);
            colors[2] = getResources().getColor( R.color.primary);
            binding.slimChart.setColors(colors);

            stats[0] = 100 ;
            stats[1] = (float) getDay()/21*100;
            stats[2] = (float) (getDay()-21)/21*100;
            binding.slimChart.setStats(stats);
        }
        else if (getDay()<=90 && getDay() > 21){
            binding.fragmentDurationTitle.setText(R.string.become_a_habit);
            binding.fragmentDurationResidual.setText(String.valueOf(90 - getDay()) + "\nday left"); // هان بحط الأيام المتبقية لحتى أكتمل لل 21
            colors[0] = getResources().getColor( R.color.gray);
            colors[1] = getResources().getColor( R.color.green);
            colors[2] = getResources().getColor( R.color.primary);
            binding.slimChart.setColors(colors);

            stats[0] = 100 ;
            stats[1] = (float) getDay()/90*100;
            stats[2] = (float) (getDay()-90)/90*100;
            binding.slimChart.setStats(stats);
        }
        else {
            binding.fragmentDurationTitle.setText(R.string.become_a_lifestyle);
            binding.fragmentDurationResidual.setText("Good Gob");
            colors[0] = getResources().getColor( R.color.gray);
            colors[1] = getResources().getColor( R.color.green);

            binding.slimChart.setColors(colors);

            stats[0] = 100 ;
            stats[1] = 100;
            binding.slimChart.setStats(stats);
        }

        binding.slimChart.setStrokeWidth(10);
        binding.slimChart.setStartAnimationDuration(3000);
        binding.slimChart.playStartAnimation();
    }

    private int getDay(){
        Calendar cal = Calendar.getInstance(); // مؤشر على الكاليند الحالي
        cal.setTimeInMillis(Long.parseLong( timestamp )); //    أعطيته الوقت يلي هو نصي وبدي اياه يحوله ل  long
        int day = cal.get(Calendar.DAY_OF_YEAR); // وهان جاب اليوم يلي تابع للرقم

        Calendar nowCal = Calendar.getInstance(); // وهان جاب اليوم الحالي
        int nowDay = nowCal.get(Calendar.DAY_OF_YEAR);

        return nowDay - day ; // وهان حرجع الأيام يلي عدها
    }

    private int getHour(){
        long currentTimestamp = Long.parseLong( timestamp );

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTimestamp);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        Calendar nowCal = Calendar.getInstance();
        int nowHour = nowCal.get(Calendar.HOUR_OF_DAY);

        return nowHour - hour;
    }

    private int getMinutes(){
        long currentTimestamp = Long.parseLong( timestamp );

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTimestamp);
        int minute = cal.get(Calendar.MINUTE);

        Calendar nowCal = Calendar.getInstance();
        int nowMinute = nowCal.get(Calendar.MINUTE);

        return nowMinute - minute;
    }

}
