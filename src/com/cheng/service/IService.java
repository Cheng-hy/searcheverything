package com.cheng.service;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 各功能的接口
 * 
 * @author Cheng
 *
 */
public interface IService {

	/**
	 * 功能查询的主方法接口，进入到这个方法中，服务端可以跟客户端进行交互，<br>
	 * 可接收客户端的输入，也可以向客户端输出内容
	 * 
	 * @param scanner 客户端输入
	 * @param pw      服务端输出
	 */
	public void mainMehtod(Scanner scanner, PrintWriter pw);

	/**
	 * 信息获取接口，进入到这个方法中，服务端可以跟Web进行交互，<br>
	 * 可接收客户端的输入，也可以向客户端输出内容
	 * 
	 * @param scanner 客户端输入
	 * @param pw      服务端输出
	 * @param String  需求名字
	 */
	// public String getContent(Scanner scanner, PrintWriter pw, String name);
}
