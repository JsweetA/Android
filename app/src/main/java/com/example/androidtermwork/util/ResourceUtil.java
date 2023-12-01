package com.example.androidtermwork.util;

import android.content.Context;

/**
 * 资源工具类
 */
public class ResourceUtil
{
    /**
     * 按名称获取资源的id
     * @param context
     * @param var  资源名称
     * @param type 资源类型
     * @return 资源id
     */
    public static int getIdentifier(Context context, String var, String type) {

        try {
            return context.getResources().getIdentifier(var, type, context.getPackageName());
        } catch (Exception e) {
            //e.printStackTrace();
            return 0;
        }
    }
}
