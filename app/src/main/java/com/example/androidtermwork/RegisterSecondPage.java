package com.example.androidtermwork;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.androidtermwork.util.StatusBarUtil;

public class RegisterSecondPage extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private Button next; //下一步
    private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true
    private ImageView imageView_password;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_page);
        OnButtonToNext();
        //密码显示或隐藏
        showOrHide();
        //设置状态栏字体为黑色
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
    }

    public void OnButtonToNext() {
        final EditText passwordEdit = findViewById(R.id.userPassword);
        next = findViewById(R.id.submit_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_password = passwordEdit.getText().toString().trim();
                // 密码长度大于等于6且小于等于20
                if (input_password.length() >= 6 && input_password.length() <= 20) {
                    // 通过接口设置密码
                    Intent intent = new Intent(RegisterSecondPage.this, RegisterThirdPage.class);
                    startActivity(intent);
                } else {
                    builder = new AlertDialog.Builder(RegisterSecondPage.this)
                            .setMessage("普通成员的密码需要6~20位字符；为保证安全性，请在大写英文字母，小写英文字母，数字和特殊符号中选中至少两项组合为密码。")
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
     * 密码显示或隐藏 （切换）
     */
    private void showOrHide() {
        final EditText password = findViewById(R.id.userPassword);
        imageView_password = findViewById(R.id.imageView);
        imageView_password.setImageResource(R.mipmap.visibleoff);
        imageView_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHideFirst == true) {
                    imageView_password.setImageResource(R.mipmap.visibleon);
                    //密文
                    HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                    password.setTransformationMethod(method1);
                    isHideFirst = false;
                } else {
                    imageView_password.setImageResource(R.mipmap.visibleoff);
                    //密文
                    TransformationMethod method = PasswordTransformationMethod.getInstance();
                    password.setTransformationMethod(method);
                    isHideFirst = true;

                }
                // 光标的位置
                int index = password.getText().toString().length();
                password.setSelection(index);
            }
        });
    }
}
