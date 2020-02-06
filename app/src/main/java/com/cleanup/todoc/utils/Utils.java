package com.cleanup.todoc.utils;

import androidx.annotation.NonNull;

import com.cleanup.todoc.model.SortMethod;
import com.cleanup.todoc.model.Task;

import java.util.Collections;
import java.util.List;

public class Utils {

    public static List<Task> sortTasks(@NonNull final List<Task> tasks, @NonNull SortMethod sortMethod) {

        if (sortMethod == null)  sortMethod = SortMethod.NONE;

        if(tasks.size() != 0){
            switch (sortMethod) {
                case ALPHABETICAL:
                    Collections.sort(tasks, new Task.TaskAZComparator());
                    break;
                case ALPHABETICAL_INVERTED:
                    Collections.sort(tasks, new Task.TaskZAComparator());
                    break;
                case RECENT_FIRST:
                    Collections.sort(tasks, new Task.TaskRecentComparator());
                    break;
                case OLD_FIRST:
                    Collections.sort(tasks, new Task.TaskOldComparator());
                    break;

            }
        }
        return tasks;
    }

}

