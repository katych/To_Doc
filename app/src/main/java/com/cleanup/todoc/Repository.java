package com.cleanup.todoc;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.cleanup.todoc.model.Task;

import java.util.List;

public class Repository {

    private Dao dao;
    private LiveData<List<Task>> allTask;

    public Repository(Application application) {

        DataBase dataBase = DataBase.getInstance(application);
        dao = dataBase.dao();
        allTask = dao.getAllTasks();

    }

    public void insert(Task task) {
        new InsertTaskAsyncTask(dao).execute(task);
    }

    public void update(Task task) {

        new UpdateTaskAsyncTask(dao).execute(task);
    }

    public void delete(Task task) {
        new DeletetTaskAsyncTask(dao).execute(task);
    }

    public void deleteAllTasks() {
        new DeleteAllTaskAsyncTask(dao).execute();
    }

    public LiveData<List<Task>> getAllTask() {
        return allTask;
    }


    private static class InsertTaskAsyncTask extends AsyncTask<Task,Void , Void>{

        private Dao dao ;

        public InsertTaskAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            dao.insert(tasks[0]);
            return null;
        }
    }
    private static class UpdateTaskAsyncTask extends AsyncTask<Task,Void , Void>{

        private Dao dao ;

        public UpdateTaskAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            dao.update(tasks[0]);
            return null;
        }
    }
    private static class DeletetTaskAsyncTask extends AsyncTask<Task,Void , Void>{

        private Dao dao ;

        public DeletetTaskAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            dao.delete(tasks[0]);
            return null;
        }
    }
    private static class DeleteAllTaskAsyncTask extends AsyncTask<Void,Void , Void>{

        private Dao dao ;

        public DeleteAllTaskAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            dao.deleteAllTasks();
            return null;
        }
    }
}
