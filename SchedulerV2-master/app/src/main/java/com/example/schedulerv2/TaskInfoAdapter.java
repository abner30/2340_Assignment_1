package com.example.schedulerv2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskInfoAdapter extends RecyclerView.Adapter<TaskInfoAdapter.TaskViewHolder> {
    private List<TaskInfo> tasks;
    private OnItemClickListener listener;
    public void setOnItemClickListener(TaskInfoAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public TaskInfoAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }


    public TaskInfoAdapter(ArrayList<TaskInfo> taskList) {
        this.tasks = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_task_card, parent, false);
        return new TaskViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Log.w("onBindViewHolder", "" + position);
        TaskInfo task = tasks.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
    public void updateTaskList(List<TaskInfo> newTasks) {
        tasks.clear();
        tasks.addAll(newTasks);
        notifyDataSetChanged();
    }
    static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView taskTypeTextView, taskNameTextView, taskDetailsTextView, taskDueDateTextView,taskLocationTextView, taskTimeTextView , taskAssociatedCourseTextView;
        public CheckBox completionCheckBox;

        private OnItemClickListener listener;

        public View itemView;

        TaskViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            taskNameTextView = itemView.findViewById(R.id.taskTitleTextView);
            taskTypeTextView = itemView.findViewById(R.id.taskTypeTextView);
            taskDueDateTextView = itemView.findViewById(R.id.taskDueDateTextView);
            taskLocationTextView = itemView.findViewById(R.id.locationTextView);
            taskTimeTextView = itemView.findViewById(R.id.taskTimeTextView);
            taskAssociatedCourseTextView = itemView.findViewById(R.id.associatedCourseTextView);
            completionCheckBox = itemView.findViewById(R.id.completeCheckBox);
            this.itemView = itemView;
            this.listener = listener;


        }

        void bind(final TaskInfo task) {
            taskNameTextView.setText(task.getTitle());
            taskTypeTextView.setText(task.getTaskType());
            taskDueDateTextView.setText(task.getDueDate());
            taskLocationTextView.setText(task.getLocation());
            taskTimeTextView.setText(task.getTime());
            taskAssociatedCourseTextView.setText(task.getAssociatedCourse());
            completionCheckBox.setChecked(task.isComplete());
    //        taskTypeTextView.setText(task.getTaskType());
            Log.w("TaskInfoPrint", "Bind " + task.getTitle());

            itemView.findViewById(R.id.layout_task_card_linearlayout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.w("TaskInfoAdapter", listener.toString());
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });

            //completionCheckBox.setChecked(task.getCompletionStatus() == TaskInfo.Completion.COMPLETE);

            // Dynamically display task details based on the type
            String details = "";
            /*
            switch (task.getType()) {
                case EXAM:
                    details = "Course: " + task.getCourse() + "\nTime: " + task.getTime() + "\nLocation: " + task.getLocation();
                    break;
                case ASSIGNMENT:
                    details = "Course: " + task.getCourse() + "\nDue: " + task.getDate();
                    break;
                case OTHER:
                    details = "Details: " + task.getDescription();
                    break;
            }
            taskDetailsTextView.setText(details);

             */
        }
    }
}
