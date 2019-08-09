package com.cheng.model;

import com.cheng.service.BusinessSocketService;

/**
 * 存储一个ip地址信息的javabean对象。<br>
 * 1：属性 <br>
 * 2：构造方法 <br>
 * 它只能用来存储数据 <br>
 * 
 * @author Cheng
 *
 */
public class IP {
	private String ip;
	private String number;
	private String address;

	public IP() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IP(String ip, String number, String address) {
		super();
		this.ip = ip;
		this.number = number;
		this.address = address;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		String content = "";
		content += "IP地址：" + ip + BusinessSocketService.enter;
		content += "地理位置：" + address + BusinessSocketService.enter;
		return content;
	}
}
