package com.cleanup.todoc.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.database.DataBase;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<Task>> allTask;

    public TaskRepository(Application application) {

        DataBase dataBase = DataBase.getInstance(application);
        taskDao = dataBase.taskDao();
        allTask = taskDao.getAllTasks();

    }

    public void insert(Task task) {
        new InsertTaskAsyncTask(taskDao).execute(task);
    }

    public void update(Task task) {

        new UpdateTaskAsyncTask(taskDao).execute(task);
    }

    public void delete(Task task) {
        new DeletetTaskAsyncTask(taskDao).execute(task);
    }

    public void deleteAllTasks() {
        new DeleteAllTaskAsyncTask(taskDao).execute();
    }

    public LiveData<List<Task>> getAllTask() {
        return allTask;
    }


    private static class InsertTaskAsyncTask extends AsyncTask<Task,Void , Void>{

        private TaskDao taskDao ;

        public InsertTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }
    private static class UpdateTaskAsyncTask extends AsyncTask<Task,Void , Void>{

        private TaskDao taskDao ;

        public UpdateTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }
    private static class DeletetTaskAsyncTask extends AsyncTask<Task,Void , Void>{

        private TaskDao taskDao ;

        public DeletetTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }
    private static class DeleteAllTaskAsyncTask extends AsyncTask<Void,Void , Void>{

        private TaskDao taskDao ;

        public DeleteAllTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            taskDao.deleteAllTasks();
            return null;
        }
    }
}
