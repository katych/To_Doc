package com.cleanup.todoc.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;


@Database(entities = {Task.class , Project.class}, version = 2)

public abstract class DataBase extends RoomDatabase {


    public static DataBase instance ;



    public abstract TaskDao taskDao() ;
    public abstract ProjectDao projectDao();


public static synchronized DataBase getInstance(Context context){

    if (instance == null){

        instance = Room.databaseBuilder(context.getApplicationContext(),
                DataBase.class,"database")
                .fallbackToDestructiveMigration()
                .build();
    }
    return instance;
}


}
