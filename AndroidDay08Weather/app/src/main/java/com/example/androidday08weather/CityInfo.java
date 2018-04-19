package com.example.androidday08weather;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mike on 2017/6/30.
 */

public class CityInfo {
    private Context context;
    private SharedPreferences cityCode;
    private String code;

    public CityInfo (Context context) {
        this.context = context;
    }

    public void setCity(String code) {
        this.code = code;
        cityCode = context.getSharedPreferences("cityCode", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cityCode.edit();
        editor.putString("cityInfo", code);
        editor.commit();
    }

    public String getCity() {
        cityCode = context.getSharedPreferences("cityCode", context.MODE_PRIVATE);
        String cityInfo = cityCode.getString("cityInfo", code);
        return cityInfo;
    }
}
