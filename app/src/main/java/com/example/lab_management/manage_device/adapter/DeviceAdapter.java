package com.example.lab_management.manage_device.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab_management.R;
import com.example.lab_management.objects.Device;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.objects.VerifyReport;
import com.example.lab_management.sqlhelper.SqLiteHelper;

import java.util.List;

public class DeviceAdapter extends BaseAdapter {
    Activity context;
    int layoutId;
    List<Device> listDevice;

    public DeviceAdapter(Activity context1, int layoutId, List<Device> list) {
        this.context = context1;
        this.layoutId = layoutId;
        this.listDevice = list;
    }

    public List<Device> getList() {
        return listDevice;
    }

    public void setList(List<Device> listDevice) {
        this.listDevice = listDevice;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return listDevice!=null? listDevice.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return listDevice.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent) {
        convertView = context.getLayoutInflater().inflate(layoutId, null);
        if(listDevice.size()<=0)
            return convertView;
        SqLiteHelper sqliteHelper = SqLiteHelper.getInstance(context);
        List<Lab> labs = sqliteHelper.Get_Lab();
        TextView tv_header, tv_tenPhong,tv_tentb,tv_loaitb,tv_tinhtrang, tv_ghichu,tv_ngaylap;
        tv_header = convertView.findViewById(R.id.tvSTT);
        tv_tentb = convertView.findViewById(R.id.tvTenTb);
        tv_loaitb = convertView.findViewById(R.id.tvLoaiTb);
        tv_tinhtrang = convertView.findViewById(R.id.tvTinhTrang);
        tv_ngaylap = convertView.findViewById(R.id.tvNgayLap);
        tv_ghichu = convertView.findViewById(R.id.tvGhiChu);
        tv_tenPhong = convertView.findViewById(R.id.tvLab);
        Device dv = listDevice.get(position);

        Lab pth = null;

        for (Lab lab : labs){
            if(lab.getLab_ID() == dv.getMaPhong())
            {
                pth = lab;
                break;
            }
        }
        if(pth!= null){
            tv_tenPhong.setText("Tên phòng: "+pth.getLab_Name());

        } else tv_tenPhong.setText("Tên phòng: "+dv.getMaPhong());
        String strHeader = "Mã thiết bị: "+dv.getMaTb() +"";
        tv_header.setText(strHeader);

        tv_tentb.setText("Tên thiết bị: "+dv.getTenTb());
        tv_loaitb.setText("Loại thiết bị: "+dv.getLoaitb());
        tv_tinhtrang.setText("Tình trạng: "+dv.getTinhtrang());
        tv_ngaylap.setText("Ngày lắp: "+dv.getNgaynhap());
        tv_ghichu.setText("Ghi chú: "+dv.getGhichu());
        return convertView;
    }
}
