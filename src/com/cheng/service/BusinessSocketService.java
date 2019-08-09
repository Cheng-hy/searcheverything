package com.cheng.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 各功能处理服务端
 * 
 * @author Cheng
 *
 */
public class BusinessSocketService {
	static Socket mySocket;
	static ServerSocket serverSocket;

	public static String enter = "\r\n";

	public static void startServer() {
//		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(4455);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
//				Socket socket = serverSocket.accept();
				mySocket = serverSocket.accept();
				System.out.println("有人来了，");
//				BusinessSocketService bs = new BusinessSocketService(socket);
				userInput(mySocket);
			} catch (Exception e) {

			}
		}
	}

	// 用户按键输入处理
	public static void userInput(Socket mySocket) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(mySocket.getOutputStream());
			mainMenu(pw);
			// 客户会输入对应的数字，服务拿到对应的数字
			Scanner scanner = new Scanner(mySocket.getInputStream());
			String command = scanner.nextLine();
			dealUserInput(scanner, pw, command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出主菜单给用户的客户端
	 * 
	 * @param pw 输入字符流
	 */
	public static void mainMenu(PrintWriter pw) {
		String content = "欢迎进入百事通平台" + enter;
		content += "输入1 -> 进入查询IP功能" + enter;
		content += "输入2 -> 进入查询身份证号码功能" + enter;
		content += "输入3 -> 进入查询手机号码归属地功能" + enter;
		content += "输入4 -> 进入查询电影地址下载功能" + enter;
		content += "输入5 -> 进入查询天气预报功能" + enter;
		content += "输入q -> 退出百事通" + enter;
		pw.print(content);
		pw.flush();
	}

	/**
	 * 处理客户输入
	 * 
	 * @param scanner
	 * @param pw
	 * @param command 控制
	 */
	public static void dealUserInput(Scanner scanner, PrintWriter pw, String command) {
		switch (command) {
		case "1":
			// 进入ip查询
			IService ipService = new IpServiceImpl();// 面向接口编程
//			IpServiceImpl ipService = new IpServiceImpl();//面向对象编程
			ipService.mainMehtod(scanner, pw);
			break;
		case "2":
			// 进入查询身份证号码功能
			IService idcardService = new IdcardServiceImpl();
			idcardService.mainMehtod(scanner, pw);
			break;
		case "3":
			// 进入查询手机号码归属地功能
			IService mobilePhone = new MobilePhoneServiceImpl();
			mobilePhone.mainMehtod(scanner, pw);
			break;
		case "4":
			// 进入查询电影地址下载功能
			IService movieService = new MovieServiceImpl();
			movieService.mainMehtod(scanner, pw);
			break;
		case "5":
			// 进入查询天气预报功能
			IService weatherService = new WeatherServiceImpl();
			weatherService.mainMehtod(scanner, pw);
			break;
		case "q":
			try {
				pw.print("退出访问");
				pw.flush();
				mySocket.close();
				System.out.println("用户退出了。");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			pw.println("请选择！");
			pw.flush();
			userInput(mySocket);
			break;
		}
	}

}
