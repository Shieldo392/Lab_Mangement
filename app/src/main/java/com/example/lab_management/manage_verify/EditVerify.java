package com.example.lab_management.manage_verify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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

public class EditVerify extends AppCompatActivity implements onStatusClick {

    String note = "";
    String note_device = "";
    TextView tv_Time, tv_maPhieu;
    RadioButton rad_sang, rad_chieu;
    ImageButton btn_date;
    ListView lv_devicie;
    DeviceAdapter deviceApdater;
    List<Device> deviceList = new ArrayList<>();
    Spinner sp_giangVien, sp_lab;
    List<Lab> labs = new ArrayList<>();
    List<User> users = new ArrayList<>();
    UserAdapter adapter_spinner;
    LabArrayAdapter adapter_lab;
    EditText edt_note;
    SqLiteHelper sqliteHelper = null;
    VerifyReport report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_verify);
        sqliteHelper = SqLiteHelper.getInstance(this);

        GetWidgets();
        setAdapter();
        setEvenOnClick();
        GetIntent();

    }

    private void GetIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        report = bundle.getParcelable("report");
        if(report!= null){
            tv_maPhieu.setText(report.getId()+"");
            tv_Time.setText(report.getTime());
            if (report.getShift())
                rad_sang.setChecked(true);
            else rad_chieu.setChecked(true);
            int index_spinenr_gv = -1;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId_user() == (report.getUser_id()))
                    index_spinenr_gv = i;
            }
            int index_spinenr_maPhong = -1;
            for (int i = 0; i < labs.size(); i++) {
                if (labs.get(i).equals(report.getMaPhong()))
                    index_spinenr_maPhong = i;
            }

            sp_giangVien.setSelection(index_spinenr_gv);
            sp_lab.setSelection(index_spinenr_maPhong);
        }
    }

    private void setEvenOnClick() {
        Date date = new Date();
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String str = (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth) + "/"
                        + (month + 1 < 10 ? "0" + (month + 1) : month + 1) + "/" + year + "";
                tv_Time.setText(str);
            }
        };

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(EditVerify.this, callback, 2021, date.getMonth(), date.getDate());
                dialog.setTitle("Chọn thời gian bắt đầu");
                dialog.show();
            }
        });


    }
    private void processEdit() {
        Lab lab = (Lab) sp_lab.getSelectedItem();
        User tenGV =(User) sp_giangVien.getSelectedItem();
        int id = Integer.parseInt(tv_maPhieu.getText() +"");

        String tenPhong = lab.getLab_Name() +"";
        boolean shift = true;
        if (rad_chieu.isChecked())
            shift = false;
        String time = tv_Time.getText() + "";
        if(note_device =="")
            note = "Đầy đủ thiết bị";
        else note = "Thiếu " + note_device;

        String note_final = edt_note.getText()+"(" + note+")";

        VerifyReport verifyReport = new VerifyReport(id, lab.getLab_ID(),tenGV.getId_user(), shift, time, note_final);
        long result = sqliteHelper.Update_Verify_By_ID(verifyReport);
        if(result<=0)
        {
            Toast.makeText(EditVerify.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(EditVerify.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void setAdapter() {
        labs = sqliteHelper.Get_Lab();
        adapter_lab = new LabArrayAdapter(this, R.layout.item_spinner, labs);
        sp_lab.setAdapter(adapter_lab);


        sp_lab.setSelection(0);
        deviceList = labs.get(0).getDeviceList();
        //device
        deviceApdater = new DeviceAdapter(deviceList, EditVerify.this, this);
        lv_devicie.setAdapter(deviceApdater);

        //giaos vien
        users = sqliteHelper.Get_User();
        adapter_spinner = new UserAdapter(this, R.layout.item_spinner, users);
        sp_giangVien.setAdapter(adapter_spinner);

        // verify report
       /* verifyReportArrayAdapter = new VerifyAdapter(this, R.layout.item_report, verifyReports);
        lv_report.setAdapter(verifyReportArrayAdapter);*/


    }

    private void GetWidgets() {
        tv_maPhieu = findViewById(R.id.edt_ma);
        sp_giangVien = findViewById(R.id.sp_giangVien);
        tv_Time = findViewById(R.id.edt_thoiGian);
        rad_sang = findViewById(R.id.rad_sang);
        rad_chieu = findViewById(R.id.rad_chieu);
        btn_date = findViewById(R.id.btn_time);
        sp_lab = findViewById(R.id.sp_maPhong);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        tv_Time.setText(sdf.format(date));

        lv_devicie = findViewById(R.id.lv_device);
        edt_note = findViewById(R.id.edt_note);
    }

    @Override
    public void OnStatusClick(int postion) {
        note_device += deviceList.get(postion).getTenTb() + " ;";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_edit:
                AlertDialog.Builder al = new AlertDialog.Builder(EditVerify.this)
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
                al.show();
                break;
            case R.id.mn_cancel:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditVerify.this)
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
}