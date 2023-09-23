package com.example.getbetter.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.getbetter.adapter.UsersHabitsRecyclerAdapter;
import com.example.getbetter.databinding.FragmentStatisticsBinding;
import com.example.getbetter.model.UserHabit;
import com.example.getbetter.storage.UserPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class StatisticsFragment extends Fragment {
    FragmentStatisticsBinding binding ;


    private final ArrayList<UserHabit> userHabitArrayList = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore;
    private UserPreference userPreference;

    private int attempts = 0;
    private int inProgress = 0;
    private int workingOn = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStatisticsBinding.inflate(inflater,container,false) ;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userPreference = new UserPreference( getActivity() );
        firebaseFirestore = FirebaseFirestore.getInstance();

        UsersHabitsRecyclerAdapter usersHabitsRecyclerAdapter = new UsersHabitsRecyclerAdapter(userHabitArrayList);

        firebaseFirestore.collection("user_has_habit")
                .whereEqualTo("id_user" , userPreference.getUserData().getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            userHabitArrayList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()){
                                if (Objects.equals(document.getString("timestamp_end"), "")){
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTimeInMillis(Long.parseLong(Objects.requireNonNull(document.getString("timestamp"))));
                                    int day = cal.get(Calendar.DAY_OF_YEAR);

                                    Calendar nowCal = Calendar.getInstance();
                                    int nowDay = nowCal.get(Calendar.DAY_OF_YEAR);

                                    if ((nowDay - day) < 90){
                                        inProgress++;
                                    } else {
                                        workingOn++;
                                    }
                                }
                                else {
                                    attempts++;
                                }
                                userHabitArrayList.add( new UserHabit(document.getString("name_habit") , document.getString("timestamp"), document.getString("timestamp_end") ));
                            }
                            usersHabitsRecyclerAdapter.notifyDataSetChanged();

                            binding.statisticsHabitsInProgress.setText(String.valueOf(inProgress));
                            binding.statisticsHabitsWorkingOn.setText(String.valueOf(workingOn));
                            binding.statisticsNumberOfAttempts.setText(String.valueOf(attempts));

                            inProgress=0;
                            workingOn=0;
                            attempts=0;
                        }
                    }
                });

        binding.statisticsRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        binding.statisticsRecycler.setAdapter(usersHabitsRecyclerAdapter);
    }
}
