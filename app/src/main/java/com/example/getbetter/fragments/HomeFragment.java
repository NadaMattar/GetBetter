package com.example.getbetter.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.getbetter.adapter.HabitsRecyclerAdapter;
import com.example.getbetter.databinding.FragmentHomeBinding;
import com.example.getbetter.model.Habit;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding ;
    private FirebaseFirestore firebaseFirestore;
    private final ArrayList<Habit> habitArrayList = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false) ;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("habits")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            habitArrayList.clear();

                            for (QueryDocumentSnapshot document : task.getResult()){
                                habitArrayList.add(new Habit( document.getString("id") , document.getString("name") , document.getBoolean("type")));
                            }
//
                            HabitsRecyclerAdapter habitsRecyclerAdapter = new HabitsRecyclerAdapter(habitArrayList);
                            binding.homeHabitsRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
                            binding.homeHabitsRecycler.setAdapter(habitsRecyclerAdapter);

                        }
                    }
                });



    }
}