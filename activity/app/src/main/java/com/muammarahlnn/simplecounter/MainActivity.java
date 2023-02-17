package com.muammarahlnn.simplecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file MainActivity, 17/02/2023 21.25 by Muammar Ahlan Abimanyu
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvCounter;

    private Button btnMinus, btnPlus;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.tv_counter);
        btnMinus = findViewById(R.id.btn_minus);
        btnPlus = findViewById(R.id.btn_plus);

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                setCounter(counter);
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                setCounter(counter);
            }
        });
    }

    private void setCounter(int counter) {
        tvCounter.setText(String.valueOf(counter));
    }
}