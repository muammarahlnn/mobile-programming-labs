package com.muammarahlnn.localsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muammarahlnn.localsqlite.db.DatabaseContract;
import com.muammarahlnn.localsqlite.db.StudentHelper;
import com.muammarahlnn.localsqlite.entity.Student;

public class FormActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT = "extra_student";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;

    private StudentHelper studentHelper;

    private Student student;

    private EditText etName, etNim;

    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etName = findViewById(R.id.et_name);
        etNim = findViewById(R.id.et_nim);
        Button btnSave = findViewById(R.id.btn_save);
        Button btnDelete = findViewById(R.id.btn_delete);

        studentHelper = StudentHelper.getInstance(getApplicationContext());
        studentHelper.open();

        student = getIntent().getParcelableExtra(EXTRA_STUDENT);
        if (student != null) {
            isEdit = true;
        } else {
            student = new Student();
        }

        String actionBarTitle;
        String buttonTitle;
        if (isEdit) {
            actionBarTitle = "Change";
            buttonTitle = "Update";

            if (student != null) {
                etName.setText(student.getName());
                etNim.setText(student.getNim());
            }

            btnDelete.setVisibility(View.VISIBLE);
        } else {
            actionBarTitle = "Add";
            buttonTitle = "Save";
        }
        btnSave.setText(buttonTitle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnSave.setOnClickListener(view -> save());
        btnDelete.setOnClickListener(view -> delete());
    }

    private void save() {
        String name = etName.getText().toString().trim();
        String nim = etNim.getText().toString().trim();
        if (name.isEmpty()) {
            etName.setError("Please fill this field");
            return;
        }
        if (nim.isEmpty()) {
            etNim.setError("Please fill this field");
            return;
        }

        student.setName(name);
        student.setNim(nim);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_STUDENT, student);

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.StudentColumns.NAME, name);
        values.put(DatabaseContract.StudentColumns.NIM, nim);

        if (isEdit) {
            long result = studentHelper.update(String.valueOf(student.getId()), values);
            if (result > 0) {
                setResult(RESULT_UPDATE, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        } else {
            long result = studentHelper.insert(values);
            if (result > 0) {
                student.setId((int) result);
                setResult(RESULT_ADD, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete() {
        long result = studentHelper.deleteById(String.valueOf(student.getId()));
        if (result > 0) {
            Intent intent = new Intent();
            setResult(RESULT_DELETE, intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }
}