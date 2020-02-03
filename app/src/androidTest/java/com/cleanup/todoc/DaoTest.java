package com.cleanup.todoc;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.database.DataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class DaoTest {

    private DataBase dataBase;
    private TaskDao taskDao;
    private ProjectDao projectDao;

    public static final Project PROJECT1 = new Project(4L, "Project 4 ", 0xFFEADAD1);
    public static final Task TASK1 = new Task(1, 4L, "task1", new Date().getTime());


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        this.dataBase = Room.inMemoryDatabaseBuilder(context,
                DataBase.class).allowMainThreadQueries()
                .build();

        taskDao = dataBase.taskDao();
        projectDao = dataBase.projectDao();
    }

    @After
    public void closeDb() {
        dataBase.close();
    }


    @Test
    public void insertAndGetProject() throws InterruptedException {

        //Before add new project
        this.dataBase.projectDao().insertProject(PROJECT1);

        //TEST
        Project project = LiveDataTestUtil.getValue(projectDao.getAllProjects()).get(0);
        assertTrue(project.getName().equals(PROJECT1.getName()) && project.getId() == PROJECT1.getId());
    }


    @Test
    public void insertAndGetTask() throws InterruptedException {
        projectDao.insertProject(PROJECT1);
        taskDao.insert(TASK1);
        Task task = LiveDataTestUtil.getValue(taskDao.getAllTasks()).get(0);
        assertTrue(task.getName().equals(TASK1.getName()) && task.getId() == TASK1.getId());
    }

    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException {
        List<Task> tasks = LiveDataTestUtil.getValue(taskDao.getAllTasks());
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void getProjectsWhenNoProjectInserted() throws InterruptedException {
        List<Project> projects = LiveDataTestUtil.getValue(projectDao.getAllProjects());
        assertTrue(projects.isEmpty());
    }

    @Test
    public void insertAndUpdateTask() throws InterruptedException {
        projectDao.insertProject(PROJECT1);
        taskDao.insert(TASK1);

        Task test = LiveDataTestUtil.getValue(taskDao.getAllTasks()).get(0);
        test.setName("test");
        taskDao.update(test);

        Task task = LiveDataTestUtil.getValue(taskDao.getAllTasks()).get(0);
        assertTrue(task.getName().equals("test") && task.getId() == TASK1.getId());
    }

    @Test
    public void insetAndDeleteTask() throws InterruptedException {
        projectDao.insertProject(PROJECT1);
        taskDao.insert(TASK1);

        Task task = LiveDataTestUtil.getValue(taskDao.getAllTasks()).get(0);
        assertTrue(task.getName().equals(TASK1.getName()) && task.getId() == TASK1.getId());

        taskDao.delete(TASK1);
        List<Task> tasks = LiveDataTestUtil.getValue(taskDao.getAllTasks());
        assertFalse(tasks.contains(task));

    }
}
