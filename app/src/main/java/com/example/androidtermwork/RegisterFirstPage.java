package com.example.androidtermwork;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtermwork.util.StatusBarUtil;

public class RegisterFirstPage extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private ImageView inputDel;  //一键清除输入内容
    private Button next; //下一步

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_password_page);
        //复用找回密码页面，将“请输入手机号”替换为“新用户注册”
        TextView textView = findViewById(R.id.fontText);
        textView.setText("新用户注册");

        OnButtonToNext();
        //清空输入框
        inputClear();
        //设置状态栏字体为黑色
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
    }

    public void OnButtonToNext() {
        final EditText phoneNumber = findViewById(R.id.userPhone);
        next = findViewById(R.id.submit_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_phoneNumber = phoneNumber.getText().toString().trim();
                // 密码长度大于等于6且小于等于20
                if (input_phoneNumber.length() == 11) {
                    // 通过接口设置密码
                    Intent intent = new Intent(RegisterFirstPage.this, RegisterSecondPage.class);
                    startActivity(intent);
                } else {
                    builder = new AlertDialog.Builder(RegisterFirstPage.this)
                            .setMessage("请输入11位手机号。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //ToDo: 关闭弹窗
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create().show();
                }
            }
        });
    }

    /**
     * 清空输入框
     */
    private void inputClear() {
        //设置监听器
        final EditText input = findViewById(R.id.userPhone);
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
