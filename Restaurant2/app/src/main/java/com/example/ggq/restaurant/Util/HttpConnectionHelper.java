package com.example.ggq.restaurant.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.ggq.restaurant.com.Hotpot.entity.Food;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by ggq on 2017/4/20.
 */

public class HttpConnectionHelper {
    public static HttpURLConnection requestSetPost(HttpURLConnection con ,HttpError Error)
    {
        con.setDoInput(true);
        con.setDoOutput(true);
        try {
            con.setRequestMethod("POST");
        } catch (ProtocolException e) {
           Error.netError("set error");
        }
        con.setUseCaches(false);
        con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        con.setRequestProperty("Charset","utf-8");
        return con;
    }

    /**
     *<p>Description:用来传递键值对</p>
     * @param url
     * @param map
     * @param Error
     * @return
     */
    public static String sendPost(String url, HashMap<String,String> map,HttpError Error)
    {
        URL url1 = null;
        HttpURLConnection con = null;
        ObjectOutputStream out = null;
        try {
            url1 = new URL(url);
        } catch (MalformedURLException e) {
            Error.netError("url error");
        }
        try {
             con = (HttpURLConnection) url1.openConnection();
        } catch (IOException e) {
           Error.netError("connection error");
        }
        con = requestSetPost(con,Error);
        try {
            con.connect();
        } catch (IOException e) {
            Error.onError("Connectioning error");
        }
        try {
           out =  new ObjectOutputStream(con.getOutputStream());
        } catch (IOException e) {
            Error.onError("get IO error");
        }
        try {
            out.writeObject(map);
            out.flush();
        } catch (IOException e) {
            Error.onError("Write error");
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        String r1 = "";
        try {
            if(con.getResponseCode() == 200)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));

                while ((r1 = reader.readLine())!=null )
                {
                    result+=r1;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result == null)
        {
            result = "error";
        }
        return result;
    }
    public Bitmap getImgFromUrl(String url,HttpError error)
    {
        Bitmap bitmap = null;
        try {
            //将地址才转换成URl
            URL u = new URL(url);
            //获得连接
            HttpURLConnection con = (HttpURLConnection) u.openConnection();
//            设置连接属性
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            //发生请求
            con.connect();
            if(con.getResponseCode() == 200)
            {
                InputStream io = con.getInputStream();
                bitmap = BitmapFactory.decodeStream(io);
            }
        } catch (MalformedURLException e) {
            error.netError("new error");
            e.printStackTrace();
        } catch (IOException e) {
            error.netError("new error");
            e.printStackTrace();
        }
        if(bitmap == null)
        {
            error.onError("bitmap==null");
            return null;
        }
        else
            return bitmap;

    }
    public String sendFood(String url, Food food )
    {
        URL url1 = null;
        HttpURLConnection con = null;
        ObjectOutputStream out = null;
        try {
            url1 = new URL(url);
            con = (HttpURLConnection) url1.openConnection();
            con = requestSetPost(con, new HttpError() {
                @Override
                public void onFinsh(String einf) {

                }

                @Override
                public void onError(String einf) {

                }

                @Override
                public void netError(String einf) {

                }
            });
            con.connect();
            out = new ObjectOutputStream(con.getOutputStream());
            out.writeObject(food);
            out.flush();
            out.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        String r1 = "";
        try {
            if(con.getResponseCode() == 200)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));

                while ((r1 = reader.readLine())!=null )
                {
                    result+=r1;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result == null)
        {
            result = "error";
        }
        return result;
    }
}
