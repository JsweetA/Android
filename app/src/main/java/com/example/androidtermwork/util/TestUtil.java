package com.example.androidtermwork.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.View;

import com.example.androidtermwork.pojo.TVStation;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * 测试工具类
 */
public class TestUtil
{

    private static Gson gson;

    private static void gsonInit() {
        if (gson == null) {
            synchronized (TestUtil.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
    }
    /**
     * 读取assets下的JSON文件为字符串
     * @param fileName 文件名
     * @param context context
     * @return 字符串 - JSON文件内容
     */
    public static String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * SnakeBar消息提示
     * @param message  要显示的信息
     * @param duration LENGTH_SHORT = -1 or LENGTH_LONG = 0
     * @param base     附加到的视图，往上搜索至最外层组件。通常，最简单的方法是仅传递封装您的内容的最外层组件。
     */
    public static void showSnakeBarMessage(String message, int duration, View base) {

        Snackbar.make(base, message, duration).show();
    }

    /**
     * 获取电视台数据，模拟数据库分页查询，测试下拉加载更多
     * @param page
     * @param context
     * @return
     */
    public static List<TVStation> getTVStationsPaging(int page, Context context){
        if(page > 2){
            return Collections.emptyList();
        }
        gsonInit();
        return gson.fromJson(getJson("mock_data_tv_" + String.format("%02d", page) + ".json", context),
                new TypeToken<List<TVStation>>(){}.getType());
    }

    /**
     * 获取测试数据（Java对象）
     * @param jsonFileName
     * @param typeToken
     * @param context
     * @param <T>
     * @return
     */
    public static <T> T getMockData(String jsonFileName, TypeToken<T> typeToken, Context context){
        gsonInit();
        return gson.fromJson(getJson(jsonFileName, context), typeToken.getType());
    }

}
