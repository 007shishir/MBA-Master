package com.saiful.mbamaster.mcq;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Mcq_Repository {

    private Mcq_Q_Dao mcq_q_dao;
    private LiveData<List<Mcq_Q_entity>> select_row;
    //private List<Mcq_Q_entity> find_questioption;

    //to get the id, not sure if this works!
//    String get_id;


    Mcq_Repository(Application application) {
        Mcq_Database db =Mcq_Database.getDatabase(application);
        mcq_q_dao = db.mcq_q_dao();
//        select_row = mcq_q_dao.select_row(get_id);
    }

    public LiveData<List<Mcq_Q_entity>> getSelect_row(String id) {
        select_row = mcq_q_dao.select_row(id);
        return select_row;
    }

    public void addMcq_q (Mcq_Q_entity mcq_q_entity)
    {
        new insertAsyncTask(mcq_q_dao).execute(mcq_q_entity);
    }

    private static class insertAsyncTask extends AsyncTask<Mcq_Q_entity, Void, Void>
    {
        private Mcq_Q_Dao mAsyncTaskDao;

        insertAsyncTask (Mcq_Q_Dao mcqQDao)
        {
            mAsyncTaskDao = mcqQDao;
        }

        @Override
        protected Void doInBackground(Mcq_Q_entity... mcq_q_entities) {
            mAsyncTaskDao.addMcq_q(mcq_q_entities[0]);
            return null;
        }
    }
}