package com.cleanup.todoc.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.SortMethod;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;
    private LiveData<List<Task>> allTasks;
    private LiveData<List<Project>> allProjects;
    private MutableLiveData<SortMethod> mSortMethodData = new MutableLiveData<>();
    private SortMethod mSortMethod;


    public ViewModel(@NonNull Application application) {
        super(application);

        taskRepository = new TaskRepository(application);
        allTasks = taskRepository.getAllTask();

        projectRepository = new ProjectRepository(application);
        allProjects = projectRepository.getAllProjects();
    }

    public void insert(Task task) {
        taskRepository.insert(task);
    }

    public void update(Task task) {

        taskRepository.update(task);
    }

    public void delete(Task task) {

        taskRepository.delete(task);
    }

    public void deleteAllTask() {

        taskRepository.deleteAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }


    public void insertProject(Project project) {
        projectRepository.insertProject(project);
    }

    public void updateProject(Project project) {
        projectRepository.updateProject(project);
    }

    public void deleteProject(Project project) {
        projectRepository.deleteProject(project);
    }

    public void deleteAllProjects() {

        projectRepository.deleteAllProjects();
    }

    public LiveData<List<Project>> getAllProjects() {
        return allProjects;
    }


    public LiveData<SortMethod> updateSortMethod(SortMethod mSortMethod) {
        mSortMethodData.setValue(mSortMethod);
        return mSortMethodData;

    }


   /* public void sortTasks(SortMethod sortMethod) {
      mSortMethod = sortMethod;
      mSortMethodData.setValue(sortMethod);

    }*/

   public SortMethod getSort() {
        if (mSortMethodData == null) {
            mSortMethodData.setValue(SortMethod.NONE);
            return mSortMethodData.getValue();
        }
        return mSortMethodData.getValue();
    }

}
