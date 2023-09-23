package com.example.getbetter.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.getbetter.adapter.HabitOptionRecyclerAdapter;
import com.example.getbetter.databinding.ActivityHabitOptionsBinding;
import com.example.getbetter.model.HabitOption;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HabitOptionsActivity extends AppCompatActivity {
    ActivityHabitOptionsBinding binding ;

    private ArrayList<HabitOption> habitOptions = new ArrayList<>();

    private FirebaseFirestore firebaseFirestore;

    private String id;
    private String name;
    private String bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHabitOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        bio = getIntent().getStringExtra("bio");
//

        binding.habitOptionsTitle.setText(name);
        firebaseFirestore.collection("habit_options")
                .whereEqualTo("id_section" , id )
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        habitOptions.clear();
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                habitOptions.add(new HabitOption(document.getString("text")));
                            }
                            HabitOptionRecyclerAdapter habitOptionRecyclerAdapter = new HabitOptionRecyclerAdapter( habitOptions );

                            binding.habitOptionsRecycler.setLayoutManager(  new LinearLayoutManager( HabitOptionsActivity.this ,LinearLayoutManager.VERTICAL,false) );
                            binding.habitOptionsRecycler.setAdapter( habitOptionRecyclerAdapter);

                        }
                    }
                });

        binding.habitOptionsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}