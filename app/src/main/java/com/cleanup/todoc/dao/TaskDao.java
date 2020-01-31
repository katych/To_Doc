package com.cleanup.todoc.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);


    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    @Query("SELECT * FROM task_table ")
    LiveData<List<Task>> getAllTasks();

}
