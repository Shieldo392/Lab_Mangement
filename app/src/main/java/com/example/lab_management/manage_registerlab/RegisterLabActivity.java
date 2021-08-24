package com.example.lab_management.manage_registerlab;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.lab_management.R;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.objects.RegisterLab;
import com.example.lab_management.objects.Term;
import com.example.lab_management.sqlhelper.SqLiteHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class RegisterLabActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerlab);

        getWidgets();

        setEvents();
        GetData();

        refreshDataForListview();
    }

    Spinner spinLab, spinSession, spinTerm;
    EditText editTime, editReplaced;
    Button btnPickTime, btnPickReplaced;
    Switch switchReplaced;
    ListView listRegisterLab;
    int curIndexSelected = -1;

    ArrayAdapter<RegisterLab> adapterRegisterLab;

    List<String> labs, sessions, terms; // hien thi len man hinh
    List<Integer> labsID, termsID; // luu vi tri tren list va lay ra id
    ArrayAdapter<String> adapterLab, adapterSession, adapterTerm;

    DateFormat df_vn = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.registerlab_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemAdd:
                AddRegiterLab();
                return true;
            case R.id.itemUpdate:
                UpdateRegisterLab();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void AddRegiterLab() {
        RegisterLab registerLabInfo = new RegisterLab();
        //registerLabInfo.setRegisterID();

        SharedPreferences share = getSharedPreferences("username",MODE_PRIVATE);
        int userID = Integer.parseInt(Objects.requireNonNull(share.getString("user_id", "1")));
        registerLabInfo.setUserID(userID); // TODO: get user static
        registerLabInfo.setSession(spinSession.getSelectedItem() + "");

        try {
            registerLabInfo.setTime(df_vn.parse(editTime.getText() + ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        registerLabInfo.setTermID(termsID.get(spinTerm.getSelectedItemPosition()));
        registerLabInfo.setLabID(labsID.get(spinLab.getSelectedItemPosition()));
        if (switchReplaced.isChecked()){
            try{
                registerLabInfo.setTime(df_vn.parse(editReplaced.getText()+""));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else{
            registerLabInfo.setTime(null);
        }
        boolean result = SqLiteHelper.getInstance(RegisterLabActivity.this).Insert_RegisterLab(registerLabInfo);
        if (result){
            Toast.makeText(RegisterLabActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            curIndexSelected = -1;
        }
        else{
            Toast.makeText(RegisterLabActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
        }
        refreshDataForListview();
    }

    void UpdateRegisterLab(){
        if (curIndexSelected >= 0){
            RegisterLab registerLabInfo = new RegisterLab();

            //registerLabInfo.getRegisterID(adapterRegisterLab.getItem(curIndexSelected).getRegisterID());
            registerLabInfo.setLabID(labsID.get(spinLab.getSelectedItemPosition()));
            SharedPreferences share = getSharedPreferences("user",MODE_PRIVATE);
            int userID = Integer.parseInt(Objects.requireNonNull(share.getString("user_id", "1")));
            registerLabInfo.setUserID(userID);
            registerLabInfo.setSession(spinSession.getSelectedItem() + "");
            try {
                registerLabInfo.setTime(df_vn.parse(editTime.getText() + ""));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            registerLabInfo.setTermID(termsID.get(spinTerm.getSelectedItemPosition()));
            if (switchReplaced.isChecked()){
                try {
                    registerLabInfo.setReplaced(df_vn.parse(editTime.getText() + ""));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else{
                registerLabInfo.setReplaced(null);
            }

            boolean result = SqLiteHelper.getInstance(RegisterLabActivity.this).Update_RegisterLab(registerLabInfo);
            if (result){
                Toast.makeText(RegisterLabActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                curIndexSelected = -1;
            }
            else{
                Toast.makeText(RegisterLabActivity.this, "Sửa không thành công", Toast.LENGTH_SHORT).show();
            }
            refreshDataForListview();
        }
    }

    void getWidgets(){
        spinLab = findViewById(R.id.spinMaPhong);
        spinSession = findViewById(R.id.spinCaThucHanh);
        spinTerm = findViewById(R.id.spinMaHocPhan);

        editTime = findViewById(R.id.editThu);
        editReplaced = findViewById(R.id.editThuThayThe);

        btnPickTime = findViewById(R.id.btnPickTime);
        btnPickReplaced = findViewById(R.id.btnPickTimeThayThe);

        switchReplaced = findViewById(R.id.switchThayTheLyThuyet);

        listRegisterLab = findViewById(R.id.listDangKyPhongThucHanh);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    void GetData(){
        labs = new ArrayList<>();
        sessions = new ArrayList<>();
        terms = new ArrayList<>();
        labsID = new ArrayList<>();
        termsID = new ArrayList<>();

        // get data with database
        List<Lab> labList = SqLiteHelper.getInstance(RegisterLabActivity.this).GetAllLab();
        labList.forEach(lab -> {
            labs.add(lab.getLab_Name());
            labsID.add(lab.getLab_ID());
        });
        //labs = Arrays.asList("Phong_01", "Phong_02", "Phong_03", "PhongLab_1", "PhongLab_2", "HoiThao_1");
        sessions = Arrays.asList("1, 2, 3", "4, 5, 6", "7, 8, 9", "10, 11, 12", "13, 14, 15", "Thâu đêm");

        List<Term> termList = SqLiteHelper.getInstance(RegisterLabActivity.this).GetAllTerm();
        Log.i(TAG, "GetData: " + termList.size());
        termList.forEach(term -> {
            terms.add(term.getTermName());
            termsID.add(term.getTermID());
        });
        //terms = Arrays.asList("141341341", "346356456745", "34235346457");

        adapterLab = new ArrayAdapter<>(RegisterLabActivity.this, android.R.layout.simple_spinner_item, labs);
        adapterSession = new ArrayAdapter<>(RegisterLabActivity.this, android.R.layout.simple_spinner_item, sessions);
        adapterTerm = new ArrayAdapter<>(RegisterLabActivity.this, android.R.layout.simple_spinner_item, terms);

        adapterLab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSession.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTerm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinLab.setAdapter(adapterLab);
        spinSession.setAdapter(adapterSession);
        spinTerm.setAdapter(adapterTerm);


        Date today = Calendar.getInstance().getTime();
        String todayAsString = df_vn.format(today);
        editTime.setText(todayAsString);
        editReplaced.setText(todayAsString);
    }
    void setEvents(){
        switchReplaced.setChecked(false);
        editReplaced.setEnabled(false);
        btnPickReplaced.setEnabled(false);
        editReplaced.setVisibility(View.INVISIBLE);
        btnPickReplaced.setVisibility(View.INVISIBLE);
        switchReplaced.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                editReplaced.setEnabled(true);
                btnPickReplaced.setEnabled(true);
                editReplaced.setVisibility(View.VISIBLE);
                btnPickReplaced.setVisibility(View.VISIBLE);
            }
            else{
                editReplaced.setEnabled(false);
                btnPickReplaced.setEnabled(false);
                editReplaced.setVisibility(View.INVISIBLE);
                btnPickReplaced.setVisibility(View.INVISIBLE);
            }
        });

        btnPickTime.setOnClickListener(v -> {
            final DatePickerDialog.OnDateSetListener listener = (view, year, month, dayOfMonth) -> {
                String dayOfMonthString = String.format(Locale.getDefault(), "%02d", dayOfMonth);
                String monthString = String.format(Locale.getDefault(), "%02d", month + 1);
                String yearString = String.format(Locale.getDefault(), "%04d", year);
                String thu = dayOfMonthString + "/" + monthString + "/" + yearString;
                editTime.setText(thu);
            };
            DatePickerDialog dialog = new DatePickerDialog(RegisterLabActivity.this, listener, 2021, 5, 1);
            dialog.setTitle("Thời gian đăng ký");
            dialog.show();
        });

        btnPickReplaced.setOnClickListener(v -> {
            final DatePickerDialog.OnDateSetListener listener = (view, year, month, dayOfMonth) -> {
                String dayOfMonthString = String.format(Locale.getDefault(), "%02d", dayOfMonth);
                String monthString = String.format(Locale.getDefault(), "%02d", month + 1);
                String yearString = String.format(Locale.getDefault(), "%04d", year);
                String thu = dayOfMonthString + "/" + monthString + "/" + yearString;
                editReplaced.setText(thu);
            };
            DatePickerDialog dialog = new DatePickerDialog(RegisterLabActivity.this, listener, 2021, 5, 1);
            dialog.setTitle("Lý thuyết thay thế");
            dialog.show();
        });

        listRegisterLab.setOnItemClickListener((parent, view, position, id) -> {
            // focus
            curIndexSelected = position;

            // fill
            RegisterLab registerLab = adapterRegisterLab.getItem(position);

            // kho vl
            spinLab.setSelection(labsID.indexOf(registerLab.getLabID()));
            spinSession.setSelection(adapterSession.getPosition(registerLab.getSession()));

            editTime.setText(df_vn.format(registerLab.getTime()) + "");

            spinTerm.setSelection(termsID.indexOf(registerLab.getTermID()));

            if (!registerLab.getReplaced().equals("")){
                switchReplaced.setChecked(true);
                editReplaced.setText(df_vn.format(registerLab.getReplaced()));
            }
            else{
                switchReplaced.setChecked(false);
            }
        });

        listRegisterLab.setOnItemLongClickListener((parent, view, position, id) -> {
            final RegisterLab item = adapterRegisterLab.getItem(position);
            new AlertDialog.Builder(RegisterLabActivity.this)
                    .setTitle("Bạn có muốn xoá không?")
                    .setMessage("Xoá thông tin đăng ký: " + item.getRegisterID() + "?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        boolean result = SqLiteHelper.getInstance(RegisterLabActivity.this).Delete_RegisterLab(item);
                        if (result){
                            Toast.makeText(RegisterLabActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                            curIndexSelected = -1;
                        }
                        else{
                            Toast.makeText(RegisterLabActivity.this, "Xoá không thành công", Toast.LENGTH_SHORT).show();
                        }
                        refreshDataForListview();
                    })
                    .setNegativeButton("Không", (dialog, which) -> dialog.dismiss())
                    .show();
            return false;
        });
    }

    void refreshDataForListview(){
        try{
            List<RegisterLab> dangKyList = SqLiteHelper.getInstance(this).GetAllRegisterLab();
            adapterRegisterLab = new ArrayAdapter<>(RegisterLabActivity.this, android.R.layout.simple_list_item_1, dangKyList);
            listRegisterLab.setAdapter(adapterRegisterLab);
            adapterRegisterLab.notifyDataSetChanged();
        }
        catch (Exception e){
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

}