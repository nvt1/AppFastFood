package com.example.fastfoodonline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.fastfoodonline.R;
import com.example.fastfoodonline.retrofit.ApiBanHang;
import com.example.fastfoodonline.retrofit.RetrofitClient;
import com.example.fastfoodonline.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class XemDonActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_don);

        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        compositeDisposable.add(apiBanHang.xemDonHang(Utils.user_current.getId())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                donHangModel -> {
                    Toast.makeText(getApplicationContext(),donHangModel.getResult().get(0).getItem().get(1).getTensp(), Toast.LENGTH_SHORT).show();

                },
                throwable -> {

                }
        ));
    }
}