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

public class activity_problem_3 extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_3);
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
        TextView tv=(TextView)findViewById(R.id.from4);
        String str4="来源：《中国共产党简史》<font color='#313DC3'>（人民出版社、中国共产党史出版社2021年版）</font>";
        tv.setTextSize(16);
        tv.setText(Html.fromHtml(str4));
    }
    public void next1(View view){
        Intent intent =new Intent(activity_problem_3.this, activity_problem_result.class);
        startActivity(intent);
        finish();
    }
}