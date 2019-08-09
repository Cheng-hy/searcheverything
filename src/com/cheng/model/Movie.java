package com.cheng.model;

import com.cheng.service.BusinessSocketService;

/**
 * 存储一个电影信息的javabean对象。<br>
 * 1：属性 <br>
 * 2：构造方法 <br>
 * 它只能用来存储数据 <br>
 * 
 * @author Cheng
 *
 */
public class Movie {
	private String name;
	private String title;
	private String urlDownload;

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie(String name, String title, String urlDownload) {
		super();
		this.name = name;
		this.title = title;
		this.urlDownload = urlDownload;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrlDownload() {
		return urlDownload;
	}

	public void setUrlDownload(String urlDownload) {
		this.urlDownload = urlDownload;
	}

	@Override
	public String toString() {
		String content = "";
		content += "电影名称：" + name + BusinessSocketService.enter;
		content += "标题：" + title + BusinessSocketService.enter;
		content += "下载地址：" + urlDownload + BusinessSocketService.enter;
		content += "---------------我是分隔线---------------\r\n";
		return content;
	}

}
