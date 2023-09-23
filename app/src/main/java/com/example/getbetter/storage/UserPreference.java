package com.example.getbetter.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.getbetter.model.User;

public class UserPreference {

    public Context context;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public UserPreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("get_better_2023" , 0);
        editor = sharedPreferences.edit();
    }

    public void setUserData(User user){
        editor.putString("id" , user.getId());
        editor.putString("email" , user.getEmail());
        editor.putString("name" , user.getName());

        editor.apply();
    }

    public User getUserData(){
        User user = new User();
        user.setId(sharedPreferences.getString("id" , ""));
        user.setEmail(sharedPreferences.getString("email" , ""));
        user.setName(sharedPreferences.getString("name" , ""));
        return user;
    }

    public void setSignIn(){
        editor.putBoolean("is_sign_in" , true);
        editor.apply();
    }

    public boolean isSignIn(){
        return sharedPreferences.getBoolean("is_sign_in" , false);
    }

    public void setFCMToken(String token){
        editor.putString("fcm_token" , token);
        editor.apply();
    }

    public String getFCMToken(){
        return sharedPreferences.getString("fcm_token" , "");
    }
}
