package com.example.androidtermwork;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.androidtermwork.util.StatusBarUtil;

public class MinePage extends AppCompatActivity {

    private Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page);
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
        toolbar = findViewById(R.id.mine_toolbar);
        eventListener();
    }
    private void eventListener() {
        //右上角返回
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 返回
            }
        });
    }
}