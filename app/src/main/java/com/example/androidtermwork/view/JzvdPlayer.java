package com.example.androidtermwork.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.app.AppCompatActivity;

import cn.jzvd.JzvdStd;

public class JzvdPlayer extends JzvdStd {

    public JzvdPlayer(Context context) {
        super(context);
    }

    public JzvdPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setScreenNormal() {
        super.setScreenNormal();
        backButton.setVisibility(VISIBLE);
    }

    @Override
    protected void clickBack() {
        if (!backPress() && CURRENT_JZVD.screen == SCREEN_NORMAL) {
            ((AppCompatActivity) jzvdContext).finish();
        }
    }
}
