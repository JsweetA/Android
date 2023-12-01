package com.example.androidtermwork;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class AndroidToastForJs {

    public static String title;
    public static String resource;
    public static String title2;
    public static String time;
    public static String article;
    public static String editor;

    private Context mContext;

    public AndroidToastForJs(Context context) {
        this.mContext = context;
    }

    //webview中调用toast原生组件
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    //以json实现webview与js之间的数据交互
    public String jsontohtml() {
        JSONObject map = null;
        try {
            map = new JSONObject();
            map.put("title", title);
            map.put("resource", resource);
            map.put("title2", title2);
            map.put("time", time);
            map.put("article", article);
            map.put("editor", editor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map.toString();
    }
}
