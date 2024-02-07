
package com.example.schedulerv2;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {
    private static final String PREFS_FILE = "com.yourpackage.app.SharedPreferences";
    private static final String TASKS_KEY = "tasks";
    private static final int ADD_TASK_REQUEST = 1;
    private static final int EDIT_TASK_REQUEST = 2;

    private RecyclerView tasksRecyclerView;
    private TaskInfoAdapter adapter;
    private ArrayList<TaskInfo> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        loadTasks();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskInfoAdapter(taskList);
        tasksRecyclerView.setAdapter(adapter);

        findViewById(R.id.addTaskButton).setOnClickListener(view -> {
            Intent intent = new Intent(TaskActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, ADD_TASK_REQUEST);
        });

        findViewById(R.id.taskback).setOnClickListener(view -> finish());

        setupSwipeToDelete();
        adapter.setOnItemClickListener(new TaskInfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(TaskActivity.this, AddTaskActivity.class);
                intent.putExtra("editTaskInfo", taskList.get(position));
                intent.putExtra("taskPosition", position);

                startActivityForResult(intent, EDIT_TASK_REQUEST);
            }
        });
    }

    private void setupSwipeToDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                taskList.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                saveTasks(); // Save changes
            }
        }).attachToRecyclerView(tasksRecyclerView);
    }

    private void saveTasks() {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(taskList);
        editor.putString(TASKS_KEY, json);
        editor.apply();
    }

    private void loadTasks() {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(TASKS_KEY, null);
        Type type = new TypeToken<ArrayList<TaskInfo>>() {}.getType();
        taskList = gson.fromJson(json, type);

        if (taskList == null) {
            taskList = new ArrayList<>();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            TaskInfo task = (TaskInfo) data.getSerializableExtra("task");
            int position = data.getIntExtra("taskPosition", -1);
            if (requestCode == ADD_TASK_REQUEST) {
                taskList.add(task);
                adapter.notifyItemInserted(taskList.size() - 1);
            } else if (requestCode == EDIT_TASK_REQUEST && position != -1) {
                taskList.set(position, task);
                adapter.notifyItemChanged(position);
            }
            saveTasks(); // Save changes after adding or editing
        }
    }
}


//package com.example.schedulerv2;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//
//import java.util.ArrayList;
//
//public class TaskActivity extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private TaskAdapter adapter;
//    private List<Task> tasks = new ArrayList<>();
//    private AppDatabase db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_task);
//
//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new TaskAdapter(tasks, task -> {
//            // Placeholder for edit task action
//            Intent editIntent = new Intent(TaskActivity.this, EditTaskActivity.class);
//            editIntent.putExtra("taskId", task.id);
//            startActivityForResult(editIntent, 2); // Use a unique request code for edit
//        });
//        recyclerView.setAdapter(adapter);
//
//        FloatingActionButton fab = findViewById(R.id.fab_add_task);
//        fab.setOnClickListener(view -> {
//            Intent addIntent = new Intent(TaskActivity.this, AddTaskActivity.class);
//            startActivityForResult(addIntent, 1); // Use a unique request code for add
//        });
//
//        setupSwipeToDelete();
//
//        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks-db").build();
//
//        loadTasks();
//    }
//
//    private void setupSwipeToDelete() {
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false; // no move
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                Task task = tasks.get(viewHolder.getAdapterPosition());
//                deleteTask(task);
//            }
//        }).attachToRecyclerView(recyclerView);
//    }
//
//    private void loadTasks() {
//        // Assuming you have a method in your DAO to fetch all tasks
//        new Thread(() -> {
//            tasks.clear();
//            tasks.addAll(db.taskDao().getAllTasks());
//            runOnUiThread(() -> adapter.notifyDataSetChanged());
//        }).start();
//    }
//
//    private void deleteTask(Task task) {
//        new Thread(() -> {
//            db.taskDao().delete(task);
//            tasks.remove(task);
//            runOnUiThread(() -> adapter.notifyDataSetChanged());
//        }).start();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if ((requestCode == 1 || requestCode == 2) && resultCode == RESULT_OK) {
//            // Task was added or edited, reload tasks
//            loadTasks();
//        }
//    }
//}
