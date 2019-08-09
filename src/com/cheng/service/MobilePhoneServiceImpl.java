package com.cheng.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cheng.log.Helper;
import com.cheng.model.Mobile;

/**
 * 手机号码查询 <br>
 * 使用了Jsoup
 * 
 * @author Cheng
 *
 */
public class MobilePhoneServiceImpl implements IService {

	/**
	 * 查询的主方法，进入到这个方法中，服务端可以跟客户端进行交互，<br>
	 * 可接收客户端的输入，也可以向客户端输出内容
	 * 
	 * @param scanner 客户端输入
	 * @param pw      服务端输出
	 */
	@Override
	public void mainMehtod(Scanner scanner, PrintWriter pw) {
		pw.print("请输入手机号码" + BusinessSocketService.enter);
		pw.println("请输入q返回到主菜单" + BusinessSocketService.enter);
		pw.flush();
		String phoneNumber = scanner.nextLine();
		// 如果输入的是q，返回到主菜单
		if (phoneNumber.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw);
			String msgCommand = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msgCommand);
			return;
		} else {
			// 检查手机号是否合法
			boolean sign = checkPhoneNumberIsValid(phoneNumber);
			if (!sign) {
				// 如果不合法，提示用户不合法
				pw.print("你输入的手机号码不合法，请重新输入" + BusinessSocketService.enter);
				pw.flush();
				mainMehtod(scanner, pw);
			}
			// 如果合法，调用一个业务方法
			String content = getContent(phoneNumber);
			pw.println("服务端响应：" + BusinessSocketService.enter + content);
			pw.flush();
			Helper.log(content);
			mainMehtod(scanner, pw);
		}
	}

	/**
	 * 功能：检查用户输入的手机号码是否合法
	 * 
	 * @param phoneNumber 手机号码
	 * @return true:合法； false:不合法
	 */
	public static boolean checkPhoneNumberIsValid(String phoneNumber) {
		boolean sign = true;
		String regex = "^1[3456789]\\d{9}$";
		if (phoneNumber.matches(regex) == true) {
			sign = true;
		} else {
			sign = false;
		}
		return sign;
	}

	/**
	 * 通过手机号码发起一个网络连接，获取网页内容
	 * 
	 * @param phoneNumber
	 * @return 一个字符串：（包含）<br>
	 *         手机号码：xxxxxxxxxxx; <br>
	 *         品牌：xxx; <br>
	 *         归属地：xx省xx市; <br>
	 *         区号：0000; <br>
	 */
	public static String getContent(String phoneNumber) {
		Mobile mobile = new Mobile();
		mobile.setPhoneNumber(phoneNumber);
		String content = "";

		String url = "http://www.ip138.com:8080/search.asp?mobile=" + phoneNumber + "&action=mobile";

		try {
			Connection connect = Jsoup.connect(url);
			Connection data = connect.data(RequestHeaders.requestHeaders());
			Document doc = data.get();
//			Document doc = Jsoup.connect(url).get();//yuan

//			Document doc = RequestHeaders.requestHeaders(url);

//			Element tbody = doc.getElementsByTag("tbody").get(0);
//			Elements trs = tbody.getElementsByTag("tr");
			Elements elemnts = doc.getElementsByAttributeValue("class", "tdc2");
			if (elemnts.size() >= 2) {
				String address = elemnts.get(1).text();
				String brand = elemnts.get(2).text();
				String areaCode = elemnts.get(3).text();
				mobile.setAddress(address);
				mobile.setBrand(brand);
				mobile.setAreaCode(areaCode);
			}
			content = mobile.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}

}
