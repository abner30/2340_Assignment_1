//package com.example.schedulerv2;
//
//import java.io.Serializable;
//
//public class TaskInfo implements Serializable {
//    enum TaskType { EXAM, ASSIGNMENT, OTHER }
//    enum Completion { COMPLETE, INCOMPLETE }
//
//    private TaskType type;
//    private String title;
//    private String dueDate;
//    private String time;
//    private String associatedCourse;
//    private String location;
//    private boolean isComplete;
//
//    public TaskInfo(TaskType type, String name, String dueDate, String time, String associatedCourse, String location, boolean isComplete) {
//        this.type = type;
//        this.title = name;
//        this.dueDate = dueDate;
//        this.time = time;
//        this.associatedCourse = associatedCourse;
//        this.location = location;
//        this.isComplete = isComplete;
//    }
//
//    public TaskInfo(TaskType type, String name, String dueDate) {
//        this.type = type;
//        this.title = name;
//        this.dueDate = dueDate;
//        this.time = "";
//        this.associatedCourse = "";
//        this.location = "";
//        this.isComplete = false;
//    }
//
//    public TaskType getType() { return type; }
//    public void setType(TaskType type) { this.type = type; }
//
//    public String getTitle() { return title; }
//    public void setName(String name) { this.title = name; }
//
//    public String getDueDate() { return dueDate; }
//    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
//
//    public String getTime() { return time; }
//    public void setTime(String time) { this.time = time; }
//
//    public String getAssociatedCourse() { return associatedCourse; }
//    public void setAssociatedCourse(String associatedCourse) { this.associatedCourse = associatedCourse; }
//
//    public String getLocation() { return location; }
//    public void setLocation(String location) { this.location = location; }
//
//    public boolean isComplete() { return isComplete; }
//    public void setComplete(boolean complete) { isComplete = complete; }
//}


package com.example.schedulerv2;

import java.io.Serializable;

public class TaskInfo implements Serializable {
    private int id; // Unique identifier for database purposes
    private String taskType; // Exam, Assignment, or Other
    private String title; // Title of the Task
    private String dueDate; // Due Date of the Task
    private String time; // Time of the Task
    private String associatedCourse; // Associated Course with the Task
    private String location; // Location of the Task
    private boolean isComplete; // Status of the Task (Complete/Incomplete)

    // Constructor with mandatory fields
    public TaskInfo(String taskType, String title, String dueDate, String time, String associatedCourse, String location, boolean isComplete) {
        this.taskType = taskType;
        this.title = title;
        this.dueDate = dueDate;
        // Initialize optional fields with defaults
        this.time = time;
        this.associatedCourse = associatedCourse;
        this.location = location;
        this.isComplete = isComplete;
    }

    public TaskInfo(String taskType, String title, String dueDate) {
        this.taskType = taskType;
        this.title = title;
        this.dueDate = dueDate;
        // Initialize optional fields with defaults
        this.time = "";
        this.associatedCourse = "";
        this.location = "";
        this.isComplete = false;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAssociatedCourse() {
        return associatedCourse;
    }

    public void setAssociatedCourse(String associatedCourse) {
        this.associatedCourse = associatedCourse;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getType() {
        return taskType;
    }

}

//
////package com.example.schedulerv2;
////
////import java.io.Serializable;
////
////public class TaskInfo implements Serializable {
////    enum TaskType { EXAM, ASSIGNMENT, OTHER }
////    enum Completion { COMPLETE, INCOMPLETE }
////
////    private TaskType type;
////    private String name;
////    private String date; // Common to all types, could be due date or exam date
////    private String course; // Mostly for EXAM and ASSIGNMENT
////    private String time; // Mostly for EXAM
////    private String location; // Mostly for EXAM
////    private String description; // Mostly for OTHER
////    private Completion completionStatus; // Common to all types
////
////    // Constructor
////    public TaskInfo(TaskType type, String name, String date, String course, String time,
////                    String location, String description, Completion completionStatus) {
////        this.type = type;
////        this.name = name;
////        this.date = date;
////        this.course = course;
////        this.time = time;
////        this.location = location;
////        this.description = description;
////        this.completionStatus = completionStatus;
////    }
////
////    // Getters
////    public TaskType getType() { return type; }
////    public String getName() { return name; }
////    public String getDate() { return date; }
////    public String getCourse() { return course; }
////    public String getTime() { return time; }
////    public String getLocation() { return location; }
////    public String getDescription() { return description; }
////    public Completion getCompletionStatus() { return completionStatus; }
////
////    // Setters, if modification of tasks is needed after creation
////    public void setType(TaskType type) { this.type = type; }
////    public void setName(String name) { this.name = name; }
////    public void setDate(String date) { this.date = date; }
////    public void setCourse(String course) { this.course = course; }
////    public void setTime(String time) { this.time = time; }
////    public void setLocation(String location) { this.location = location; }
////    public void setDescription(String description) { this.description = description; }
////    public void setCompletionStatus(Completion completionStatus) { this.completionStatus = completionStatus; }
////}
