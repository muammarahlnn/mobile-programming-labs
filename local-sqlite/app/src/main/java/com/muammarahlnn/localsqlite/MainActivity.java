package com.muammarahlnn.localsqlite;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.muammarahlnn.localsqlite.db.StudentHelper;
import com.muammarahlnn.localsqlite.entity.Student;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextView tvName, tvNim;
    private Button btnSave;

    private Student student;

    private final ActivityResultLauncher<Intent> resultLauncher =
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           if (result.getData() != null) {
               switch (result.getResultCode()) {
                   case FormActivity.RESULT_ADD -> {
                       student = result.getData().getParcelableExtra(FormActivity.EXTRA_STUDENT);
                       showCurrentUser(student);
                       Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
                   }
                   case FormActivity.RESULT_UPDATE -> {
                       student = result.getData().getParcelableExtra(FormActivity.EXTRA_STUDENT);
                       showCurrentUser(student);
                       Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show();
                   }
                   case FormActivity.RESULT_DELETE -> {
                       student = null;
                       showCurrentUser(student);
                       Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show();
                   }
               }
           }
        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tv_name);
        tvNim = findViewById(R.id.tv_nim);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(view -> {
            Intent toForm = new Intent(MainActivity.this, FormActivity.class);
            if (student != null) {
                toForm.putExtra(FormActivity.EXTRA_STUDENT, student);
            }
            resultLauncher.launch(toForm);
        });

        new LoadStudentsAsync(this, students -> {
            if (students.size() > 0) {
                student = students.get(0);
            } else {
                student = null;
            }
            showCurrentUser(student);
        }).execute();
    }

    private void showCurrentUser(Student student) {
        if (student != null) {
            tvName.setText(student.getName());
            tvNim.setText(student.getNim());
            btnSave.setText(getString(R.string.change));
        } else {
            tvName.setText("-");
            tvNim.setText("-");
            btnSave.setText(getString(R.string.save));
        }
    }
    private static class LoadStudentsAsync {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadStudentsCallback> weakCallback;

        private LoadStudentsAsync(Context context, LoadStudentsCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                Context context = weakContext.get();
                StudentHelper studentHelper = StudentHelper.getInstance(context);
                studentHelper.open();

                Cursor studentsCursor = studentHelper.queryAll();
                ArrayList<Student> students = MappingHelper.mapCursorToArrayList(studentsCursor);
                studentHelper.close();

                handler.post(() -> weakCallback.get().postExecute(students));
            });
        }
    }

    interface LoadStudentsCallback {
        void postExecute(ArrayList<Student> students);
    }
}