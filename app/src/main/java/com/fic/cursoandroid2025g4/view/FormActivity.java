package com.fic.cursoandroid2025g4.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fic.cursoandroid2025g4.view.FormActivity;
import com.fic.cursoandroid2025g4.view.task.TaskAdapter;
import com.fic.cursoandroid2025g4.R;
import com.fic.cursoandroid2025g4.controller.TaskController;

public class FormActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDescription;
    private Button btnAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();

        btnAddTask.setOnClickListener(view -> {
            String title = etTitle.getText().toString();
            String author = etDescription.getText().toString();
            saveBook(title,author,false,"12/08/2025");
        });

    }

    private void saveBook(String title, String description, boolean status, String date){
        TaskController taskController = new TaskController(this);
        boolean result = taskController.addTask(title,description,status,date);

        if(result){
            Toast.makeText(this, "Se ha insertado la tarea correctamente", Toast.LENGTH_SHORT).show();
            clearForm();
            showTaskActivity();
        }else{
            Toast.makeText(this, "Error al insertar una tarea", Toast.LENGTH_SHORT).show();

        }
    }

    private void showTaskActivity(){
        Intent intent =  new Intent(FormActivity.this, TaskActivity.class);
        startActivity(intent);
    }

    private void clearForm(){
        etTitle.setText("");
        etDescription.setText("");
    }

    private void initViews(){
        etDescription = findViewById(R.id.etDescription);
        etTitle = findViewById(R.id.etTitle);
        btnAddTask = findViewById(R.id.btnAddTask);
    }

}