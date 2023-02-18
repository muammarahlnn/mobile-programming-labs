package com.muammarahlnn.simpleintent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file MainActivity, 17/02/2023 22.06 by Muammar Ahlan Abimanyu
 */
public class MainActivity extends AppCompatActivity {

    public static final String TYPE_DATA = "data";
    public static final String TYPE_OBJECT = "object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMoveData = findViewById(R.id.btn_move_data);
        Button btnMoveObject = findViewById(R.id.btn_move_object);

        String name = "Muammar Ahlan Abimanyu";
        String nim = "H071191032";
        btnMoveData.setOnClickListener(view -> {
            Intent toDetail = new Intent(MainActivity.this, DetailActivity.class);
            toDetail.putExtra(DetailActivity.EXTRA_TYPE, TYPE_DATA);
            toDetail.putExtra(DetailActivity.EXTRA_NAME, name);
            toDetail.putExtra(DetailActivity.EXTRA_NIM, nim);
            startActivity(toDetail);
        });

        Student student = new Student(name, nim);
        btnMoveObject.setOnClickListener(view -> {
            Intent toDetail = new Intent(MainActivity.this, DetailActivity.class);
            toDetail.putExtra(DetailActivity.EXTRA_TYPE, TYPE_OBJECT);
            toDetail.putExtra(DetailActivity.EXTRA_STUDENT, student);
            startActivity(toDetail);
        });
    }
}