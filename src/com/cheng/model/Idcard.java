package com.cheng.model;

import com.cheng.service.BusinessSocketService;

/**
 * 存储一个身份证信息的javabean对象。<br>
 * 1：属性 <br>
 * 2：构造方法 <br>
 * 它只能用来存储数据 <br>
 * 
 * @author Cheng
 *
 */
public class Idcard {
	private String idcardNumber;
	private String sex;
	private String birthday;
	private String address;

	public Idcard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Idcard(String idcardNumber, String sex, String birthday, String address) {
		super();
		this.idcardNumber = idcardNumber;
		this.sex = sex;
		this.birthday = birthday;
		this.address = address;
	}

	public String getIdcardNumber() {
		return idcardNumber;
	}

	public void setIdcardNumber(String idcardNumber) {
		this.idcardNumber = idcardNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		String content = "";
		content += "身份证号：" + idcardNumber + BusinessSocketService.enter;
		content += "性别：" + sex + BusinessSocketService.enter;
		content += "生日：" + birthday + BusinessSocketService.enter;
		content += "地址：" + address + BusinessSocketService.enter;
		return content;

	}

}
