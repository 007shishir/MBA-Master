package com.saiful.mbamaster.mcq;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Mcq_Q_entity.class}, version = 1, exportSchema = false)
public abstract class Mcq_Database extends RoomDatabase {
    public abstract Mcq_Q_Dao mcq_q_dao();

    private static volatile Mcq_Database INSTANCE;

    public static Mcq_Database getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (Mcq_Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Mcq_Database.class, "McqDb")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
