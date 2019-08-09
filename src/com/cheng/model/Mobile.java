package com.cheng.model;

import com.cheng.service.BusinessSocketService;

/**
 * 存储一个手机号码信息的javabean对象。<br>
 * 1：属性 <br>
 * 2：构造方法 <br>
 * 它只能用来存储数据 <br>
 * 
 * @author Cheng
 *
 */
public class Mobile {
	private String phoneNumber;
	private String address;
	private String brand;
	private String areaCode;

	public Mobile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mobile(String phoneNumber, String address, String brand, String areaCode) {
		super();
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.brand = brand;
		this.areaCode = areaCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String toString() {
		String content = "";
		content += "手机号码：" + phoneNumber + BusinessSocketService.enter;
		content += "品牌：" + brand + BusinessSocketService.enter;
		content += "归属地：" + address + BusinessSocketService.enter;
		content += "区号：" + areaCode + BusinessSocketService.enter;
		return content;
	}

}
