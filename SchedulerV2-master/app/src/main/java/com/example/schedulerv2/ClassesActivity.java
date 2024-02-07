package com.example.schedulerv2;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Type;
import java.util.ArrayList;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ClassesActivity extends AppCompatActivity {

    private RecyclerView classesRecyclerView;
    private ClassInfoAdapter adapter;
    private ArrayList<ClassInfo> classList = new ArrayList<>();
    private final int ADD_CLASS_REQUEST = 1;
    private final int EDIT_CLASS_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        classesRecyclerView = findViewById(R.id.classesRecyclerView);
        classesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ClassInfoAdapter(classList);
        classList = loadClassList();
        adapter = new ClassInfoAdapter(classList);


        classesRecyclerView.setAdapter(adapter);

        Button addButton = findViewById(R.id.addClassButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassesActivity.this, AddClassActivity.class);
                startActivityForResult(intent, ADD_CLASS_REQUEST);

            }
        });

        Button backButton = findViewById(R.id.classback);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter.setOnItemClickListener(new ClassInfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ClassesActivity.this, AddClassActivity.class);
                intent.putExtra("editClassInfo", classList.get(position));
                intent.putExtra("classPosition", position);

                startActivityForResult(intent, EDIT_CLASS_REQUEST);
            }
        });



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ClassInfo removedClass = classList.remove(position);
                adapter.notifyItemRemoved(position);

                Snackbar.make(classesRecyclerView, "Class removed", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                classList.add(position, removedClass);
                                adapter.notifyItemInserted(position);
                            }
                        }).show();
            }

        }).attachToRecyclerView(classesRecyclerView);
    }

    protected void onPause() {
        super.onPause();
        saveClassList(classList); // Save class list when the activity is not in the foreground
    }
    private void saveClassList(ArrayList<ClassInfo> classList) {
        SharedPreferences sharedPreferences = getSharedPreferences("ClassesPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(classList);
        editor.putString("classList", json);
        editor.apply();
    }

    private ArrayList<ClassInfo> loadClassList() {
        SharedPreferences sharedPreferences = getSharedPreferences("ClassesPrefs", MODE_PRIVATE);
        String json = sharedPreferences.getString("classList", null);
        Type type = new TypeToken<ArrayList<ClassInfo>>() {}.getType();
        Gson gson = new Gson();
        ArrayList<ClassInfo> classList = gson.fromJson(json, type);
        if (classList == null) {
            return new ArrayList<>();
        }
        return classList;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            ClassInfo classInfo = (ClassInfo) data.getSerializableExtra("classInfo");
            if (requestCode == ADD_CLASS_REQUEST) {
                classList.add(classInfo);
                adapter.notifyItemInserted(classList.size() - 1);
            } else if (requestCode == EDIT_CLASS_REQUEST) {
                int position = data.getIntExtra("classPosition", -1);

                if (position != -1) {
                    classList.set(position, classInfo);
                    adapter.notifyItemChanged(position);
                }
            }
        }
    }



}


//package com.example.schedulerv2;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//
//public class ClassesActivity extends AppCompatActivity {
//
//    private RecyclerView classesRecyclerView;
//    private ClassInfoAdapter adapter;
//    private ArrayList<ClassInfo> classList = new ArrayList<>();
//    private final int ADD_CLASS_REQUEST = 1; // Request code for starting AddClassActivity
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_classes);
//
//        classesRecyclerView = findViewById(R.id.classesRecyclerView);
//        classesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new ClassInfoAdapter(classList);
//        classesRecyclerView.setAdapter(adapter);
//
//        Button addClassButton = findViewById(R.id.addClassButton);
//        addClassButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ClassesActivity.this, AddClassActivity.class);
//                startActivityForResult(intent, ADD_CLASS_REQUEST);
//            }
//        });
//
//        Button backButton = findViewById(R.id.classback);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish(); // Ends ClassesActivity, returning to MainActivity
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == ADD_CLASS_REQUEST && resultCode == RESULT_OK && data != null) {
//            ClassInfo newClass = (ClassInfo) data.getSerializableExtra("classInfo");
//            classList.add(newClass);
//            adapter.notifyDataSetChanged();
//        }
//    }
//}
