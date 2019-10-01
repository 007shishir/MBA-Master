package com.saiful.mbamaster.mcq;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Mcq_ViewModel extends AndroidViewModel {
    Mcq_Repository mcq_repository;
    private LiveData<List<Mcq_Q_entity>> select_row;


    public Mcq_ViewModel(Application application) {
        super(application);
        mcq_repository = new Mcq_Repository(application);
    }


    public LiveData<List<Mcq_Q_entity>> getSelect_row(String id) {
        select_row = mcq_repository.getSelect_row(id);
        return select_row;
    }


    public void addMcq_q (Mcq_Q_entity entity)
    {
        mcq_repository.addMcq_q(entity);
    }
}
