package com.example.lab_management;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lab_management.manage_device.ListDevice;
import com.example.lab_management.manage_labs.ManageLabs;
import com.example.lab_management.manage_registerlab.RegisterLabActivity;
import com.example.lab_management.manage_verify.ListVerify;
import com.example.lab_management.utils.FakeData;

public class MainActivity extends AppCompatActivity {

    LinearLayout ln_manage_verify_report;
    LinearLayout ln_manage_registerlab;
    LinearLayout ln_manage_device, ln_manage_labs;
    TextView hello_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidgets();
        FakeData.Insert_Device(MainActivity.this);

        String username = getSharedPreferences("username",MODE_PRIVATE).getString("user_name","");
        hello_user.setText("Xin chào " + username);

        setOnClickEvent();
    }

    private void setOnClickEvent() {
        ln_manage_verify_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListVerify.class);
                startActivity(intent);//cho dat:
            }
        });
        ln_manage_registerlab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterLabActivity.class);
            startActivity(intent);
        });

        ln_manage_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDevice.class);
                startActivity(intent);
            }
        });

        ln_manage_labs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManageLabs.class);
                startActivity(intent);
            }
        });

    }

    private void getWidgets() {
        ln_manage_verify_report = findViewById(R.id.ln_manage_verify_report);
        ln_manage_registerlab = findViewById(R.id.ln_manage_registerlab);
        ln_manage_device = findViewById(R.id.ln_manage_device);
        ln_manage_labs = findViewById(R.id.ln_manage_labs);
        hello_user = findViewById(R.id.tv_hello);
    }
}