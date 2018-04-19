package com.example.androidday08weather;

public class WeatherBean {
	// 城市
	private String city;
	// 城市ID
	private String code;
	// 星期
	private String week;
	// 日期
	private String date_y;
	// 气温
	private String temp;
	// 风
	private String wind;
	// 天气
	private String weather;
	// 空气质量
	private String pm;

	// 当前气温
	private String tempCurrent;
	// 当前风
	private String windCurrent;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getDate_y() {
		return date_y;
	}

	public void setDate_y(String date_y) {
		this.date_y = date_y;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getTempCurrent() {
		return tempCurrent;
	}

	public void setTempCurrent(String tempCurrent) {
		this.tempCurrent = tempCurrent;
	}

	public String getWindCurrent() {
		return windCurrent;
	}

	public void setWindCurrent(String windCurrent) {
		this.windCurrent = windCurrent;
	}
}
