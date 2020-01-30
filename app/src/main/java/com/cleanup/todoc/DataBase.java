package com.cleanup.todoc;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.cleanup.todoc.model.Task;


@Database(entities = {Task.class}, version = 1)

public abstract class DataBase extends RoomDatabase {


    public static DataBase instance ;



    public abstract Dao dao() ;


public static synchronized DataBase getInstance(Context context){

    if (instance == null){

        instance = Room.databaseBuilder(context.getApplicationContext(),
                DataBase.class,"task_database")
                .fallbackToDestructiveMigration()
                .build();
    }
    return instance;
}


}
