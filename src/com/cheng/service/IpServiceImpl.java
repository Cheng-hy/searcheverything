package com.cheng.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.cheng.log.Helper;
import com.cheng.model.IP;

/**
 * IP地址查询<br>
 * 使用Java原有的url包<br>
 * 
 * @author Cheng
 *
 */
public class IpServiceImpl implements IService {

	/**
	 * ip地址查询的主方法，进入到这个方法中，服务端可以跟客户端进行交互，<br>
	 * 可接收客户端的输入，也可以向客户端输出内容 <br>
	 * 
	 * @param scanner 客户端输入
	 * @param pw      服务端输出
	 */
	@Override
	public void mainMehtod(Scanner scanner, PrintWriter pw) {
		// 进入查询
		pw.print("请输入IP地址" + BusinessSocketService.enter);
		pw.println("请输入q返回到主菜单" + BusinessSocketService.enter);
		pw.flush();
		String ip = scanner.nextLine();
		// 如果输入的是q，返回到主菜单
		if (ip.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw);
			String msgCommand = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msgCommand);
		} else {
			// 检查IP地址是否合法
			boolean sign = checkIPIsValid(ip);
			if (!sign) {
				// 如果不合法，提示用户不合法
				pw.print("你输入的IP地址不合法，请重新输入" + BusinessSocketService.enter);
				pw.flush();
				mainMehtod(scanner, pw);
			}
			// 如果合法，调用一个业务方法
			String ipContent = getIpContent(ip);
			pw.println("服务端响应：" + BusinessSocketService.enter + ipContent);
			pw.flush();
			Helper.log(ipContent);
			mainMehtod(scanner, pw);
		}
	}

	/**
	 * 功能：检查IP地址是否合法
	 * 
	 * @param ip
	 * @return true:合法； false:不合法
	 */
	public boolean checkIPIsValid(String ip) {
		boolean sign = true;
		// 				  100~199|200~249  |250~255|10~99   |1~9 /* \ . 100~199|200~249  |250~255|10~99   |0~9 (.后的出现3次)
		String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])(\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)){3}$";
		if (ip.matches(regex) == true) {
			sign = true;
		} else {
			sign = false;
		}
		return sign;
	}

	/**
	 * 通过ip发起一个网络连接，获取网页内容
	 * 
	 * @return 运行商
	 */
	public String getIpContent(String ip) {
		String rContent = "";
		// 发起一个网络连接，获取网页内容
		String content = getContentFromURL(ip);
		// 从网页里获取出运行商
		String address = getSpNameFromContent(content);
		
		IP inContent = new IP();
		inContent.setIp(ip);
		inContent.setAddress(address);
		rContent = inContent.toString();
		
		return rContent;
	}

	/**
	 * 功能：根据ip地址在网页上搜索
	 * 
	 * @param ip 地址
	 * @return
	 */
	public static String getContentFromURL(String ip) {
		StringBuffer sb = new StringBuffer();
		// http://www.ip138.com/ips138.asp?ip=113.119.196.31&action=2
//		String webUrl = "http://www.ip138.com/ips138.asp?ip="+ ip +"&action=2";
		String webUrl = "http://ip.tool.chinaz.com/" + ip;
		try {
			URL url = new URL(webUrl);
			URLConnection conn = url.openConnection();
			Scanner s = new Scanner(conn.getInputStream());
			while (s.hasNextLine()) {
				sb.append(s.nextLine()).append(BusinessSocketService.enter);
			}
			s.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 功能：从一个网页内容里取出运营商名称
	 * 
	 * @param content 网页的内容
	 */
	public static String getSpNameFromContent(String content) {
		String spName = "";
		if (content.contains("w50-0\">") && content.contains("</span>")) {
			int beginIndex = content.lastIndexOf("w50-0\">");
			int endIndex = content.indexOf("</span>", beginIndex);
			spName = content.substring(beginIndex + 7, endIndex);
		}
		return spName;
	}
	
}
