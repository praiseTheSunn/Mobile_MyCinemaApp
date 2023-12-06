package com.example.mycinemaapp.viewModels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.mycinemaapp.models.UserEntity;
import com.example.mycinemaapp.repositories.UserRepository;

public class RegisterViewModel extends ViewModel {

    UserRepository userRepository = null;


    public void insertNewUser(Context context, UserEntity userEntity) {
        if (userRepository == null) {
            userRepository = new UserRepository(context);
        }
        userRepository.insertUser(userEntity);
    }

    public boolean checkEmailExists(Context context, String email) {
        if (userRepository == null) {
            userRepository = new UserRepository(context);
        }
        return userRepository.checkEmailExists(email);
    }
}