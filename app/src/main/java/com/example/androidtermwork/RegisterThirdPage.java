package com.example.androidtermwork;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtermwork.util.StatusBarUtil;

public class RegisterThirdPage extends AppCompatActivity {
    private ImageView inputDel;  //一键清除输入内容
    private Button intoApp; //进入学习强国

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_third_page);

        OnButtonIntoApp();
        //清空输入框
        inputClear();
        //设置状态栏字体为黑色
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
    }

    //点击进入学习强国
    public void OnButtonIntoApp() {
        intoApp = findViewById(R.id.submit_intoApp);
        intoApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    LoginPage.showCommonDialog(RegisterThirdPage.this);
                }
                try {
                    Intent intent = new Intent(RegisterThirdPage.this, LoginPage.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    LoginPage.dialogHandler.sendEmptyMessageDelayed(0,1000);
                }
            }
        });
    }

    /**
     * 清空输入框
     */
    private void inputClear() {
        //设置监听器
        final EditText input = findViewById(R.id.userName);
        inputDel = findViewById(R.id.del);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (input.getEditableText().length() >= 1) {
                    inputDel.setVisibility(View.VISIBLE);
                } else {
                    inputDel.setVisibility(View.GONE);
                }
            }
        });
        inputDel.setOnClickListener(new View.OnClickListener() {
            @Override
            //点击事件（清空）
            public void onClick(View view) {
                input.setText("");
            }
        });
    }
}
