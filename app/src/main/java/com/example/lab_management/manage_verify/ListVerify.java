package com.example.lab_management.manage_verify;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lab_management.R;
import com.example.lab_management.manage_verify.adapter.VerifyAdapter;
import com.example.lab_management.objects.VerifyReport;
import com.example.lab_management.sqlhelper.SqLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class ListVerify extends AppCompatActivity {

    RelativeLayout btn_add;
    ListView lv_verify;
    VerifyAdapter verifyAdapter = null;
    List<VerifyReport> list = new ArrayList<>();
    SqLiteHelper sqliteHelper = null;
    ImageView imv_nodata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_verify);
        getWidgets();

        sqliteHelper = SqLiteHelper.getInstance(ListVerify.this);
        list = sqliteHelper.Get_Verify();
        if (list.size()<=0)
            imv_nodata.setVisibility(View.VISIBLE);
        verifyAdapter = new VerifyAdapter(ListVerify.this, R.layout.item_report, list);
        lv_verify.setAdapter(verifyAdapter);

        lv_verify.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ListVerify.this)
                        .setMessage("Bạn có muốn xóa không?")
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                VerifyReport report = list.get(position);
                                int rowID = sqliteHelper.Delete_Verify_By_ID(report.getId());
                                if (rowID != -1) {
                                    Toast.makeText(ListVerify.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                                    list = sqliteHelper.Get_Verify();
                                    verifyAdapter.setList(list);
                                    verifyAdapter.notifyDataSetChanged();
                                } else
                                    Toast.makeText(ListVerify.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }).setTitle("Xác nhận");
                alert.show();
                /*VerifyReport report = list.get(position);
                int rowID = sqliteHelper.Delete_Verify_By_ID(report.getId());
                if (rowID != -1) {
                    Toast.makeText(MainActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    list = sqliteHelper.Get_Verify();
                    verifyAdapter.setList(list);
                    verifyAdapter.notifyDataSetChanged();
                } else
                    Toast.makeText(MainActivity.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();*/
                return false;
            }
        });

        lv_verify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListVerify.this, EditVerify.class);
                Bundle bundle = new Bundle();
                VerifyReport verifyReport = list.get(position);
                bundle.putParcelable("report", verifyReport);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListVerify.this, AddNewVerify.class);
                startActivity(intent);
            }
        });

    }



    private void getWidgets() {
        btn_add = findViewById(R.id.btn_add);
        lv_verify = findViewById(R.id.lv_report);
        imv_nodata = findViewById(R.id.imv_no_data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(verifyAdapter!=null){
            list = sqliteHelper.Get_Verify();
            verifyAdapter.setList(list);
            verifyAdapter.notifyDataSetChanged();
        }
    }
}