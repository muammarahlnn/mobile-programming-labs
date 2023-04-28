package com.muammarahlnn.localsqlite;

import android.database.Cursor;

import com.muammarahlnn.localsqlite.db.DatabaseContract;
import com.muammarahlnn.localsqlite.entity.Student;

import java.util.ArrayList;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file MappingHelper, 28/04/2023 15.43 by Muammar Ahlan Abimanyu
 */
public class MappingHelper {

    public  static ArrayList<Student> mapCursorToArrayList(Cursor cursor) {
        ArrayList<Student> students = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.StudentColumns._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.StudentColumns.NAME));
            String nim = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.StudentColumns.NIM));
            students.add(new Student(id, name, nim));
        }
        return students;
    }
}
