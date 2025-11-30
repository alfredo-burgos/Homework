package com.fic.cursoandroid2025g4.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.cursoandroid2025g4.R;
import com.fic.cursoandroid2025g4.controller.TaskController;
import com.fic.cursoandroid2025g4.model.task.Task;
import com.fic.cursoandroid2025g4.view.task.TaskAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TaskActivity extends AppCompatActivity {

    private TaskAdapter taskAdapter;
    private TaskController taskController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task);

        RecyclerView recyclerViewTasks = findViewById(R.id.rvTask);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));

        taskAdapter = new TaskAdapter();
        recyclerViewTasks.setAdapter(taskAdapter);

        taskController = new TaskController(this);
        List<Task> books = taskController.getAllTasks();
        taskAdapter.setData(books);

        FloatingActionButton addTask = findViewById(R.id.addTask);

        addTask.setOnClickListener(view -> {
            showAddTasksActivity();
        });

    }

    private void showAddTasksActivity(){
        Intent intent = new Intent(TaskActivity.this, FormActivity.class);
        startActivity(intent);
    }


}