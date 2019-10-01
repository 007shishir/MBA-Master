package com.saiful.mbamaster.memorise;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Memorize_ViewModel extends AndroidViewModel {
    Memorize_Repository repository;
    LiveData<List<Memorize_entity>> select_livedata;
    public Memorize_ViewModel(@NonNull Application application) {
        super(application);
        repository = new Memorize_Repository(application);
//        select_livedata = repository.getSelect_livedata(id);
    }

    public void addMemorizeQ(Memorize_entity entity){
        repository.addMemorizeQ(entity);
    }

    LiveData<List<Memorize_entity>> getSelect_livedata(String id){
        select_livedata = repository.getSelect_livedata(id);
        return select_livedata;
    }
}
