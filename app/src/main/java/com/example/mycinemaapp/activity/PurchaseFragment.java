package com.example.mycinemaapp.activity;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.viewModels.PurchaseViewModel;

public class PurchaseFragment extends Fragment {

    private PurchaseViewModel mViewModel;

    public static PurchaseFragment newInstance() {
        return new PurchaseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_purchase, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.btnDone);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = PurchaseFragmentDirections.actionPurchaseFragmentToHomePageFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }
}