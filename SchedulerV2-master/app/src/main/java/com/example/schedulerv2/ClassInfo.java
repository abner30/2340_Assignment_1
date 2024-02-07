package com.example.schedulerv2;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ClassInfo implements Serializable {
    private String dateTime;
    private String daysOfWeek;
    private String professor;
    private String section;
    private String location;

    public ClassInfo(String dateTime, String daysOfWeek, String professor, String section, String location) {
        this.dateTime = dateTime;
        this.daysOfWeek = daysOfWeek;
        this.professor = professor;
        this.section = section;
        this.location = location;
    }

    // Getters
    public String getDateTime() {
        return dateTime;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public String getProfessor() {
        return professor;
    }

    public String getSection() {
        return section;
    }

    public String getLocation() {
        return location;
    }


}