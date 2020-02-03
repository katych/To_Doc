package com.cleanup.todoc;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;
    LiveData<List<Task>> allTasks;
    LiveData<List<Project>> allProjects;


    public ViewModel(@NonNull Application application) {
        super(application);

        taskRepository = new TaskRepository(application);
        allTasks= taskRepository.getAllTask();

        projectRepository= new ProjectRepository(application);
       allProjects =projectRepository.getAllProjects();
    }

    public void insert (Task task){
        taskRepository.insert(task);
    }

   public void update (Task task) {
        taskRepository.update(task);
   }

    public void delete (Task task) {
        taskRepository.delete(task);
    }

    public void deleteAllTask () {
        taskRepository.deleteAllTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }



    public void insertProject (Project project){
        projectRepository.insertProject(project);
    }

    public void updateProject (Project project) { projectRepository.updateProject(project); }

    public void deleteProject (Project project) { projectRepository.deleteProject(project); }

    public void deleteAllProjects () {
        projectRepository.deleteAllProjects();
    }

    public LiveData<List<Project>> getAllProjects(){
        return allProjects;
    }

}
