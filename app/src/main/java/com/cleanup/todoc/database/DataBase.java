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
                .build();
    }
    return instance;
}
    /**
     * Returns a callback to add the projects in the database
     * @return a callback to add the projects in the database
     */

  /*  private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1L);
                contentValues.put("name", "Projet Tartampion");
                contentValues.put("color", 0xFFEADAD1);
                db.insert("Project", OnConflictStrategy.IGNORE, contentValues);

                contentValues.put("id", 2L);
                contentValues.put("name", "Projet Lucidia");
                contentValues.put("color", 0xFFB4CDBA);
                db.insert("Project", OnConflictStrategy.IGNORE, contentValues);

                contentValues.put("id", 3L);
                contentValues.put("name", "Projet Circus");
                contentValues.put("color", 0xFFA3CED2);
                db.insert("Project", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }*/
}
