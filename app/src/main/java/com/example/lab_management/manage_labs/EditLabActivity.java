package com.example.lab_management.manage_labs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab_management.R;
import com.example.lab_management.objects.Device;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.sqlhelper.SqLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class EditLabActivity extends AppCompatActivity {

    EditText edt_labName, edt_area, edt_floor, edt_note;
    ImageView imv_menu;
    SqLiteHelper sqLiteHelper;
    PopupMenu popupMenu;
    Lab lab;
    List<Device> deviceList = new ArrayList<>();
    TextView tv_mouse_broken, tv_mouse, tv_Monitors, tv_Monitor_broken, tv_cases, tv_cases_broken;
    TextView tv_keyboards, tv_keyboards_broken, tv_projectors, tv_projectors_broken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lab);
        sqLiteHelper = SqLiteHelper.getInstance(EditLabActivity.this);
        GetWidgets();
        GetIntent();
        imv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle_lab");
        lab = bundle.getParcelable("lab");
        if(lab!= null){
            edt_labName.setText(lab.getLab_Name());
            edt_floor.setText(lab.getTang());
            edt_area.setText(lab.getKhuNha());
            edt_note.setText(lab.getGhiChu());
        }

        assert lab != null;

        deviceList = sqLiteHelper.GetListDeviceByLabID(lab.getLab_ID());

        int mouse = 0, mouse_br = 0, monitors = 0, monitors_br = 0, cases = 0, cases_br = 0;
        int kbs = 0, kb_br = 0, prjs = 0, prjs_br = 0;
        for(Device device: deviceList){
            switch (device.getLoaitb()){
                case "Chuột":
                    mouse++;
                    if(device.getTinhtrang().equals("Hỏng"))
                        mouse_br++;
                    break;
                case "Màn hình":
                    monitors++;
                    if(device.getTinhtrang().equals("Hỏng"))
                        monitors_br++;
                    break;
                case "Case":
                    cases++;
                    if(device.getTinhtrang().equals("Hỏng"))
                        cases_br++;
                    break;
                case "Bàn phím":
                    kbs++;
                    if(device.getTinhtrang().equals("Hỏng"))
                        kb_br++;
                    break;
                case "Máy chiếu":
                    prjs++;
                    if(device.getTinhtrang().equals("Hỏng"))
                        prjs_br++;
                    break;
            }
        }
        tv_cases.setText(cases+"");
        tv_cases_broken.setText(cases_br+"");

        tv_keyboards.setText(kbs+"");
        tv_keyboards_broken.setText(kb_br+"");

        tv_Monitor_broken.setText(monitors_br+"");
        tv_Monitors.setText(monitors+"");

        tv_mouse.setText(mouse+"");
        tv_mouse_broken.setText(mouse_br+"");

        tv_projectors.setText(prjs+"");
        tv_projectors_broken.setText(prjs_br+"");


    }

    private void GetWidgets() {
        edt_area = findViewById(R.id.edt_area);
        edt_labName = findViewById(R.id.edt_labName);
        edt_floor = findViewById(R.id.edt_floor);
        edt_note = findViewById(R.id.edt_note);

        imv_menu=  findViewById(R.id.imv_menu);

        tv_mouse_broken = findViewById(R.id.tv_mouse_broken);
        tv_mouse = findViewById(R.id.tv_mouses);
        tv_cases = findViewById(R.id.cases);
        tv_cases_broken = findViewById(R.id.case_broken);
        tv_Monitors = findViewById(R.id.monitor);
        tv_Monitor_broken = findViewById(R.id.monitor_broken);
        tv_keyboards = findViewById(R.id.keyboards);
        tv_keyboards_broken = findViewById(R.id.keyboard_broken);
        tv_projectors = findViewById(R.id.projectors);
        tv_projectors_broken = findViewById(R.id.projector_broken);


        popupMenu = new PopupMenu(EditLabActivity.this, imv_menu);
        popupMenu.inflate(R.menu.menu_edit);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mn_edit:
                        AlertDialog alert = new AlertDialog.Builder(EditLabActivity.this).setTitle("Xác nhận")
                                .setMessage("Bạn có chắc chắn muốn Sửa hay không?")
                                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        processEdit();
                                    }
                                }).setNegativeButton("Hủy", null).create();
                        alert.show();

                        break;
                    case R.id.mn_cancel:
                        finish();
                        break;

                }

                return false;
            }
        });

    }

    private void processEdit() {
        Lab lab = new Lab();
        String labName = edt_labName.getText()+"";
        String area = edt_area.getText()+"";
        String floor = edt_floor.getText()+"";
        String note = edt_note.getText()+"";

        lab.setLab_Name(labName);
        lab.setTang(floor);
        lab.setKhuNha(area);
        lab.setGhiChu(note);
        long result = sqLiteHelper.Insert_Lab(lab);
        if(result!=-1)
        {
            Toast.makeText(EditLabActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(EditLabActivity.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
}