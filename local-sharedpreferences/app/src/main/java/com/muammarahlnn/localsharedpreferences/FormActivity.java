package com.muammarahlnn.localsharedpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE_FORM = "extra_type_form";
    public final static String EXTRA_RESULT = "extra_result";
    public final static String EXTRA_STUDENT = "extra_student";

    public static final int RESULT_CODE = 101;
    public static final int TYPE_ADD = 1;
    public static final int TYPE_EDIT = 2;

    private EditText etName, etNim;

    private final String FIELD_REQUIRED = "Please fill this field";

    private StudentModel studentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etName = findViewById(R.id.et_name);
        etNim = findViewById(R.id.et_nim);
        Button btnSave = findViewById(R.id.btn_save);

        studentModel = getIntent().getParcelableExtra(EXTRA_STUDENT );
        int formType = getIntent().getIntExtra(EXTRA_TYPE_FORM, 0);

        String actionBarTitle = "";
        String buttonTitle = "";
        switch (formType) {
            case TYPE_ADD -> {
                actionBarTitle = "Add new student";
                buttonTitle = "Save";
            }
            case TYPE_EDIT -> {
                actionBarTitle = "Change";
                buttonTitle = "Update";
                showCurrentStudent();
            }
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        btnSave.setText(buttonTitle);

        btnSave.setOnClickListener(view -> {
            String name = etName.getText().toString().trim();
            String nim = etNim.getText().toString().trim();
            if (name.isEmpty()) {
                etName.setError(FIELD_REQUIRED);
                return;
            }
            if (nim.isEmpty()) {
                etNim.setError(FIELD_REQUIRED);
                return;
            }
            saveStudent(name, nim);

            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_RESULT, studentModel);
            setResult(RESULT_CODE, resultIntent);
            finish();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    private void showCurrentStudent() {
        etName.setText(studentModel.getName());
        etNim.setText(studentModel.getNim());
    }

    private void saveStudent(String name, String nim) {
        StudentPreference studentPreference = new StudentPreference(this);
        studentModel.setName(name);
        studentModel.setNim(nim);
        studentPreference.setStudent(studentModel);
        Toast.makeText(this, "Student saved", Toast.LENGTH_SHORT).show();
    }
}