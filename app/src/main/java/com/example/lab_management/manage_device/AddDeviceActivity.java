package com.example.lab_management.manage_device;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class AddDeviceActivity extends AppCompatActivity {
    EditText editNgayNhap, editGhiChu;
    Button btnPickTime;
    AutoCompleteTextView autoTenTb;
    RadioGroup rad;
    RadioButton radTot, radHong;
    Spinner spnTenphong, spnLoaiTb;

    List<Lab> labs = new ArrayList<>();
    ListView lv_device;
    LabArrayAdapter adapter_lab;
    Device device;
    DeviceAdapter deviceAdapter = null;
    List<Device> listDevice = new ArrayList<>();
    int curIndexSelected = -1;

    List<String> loaitb;

    ArrayList<String> arrAuto = new ArrayList<String>();
    ArrayAdapter<String> adapterAuto;

    ArrayAdapter<String> adapterLoaitb;
    SqLiteHelper sqliteHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        sqliteHelper = SqLiteHelper.getInstance(this);
        deviceAdapter = new DeviceAdapter(this, R.layout.item_device, listDevice);
        lv_device.setAdapter(deviceAdapter);
        getwidgets();
        getSpinnerDat();
    }
    void getSpinnerDat(){
        labs = sqliteHelper.Get_Lab();
        //setDeviceListEachLab(labs);
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
    }
    void processEdit(){
        if(curIndexSelected  >= 0){
            if(curIndexSelected  >= 0){
                Lab lab = (Lab) spnTenphong.getSelectedItem();
                Device dv = new Device();
                String tinhTrang = "";
                if(radTot.isChecked()){
                    tinhTrang="Tốt";
                }
                else if(radHong.isChecked()){
                    tinhTrang = "Hỏng";
                }
                dv = new Device(listDevice.get(curIndexSelected).getMaTb(),autoTenTb.getText().toString(),
                        spnLoaiTb.getSelectedItem().toString(),lab.getLab_ID(),tinhTrang,editNgayNhap.getText().toString(),editGhiChu.getText().toString());
                long result = sqliteHelper.Update_Device_By_ID(dv);
                if(result<=0)
                {
                    Toast.makeText(AddDeviceActivity.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AddDeviceActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                finish();
                processAutoTenTb(autoTenTb.getText().toString());
            }
        }
    }
    private void processAdd(){
        //Lab lab = (Lab) sp_lab.getSelectedItem();
        Lab lab = (Lab) spnTenphong.getSelectedItem();
        String tenTb = autoTenTb.getText().toString().trim();
        String loaitb = spnLoaiTb.getSelectedItem().toString();
        String tinhTrang = "";
        if(radTot.isChecked()){
            tinhTrang="Tốt";
        }
        else if(radHong.isChecked()){
            tinhTrang = "Hỏng";
        }
        String ngaylap = editNgayNhap.getText().toString().trim();
        String ghiChu = editGhiChu.getText().toString().trim();
        Device dv = new Device(0,tenTb,loaitb,lab.getLab_ID(),tinhTrang,ngaylap,ghiChu);
        long result = sqliteHelper.Insert_Device(dv);
        if(result==-1)
        {
            Toast.makeText(AddDeviceActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(AddDeviceActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();

        clearText();
        processAutoTenTb(tenTb);
    }

    private void clearText(){
        radTot.setChecked(true);
        radHong.setChecked(true);
        editGhiChu.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option_device, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
            case R.id.mn_edit_device:
                AlertDialog.Builder builder = new AlertDialog.Builder(AddDeviceActivity.this)
                        .setTitle("Xác nhận")
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        })
                        .setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                processEdit();
                            }
                        })
                        .setMessage("Bạn có muốn sửa không?");
                builder.show();
                break;
            case R.id.mn_cancel_add_dv:
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
        return super.onOptionsItemSelected(item);
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