package com.example.androidtermwork;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
//        //设置全屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        //隐藏顶部标题栏
//        getSupportActionBar().hide();
        //设置延时时间
        handler.sendEmptyMessageDelayed(0,1000);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //实现页面的跳转
            Intent intent = new Intent(WelcomePage.this, LoginPage.class);
            startActivity(intent);
            finish();
            super.handleMessage(msg);
        }
    };
}
