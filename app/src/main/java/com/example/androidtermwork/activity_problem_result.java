package com.example.androidtermwork;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidtermwork.util.StatusBarUtil;

public class activity_problem_result extends AppCompatActivity {
    private Button btn;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_result);
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
        btn = (Button) findViewById(R.id.btn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_problem_result.this,PointsPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}