package com.example.lab_management.manage_device;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lab_management.R;
import com.example.lab_management.manage_device.adapter.DeviceAdapter;
import com.example.lab_management.manage_verify.EditVerify;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDeviceActivity extends AppCompatActivity {
    EditText editNgayNhap, editGhiChu;
    Button btnPickTime;
    AutoCompleteTextView autoTenTb;
    RadioGroup rad;
    RadioButton radTot, radHong;
    Spinner spnTenphong, spnLoaiTb;
    ImageButton imv_optionmenu;

    List<Lab> labs = new ArrayList<>();
    LabArrayAdapter adapter_lab;

    DeviceAdapter deviceAdapter = null;


    List<String> loaitb;

    ArrayList<String> arrAuto = new ArrayList<String>();
    ArrayAdapter<String> adapterAuto;

    ArrayAdapter<String> adapterLoaitb;
    SqLiteHelper sqliteHelper = null;
    PopupMenu popupMenu = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        sqliteHelper = SqLiteHelper.getInstance(this);
        getwidgets();
        getSpinnerDat();
        setOnClick();
    }
    void getSpinnerDat(){
        labs = sqliteHelper.Get_Lab();
        adapter_lab = new LabArrayAdapter(AddDeviceActivity.this, R.layout.item_spinner, labs);
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
        imv_optionmenu = findViewById(R.id.option_menu);
    }
    public void setOnClick(){
        btnPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String dayOfMonthString = String.format("%02d",dayOfMonth);
                        String monthString = String.format("%02d",month+1);
                        String yearString = String.format("%04d",year);
                        editNgayNhap.setText(dayOfMonthString+"/"+monthString+"/"+yearString);
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(AddDeviceActivity.this,listener,2021,7,1);
                dialog.setTitle("Ngày nhập thiết bị");
                dialog.show();
            }
        });
        popupMenu = new PopupMenu(this, imv_optionmenu);
        popupMenu.inflate(R.menu.menu_option_device);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mn_add_device:
                        AlertDialog.Builder al = new AlertDialog.Builder(AddDeviceActivity.this)
                                .setTitle("Xác nhận")
                                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                })
                                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        processAdd();
                                    }
                                })
                                .setMessage("Bạn có muốn thêm không?");
                        al.show();
                        break;
                    case R.id.mn_cancel:
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddDeviceActivity.this)
                                .setTitle("Message")
                                .setMessage("Bạn có thực sự muốn hủy?")
                                .setPositiveButton(android.R.string.yes,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        })
                                .setNegativeButton(android.R.string.no, null);
                        alertDialog.show();
                        break;
                    }
                return false;
            }

        });
        imv_optionmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
    }
    private void processAdd(){

        Lab lab = (Lab) spnTenphong.getSelectedItem();
        String tenTb = autoTenTb.getText()+"";
        String loaitb = spnLoaiTb.getSelectedItem().toString();
        String tinhTrang = "";
        if(radTot.isChecked()){
            tinhTrang="Tốt";
        }
        else if(radHong.isChecked()){
            tinhTrang = "Hỏng";
        }
        Pattern special = Pattern.compile("[!@#$,?.*%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(tenTb);
        String ngaylap = editNgayNhap.getText().toString().trim();
        String ghiChu = editGhiChu.getText().toString().trim();
        if(tenTb.isEmpty()){
            Toast.makeText(getBaseContext(), "Vui lòng nhập tên thiết bị thông tin", Toast.LENGTH_SHORT).show();
        }
        else if(hasSpecial.find()==true){
            Toast.makeText(getBaseContext(), "Tên thiết bị không được chứa ký tự đặc biệt", Toast.LENGTH_SHORT).show();
        }
        else{
            Device dv = new Device(0,tenTb,loaitb,lab.getLab_ID(),tinhTrang,ngaylap,ghiChu);
            processAutoTenTb(tenTb);
            long result = sqliteHelper.Insert_Device(dv);
            if(result==-1)
            {
                Toast.makeText(AddDeviceActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                return;
            }
            else Toast.makeText(AddDeviceActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            clearText();
        }

    }

    private void clearText(){
        radTot.setChecked(true);
        radHong.setChecked(false);
        autoTenTb.requestFocus();
        autoTenTb.setText("");
        editGhiChu.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option_device, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void processAutoTenTb(String data){
        for(int i=0; i<arrAuto.size(); i++){
            String x = arrAuto.get(i);
            if(x.trim().equalsIgnoreCase(data.trim()))
                return;
        }
        arrAuto.add(data);
        adapterAuto = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrAuto);
        autoTenTb.setAdapter(adapterAuto);
    }
}