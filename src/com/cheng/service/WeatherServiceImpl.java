package com.cheng.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSON;
import com.cheng.log.Helper;
import com.cheng.model.Data;
import com.cheng.model.Forecast;
import com.cheng.model.Weather;
import com.cheng.model.WeatherResult;

/**
 * 天气查询 <br>
 * 使用了Jsoup
 * 
 * @author Cheng
 *
 */
public class WeatherServiceImpl implements IService {

	/**
	 * 查询的主方法，进入到这个方法中，服务端可以跟客户端进行交互，<br>
	 * 可接收客户端的输入，也可以向客户端输出内容
	 * 
	 * @param scanner 客户端输入
	 * @param pw      服务端输出
	 */
	@Override
	public void mainMehtod(Scanner scanner, PrintWriter pw) {
		// 进入天气查询
		pw.print("请输入城市名称" + BusinessSocketService.enter);
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
			// 检查是否合法
			boolean sign = checkCityNameIsValid(userInputLine);
			if (!sign) {
				// 如果不合法，提示用户不合法
				pw.print("该城市未提供预报服务或你输入有误，请重新输入" + BusinessSocketService.enter);
				pw.flush();
				mainMehtod(scanner, pw);
			}
			// 如果合法，调用一个业务方法
			String content = getContent(userInputLine);
			pw.println("服务端响应：" + BusinessSocketService.enter + content);
			pw.flush();
			Helper.log(content);
			mainMehtod(scanner, pw);
		}
	}

	/**
	 * 功能：检查用户输入的城市是否能够提供搜索服务
	 * 
	 * @param cityName 城市名字
	 * @return true:合法； false:不合法
	 */
	public static boolean checkCityNameIsValid(String cityName) {
		boolean sign = true;
		if (cityName.length() > 1 && CityService.getCityName(cityName).contains(cityName)) {
			String cityCode = CityService.getCityCode(CityService.getCityName(cityName));
			if (cityCode.isEmpty()) {
				sign = false;
			} else {
				sign = true;
			}
		} else {
			sign = false;
		}
		return sign;
	}

	/**
	 * 搜索用户输入的城市天气
	 * 
	 * @return 匹配上的城市天气预报
	 */
	public String getContent(String name) {
		String content = "";
		Weather weather = getWeather(name.trim());
		content = weather.toString();
		return content;
	}

	/**
	 * 获取天气信息
	 * 
	 * @param city
	 * @return
	 */
	public Weather getWeather(String city) {
		// 根据城市名称去获取城市代号
		String city_code = CityService.getCityCode(city);
		// 根据城市代号去获取城市天气预报
		String content = getWeatherFromUrl(city_code);
		// 获取第二层全部数据
		WeatherResult result = JSON.parseObject(content, WeatherResult.class);
		// 获取第二层中data元素
		Data d = result.getData();
		// 获取data元素中的forecast数组的第一个元素
		Forecast f = d.getForecast().get(0);
		// 赋值到展示model
		Weather weather = new Weather();
		weather.setYmd(f.getYmd());
//		weather.setDate(f.getDate());
		weather.setWeek(f.getWeek());
		;
		weather.setCity(city);
		weather.setHigh(f.getHigh());
		weather.setLow(f.getLow());
		weather.setType(f.getType());
		weather.setNotice(f.getNotice());
		return weather;

	}

	/**
	 * 从网页上获取城市天气的方法
	 * 
	 * @param city_code 目标城市的代号
	 * @return weather 天气信息
	 */
	public String getWeatherFromUrl(String city_code) {
//		StringBuffer sb = new StringBuffer();
		String weatherFromUrl = "http://t.weather.sojson.com/api/weather/city/" + city_code;
		String weather = "";
		try {
			Connection connect = Jsoup.connect(weatherFromUrl);
			Connection data = connect.data(RequestHeaders.requestHeaders());
//			Document doc = data.get(); //未指定类型，报错
//			Document doc = data.ignoreContentType(true).get();//ignoreContentType(true)忽略请求类型
//			weather = doc.text();
			Response res = data.ignoreContentType(true).execute();// 建议用execute()去执行，如果用get()去执行的话，返回来是一个
																	// HTML页面包裹的JSON，处理起来稍微费劲
			weather = res.body();

			/**
			 * 原有的HttpConnection请求
			 */
//			URL url = new URL(weatherFromUrl);
//			URLConnection conn = url.openConnection();
//			Scanner s = new Scanner(conn.getInputStream());
//			while (s.hasNextLine()) {
//				sb.append(s.nextLine()).append("\r\n");
//			}
//			s.close();
//			weather = sb.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return weather;
	}
}
