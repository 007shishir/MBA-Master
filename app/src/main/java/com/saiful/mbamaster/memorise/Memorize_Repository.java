package com.saiful.mbamaster.memorise;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Memorize_Repository {
    private Memorize_Dao memorize_dao;
    private LiveData<List<Memorize_entity>> select_livedata;

    Memorize_Repository(Application application){
        Memorize_database db = Memorize_database.getINSTANCE(application);
        memorize_dao = db.memorize_dao();
//        select_livedata = memorize_dao.select_liveData();
    }

    LiveData<List<Memorize_entity>> getSelect_livedata(String id){
        select_livedata = memorize_dao.select_liveData(id);
        return select_livedata;
    }

    int getCountPrimaryQuestion(String id){
        return memorize_dao.countPrimaryQuestion(id);
    }

    public void addMemorizeQ(Memorize_entity memorize_entity){
        new insertAsyncTask(memorize_dao).execute(memorize_entity);
    }

    private static class insertAsyncTask extends AsyncTask<Memorize_entity, Void, Void>
    {
        private Memorize_Dao mAsyncTaskDao;

        insertAsyncTask (Memorize_Dao mmDao)
        {
            mAsyncTaskDao = mmDao;
        }

        @Override
        protected Void doInBackground(Memorize_entity... memorize_entities) {
            mAsyncTaskDao.addMemorizeQ(memorize_entities[0]);
            return null;
        }
    }
}
