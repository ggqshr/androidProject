package com.example.androidday08weather;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.R.integer;
import android.util.Xml;

public class XMLParser {

	/**
	 * 解析得到map集合
	 * 
	 * @param is
	 * @return
	 */
	public Map<String, String> getMap(InputStream is) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			/** ----使用pull解析xml----- */
			// 获取pull解析器工厂
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			// 获取pull解析器
			XmlPullParser parser = factory.newPullParser();
			// 给解析器设置输入流，且为UTF_8
			parser.setInput(is, HTTP.UTF_8);
			// XmlPullParser.END_DOCUMENT:文档的结束
			// XmlPullParser.START_DOCUMENT:文档的开始
			// XmlPullParser.START_TAG:标签的开始
			// XmlPullParser.END_TAG:标签的结束
			// XmlPullParser.TEXT：标签的内容
			int eventType = parser.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					// 获取标签名
					String name = parser.getName();
					if (name.equals("key")) {
						// 获取key标签下的文本 eg：北京
						String key = parser.nextText();
						// 下一个标签
						parser.next();
						// 下一个标签
						parser.next();
						// 获取value值 eg：101010100
						String value = parser.nextText();
						// 将读取的key跟value存储到map集合中
						map.put(key, value);
					}
				}
				// 去读下一个标签
				parser.next();
				eventType = parser.getEventType();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
