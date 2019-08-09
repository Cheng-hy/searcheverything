package com.cheng.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cheng.log.Helper;
import com.cheng.model.Movie;

/**
 * 电影下载地址查询 <br>
 * 使用了Jsoup
 * 
 * @author Cheng
 *
 */
public class MovieServiceImpl implements IService {

	/**
	 * 查询的主方法，进入到这个方法中，服务端可以跟客户端进行交互，<br>
	 * 可接收客户端的输入，也可以向客户端输出内容
	 * 
	 * @param scanner 客户端输入
	 * @param pw      服务端输出
	 */
	@Override
	public void mainMehtod(Scanner scanner, PrintWriter pw) {
		// 进入电影查询
		pw.print("请输入电影名称" + BusinessSocketService.enter);
		pw.println("请输入q返回到主菜单" + BusinessSocketService.enter);
		pw.flush();
		String userInputLine = scanner.nextLine();
		// 如果输入的是q，返回到主菜单
		if (userInputLine.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw);
			String msgCommand = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msgCommand);
			return;
		} else {
			// 检查是否合法--无
			// 如果合法，调用一个业务方法
			String content = getContent(userInputLine);
			pw.println("服务端响应：" + BusinessSocketService.enter + content);
			pw.flush();
			Helper.log(content);
			mainMehtod(scanner, pw);
		}
	}

	/**
	 * 搜索用户输入的电影名称
	 * 
	 * @return 匹配上的电影及其下载地址
	 */
	public String getContent(String name) {
		String content = "";
		List<Movie> list = this.getMovieList(name);
		for (Iterator<Movie> iterator = list.iterator(); iterator.hasNext();) {
			Movie movie = (Movie) iterator.next();
			content += movie.toString();
		}
		return content;
	}

	/**
	 * 按照电影名称获取搜索到的电影列表
	 * 
	 * @param name 电影名称
	 * @return movieList 电影列表
	 */
	public List<Movie> getMovieList(String name) {
		List<Movie> movieList = new ArrayList<>();
		String url = "";
		try {
			// 使用java.net包下的URLEncoder.encode方法转码
			url = "http://s.ygdy8.com/plus/so.php?typeid=1&keyword=" + URLEncoder.encode(name, "gb2312");

			Connection connect = Jsoup.connect(url);
			Connection data = connect.data(RequestHeaders.requestHeaders());
			Document doc = data.get();
			Elements elements = doc.getElementsByAttributeValue("class", "co_content8");
			if (elements.size() == 1) {
				Elements es = elements.get(0).getElementsByAttributeValueStarting("href", "/html");
				for (int i = 0; i < es.size(); i++) {
					Movie movie = new Movie();
					String href = es.get(i).attr("href");
					movie.setName(name);
					movie.setTitle(es.get(i).text());

					// 根据网页地址，获取电影的下载地址
					String downloadUrl = this.getMovieDownloadUrl("http://s.ygdy8.com" + href);
					movie.setUrlDownload(downloadUrl);
					movieList.add(movie);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return movieList;
	}

	/**
	 * 获取电影下载地址方法
	 * 
	 * @param url 目标网页链接
	 * @return movieDownloadUrl 电影下载链接
	 */
	public String getMovieDownloadUrl(String url) {
		String movieDownloadUrl = "";
		try {
			Connection connect = Jsoup.connect(url);
			Connection data = connect.data(RequestHeaders.requestHeaders());
			Document doc = data.get();

//			Elements elements = doc.getElementsByAttributeValue("bgcolor", "#fdfddf");
//			if (elements.size() == 1) {
//				Elements es = elements.get(0).getElementsByTag("a");
//				movieDownloadUrl = es.get(0).attr("href");
//			}

			Elements elements = doc.getElementsByAttributeValueStarting("href", "ftp:");
			Element element = elements.get(0);
			movieDownloadUrl = element.text();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movieDownloadUrl;
	}
}
