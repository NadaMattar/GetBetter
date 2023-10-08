package com.example.getbetter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.getbetter.databinding.FragmentSettingBinding;
import com.example.getbetter.storage.UserPreference;

public class SettingFragment extends Fragment {
    FragmentSettingBinding binding ;
    public UserPreference userPreference ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater,container,false) ;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userPreference = new UserPreference(view.getContext());
        binding.tvName.setText(userPreference.getUserData().getName());
        binding.tvEmail.setText(userPreference.getUserData().getEmail());
    }
}
