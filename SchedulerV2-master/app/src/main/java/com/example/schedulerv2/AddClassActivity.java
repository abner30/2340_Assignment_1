package com.example.schedulerv2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddClassActivity extends AppCompatActivity {

    private EditText editTextDateAndTime, editTextDaysOfWeek, editTextProfessor, editTextClassSection, editTextLocationAndRoom;
    private Button buttonSave;
    private boolean isEditing = false;
    private int classPosition = -1; // Default value means a new class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        editTextDateAndTime = findViewById(R.id.editTextDateAndTime);
        editTextDaysOfWeek = findViewById(R.id.editTextDaysOfWeek);
        editTextProfessor = findViewById(R.id.editTextProfessor);
        editTextClassSection = findViewById(R.id.editTextClassSection);

        editTextLocationAndRoom = findViewById(R.id.editTextLocationAndRoom);
        buttonSave = findViewById(R.id.buttonSaveClass);

        // Check if this activity was opened for editing an existing class
        if (getIntent().hasExtra("editClassInfo")) {
            ClassInfo classInfo = (ClassInfo) getIntent().getSerializableExtra("editClassInfo");
            classPosition = getIntent().getIntExtra("classPosition", -1);
            fillClassInfoToEdit(classInfo);
            isEditing = true;
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveClassInfo();
            }
        });
    }

    private void fillClassInfoToEdit(ClassInfo classInfo) {
        editTextDateAndTime.setText(classInfo.getDateTime());
        editTextDaysOfWeek.setText(classInfo.getDaysOfWeek());
        editTextProfessor.setText(classInfo.getProfessor());
        editTextClassSection.setText(classInfo.getSection());
        editTextLocationAndRoom.setText(classInfo.getLocation());
    }

    private void saveClassInfo() {
        String dateTime = editTextDateAndTime.getText().toString();
        String daysOfWeek = editTextDaysOfWeek.getText().toString();
        String professor = editTextProfessor.getText().toString();
        String section = editTextClassSection.getText().toString();
        String location = editTextLocationAndRoom.getText().toString();

        ClassInfo classInfo = new ClassInfo(dateTime, daysOfWeek, professor, section, location);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("classInfo", classInfo);
        if (isEditing) {
            resultIntent.putExtra("classPosition", classPosition); // Pass back the original position if editing
        }
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}

//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class AddClassActivity extends AppCompatActivity {
//
//    private EditText editTextDateAndTime, editTextDaysOfWeek, editTextProfessor, editTextClassSection, editTextLocationAndRoom;
//    private Button buttonSaveClass;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_class);
//
//        editTextDateAndTime = findViewById(R.id.editTextDateAndTime);
//        editTextDaysOfWeek = findViewById(R.id.editTextDaysOfWeek);
//        editTextProfessor = findViewById(R.id.editTextProfessor);
//        editTextClassSection = findViewById(R.id.editTextClassSection);
//        editTextLocationAndRoom = findViewById(R.id.editTextLocationAndRoom);
//        buttonSaveClass = findViewById(R.id.buttonSaveClass);
//
//        buttonSaveClass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveClassInfo();
//            }
//        });
//    }
//
//    private void saveClassInfo() {
//        Intent data = new Intent();
//        // Assuming ClassInfo has a suitable constructor
//        ClassInfo classInfo = new ClassInfo(
//                editTextDateAndTime.getText().toString(),
//                editTextDaysOfWeek.getText().toString(),
//                editTextProfessor.getText().toString(),
//                editTextClassSection.getText().toString(),
//                editTextLocationAndRoom.getText().toString()
//        );
//        data.putExtra("classInfo", classInfo); // Make sure ClassInfo implements Serializable
//        setResult(RESULT_OK, data);
//        finish();
//    }
//}
