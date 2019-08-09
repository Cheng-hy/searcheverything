package com.cheng.model;

import com.cheng.service.BusinessSocketService;

/**
 * 存储一个城市展示给用户看的天气预报信息的javabean对象。<br>
 * 1：属性 <br>
 * 2：构造方法 <br>
 * 它只能用来存储数据 <br>
 * 
 * @author Cheng
 *
 */
public class Weather {
	private String city;
	@Deprecated
	private String date;
	private String ymd;
	private String week;
	private String type;// 天气描述
	private String high;
	private String low;
	private String notice;

	public Weather() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Weather(String city, String date, String ymd, String week, String type, String high, String low,
			String notice) {
		super();
		this.city = city;
		this.date = date;
		this.ymd = ymd;
		this.week = week;
		this.type = type;
		this.high = high;
		this.low = low;
		this.notice = notice;
	}

	public String getYmd() {
		return ymd;
	}

	public void setYmd(String ymd) {
		this.ymd = ymd;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	/**
	 * @return 城市：xx <br>
	 *         日期：xx <br>
	 *         星期：xx <br>
	 *         天气：xx <br>
	 *         最高温度：xx <br>
	 *         最低温度：xx <br>
	 *         注意事项：xx <br>
	 */
	@Override
	public String toString() {
		String content = "";
		content += "城市：" + city + BusinessSocketService.enter;
//		content += "日期：" + date + BusinessSocketService.enter;
		content += "日期：" + ymd + BusinessSocketService.enter;
		content += "星期：" + week + BusinessSocketService.enter;
		content += "天气：" + type + BusinessSocketService.enter;
		content += "最高温度：" + high + BusinessSocketService.enter;
		content += "最低温度：" + low + BusinessSocketService.enter;
		content += "注意事项：" + notice + BusinessSocketService.enter;
		return content;
	}
}
