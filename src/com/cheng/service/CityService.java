package com.cheng.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.cheng.model.City;

/**
 * 处理city.json数据内容
 * 
 * @author Cheng
 *
 */
public class CityService {

	/**
	 * 打开文件
	 * 
	 * @return list 返回文件中的城市列表
	 */
	public static List<City> openCityJson() {
		List<City> list = new ArrayList<>();
		try {
			String cityData = new String(Files.readAllBytes(Paths.get("city.json")));
			list = JSON.parseArray(cityData, City.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据模糊的城市名获取完整的城市名 <br>
	 * 只获取，不作判断
	 * 
	 * @param cityName 模糊的城市名
	 * @return 完整的城市名
	 */
	public static String getCityName(String cityName) {
		String rCityName = "";
		List<City> list = openCityJson();
		for (Iterator<City> iterator = list.iterator(); iterator.hasNext();) {
			City city = (City) iterator.next();
			if (city.getCity_name().contains(cityName)) {
				rCityName = city.getCity_name();
				break;
			}
		}
		return rCityName;
	}

	/**
	 * 根据城市名获取对应城市的城市代号 <br>
	 * 只获取，不作判断
	 * 
	 * @param cityName 城市名
	 * @return 城市代号
	 */
	public static String getCityCode(String cityName) {
		String rCity_code = "";
		List<City> list = openCityJson();
		for (Iterator<City> iterator = list.iterator(); iterator.hasNext();) {
			City city = (City) iterator.next();
			if (city.getCity_name().contains(cityName)) {
//				String city_code = city.getCity_code();
//				if(city_code.isEmpty()) {
//					rCity_code = "请重新输入";
//					break;
//				}else {
//					rCity_code = city_code;
//					break;
//				}
				rCity_code = city.getCity_code();
				break;
			}
		}
		return rCity_code;
	}

}
