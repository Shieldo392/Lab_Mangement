package com.example.lab_management.manage_labs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lab_management.R;
import com.example.lab_management.manage_labs.adapter.LabAdapter;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.sqlhelper.SqLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class ManageLabs extends AppCompatActivity {

    List<Lab> labs;
    ListView lv_labs;
    LabAdapter adapter;
    RelativeLayout rl_addLab;
    SqLiteHelper sqLiteHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_labs);
        GetWidgets();
        SetAdapter();
        SetOnClick();
        GetDataFromSQL();
        
    }

    private void GetDataFromSQL() {
        sqLiteHelper = SqLiteHelper.getInstance(ManageLabs.this);
        labs = sqLiteHelper.Get_Lab();
        adapter.setLabs(labs);
        adapter.notifyDataSetChanged();

    }

    private void SetAdapter() {
        labs = new ArrayList<>();
        adapter = new LabAdapter(ManageLabs.this, labs);
        lv_labs.setAdapter(adapter);
    }

    private void SetOnClick() {
        rl_addLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageLabs.this, AddLabActivity.class);
                startActivity(intent);
            }
        });

        lv_labs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lab lab = labs.get(position);
                Intent intent = new Intent(ManageLabs.this, EditLabActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("lab", lab);
                intent.putExtra("bundle_lab", bundle);

                startActivity(intent);
            }
        });

        lv_labs.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alert = new AlertDialog.Builder(ManageLabs.this).setTitle("Xác nhận")
                        .setMessage("Bạn có chắc chắn muốn xóa hay không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int result = sqLiteHelper.DeleteLabByID(labs.get(position).getLab_ID());
                                if(result== 0){
                                    Toast.makeText(ManageLabs.this, "Xóa không thành công, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(ManageLabs.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                                    labs = sqLiteHelper.Get_Lab();
                                    adapter.setLabs(labs);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }).setNegativeButton("Hủy", null).create();
                alert.show();

                return false;
            }
        });
    }

    private void GetWidgets() {
        lv_labs = findViewById(R.id.lv_labs);
        rl_addLab = findViewById(R.id.btn_add);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!= null)
        {
            labs = sqLiteHelper.Get_Lab();
            adapter.setLabs(labs);
            adapter.notifyDataSetChanged();
        }
    }
}