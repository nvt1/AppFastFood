package com.example.fastfoodonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fastfoodonline.R;
import com.example.fastfoodonline.retrofit.ApiBanHang;
import com.example.fastfoodonline.retrofit.RetrofitClient;
import com.example.fastfoodonline.utils.Utils;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class ThongTinActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnsuathongtin;
    TextView txtten, txtemail, txtsdt;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);



        initView();
        initControl();
    }
    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        btnsuathongtin = findViewById(R.id.btnsuathongtin);
        txtten = findViewById(R.id.txtten);
        txtemail = findViewById(R.id.txtemail);
        txtsdt = findViewById(R.id.txtsdt);
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

        txtten.setText(Utils.user_current.getUsername());
        txtemail.setText(Utils.user_current.getEmail());
        txtsdt.setText(Utils.user_current.getMobile());


        btnsuathongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SuaThongTinActivity.class);
                startActivity(intent);
            }
        });
    }
}