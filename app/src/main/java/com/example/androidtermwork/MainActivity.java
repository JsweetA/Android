package com.example.androidtermwork;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    static final int POINTS_PAGE = 0x36f2;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        // 绑定fragment组件
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //顶部菜单栏
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        initView();
    }

    //初始化可点击文本
    public void initView() {
        int score = 9000;
        TextView scoreText = findViewById(R.id.score);
        TextView ownText = findViewById(R.id.own);
        scoreText.setText(String.valueOf(score));

        SpannableStringBuilder stringBuilder1 = new SpannableStringBuilder(scoreText.getText());
        SpannableStringBuilder stringBuilder2 = new SpannableStringBuilder(ownText.getText());
        MainActivity.TextViewSpan1 span1 = new MainActivity.TextViewSpan1();
        MainActivity.TextViewSpan2 span2 = new MainActivity.TextViewSpan2();
        //积分
        stringBuilder1.setSpan(span1, 0, scoreText.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        scoreText.setText(stringBuilder1);
        scoreText.setMovementMethod(LinkMovementMethod.getInstance());
        //我的
        stringBuilder2.setSpan(span2, 0, ownText.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ownText.setText(stringBuilder2);
        ownText.setMovementMethod(LinkMovementMethod.getInstance());
        //取消文本的点击背景
        scoreText.setHighlightColor(getResources().getColor(android.R.color.transparent));
        ownText.setHighlightColor(getResources().getColor(android.R.color.transparent));
    }

    //积分
    private class TextViewSpan1 extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            //设置是否有下划线
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            //点击事件
            //Toast.makeText(MainActivity.this, "点击积分", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, PointsPage.class);
            startActivityForResult(intent, POINTS_PAGE);
        }
    }

    //我的
        private class TextViewSpan2 extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            //设置是否有下划线
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            //点击事件
            Intent intent = new Intent(MainActivity.this,MinePage.class);
            startActivity(intent);
        }
    }

    /**
     * 获取子Activity返回值
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CANCELED || data == null)
            return;
        if (requestCode == POINTS_PAGE){
           handlePointsPageResult(data.getStringExtra("goAction"));
        }
    }

    /**
     * 根据“学习积分”Activity的返回值，导航至不同的Tab
     * @param goAction
     */
    private void handlePointsPageResult(String goAction)
    {
        switch (goAction){
            //如果点击的是“我要选读文章”的去做任务，则导航到第二个Tab
            case "res_article":navController.navigate(R.id.navigation_dashboard);break;
            case "res_tv":navController.navigate(R.id.navigation_notifications);break;
        }
    }
}
