package com.fic.cursoandroid2025g4.view.task;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.cursoandroid2025g4.R;
import com.fic.cursoandroid2025g4.model.task.Task;

import java.util.ArrayList;
import java.util.List;

public  class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private final List<Task> task_List = new ArrayList<>();
    private OnTaskActionListener onTaskActionListener;

    public TaskAdapter(OnTaskActionListener onTaskActionListener) {
        this.onTaskActionListener = onTaskActionListener;
    }


    public void setData(List<Task> tasks){
        task_List.clear();
        if(tasks != null){
            task_List.addAll(tasks);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task,parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = task_List.get(position);
        holder.cvParent.setOnClickListener(view -> {
            onTaskActionListener.onEdit(task);
        });

        holder.btnDelete.setOnClickListener(view -> {
            onTaskActionListener.onDelete(task);
        });

        holder.cbCheck.setOnClickListener(view -> {
            task.is_completed = !task.is_completed;

            if (task.is_completed){
                holder.btnDelete.setVisibility(View.VISIBLE);
            } else {
                holder.btnDelete.setVisibility(View.GONE);
            }

        });


        holder.tvTitle.setText(task.task_title);

    }

    @Override
    public int getItemCount() {
        return task_List.size();
    }

    public interface OnTaskActionListener {
        void onEdit(Task task);
        void onDelete(Task task);
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        CardView cvParent;
        ImageButton btnDelete;
        CheckBox  cbCheck;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTask);
            cvParent = itemView.findViewById(R.id.cvTaskItem);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            cbCheck = itemView.findViewById(R.id.cbMarcar);

        }
    }
}