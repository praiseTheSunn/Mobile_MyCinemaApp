package com.example.mycinemaapp.viewModels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.mycinemaapp.models.UserEntity;
import com.example.mycinemaapp.repositories.UserRepository;

import java.util.List;

public class LoginViewModel extends ViewModel {

    UserRepository userRepository = null;
    public boolean checkPassword(Context context, String email, String etPassword) {
        if (userRepository == null) {
            userRepository = new UserRepository(context);
        }
        List<UserEntity> userEntityList = userRepository.getAllUsers();
        Log.d("login", "" + userEntityList.size());

//        for (int i = 0; i < userEntityList.size(); i++) {
//            if (userEntityList.get(i).getPassword().equals(etPassword))
//                return true;
//        }
//        return false;

        String truePassword = userRepository.getPasswordByEmail(email);
        return (truePassword.equals(etPassword));
    }

}