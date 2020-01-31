package com.cleanup.todoc;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    LiveData<List<Task>> allTasks;

    public ViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        allTasks= repository.getAllTask();
    }

    public void insert (Task task){
        repository.insert(task);
    }

   public void update (Task task) {
        repository.update(task);
   }

    public void delete (Task task) {
        repository.delete(task);
    }

    public void deleteAllTask () {
        repository.deleteAllTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

}
