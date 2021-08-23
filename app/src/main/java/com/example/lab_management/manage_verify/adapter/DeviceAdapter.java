package com.example.lab_management.manage_verify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.example.lab_management.R;
import com.example.lab_management.objects.Device;

import java.util.List;

public class DeviceAdapter extends BaseAdapter {
    List<Device> deviceList;
    Context mContext;
    onStatusClick statusClick;

    public DeviceAdapter(List<Device> deviceList, Context mContext, onStatusClick statusClick) {
        this.deviceList = deviceList;
        this.mContext = mContext;
        this.statusClick = statusClick;
    }

    @Override
    public int getCount() {
        return deviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Device device = deviceList.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.item_dv, parent, false);

        TextView tvDeviceName;
        CheckBox checkBox;
        tvDeviceName = convertView.findViewById(R.id.device_name);
        checkBox = convertView.findViewById(R.id.ckb_status);
        tvDeviceName.setText(device.getTenTb());
        checkBox.setChecked(true);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!checkBox.isChecked())
                    statusClick.OnStatusClick(position);
            }
        });


        return convertView;
    }
}
