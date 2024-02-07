package com.example.schedulerv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    private EditText editTextName, editTextDate, editTextCourse, editTextTime, editTextLocation, editTextDescription, editTextCourseAssignment;
    private CheckBox checkBoxComplete;
    private RadioGroup taskTypeRadioGroup;
    private Button buttonSaveTask;
    private Button buttonCancelTask;

    // Edit mode flag and position
    private boolean isEditMode = false;
    private int editPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initializeViews();

        // Check if we're in edit mode old
//        if (getIntent().hasExtra("editTask")) {
//            isEditMode = true;
//            TaskInfo task = (TaskInfo) getIntent().getSerializableExtra("editTask");
//            editPosition = getIntent().getIntExtra("editPosition", -1);
//            populateFields(task);
//        }
//
//
//        buttonSaveTask.setOnClickListener(v -> saveTask());

        if (getIntent().hasExtra("editTask")) {
            isEditMode = getIntent().getBooleanExtra("editMode", false);
            TaskInfo task = (TaskInfo) getIntent().getSerializableExtra("editTask");
            editPosition = getIntent().getIntExtra("editPosition", -1);
            populateFields(task);
        }

        buttonSaveTask.setOnClickListener(v -> saveTask());
    }

    private void initializeViews() {
        taskTypeRadioGroup = findViewById(R.id.taskTypeRadioGroup);
        editTextName = findViewById(R.id.taskTitleTextView);
        editTextDate = findViewById(R.id.taskDueDateTextView);
        editTextCourse = findViewById(R.id.associatedCourseTextView);
        editTextTime = findViewById(R.id.taskTimeTextView);
        editTextLocation = findViewById(R.id.locationTextView);
        checkBoxComplete = findViewById(R.id.completeCheckBox);
        buttonSaveTask = findViewById(R.id.saveButton);
        buttonCancelTask = findViewById(R.id.cancelButton);
    }

    private void adjustViewsBasedOnTaskType(int position) {
        editTextCourse.setVisibility(View.GONE);
        editTextTime.setVisibility(View.GONE);
        editTextLocation.setVisibility(View.GONE);

        switch (position) {
            case 0: // Exam
                editTextCourse.setVisibility(View.VISIBLE);
                editTextTime.setVisibility(View.VISIBLE);
                editTextLocation.setVisibility(View.VISIBLE);
                break;
            case 1: // Assignment
                editTextCourseAssignment.setVisibility(View.VISIBLE);
                break;
            case 2: // Other
                editTextDescription.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void populateFields(TaskInfo task) {
        editTextName.setText(task.getTitle());
        editTextDate.setText(task.getDueDate());
        editTextCourse.setText(task.getAssociatedCourse());
        editTextTime.setText(task.getTime());
        editTextLocation.setText(task.getLocation());
 //       editTextDescription.setText(task.get());
 //       checkBoxComplete.setChecked(task.getCompletionStatus() == TaskInfo.Completion.COMPLETE);
 //       spinnerTaskType.setSelection(task.getType().ordinal());
    }

    private void saveTask() {
        int selectedId = taskTypeRadioGroup.getCheckedRadioButtonId();
        String type;
        if(selectedId == R.id.examRadioButton) {
            type = "Exam";
        } else if (selectedId == R.id.assignmentRadioButton) {
            type = "Assignment";
        } else {
            type = "Other";
        }

        String name = editTextName.getText().toString();
        String date = editTextDate.getText().toString();
        String course = editTextCourse.getVisibility() == View.VISIBLE ? editTextCourse.getText().toString() : "";
        String time = editTextTime.getVisibility() == View.VISIBLE ? editTextTime.getText().toString() : "";
        String location = editTextLocation.getVisibility() == View.VISIBLE ? editTextLocation.getText().toString() : "";
        boolean isComplete = checkBoxComplete.isChecked();
 //       TaskInfo.TaskType type = TaskInfo.TaskType.values()[spinnerTaskType.getSelectedItemPosition()];
   //     TaskInfo.Completion completion = isComplete ? TaskInfo.Completion.COMPLETE : TaskInfo.Completion.INCOMPLETE;

//        if (name.isEmpty() || date.isEmpty()) {
//            Toast.makeText(this, "Please fill out the required fields.", Toast.LENGTH_SHORT).show();
//            return;
//        }

      //  TaskInfo task = new TaskInfo(type, name, date, course, time, location, description, completion);
        TaskInfo task = new TaskInfo(type, name, date, time, course, location, isComplete);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("task", task);
        resultIntent.putExtra("editMode", isEditMode);//new

        if (isEditMode) {
            resultIntent.putExtra("editPosition", editPosition);
        }
        setResult(RESULT_OK, resultIntent);
        finish();

    }
}

