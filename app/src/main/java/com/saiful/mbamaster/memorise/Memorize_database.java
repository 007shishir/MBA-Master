package com.saiful.mbamaster.memorise;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Memorize_entity.class}, version = 2, exportSchema = false)
public abstract class Memorize_database extends RoomDatabase {
    public abstract Memorize_Dao memorize_dao();
    private static volatile Memorize_database INSTANCE;

    public static Memorize_database getINSTANCE(final Context context){
        if (INSTANCE == null){
            synchronized (Memorize_database.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Memorize_database.class, "MemorizeDB")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
