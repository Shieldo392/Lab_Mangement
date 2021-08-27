package com.example.lab_management.manage_labs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.lab_management.R;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.sqlhelper.SqLiteHelper;

public class AddLabActivity extends AppCompatActivity {

    EditText edt_labName, edt_area, edt_floor, edt_note;
    ImageView imv_menu;
    SqLiteHelper sqLiteHelper;
    PopupMenu popupMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lab);
        GetWidgets();
        sqLiteHelper =SqLiteHelper.getInstance(this);
        imv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });


    }

    private void GetWidgets() {
        edt_area = findViewById(R.id.edt_area);
        edt_labName = findViewById(R.id.edt_labName);
        edt_floor = findViewById(R.id.edt_floor);
        edt_note = findViewById(R.id.edt_note);

        imv_menu=  findViewById(R.id.imv_menu);

        popupMenu = new PopupMenu(AddLabActivity.this, imv_menu);
        popupMenu.inflate(R.menu.menu_add);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mn_add:
                        AlertDialog alert = new AlertDialog.Builder(AddLabActivity.this).setTitle("Xác nhận")
                                .setMessage("Bạn có chắc chắn muốn Sửa hay không?")
                                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        processAdd();
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

    private void processAdd() {
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
            Toast.makeText(AddLabActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(AddLabActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
}