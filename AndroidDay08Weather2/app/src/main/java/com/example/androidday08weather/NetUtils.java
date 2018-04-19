package com.example.androidday08weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetUtils {
	/**
	 * ctrl+shift+o全局导包 检测网络状态 返回true代表有网络 返回false代表无网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isActive(Context context) {
		boolean flag = false;
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null) {
			flag = info.isAvailable();
		}
		return flag;
	}

	/**
	 * Get方式访问网络
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String uri) {
		// 创建缓存字符串对象
		StringBuffer sb = new StringBuffer();
		String result = null;

		try {
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// 设置链接超时时间
			connection.setConnectTimeout(10 * 1000);
			// 设置读取输入流超时时间
			connection.setReadTimeout(10 * 1000);
			InputStream iStream = connection.getInputStream();
			// 封装字节流到字符流，方便读取字符串
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					iStream));

			String line = bReader.readLine();

			while (line != null) {
				sb.append(line);
				line = bReader.readLine();
			}
			Log.i("NetUtils", "小括号的开始位置" + sb.indexOf("(") + "小括号的结束位置"
					+ sb.lastIndexOf(")"));
			//sb.indexOf("(")小括号的开始位置，sb.lastIndexOf(")")找到小括号的结束位置
			//sb.substring截取字符串
			result = sb.substring(sb.indexOf("(") + 1,
					sb.lastIndexOf(")"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
