package com.cleanup.todoc.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.ContentValues;
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
                .addCallback(prepopulateDatabase())
                .build();
    }
    return instance;
}

    /** -- PREPOPULATE THE DATABASE --*/


    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                Project[] projects = Project.getAllProjects();
                for (Project project : projects) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", project.getId());
                    contentValues.put("name", project.getName());
                    contentValues.put("color", project.getColor());
                    db.insert("projects", OnConflictStrategy.IGNORE, contentValues);
                }
            }
        };
    }

}
