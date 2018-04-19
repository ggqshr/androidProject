package com.example.ggq.httptest.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ggq on 2017/4/19.
 */

public class HttpUtilConn {
    public static String sendPost(String url, HashMap<String, String> paras)  {
        URL reslUrl = null;
        try {
            System.out.println("@@@@@@");
            reslUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) reslUrl.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            con.setRequestProperty("Charset","utf-8");
            StringBuilder sb = new StringBuilder();
            // 如果参数不为空
            if (paras != null && !paras.isEmpty()) {
                for (Map.Entry<String, String> entry : paras.entrySet()) {
                    // Post方式提交参数的话，不能省略内容类型与长度
                    sb.append(entry.getKey()).append('=').append(
                            URLEncoder.encode(entry.getValue(), "utf-8")).append(
                            '&');
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            ArrayList<String> nn = new ArrayList<>();
            nn.add("nihaio");
            nn.add("sss");
            String test = "sss";
            byte[] bs = nn.toString().getBytes();
            byte[] ss = nn.toString().getBytes();
            con.connect();
            ObjectOutputStream oout =  new ObjectOutputStream(con.getOutputStream());
//            OutputStream out = con.getOutputStream();
            oout.writeObject(nn);
            oout.flush();
//            out.write(bs);
//            out.flush();
            String result = "";
            String readLine = "";
            if(con.getResponseCode()==200)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));

                while ((readLine = reader.readLine())!=null )
                {
                    result+=readLine;
                }
            }
            if(result == null)
            {
                result = "error";
                return result;
            }
            else
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
