package com.example.mycinemaapp.activity;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.models.UserEntity;
import com.example.mycinemaapp.utils.Utility;
import com.example.mycinemaapp.viewModels.HomeFragmentViewModel;
import com.example.mycinemaapp.viewModels.RegisterViewModel;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(RegisterViewModel.class);

        EditText etYourName, etEmail, etPassword, etConfirmPassword;

        // Find references to EditText fields
        etYourName = view.findViewById(R.id.etYourName);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword);

        // Find reference to Create Account Button
        Button btnCreateAccount = view.findViewById(R.id.btnCreateAccount);

        // Set OnClickListener for the button
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate input fields
                if (checkEmpty(etYourName, etEmail, etPassword, etConfirmPassword)) {
                    Utility.showToast(getContext(), "One of your fields is empty!");

                } else if (!checkConfirmPasswordEqual(etPassword, etConfirmPassword)) {
                    // Validation failed, show a message or handle accordingly
                    Utility.showToast(getContext(), "Confirm password is not equal to password!");
                }
                else if (checkEmailExists(etEmail)) {
                    Utility.showToast(getContext(), "This email has already registered!");
                }
                else {
                    createAccount(etYourName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
                }
            }
        });

    }

    private boolean checkEmailExists(EditText etEmail) {
        return mViewModel.checkEmailExists(getContext(), etEmail.getText().toString());
    }

    // Validate input fields
    private boolean checkEmpty(EditText etYourName,EditText etEmail,EditText etPassword,EditText etConfirmPassword) {
        return etYourName.getText().toString().trim().isEmpty() ||
                etEmail.getText().toString().trim().isEmpty() ||
                etPassword.getText().toString().trim().isEmpty() ||
                etConfirmPassword.getText().toString().trim().isEmpty();
    }

    private boolean checkConfirmPasswordEqual(EditText etPassword,EditText etConfirmPassword) {
        return etPassword.getText().toString().equals(etConfirmPassword.getText().toString());
    }

    // Create a new account (replace this with your actual account creation logic)
    private void createAccount(String etYourName,String etEmail,String etPassword) {
        // Implement your account creation logic here
        // You can send data to a server, use Firebase, or store locally as needed
        UserEntity userEntity = new UserEntity(etYourName, etEmail, etPassword);
        mViewModel.insertNewUser(getContext(), userEntity);
        Navigation.findNavController(getView()).navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment());
    }
}