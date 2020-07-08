package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Iterator;
import java.util.Map;

public class CommonUtil {

    /**
     * 将字符串数据保存到本地
     */
    public static void saveSettingNote(Context context, String filename, Map<String, String> map) {
        SharedPreferences.Editor note = context.getSharedPreferences(filename, Context.MODE_PRIVATE).edit();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            note.putString(entry.getKey(), entry.getValue());
        }
        note.commit();
    }

    /**
     * 从本地取出要保存的数据
     */
    public static String getSettingNote(Context context, String filename, String dataname) {
        SharedPreferences read = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return read.getString(dataname, null);
    }

}
