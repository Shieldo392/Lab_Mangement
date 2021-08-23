package com.example.lab_management.manage_device;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.lab_management.R;
import com.example.lab_management.manage_verify.adapter.LabArrayAdapter;
import com.example.lab_management.objects.Device;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.sqlhelper.SqLiteHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditDevice extends AppCompatActivity {
    EditText editNgayNhap, editGhiChu;
    Button btnPickTime;
    AutoCompleteTextView autoTenTb;
    RadioGroup rad;
    RadioButton radTot, radHong;
    Spinner spnTenphong, spnLoaiTb;

    List<Lab> labs = new ArrayList<>();

    LabArrayAdapter adapter_lab;
    Device device;
    int curIndexSelected = -1;

    List<String> loaitb;

    ArrayList<String> arrAuto = new ArrayList<String>();
    ArrayAdapter<String> adapterAuto;

    ArrayAdapter<String> adapterLoaitb;
    SqLiteHelper sqliteHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_device);
        sqliteHelper = SqLiteHelper.getInstance(this);
        getwidgets();
        getSpinnerDat();
    }
    void getSpinnerDat(){
        labs = sqliteHelper.Get_Lab();
        //setDeviceListEachLab(labs);
        adapter_lab = new LabArrayAdapter(EditDevice.this, R.layout.item_spinner, labs);
        spnTenphong.setAdapter(adapter_lab);

        spnTenphong.setSelection(0);
        loaitb = Arrays.asList("Màn hình","Case","Chuột","Bàn phím","Máy chiếu");

        adapterLoaitb = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,loaitb);

        adapterLoaitb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnLoaiTb.setAdapter(adapterLoaitb);

        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);
        editNgayNhap.setText(todayAsString);
    }
    public void getwidgets(){
        btnPickTime = findViewById(R.id.btnPickTime);

        editGhiChu = findViewById(R.id.editGhiCHu);
        editNgayNhap = findViewById(R.id.editngay);
        autoTenTb = findViewById(R.id.autoTenTB);
        spnLoaiTb = findViewById(R.id.spnloaitb);
        spnTenphong = findViewById(R.id.spnTenphong);

        rad = findViewById(R.id.radgroup);
        radTot = findViewById(R.id.radtot);
        radHong = findViewById(R.id.radhong);
    }

}