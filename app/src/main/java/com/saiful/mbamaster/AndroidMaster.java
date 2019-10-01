package com.saiful.mbamaster;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class AndroidMaster extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
