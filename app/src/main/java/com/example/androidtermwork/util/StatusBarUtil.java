package com.example.androidtermwork.util;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

/**
 * 状态栏工具类
 */
public class StatusBarUtil {

    /**
     * 设置状态栏文字颜色（Android 6.0及以上）
     * @param activity
     * @param dark 是否为黑色
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}
