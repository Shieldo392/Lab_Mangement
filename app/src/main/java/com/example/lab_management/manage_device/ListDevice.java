package com.example.lab_management.manage_device;

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
import com.example.lab_management.manage_device.adapter.DeviceAdapter;
import com.example.lab_management.manage_verify.EditVerify;
import com.example.lab_management.manage_verify.ListVerify;
import com.example.lab_management.manage_verify.adapter.VerifyAdapter;
import com.example.lab_management.objects.Device;
import com.example.lab_management.objects.VerifyReport;
import com.example.lab_management.sqlhelper.SqLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class ListDevice extends AppCompatActivity {
    RelativeLayout btn_add;
    ListView lv_device;
    DeviceAdapter deviceAdapter = null;
    List<Device> list = new ArrayList<>();
    SqLiteHelper sqliteHelper = null;
    ImageView imv_nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_device);
        getWidgets();

        sqliteHelper = SqLiteHelper.getInstance(ListDevice.this);
        list = sqliteHelper.Get_Device();
        if (list.size()<=0)
            imv_nodata.setVisibility(View.VISIBLE);
        deviceAdapter = new DeviceAdapter(ListDevice.this, R.layout.item_device, list);
        lv_device.setAdapter(deviceAdapter);

        lv_device.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ListDevice.this)
                        .setMessage("Bạn có muốn xóa không?")
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Device device = list.get(position);
                                int rowID = sqliteHelper.Delete_Device_By_ID(device.getMaTb());
                                if (rowID != -1) {
                                    Toast.makeText(ListDevice.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                                    list = sqliteHelper.Get_Device();
                                    deviceAdapter.setList(list);
                                    deviceAdapter.notifyDataSetChanged();
                                } else
                                    Toast.makeText(ListDevice.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }).setTitle("Xác nhận");
                alert.show();
                return false;
            }
        });
        lv_device.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListDevice.this, EditDevice.class);
                Bundle bundle = new Bundle();
                Device device = list.get(position);
                bundle.putParcelable("device", device);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListDevice.this, AddDeviceActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getWidgets() {
        btn_add = findViewById(R.id.btn_add);
        lv_device = findViewById(R.id.lv_device);
        imv_nodata = findViewById(R.id.imv_no_data);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(deviceAdapter!=null){
            list = sqliteHelper.Get_Device();
            deviceAdapter.setList(list);
            deviceAdapter.notifyDataSetChanged();
        }
    }
}