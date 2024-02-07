package com.example.schedulerv2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Make sure this import statement matches the location of your TaskInfo class
import com.example.schedulerv2.TaskInfo;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<TaskInfo> taskList;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    // Interface for handling clicks on items
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    public TaskAdapter(List<TaskInfo> taskList, LayoutInflater inflater) {
        this.taskList = taskList;
        this.inflater = inflater;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.layout_task_card, parent, false);
        return new TaskViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskInfo currentTask = taskList.get(position);
        // Adjust these lines according to your actual TaskInfo properties and layout IDs
        holder.title.setText(currentTask.getTitle());
        holder.dueDate.setText(currentTask.getDueDate());
        holder.time.setText(currentTask.getTime().isEmpty() ? "N/A" : currentTask.getTime());
        holder.associatedCourse.setText(currentTask.getAssociatedCourse().isEmpty() ? "N/A" : currentTask.getAssociatedCourse());
        holder.location.setText(currentTask.getLocation().isEmpty() ? "N/A" : currentTask.getLocation());
        holder.complete.setChecked(currentTask.isComplete());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView title, dueDate, time, associatedCourse, location;
        CheckBox complete;

        TaskViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.taskTitleTextView);
            dueDate = itemView.findViewById(R.id.taskDueDateTextView);
            time = itemView.findViewById(R.id.taskTimeTextView); // Assuming you have this TextView
            associatedCourse = itemView.findViewById(R.id.associatedCourseTextView); // Assuming you have this TextView
            location = itemView.findViewById(R.id.locationTextView); // Assuming you have this TextView
            complete = itemView.findViewById(R.id.completeCheckBox);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            });

            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemLongClick(position);
                    return true;
                }
                return false;
            });
        }
    }
}


//package com.example.schedulerv2;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import kotlinx.coroutines.scheduling.Task;
//
//public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
//    private List<Task> tasks;
//    private Context context;
//    private final OnTaskClickListener listener;
//
//    public interface OnTaskClickListener {
//        void onTaskClick(Task task);
//    }
//
//    public TaskAdapter(List<Task> tasks, Context context, OnTaskClickListener listener) {
//        this.tasks = tasks;
//        this.context = context;
//        this.listener = listener;
//    }
//
//    @Override
//    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_task_card, parent, false);
//        return new TaskViewHolder(view, listener);
//    }
//
//    @Override
//    public void onBindViewHolder(TaskViewHolder holder, int position) {
//        Task task = tasks.get(position);
//        holder.bind(task);
//    }
//
//    @Override
//    public int getItemCount() {
//        return tasks.size();
//    }
//
//    static class TaskViewHolder extends RecyclerView.ViewHolder {
//        // Views
//        public TaskViewHolder(View itemView, OnTaskClickListener listener) {
//            super(itemView);
//            itemView.setOnClickListener(v -> listener.onTaskClick((Task) itemView.getTag()));
//        }
//
//        public void bind(Task task) {
//            itemView.setTag(task); // Use tag to pass task to click listener
//            // Bind data to views
//        }
//    }
//}
