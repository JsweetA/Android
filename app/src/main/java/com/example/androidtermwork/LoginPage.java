package com.example.androidtermwork;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtermwork.util.StatusBarUtil;
@RequiresApi(api = Build.VERSION_CODES.M)
public class LoginPage extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true
    private ImageView imageView_password;
    private ImageView inputDel;  //一键清除输入内容
    public static ProgressDialog mDefaultDialog; //加载中对话框

    //加载中对话框：正在进行网络请求

    /**
     * setCancelable默认为true，表示可以触摸屏幕或者按后退键关闭对话框。如果改为false，那么必须要使用代码来控制关闭对话框，触摸屏幕或者按后退键都是无法关闭对话框。
     * setCanceledOnTouchOutside 默认true表示触摸屏幕可以关闭对话框。如果改为false，表示按后退键可以退出对话框，触摸屏幕不会退回对话框。
     */
    public static void showCommonDialog(Context context) {
        mDefaultDialog = new ProgressDialog(context);
        //默认就是小圆圈的那种形式
        mDefaultDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
        mDefaultDialog.setMessage("正在进行网络请求...");
        // mDefaultDialog.setCancelable(true);
        mDefaultDialog.setCanceledOnTouchOutside(false);
        mDefaultDialog.show();
    }

    //延时关闭加载中对话框
    @SuppressLint("HandlerLeak")
    public static Handler dialogHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mDefaultDialog.dismiss();
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        //登录
        OnButtonToLogin();
        //注册
        OnButtonToRegister();
        //初始化页面底部可点击文本
        initView();
        //密码显示或隐藏
        showOrHide();
        //清空输入框
        inputClear();
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

    public void OnButtonToLogin() {
        final EditText phoneEdit = findViewById(R.id.userPhone);
        final EditText passwordEdit = findViewById(R.id.userPassword);

        final Button btn_login = findViewById(R.id.submit);
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                final String input_phone = phoneEdit.getText().toString().trim();
                String input_password = passwordEdit.getText().toString().trim();
                // 不为空
                if (input_phone.length() != 0 && input_password.length() != 0) {
                    // 密码长度大于等于6且小于等于20
                    if (input_password.length() >= 6 && input_password.length() <= 20) {
                        // 通过接口判断密码是否正确
                        if (input_password.length() != 0) {
                            //加载中对话框
                            showCommonDialog(LoginPage.this);
                            try {
                                Intent intent = new Intent(LoginPage.this, MainActivity.class);
                                startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            dialogHandler.sendEmptyMessageDelayed(0, 1000);
                        } else {
                            builder = new AlertDialog.Builder(LoginPage.this)
                                    .setMessage("号码或者密码不正确")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //ToDo: 关闭弹窗
                                            dialogInterface.dismiss();
                                        }
                                    });
                            builder.create().show();
                        }
                    } else {
                        builder = new AlertDialog.Builder(LoginPage.this)
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
                } else {
                    Toast.makeText(LoginPage.this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void OnButtonToRegister() {
        Button regis = findViewById(R.id.register);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, RegisterFirstPage.class);
                startActivity(intent);
            }
        });
    }

    //初始化页面底部可点击文本

    public void initView() {
        TextView textView1 = findViewById(R.id.retrieve);
        TextView textView2 = findViewById(R.id.loss);
        SpannableStringBuilder stringBuilder1 = new SpannableStringBuilder("找回密码");
        SpannableStringBuilder stringBuilder2 = new SpannableStringBuilder("账号挂失");
        TextViewSpan1 span1 = new TextViewSpan1();
        TextViewSpan2 span2 = new TextViewSpan2();
        //找回密码
        stringBuilder1.setSpan(span1, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView1.setText(stringBuilder1);
        //账号挂失
        stringBuilder2.setSpan(span2, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView2.setText(stringBuilder2);
        //一定要记得设置这个方法  不然不起作用
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        //取消文本的点击背景
        textView1.setHighlightColor(getResources().getColor(android.R.color.transparent));
        textView2.setHighlightColor(getResources().getColor(android.R.color.transparent));
        //修改状态栏字体为黑色
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
    }

    //找回密码
    private class TextViewSpan1 extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            //设置是否有下划线
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            //点击事件
            Intent intent = new Intent(LoginPage.this, RetrievePasswordPage.class);
            startActivity(intent);
            //点击后设置可点击文本的字体颜色
//            TextView textView = findViewById(R.id.retrieve);
//            textView.setTextColor(Color.parseColor("#6495ED"));
        }
    }

    //账号挂失
    private class TextViewSpan2 extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            //设置是否有下划线
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            //点击事件
            Intent intent = new Intent(LoginPage.this, LossPage.class);
            startActivity(intent);
        }
    }
}
