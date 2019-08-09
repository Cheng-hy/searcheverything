package com.cheng.model;

/**
 * 天气API上json数据中的第二层数据<br>
 * javabean对象。<br>
 * 1：属性 <br>
 * 它只能用来存储数据 <br>
 * 
 * @author Cheng
 *
 */
public class WeatherResult {
	private String date;
	private String message;
	private String status;
	private Data data;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
