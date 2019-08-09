package com.cheng.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cheng.log.Helper;
import com.cheng.model.Idcard;

/**
 * 身份证查询 <br>
 * 使用了Jsoup
 * 
 * @author Cheng
 *
 */
public class IdcardServiceImpl implements IService {

	/**
	 * 查询的主方法，进入到这个方法中，服务端可以跟客户端进行交互，<br>
	 * 可接收客户端的输入，也可以向客户端输出内容 <br>
	 * 
	 * @param scanner 客户端输入
	 * @param pw      服务端输出
	 */
	@Override
	public void mainMehtod(Scanner scanner, PrintWriter pw) {
		pw.print("请输入身份证号码" + BusinessSocketService.enter);
		pw.println("请输入q返回到主菜单" + BusinessSocketService.enter);
		pw.flush();
		String idcard = scanner.nextLine();
		// 如果输入的是q，返回到主菜单
		if (idcard.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw);
			String msgCommand = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msgCommand);
			return;
		} else {
			// 检查身份证是否合法
			boolean sign = checkIdCardIsValid(idcard);
			if (!sign) {
				// 如果不合法，提示用户不合法
				pw.print("你输入的身份证不合法，请重新输入" + BusinessSocketService.enter);
				pw.flush();
				mainMehtod(scanner, pw);
			}
			// 如果合法，调用一个业务方法
			String idCardContent = getIdcardContent(idcard);
			pw.println("服务端响应：" + BusinessSocketService.enter + idCardContent);
			pw.flush();
			Helper.log(idCardContent);
			mainMehtod(scanner, pw);
		}
	}

	/**
	 * 功能：检查用户输入的身份证号码是否合法
	 * 
	 * @param idcard 身份证
	 * @return true:合法； false:不合法
	 */
	public static boolean checkIdCardIsValid(String idcard) {
		boolean sign = true;
		String regex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}[\\dXx]$)";
		if (idcard.matches(regex) == true) {
			sign = true;
		} else {
			sign = false;
		}
		return sign;
	}

	/**
	 * 通过身份证发起一个网络连接，获取网页内容
	 * 
	 * @param idcardNumber
	 * @return 一个字符串：（包含）<br>
	 *         身份证号：xxxxxxxxxxxxxxxxxx; <br>
	 *         性别：男/女; <br>
	 *         生日：yyyy-mm-dd; <br>
	 *         地址：xx省xx市xx区; <br>
	 */
	public static String getIdcardContent(String idcardNumber) {
		Idcard idcard = new Idcard();
		idcard.setIdcardNumber(idcardNumber);
		String idcardContent = "";
		String url = "http://qq.ip138.com/idsearch/index.asp?userid=" + idcardNumber + "&action=idcard";

		try {
			Connection connect = Jsoup.connect(url);
			Connection data = connect.data(RequestHeaders.requestHeaders());
			Document doc = data.get();
//			Document doc = Jsoup.connect(url).get();//yuan

//			Document doc = RequestHeaders.requestHeaders(url);

			Element tbody = doc.getElementsByTag("tbody").get(0);
			Elements trs = tbody.getElementsByTag("tr");
			if (trs.size() >= 5) {
				Element sex = trs.get(1).getElementsByTag("td").get(1);
				Element birthday = trs.get(2).getElementsByTag("td").get(1);
				Element address = trs.get(4).getElementsByTag("td").get(1);

				idcard.setSex(sex.text());
				idcard.setBirthday(birthday.text());
				idcard.setAddress(address.text());
			}
			idcardContent = idcard.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return idcardContent;
	}

}
