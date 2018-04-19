package com.example.androidday08weather;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 搜索页面
 * 
 * @author MAC
 * 
 */
public class SearchActivity extends Activity {

	private Map<String, String> map;

	private EditText searchEditText;
	private ImageView searchIv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initView();
		initData();
		// 设置搜索点击监听
		searchIv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String city = searchEditText.getText().toString().trim();
				if (!TextUtils.isEmpty(city)) {
					// 根据城市获取cityCode
					String cityCode = map.get(city);
					if (!TextUtils.isEmpty(cityCode)) {
						// Integer.valueOf(cityCode)将字符串转化成整型
						setResult(Integer.valueOf(cityCode));
						// 将当前页面kill掉
						finish();
					} else {
						Toast.makeText(SearchActivity.this, "请确认你输入正确的城市了",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(SearchActivity.this, "请确认你输入城市了",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void initView() {
		searchEditText = (EditText) findViewById(R.id.search_edit);
		searchIv = (ImageView) findViewById(R.id.search_ibtn);
	}

	private void initData() {
		try {
			// 获取assets目录下的city_code.xml输入流
			InputStream iStream = getAssets().open("city_code.xml");
			map = new XMLParser().getMap(iStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
