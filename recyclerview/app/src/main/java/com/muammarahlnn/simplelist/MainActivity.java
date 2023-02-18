package com.muammarahlnn.simplelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file MainActivity, 18/02/2023 21.00 by Muammar Ahlan Abimanyu
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup rv
        RecyclerView rvStudents = findViewById(R.id.rv_students);
        rvStudents.setHasFixedSize(true);
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        rvStudents.addItemDecoration(
            new DividerItemDecoration(rvStudents.getContext(), DividerItemDecoration.VERTICAL)
        );

        // set adapter to rv
        StudentAdapter adapter = new StudentAdapter(DataSource.students);
        rvStudents.setAdapter(adapter);
    }
}