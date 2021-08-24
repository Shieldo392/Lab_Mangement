package com.example.lab_management;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lab_management.manage_registerlab.RegisterLabActivity;
import com.example.lab_management.manage_verify.ListVerify;

public class MainActivity extends AppCompatActivity {

    LinearLayout ln_manage_verify_report;
    LinearLayout ln_manage_registerlab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidgets();
        setOnClickEvent();
    }

    private void setOnClickEvent() {
        ln_manage_verify_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListVerify.class);
                startActivity(intent);
            }
        });
        ln_manage_registerlab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterLabActivity.class);
            startActivity(intent);
        });

    }

    private void getWidgets() {
        ln_manage_verify_report = findViewById(R.id.ln_manage_verify_report);
        ln_manage_registerlab = findViewById(R.id.ln_manage_registerlab);
    }
}