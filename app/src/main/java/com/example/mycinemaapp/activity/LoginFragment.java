package com.example.mycinemaapp.activity;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.utils.Utility;
import com.example.mycinemaapp.viewModels.LoginViewModel;
import com.example.mycinemaapp.viewModels.RegisterViewModel;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(LoginViewModel.class);

        EditText etEmail = view.findViewById(R.id.etYourEmailLogin);
        EditText etPassword = view.findViewById(R.id.etPasswordLogin);
        TextView registerText = view.findViewById(R.id.tvSignUpNow);

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment());
            }
        });

        // Find reference to Create Account Button
        Button btnCreateAccount = view.findViewById(R.id.btnLogin);

        // Set OnClickListener for the button
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate input fields

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                Log.d("login", "" + email + password);


                if (email.trim().isEmpty() || password.trim().isEmpty()) {
                    Utility.showToast(getContext(),"Please enter your email and password!");
                }
                else if (mViewModel.checkPassword(getContext(), email, password)) {
                    Utility.hideKeyboard(requireActivity());
                    Utility.showToast(getContext(), "Login succesfully!");
                    Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragmentToHomePageFragment());
                }
                else {
                    Utility.showToast(getContext(), "Wrong password!");
                }
            }
        });


    }




}