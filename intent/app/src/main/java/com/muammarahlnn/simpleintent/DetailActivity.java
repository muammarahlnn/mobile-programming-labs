package com.muammarahlnn.simpleintent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file DetailActivity, 17/02/2023 22.18 by Muammar Ahlan Abimanyu
 */
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE = "extra_type";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_NIM = "extra_nim";
    public static final String EXTRA_STUDENT = "extra_student";

    private TextView tvGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvGreeting = findViewById(R.id.tv_greeting);

        String type = getIntent().getStringExtra(EXTRA_TYPE);
        if (type.equals(MainActivity.TYPE_DATA)) {
            String name = getIntent().getStringExtra(EXTRA_NAME);
            String nim = getIntent().getStringExtra(EXTRA_NIM);
            showGreeting(name, nim);
        } else if (type.equals(MainActivity.TYPE_OBJECT)) {
            Student student = getIntent().getParcelableExtra(EXTRA_STUDENT);
            showGreeting(student.getName(), student.getNim());
        }
    }

    private void showGreeting(String name, String nim) {
        String greeting = String.format("Hello there, I am %s with NIM %s", name, nim);
        tvGreeting.setText(greeting);
    }
}