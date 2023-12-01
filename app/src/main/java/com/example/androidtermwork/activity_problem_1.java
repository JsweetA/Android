package com.example.androidtermwork;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.androidtermwork.util.StatusBarUtil;

public class activity_problem_1 extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_1);
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
        TextView tv=(TextView)findViewById(R.id.text3);
        String str="来源：《中国共产党简史》<font color='#313DC3'>（人民出版社、中国共产党史出版社2021年版）</font>";
        tv.setTextSize(16);
        tv.setText(Html.fromHtml(str));
    }
    public void ts(View view){
        Intent intent = new Intent();
        intent.setClass(activity_problem_1.this, activity_problem_tip.class);
        startActivity(intent);
    }
    public void next(View view){
        Intent intent = new Intent();
        intent.setClass(activity_problem_1.this, activity_problem_2.class);
        startActivity(intent);
        finish();
    }
}