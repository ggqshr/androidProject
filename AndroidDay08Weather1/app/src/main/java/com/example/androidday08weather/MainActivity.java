package com.example.androidday08weather;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.service.LocationService;
import com.baidu.mapapi.SDKInitializer;

public class MainActivity extends Activity {
	private ImageView refreshIv;
	private ImageView searchIv;

	private TextView cityTv;
	private TextView pmTv;
	private TextView errorTv;
	private View bottomLine;

	private TextView tempTv;
	private TextView weatherTv;
	private TextView windTv;
	private TextView dateTv;

	private LinearLayout otherLL;

	// 存储星期情况
	private List<String> list;

	private Handler handler;

	private String cityCode = "101020100";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LocationService locationService;
		Vibrator mVibrator;
		/***
		 * 初始化定位sdk，建议在Application中创建
		 */
		locationService = new LocationService(getApplicationContext());
		mVibrator = (Vibrator) getApplicationContext().getSystemService(
				Service.VIBRATOR_SERVICE);
		SDKInitializer.initialize(getApplicationContext());

		initView();
		initData();
		initHandler();
		getData(cityCode);
		setListener();
	}

	/**
	 * 监听处理
	 */
	private void setListener() {
		// 给刷新按钮设置点击监听事件
		refreshIv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getData(cityCode);
			}
		});
		// 给搜索按钮设置点击监听
		searchIv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 跳转页面
				// Intent intent = new Intent(MainActivity.this,
				// SearchActivity.class);
				// startActivityForResult(intent, 1);
				Intent intent = new Intent(MainActivity.this,
						LocationActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			cityCode = String.valueOf(resultCode);
			getData(cityCode);
		}
	}

	/**
	 * 初始化Handler对象
	 */
	private void initHandler() {
		handler = new Handler() {
			
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 1) {
					// 当网络加载成功的时候将动画结束，从新变为refresh图片
					refreshIv.setBackgroundResource(R.drawable.refresh);

					List<WeatherBean> list = (List<WeatherBean>) msg.obj;
					cityTv.setText(list.get(0).getCity());
					pmTv.setText(list.get(0).getPm());
					tempTv.setText(list.get(0).getTempCurrent());
					weatherTv.setText(list.get(0).getWeather() + " "
							+ list.get(0).getTemp());
					windTv.setText(list.get(0).getWindCurrent());
					dateTv.setText(list.get(0).getDate_y() + " "
							+ list.get(0).getWeek());
					// 获取PM值
					String pm = list.get(0).getPm();
					/**
					 * PM颜色设置 绿 黄 橙 鲜红 红 紫
					 */
					// contains字符串的一个方法，表示当前字符串中是否包含什么什么字符串
					if (pm.contains(getResources().getString(R.string.pm1))) {
						bottomLine.setBackgroundColor(getResources().getColor(
								R.color.pm1));
					} else if (pm.contains(getResources().getString(
							R.string.pm2))) {
						bottomLine.setBackgroundColor(getResources().getColor(
								R.color.pm2));
					} else if (pm.contains(getResources().getString(
							R.string.pm3))) {
						bottomLine.setBackgroundColor(getResources().getColor(
								R.color.pm3));
					} else if (pm.contains(getResources().getString(
							R.string.pm4))) {
						bottomLine.setBackgroundColor(getResources().getColor(
								R.color.pm4));
					} else if (pm.contains(getResources().getString(
							R.string.pm5))) {
						bottomLine.setBackgroundColor(getResources().getColor(
								R.color.pm5));
					} else if (pm.contains(getResources().getString(
							R.string.pm6))) {
						bottomLine.setBackgroundColor(getResources().getColor(
								R.color.pm6));
					}

					// 添加前先移除之前的所有View
					otherLL.removeAllViews();

					// 通过for循环来创建五个View并添加到LinearLayout布局中
					for (int i = 1; i < list.size(); i++) {
						// 根据下标来获取WeatherBean对象（eg：i＝1代表明天的天气数据 。。。。）
						WeatherBean weatherBean = list.get(i);
						// 根据Layout布局生成View对象
						View view = LayoutInflater.from(MainActivity.this)
								.inflate(R.layout.weather_item, null);
						TextView weekTv = (TextView) view
								.findViewById(R.id.week_item);
						TextView weatherTv = (TextView) view
								.findViewById(R.id.weather_item);
						TextView tempTv = (TextView) view
								.findViewById(R.id.temp_item);

						weekTv.setText(weatherBean.getWeek());
						weatherTv.setText(weatherBean.getWeather());
						tempTv.setText(weatherBean.getTemp());

						// 添加到布局中
						otherLL.addView(view);
					}
				}
			}
		};
	}

	// 初始化数据
	private void initData() {
		list = new ArrayList<String>();
		list.add("星期一");
		list.add("星期二");
		list.add("星期三");
		list.add("星期四");
		list.add("星期五");
		list.add("星期六");
		list.add("星期天");
	}

	// 获取网络数据
	private void getData(final String cityCode) {
		// 设置刷新按钮为refresh_anim.xml动画
		refreshIv.setBackgroundResource(R.drawable.refresh_anim);

		AnimationDrawable animationDrawable = (AnimationDrawable) refreshIv
				.getBackground();
		// 开始动画
		animationDrawable.start();

		if (!NetUtils.isActive(MainActivity.this)) {
			errorTv.setText("当前无网络，请确认网络连接");
			return;
		}
		
		
		// 启动线程访问网络
		new Thread(new Runnable() {

			@Override
			public void run() {
				// 滁州 101221101 101020100
				String url = "http://weather.123.duba.net/static/weather_info/"
						+ cityCode + ".html";
				String result = NetUtils.doGet(url);
				if (result != null) {
					List<WeatherBean> list = parserJson(result);
					Message message = handler.obtainMessage();
					message.what = 1;
					message.obj = list;
					handler.sendMessage(message);
				}
			}
		}).start();
	}

	/**
	 * json解析数据
	 * 
	 * @param result
	 * @return
	 */
	private List<WeatherBean> parserJson(String result) {
		List<WeatherBean> list = new ArrayList<WeatherBean>();
		// 判断传过来的是否为空参数
		if (!TextUtils.isEmpty(result)) {
			try {
				// 将json字符串转化成JSONObject对象
				JSONObject temp = new JSONObject(result);
				// 根据key获取value(这里的value是JSONObject对象)
				JSONObject object = temp.getJSONObject("weatherinfo");
				// 这里i从1开始，是因为json串中是从1开始的
				for (int i = 1; i < 7; i++) {
					WeatherBean weatherBean = new WeatherBean();
					// 表示当天的天气数据
					if (i == 1) {
						// 城市
						weatherBean.setCity(object.getString("city"));
						weatherBean.setDate_y(object.getString("date_y"));
						weatherBean.setTempCurrent(object.getString("temp")
								+ "°");
						weatherBean.setWindCurrent(object.getString("wd") + " "
								+ object.getString("ws"));
						weatherBean.setPm("PM:" + object.getString("pm") + " "
								+ object.getString("pm-level"));
					}
					weatherBean.setWeek(getWeek(i, object.getString("week")));
					// 设置一天的气温情况
					weatherBean.setTemp(object.getString("temp" + i));
					// 设置一天的天气情况
					weatherBean.setWeather(object.getString("weather" + i));
					// 设置一天的风的情况
					weatherBean.setWind(object.getString("wind" + i));
					// 将一天的天气情况添加到集合中
					list.add(weatherBean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	private String getWeek(int i, String week) {
		// 记录下标
		int index = 0;
		// list.size()获取集合的数量
		for (int j = 0; j < list.size(); j++) {
			Log.i("getWeek", "list.get(j)=" + list.get(j) + ",week=" + week);
			// equals判断两个字符串是否相等
			if (list.get(j).equals(week)) {
				// 记录下当前天的下标
				index = j;
				break;
			}
		}

		if (index + i < 8) {
			index = index + i - 1;
		} else {
			index = index + i - 8;
		}
		return list.get(index);
	}

	// 初始化View
	private void initView() {
		refreshIv = (ImageView) findViewById(R.id.refresh_iv);
		searchIv = (ImageView) findViewById(R.id.search_iv);

		cityTv = (TextView) findViewById(R.id.city_tv);
		pmTv = (TextView) findViewById(R.id.pm_tv);
		bottomLine = findViewById(R.id.view_line);
		errorTv = (TextView) findViewById(R.id.error_tv);

		tempTv = (TextView) findViewById(R.id.temp_tv);
		weatherTv = (TextView) findViewById(R.id.weather_tv);
		windTv = (TextView) findViewById(R.id.wind_tv);
		dateTv = (TextView) findViewById(R.id.date_tv);

		otherLL = (LinearLayout) findViewById(R.id.others_ll);
	}

}
