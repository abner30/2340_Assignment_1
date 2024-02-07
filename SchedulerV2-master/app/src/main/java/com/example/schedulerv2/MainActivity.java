package com.example.schedulerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<String> eventList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private ImageButton imageButton;
    private Button classButton;
    private Button examButton;
    private Button assignmentButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        CalendarView calendarView = findViewById(R.id.calendarView);
//        listView = findViewById(R.id.listView);
//        imageButton = findViewById(R.id.imageButton);
        classButton = findViewById(R.id.classhome);
        examButton = findViewById(R.id.examhome);
//        assignmentButton = findViewById(R.id.assignmenthome);

        classButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClassesActivity.class);
                startActivity(intent);
            }
        });

        examButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });

//        assignmentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, assignments.class);
//                startActivity(intent);
//            }
//        });

//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addEvent(view);
//            }
//        });





//        // Set a listener to respond to date changes
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//                //displays selected date
//                String selectedDate = (month + 1) + "/" + (dayOfMonth) + "/" + year;
//                Toast.makeText(MainActivity.this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
////                String eventDescription = Event.getEventDescription(year, month, dayOfMonth, eventList);
////
////                if (eventDescription != null) {
////                    // Display the event locally within the app (e.g., show a Toast)
////                    Toast.makeText(MainActivity.this, "Event on " + selectedDate + ": " + eventDescription, Toast.LENGTH_SHORT).show();
////                } else {
////                    // No event for the selected date
////                    Toast.makeText(MainActivity.this, "No event on " + selectedDate, Toast.LENGTH_SHORT).show();
////                }
////                calendarView.invalidate();
//            }
//        });

//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addEvent(view);
//            }
//        });

//        eventList = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventList);
//        listView.setAdapter(adapter);
//        setUpListViewListener();
    }



//    private void setUpListViewListener() {
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Context context = getApplicationContext();
//                Toast.makeText(context, "Event Removed", Toast.LENGTH_SHORT).show();
//
//                eventList.remove(position);
//                adapter.notifyDataSetChanged();
//                return true;
//            }
//        });
//    }

//    private void addEvent(View view) {
//        imageButton.bringToFront();
//
//        EditText editEvent = findViewById(R.id.editEvent);
//        editEvent.bringToFront();
//        String newEvent= (editEvent.getText().toString());
//        //Event text = new Event(editEvent.getText().toString(), Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
//        //Event newEvent= new Event(editEvent.getText().toString(), Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
//        //System.out.println(editEvent.getText().toString());
//        //System.out.println("hello");
//        if(!(editEvent.getText().toString().equals(""))) {
//            adapter.add(editEvent.getText().toString());
//            editEvent.setText("");
//        } else {
//            Toast.makeText(getApplicationContext(), "What's new?", Toast.LENGTH_LONG).show();
//        }
//        for (Event e: eventList) {
//            system.out
//        }





    }




//}