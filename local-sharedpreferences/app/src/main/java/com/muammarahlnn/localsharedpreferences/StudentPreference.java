package com.muammarahlnn.localsharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file StudentPreference, 28/04/2023 13.25 by Muammar Ahlan Abimanyu
 */
public class StudentPreference {

    private static final String PREFERENCES_NAME = "student_preferences";
    private static final String NAME = "name";
    private static final String NIM = "nim";

    private final SharedPreferences preferences;

    StudentPreference(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setStudent(StudentModel student) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME, student.getName());
        editor.putString(NIM,student.getNim());
        editor.apply();
    }

    public StudentModel getStudent() {
        StudentModel student = new StudentModel();
        student.setName(preferences.getString(NAME, ""));
        student.setNim(preferences.getString(NIM, ""));
        return student;
    }
}
