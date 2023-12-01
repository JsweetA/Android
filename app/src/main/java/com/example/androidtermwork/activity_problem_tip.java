package com.example.androidtermwork;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.androidtermwork.util.StatusBarUtil;

public class activity_problem_tip extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_tip);
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
        TextView da =(TextView) findViewById(R.id.daan);
        String str2="中华苏维埃第一次全国代表大会于1931年11月在江西<font color='#FE0101'>瑞金</font>召开，中华苏维埃共和国临时中央政府宣布成立。</font>";
        da.setText(Html.fromHtml(str2));
    }
}