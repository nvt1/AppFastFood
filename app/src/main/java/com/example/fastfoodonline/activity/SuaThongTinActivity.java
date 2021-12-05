package com.example.fastfoodonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fastfoodonline.R;
import com.example.fastfoodonline.retrofit.ApiBanHang;
import com.example.fastfoodonline.retrofit.RetrofitClient;
import com.example.fastfoodonline.utils.Utils;
import com.google.gson.Gson;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SuaThongTinActivity extends AppCompatActivity {
    EditText email, mobile, username;
    Toolbar toolbar;
    Button btnluu;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin);



        initView();
        initControl();
    }
    private void initView() {
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);

        toolbar = findViewById(R.id.toolbar);
        email = findViewById(R.id.edemail);
        mobile = findViewById(R.id.edsdt);
        username = findViewById(R.id.edten);
        btnluu = findViewById(R.id.btnluu);
    }

    private void initControl() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        username.setText(Utils.user_current.getUsername());
        email.setText(Utils.user_current.getEmail());
        mobile.setText(Utils.user_current.getMobile());

        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luuThongTin();
            }
        });

    }

    private void luuThongTin() {


        String str_email = Utils.user_current.getEmail();
        String str_sdt = Utils.user_current.getMobile();
        String str_name = Utils.user_current.getUsername();
        int id = Utils.user_current.getId();


        str_email = email.getText().toString().trim();
        str_sdt = mobile.getText().toString().trim();
        str_name = username.getText().toString().trim();

        if(TextUtils.isEmpty(str_email)){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Email", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(str_sdt)){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Mobile", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(str_name)){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Username", Toast.LENGTH_SHORT).show();
        }else{

                //pass data
                compositeDisposable.add(apiBanHang.suaThongTin( id,str_name, str_sdt)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if(userModel.isSuccess()){
                                        Toast.makeText(getApplicationContext(), "Cập nhật thông tin  thành công", Toast.LENGTH_SHORT).show();
                                        Log.d("test", new Gson().toJson(Utils.user_current.getId()));
                                        Utils.user_current.setUsername(username.getText().toString().trim());
                                        Utils.user_current.setMobile(mobile.getText().toString().trim());
                                        //cai email chi cho hien len chu khong cho sua .

                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                },
                                throwable -> {
                                    Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                        ));

        }

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}