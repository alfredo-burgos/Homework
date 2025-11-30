package com.fic.cursoandroid2025g4.controller;

import android.content.Context;
import android.util.Log;

import com.fic.cursoandroid2025g4.model.task.Task;
import com.fic.cursoandroid2025g4.model.task.TaskDao;
import com.fic.cursoandroid2025g4.model.task.TaskDatabase;

import java.util.List;

public class TaskController {

    private final TaskDao taskDao;

    public TaskController(Context context) {
        TaskDatabase database = TaskDatabase.getInstance(context);
        taskDao = database.taskDao();
    }

    //Create a book
    public boolean addTask(String title, String description, boolean status, String date){
        try{
            Task task = new Task();
            task.task_title = title;
            task.task_description = description;
            task.created_at = date;
            task.is_completed = status;
            taskDao.insert(task);
            Log.i("TASK_SAVE","El libro se almacenó correctamente");
            return true;

        }catch (Exception e){
            Log.e("TASK_ERROR",e.getMessage());
            return false;
        }

    }

    //Get all books
    public List<Task> getAllTasks(){
        return taskDao.getAllTasks();
    }

    //Update book
    public boolean updateTask(int id, String title, String description, boolean status, String date){
        try{
            Task task = new Task();
            task.id = id;
            task.task_title = title;
            task.task_description = description;
            task.created_at = date;
            task.is_completed = status;
            taskDao.update(task);
            Log.i("TASK_UPDATE","El libro se actualizo");
            return true;

        }catch (Exception e){
            Log.e("TASK_ERROR",e.getMessage());
            return false;
        }

    }

    //Delete book
    public void deleteTasks(Task task){

        taskDao.delete(task);
    }

}
