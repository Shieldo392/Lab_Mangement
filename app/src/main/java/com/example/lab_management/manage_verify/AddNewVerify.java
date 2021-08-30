package com.example.lab_management.manage_verify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab_management.R;
import com.example.lab_management.manage_verify.adapter.DeviceAdapter;
import com.example.lab_management.manage_verify.adapter.LabArrayAdapter;
import com.example.lab_management.manage_verify.adapter.UserAdapter;
import com.example.lab_management.manage_verify.adapter.onStatusClick;
import com.example.lab_management.objects.Device;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.objects.User;
import com.example.lab_management.objects.VerifyReport;
import com.example.lab_management.sqlhelper.SqLiteHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddNewVerify extends AppCompatActivity implements onStatusClick {
    String note ="";
    String note_device = "";
    TextView tv_Time;
    RadioButton rad_sang, rad_chieu;
    ImageButton btn_date;
    ListView lv_devices;
    DeviceAdapter deviceApdater;
    List<Device> deviceList = new ArrayList<>();
    Spinner sp_giangVien, sp_lab;
    List<Lab> labs = new ArrayList<>();
    List<User> users = new ArrayList<>();
    UserAdapter adapter_spinner;
    LabArrayAdapter adapter_lab;
    EditText edt_note;
    ImageButton imv_option_menu;
    PopupMenu popupMenu = null;

    SqLiteHelper sqliteHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_verify);
        sqliteHelper = SqLiteHelper.getInstance(this);
        GetWidgets();
        setAdapter();
        setEvenOnClick();


    }

    private void setAdapter() {
        labs = sqliteHelper.Get_Lab();
        setDeviceListEachLab(labs);
        adapter_lab = new LabArrayAdapter(AddNewVerify.this, R.layout.item_spinner, labs);
        sp_lab.setAdapter(adapter_lab);


        sp_lab.setSelection(0);
        deviceList = labs.get(0).getDeviceList();
        //device
        deviceApdater = new DeviceAdapter(deviceList, AddNewVerify.this, this);
        lv_devices.setAdapter(deviceApdater);

        //giaos vien
        users = sqliteHelper.Get_User();
        adapter_spinner = new UserAdapter(this, R.layout.item_spinner, users);
        sp_giangVien.setAdapter(adapter_spinner);


        // verify report
       /* verifyReportArrayAdapter = new VerifyAdapter(this, R.layout.item_report, verifyReports);
        lv_report.setAdapter(verifyReportArrayAdapter);*/


    }

    private void setDeviceListEachLab(List<Lab> labs) {
        for(Lab lab:labs){
            List<Device> devices = sqliteHelper.GetListDeviceByLabID(lab.getLab_ID());
            lab.setDeviceList(devices);
        }
    }

    private void GetWidgets() {
        sp_giangVien = findViewById(R.id.sp_giangVien);
        tv_Time = findViewById(R.id.edt_thoiGian);
        rad_sang = findViewById(R.id.rad_sang);
        rad_chieu = findViewById(R.id.rad_chieu);
        btn_date = findViewById(R.id.btn_time);
        sp_lab = findViewById(R.id.sp_maPhong);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        tv_Time.setText(sdf.format(date));

        lv_devices = findViewById(R.id.lv_device);
        edt_note = findViewById(R.id.edt_note);
        imv_option_menu = findViewById(R.id.option_menu);
    }

    private void processAdd() {
        Lab lab = (Lab) sp_lab.getSelectedItem();
        User tenGV =(User) sp_giangVien.getSelectedItem();

        String tenPhong = lab.getLab_Name() +"";
        boolean shift = true;
        if (rad_chieu.isChecked())
            shift = false;
        String time = tv_Time.getText() + "";
        if(note_device =="")
            note = "Đầy đủ thiết bị";
        else note = "Thiếu " + note_device;

        String note_final = edt_note.getText()+"(" + note+")";

        VerifyReport verifyReport = new VerifyReport(0, lab.getLab_ID(),tenGV.getId_user(), shift, time, note_final);
        long result = sqliteHelper.Insert_Verify(verifyReport);
        if(result==-1)
        {
            Toast.makeText(AddNewVerify.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(AddNewVerify.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
        clearText();
        finish();
    }
    private void clearText(){
        rad_chieu.setChecked(false);
        rad_sang.setChecked(true);
        sp_giangVien.setSelection(0);

    }

    private void setEvenOnClick() {
        Date date = new Date();
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String str = (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth) + "/"
                        + (month + 1 < 10 ? "0" + (month + 1) : month + 1) +"/"+ year + "";
                tv_Time.setText(str);
            }
        };

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(AddNewVerify.this, callback, 2021, date.getMonth(), date.getDate());
                dialog.setTitle("Chọn thời gian bắt đầu");
                dialog.show();
            }
        });

        /*sp_lab.setOnItemSelectedListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deviceList = labs.get(position).getDeviceList();
                deviceApdater.notifyDataSetChanged();
            }
        });*/
        sp_lab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deviceList = sqliteHelper.GetListDeviceByLabID(labs.get(position).getLab_ID());
                deviceApdater.setDeviceList(deviceList);
                deviceApdater.setDeviceList(deviceList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        popupMenu = new PopupMenu(this, imv_option_menu);
        popupMenu.inflate(R.menu.menu_add);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mn_add:
                        AlertDialog.Builder al = new AlertDialog.Builder(AddNewVerify.this)
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
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddNewVerify.this)
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

        imv_option_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });


    }

    @Override
    public void OnStatusClick(int postion) {
        note_device += deviceList.get(postion).getTenTb() +" ;";
    }


}