package com.cleanup.todoc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    @Insert
    void insertProject(Project project);

    @Update
    void updateProject(Project project);

    @Delete
    void deleteProject(Project project);


    @Query("DELETE FROM  project")
    void deleteAllPrejects();

    @Query("SELECT * FROM project ")
    LiveData<List<Project>> getAllProjects();

}



