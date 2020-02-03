package com.cleanup.todoc.repositories;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.database.DataBase;
import com.cleanup.todoc.model.Project;
import java.util.List;


public class ProjectRepository {

private ProjectDao projectDao;
private LiveData<List<Project>> allProjects;

    public ProjectRepository(Application application) {
        DataBase dataBase = DataBase.getInstance(application);
        projectDao = dataBase.projectDao();
        allProjects = projectDao.getAllProjects();

    }

    public void insertProject(Project project) {
        new InsertProjectAsyncTask(projectDao).execute(project);
    }

    public void updateProject(Project project) {

        new UpdateProjectAsyncTask(projectDao).execute(project);
    }

    public void deleteProject(Project project) {
        new DeleteProjectAsyncTask(projectDao).execute(project);
    }

    public void deleteAllProjects() {
        new DeleteAllProjectsAsyncTask(projectDao).execute();
    }

    public LiveData<List<Project>> getAllProjects() {
        return allProjects;
    }




    private static class InsertProjectAsyncTask extends AsyncTask<Project,Void , Void> {

        private ProjectDao projectDao ;

        public InsertProjectAsyncTask(ProjectDao projectDao) {
            this.projectDao = projectDao;
        }

        @Override
        protected Void doInBackground(Project... projects) {
            projectDao.insertProject(projects[0]);
            return null;
        }
    }

    private static class UpdateProjectAsyncTask extends AsyncTask<Project,Void , Void>{

        private ProjectDao projectDao ;

        public UpdateProjectAsyncTask(ProjectDao projectDao) {
            this.projectDao = projectDao;
        }

        @Override
        protected Void doInBackground(Project... projects) {
            projectDao.updateProject(projects[0]);
            return null;
        }
    }
    private static class DeleteProjectAsyncTask extends AsyncTask<Project,Void , Void>{

        private ProjectDao projectDao ;

        public DeleteProjectAsyncTask(ProjectDao projectDao) {
            this.projectDao = projectDao;
        }

        @Override
        protected Void doInBackground(Project... projects) {
            projectDao.deleteProject(projects[0]);
            return null;
        }
    }
    private static class DeleteAllProjectsAsyncTask extends AsyncTask<Void,Void , Void>{

        private ProjectDao projectDao ;

        public DeleteAllProjectsAsyncTask(ProjectDao projectDao) {
            this.projectDao = projectDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            projectDao.deleteAllPrejects();
            return null;
        }
    }
}


