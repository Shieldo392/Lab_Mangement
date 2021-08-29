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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lab_management.R;
import com.example.lab_management.manage_verify.adapter.LabArrayAdapter;
import com.example.lab_management.manage_verify.adapter.VerifyAdapter;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.objects.VerifyReport;
import com.example.lab_management.sqlhelper.SqLiteHelper;
import com.example.lab_management.utils.FakeData;

import java.util.ArrayList;
import java.util.List;

public class ListVerify extends AppCompatActivity {

    RelativeLayout btn_add;
    ListView lv_verify;
    VerifyAdapter verifyAdapter = null;
    List<VerifyReport> verifyReports = new ArrayList<>();
    SqLiteHelper sqliteHelper = null;
    ImageView imv_nodata;
    Spinner sp_labs;
    LabArrayAdapter labArrayAdapter;
    List<Lab> labs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_verify);
        getWidgets();
        FakeData.Insert_Verify(ListVerify.this);

        sqliteHelper = SqLiteHelper.getInstance(ListVerify.this);
        verifyReports = sqliteHelper.Get_Verify();
        labs = sqliteHelper.Get_Lab();
        SetAdapterSpinner();
        SetOnClick();

    }

    private void SetAdapterSpinner() {
        labs.add(0, new Lab(0, "Tất cả"));
        labArrayAdapter = new LabArrayAdapter(ListVerify.this, R.layout.item_spinner, labs);
        sp_labs.setAdapter(labArrayAdapter);
        sp_labs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    verifyAdapter.setList(verifyReports);
                    labArrayAdapter.notifyDataSetChanged();
                }
                else{
                    Lab lab = labs.get(position);
                    List<VerifyReport> sortByLab = new ArrayList<>();
                    for(VerifyReport verifyReport : verifyReports){
                        if(verifyReport.getLabID() == lab.getLab_ID())
                            sortByLab.add(verifyReport);
                    }
                    verifyAdapter.setList(sortByLab);
                    verifyAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void SetOnClick() {
        if (verifyReports.size()<=0)
            imv_nodata.setVisibility(View.VISIBLE);
        verifyAdapter = new VerifyAdapter(ListVerify.this, R.layout.item_report, verifyReports);
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
                                VerifyReport report = verifyReports.get(position);
                                int rowID = sqliteHelper.Delete_Verify_By_ID(report.getId());
                                if (rowID != -1) {
                                    Toast.makeText(ListVerify.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                                    verifyReports = sqliteHelper.Get_Verify();
                                    verifyAdapter.setList(verifyReports);
                                    verifyAdapter.notifyDataSetChanged();
                                } else
                                    Toast.makeText(ListVerify.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }).setTitle("Xác nhận");
                alert.show();

                return false;
            }
        });

        lv_verify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListVerify.this, EditVerify.class);
                Bundle bundle = new Bundle();
                VerifyReport verifyReport = verifyReports.get(position);
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
        sp_labs = findViewById(R.id.sp_labs);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(verifyAdapter!=null){
            verifyReports = sqliteHelper.Get_Verify();
            verifyAdapter.setList(verifyReports);
            verifyAdapter.notifyDataSetChanged();
            if(verifyReports.size()>0)
                imv_nodata.setVisibility(View.GONE);
        }
    }
}