package com.muammarahlnn.localsharedpreferences;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvName, tvNim;
    private Button btnSave;
    private StudentPreference studentPreference;
    private StudentModel studentModel;
    private boolean isPreferenceEmpty = false;

    private final ActivityResultLauncher<Intent> resultLauncher =
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           if (result.getData() != null && result.getResultCode() == FormActivity.RESULT_CODE) {
               studentModel = result.getData().getParcelableExtra(FormActivity.EXTRA_RESULT);
               showCurrentStudent();
           }
        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tv_name);
        tvNim = findViewById(R.id.tv_nim);
        btnSave = findViewById(R.id.btn_save);

        studentPreference = new StudentPreference(this);
        showCurrentStudent();

        btnSave.setOnClickListener(view -> {
            Intent toForm = new Intent(MainActivity.this, FormActivity.class);
            if (isPreferenceEmpty) {
                toForm.putExtra(FormActivity.EXTRA_TYPE_FORM, FormActivity.TYPE_ADD);
            } else {
                toForm.putExtra(FormActivity.EXTRA_TYPE_FORM, FormActivity.TYPE_EDIT);
            }
            toForm.putExtra(FormActivity.EXTRA_STUDENT, studentModel);
            resultLauncher.launch(toForm);
        });
    }

    private void showCurrentStudent() {
        studentModel = studentPreference.getStudent();
        tvName.setText(studentModel.getName().isEmpty() ? "-" : studentModel.getName());
        tvNim.setText(studentModel.getNim().isEmpty() ? "-" : studentModel.getNim());

        if (!studentModel.getName().isEmpty()) {
            btnSave.setText(getString(R.string.change));
            isPreferenceEmpty = false;
        } else {
            btnSave.setText(getString(R.string.save));
            isPreferenceEmpty = true;
        }
    }
}