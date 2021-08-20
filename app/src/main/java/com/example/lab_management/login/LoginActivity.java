package com.example.lab_management.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab_management.MainActivity;
import com.example.lab_management.R;
import com.example.lab_management.sqlhelper.SqLiteHelper;


public class LoginActivity extends AppCompatActivity {
    Button btn_login,btn_signup;
    EditText edt_username,edt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWidget();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    public void login() {
        String user = edt_username.getText()+"";
        String pass = edt_password.getText()+"";
        if(user.equals("") || pass.equals("")) {
            Toast.makeText(this,"Tên đăng nhập và mật khẩu không được để trống",Toast.LENGTH_SHORT).show();
        }
        else if(null!=checkUser(user,pass)) {
            String userDB = checkUser(user,pass);
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("user_name",userDB);
            startActivity(intent);
        }
        else {
            Toast.makeText(this,"Tên tài khoản hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
            edt_username.setText("");
            edt_password.setText("");
            edt_username.requestFocus();
        }
    }

    public String checkUser(String user_check, String pass_check) {
        SQLiteDatabase db = openOrCreateDatabase(SqLiteHelper.Database_Name, Context.MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("select * from USER where username = ? and password = ? ",new String[]{user_check,pass_check});
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            SharedPreferences.Editor sp = getSharedPreferences("username",MODE_PRIVATE).edit();
            sp.putString("user_name",username);
            sp.apply();
            cursor.close();
            return username;
        }
        return null;
    }

    private void getWidget(){
        btn_signup = findViewById(R.id.btn_signup);
        btn_login = findViewById(R.id.btn_login);
        edt_username = findViewById(R.id.edt_User);
        edt_password = findViewById(R.id.edt_Pass);
    }
}