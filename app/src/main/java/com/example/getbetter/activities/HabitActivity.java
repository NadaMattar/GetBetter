package com.example.getbetter.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.example.getbetter.R;
import com.example.getbetter.adapter.SectionsRecyclerAdapter;
import com.example.getbetter.databinding.ActivityHabitBinding;
import com.example.getbetter.fragments.DurationFragment;
import com.example.getbetter.model.Section;
import com.example.getbetter.storage.UserPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HabitActivity extends AppCompatActivity {
    ActivityHabitBinding binding;

    public FirebaseFirestore firebaseFirestore;
    public UserPreference userPreference;
    private DurationFragment durationFragment;
    private ArrayList<Section> sectionArrayList = new ArrayList<>();
    public String id;
    public String name;
    public Boolean type;
    public Boolean userIsSignHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHabitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();
        userPreference = new UserPreference( this );

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        type = getIntent().getBooleanExtra("type" , false);

        userIsSignHabit = false;

        binding.habitContainer.setVisibility(View.GONE);
        binding.habitStop.setVisibility(View.GONE);
        binding.habitStill.setVisibility(View.GONE);


        firebaseFirestore.collection("user_has_habit")
                .whereEqualTo("id_user" , userPreference.getUserData().getId())
                .whereEqualTo("id_habit" , id)
                .whereEqualTo("timestamp_end" , "")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                if (document.exists()){ // هان يعني لازالت موجودة ومشترك فيها
                                    userIsSignHabit = true;
                                    durationFragment = new DurationFragment(document.getString("timestamp"));
                                }
                                getSupportFragmentManager().beginTransaction().replace(R.id.habit_container, durationFragment).commit();
                            }
                            if (userIsSignHabit){
                                binding.habitContainer.setVisibility(View.VISIBLE);
                                binding.habitStop.setVisibility(View.VISIBLE);
                                binding.habitStill.setVisibility(View.VISIBLE);
                                binding.habitAction.setVisibility(View.GONE); //  هاي بكون مظهراها في حالة اني لسة مش مشتركة
                            }
                        }
                    }
                });

        binding.habitTitle.setText(name);
        if (type){ // اذا التايب تروو يلي هي القيمة يلي ضفتها من الفيربيز ستور  يعني مش مشترك في العادة
            binding.habitAction.setBackground(getResources().getDrawable(R.drawable.shape_rectangle_green_with_rounded));
            binding.habitAction.setText("Get into the habit");
        }

        firebaseFirestore.collection("sections")
                .whereEqualTo("habit_id" , id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            sectionArrayList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()){
                                sectionArrayList.add(new Section(document.getString("id") , document.getString("id_habit") ,document.getString("name") , document.getString("bio")));
                            }
                            SectionsRecyclerAdapter sectionsRecyclerAdapter = new SectionsRecyclerAdapter(sectionArrayList);
                            binding.habitSectionRecycler.setLayoutManager(new LinearLayoutManager(HabitActivity.this,LinearLayoutManager.VERTICAL,false));
                            binding.habitSectionRecycler.setAdapter(sectionsRecyclerAdapter);
                        }
                    }
                });

        // زر الرجوع
        binding.habitBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // هاد آكشن اذا بدي أشترك
        binding.habitAction.setOnClickListener(new View.OnClickListener() { // لما أضغط ع زر الاشتراك بالعادة
            @Override
            public void onClick(View v) {
                Map<String, Object> userHabit = new HashMap<>();
                userHabit.put("id_habit", id );
                userHabit.put("id_user", userPreference.getUserData().getId());
                userHabit.put("name_habit", name);
                userHabit.put("timestamp",  getTimestamp()); // وضع الوقت الحالي للاشتراك
                userHabit.put("timestamp_end",  "");

                firebaseFirestore.collection("user_has_habit")
                        .add(userHabit)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(HabitActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // استمرار الاشتراك بهذه العادة
        binding.habitStill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(HabitActivity.this);
                dialog.setContentView(R.layout.dialog_still_habit);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT); // طول وعرض الديالوج
                dialog.setCancelable(true); // هان علشان لو ضغطت ع أي مكان يختفي الديالوج

                TextView dialog_text =  dialog.findViewById(R.id.dialog_text);
                Button dialog_hide =  dialog.findViewById(R.id.dialog_hide);

                dialog_text.setText("Great effort keep going it will soon become a habit and then it will become a way of life for you");
                dialog_hide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();// هاي دالة استخدمتها عشان أخفيه لمن أضغط ع الزر
                    }
                });

                dialog.show(); // هذه علشان أعرض الديالوج
            }
        });

        // ايقاف الاشتراك بهذه العادة
        binding.habitStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("user_has_habit")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (Objects.equals(document.getString("id_user"), userPreference.getUserData().getId())
                                                && Objects.equals(document.getString("id_habit"), id)){

                                            Map<String, Object> userHabit = new HashMap<>();
                                            userHabit.put("timestamp_end",  getTimestamp());

                                            firebaseFirestore.collection("user_has_habit")
                                                    .document(document.getId())
                                                    .update(userHabit)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(HabitActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                    }
                                }
                            }
                        });


            }
        });
    }

    public String getTimestamp() {
        long timestamp = System.currentTimeMillis(); // الحصول على الوقت بالملي ثانية
        return String.valueOf(timestamp); // تحويله إلى نص
    }
}