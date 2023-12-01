package com.example.mycinemaapp.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeFragmentViewModel extends ViewModel {
    private MutableLiveData<Integer> motionProgress;


    public MutableLiveData<Integer> getMotionProgress() {
        return motionProgress;
    }


    public void setMotionProgress(Integer progress) {
        motionProgress = new MutableLiveData<Integer>(progress);
    }
}
