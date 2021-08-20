package com.example.lab_management.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lab_management.R;
import com.example.lab_management.objects.User;
import com.example.lab_management.sqlhelper.SqLiteHelper;
import com.example.lab_management.utils.FakeData;

public class SignUp extends AppCompatActivity {
    EditText edt_name, edt_email, edt_address, edt_birth, edt_username, edt_password, edt_passconfirm;
    Button btnBirth,btnRegister,btnReturn;
    RadioGroup radioGroup;
    RadioButton radNam, radNu;
    SqLiteHelper sqlHelper = null;
    User selectedUser = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWidgets();

        sqlHelper = SqLiteHelper.getInstance(this);
        fakeData();

        setOnClick();
    }

    public void setOnClick() {
        btnBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processBirthDay();
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = edt_password.getText()+"";
                String pass_confirm = edt_passconfirm.getText()+"";
                if (pass.equals(pass_confirm)) {
                    addUser();
                }
                else Toast.makeText(SignUp.this,"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addUser() {
        String gender;
        String name = edt_name.getText()+"";
        String username = edt_username.getText()+"";
        String password = edt_password.getText()+"";
        if(radioGroup.getCheckedRadioButtonId() == R.id.rad_male) {
            gender = "Nam";
        }
        else gender = "Nữ";
        String email = edt_email.getText()+"";
        String address = edt_address.getText()+"";
        String birth = edt_birth.getText()+"";
        selectedUser.setUserName(username);
        selectedUser.setPassword(password);
        selectedUser.setFullName(name);
        selectedUser.setGender(gender);
        selectedUser.setDateOfBirth(birth);
        selectedUser.setAddress(address);
        selectedUser.setEmail(email);
        long flag = sqlHelper.Insert_User(selectedUser);
        if(flag != -1) {
            Toast.makeText(SignUp.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
        } else Toast.makeText(SignUp.this,"Đăng ký không thành công",Toast.LENGTH_SHORT).show();
    }

    public void processBirthDay() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edt_birth.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,callBack,1990,9,25);
        datePickerDialog.setTitle("Birthday");
        datePickerDialog.show();
    }

    public void fakeData() {
        FakeData.InsertUser(SignUp.this);
    }

    public void getWidgets() {
        edt_username = findViewById(R.id.editUsername);
        edt_password = findViewById(R.id.editPassword);
        edt_passconfirm = findViewById(R.id.editPassConfirm);
        edt_name = findViewById(R.id.editTen);
        edt_email = findViewById(R.id.edit_email);
        edt_address = findViewById(R.id.edt_diachi);
        edt_birth = findViewById(R.id.editNgaySinh);
        btnBirth = findViewById(R.id.btnNgaySinh);
        btnRegister = findViewById(R.id.btn_register);
        btnReturn = findViewById(R.id.btn_signup_cancel);
        radioGroup = findViewById(R.id.rad_group);
        radNam = findViewById(R.id.rad_male);
        radNu = findViewById(R.id.rad_female);
    }
}