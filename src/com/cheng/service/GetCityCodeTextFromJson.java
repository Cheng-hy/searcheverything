package com.cheng.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * 测试获取json数据
 * 
 * @author Cheng
 *
 */
@Deprecated
public class GetCityCodeTextFromJson {

	public GetCityCodeTextFromJson() {
		super();
	}

	public String getCityCode(String city) {
		try {
			FileReader fr = new FileReader("city.json");// 读取
			BufferedReader bf = new BufferedReader(fr);// 缓存
			String jsonStr = "";
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = bf.readLine()) != null) {
				sb.append(line);
			}
			jsonStr = sb.toString();
			bf.close();
			JSONTokener jsonTokener = new JSONTokener(jsonStr);

			JSONObject jsonNew = new JSONObject();
			jsonNew.put("city_name", city);
			System.out.println(jsonNew);

			JSONArray jsonArray = new JSONArray(jsonTokener);// 取得整个文件转化成数组[{*,*,*}]
			System.out.println(jsonArray.length());

			for (int i = 0; i < jsonArray.length(); i++) {
				if (jsonArray.getJSONObject(i).has(city)) { // 无法对比
					System.out.println(jsonArray.getJSONObject(i));
					break;
				} else {
					System.out.println("null");
				}

			}

			// JSONObject jsonObject = jsonArray.getJSONObject(1); //取得数组中的第一个元素

			// String cityName = jsonObject.getString();
			// System.out.println(jsonObject);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getCityCode2() {
		try {
			FileReader fr = new FileReader("a.json");// 读取
			BufferedReader bf = new BufferedReader(fr);// 缓存
			String jsonStr = "";
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = bf.readLine()) != null) {
				sb.append(line);
			}
			jsonStr = sb.toString();
			bf.close();
			JSONTokener jsonTokener = new JSONTokener(jsonStr);

			// 获取整个json文件的内容，因为最外层是数组，所以用JSONArray来构造
			JSONArray jsonArray = new JSONArray(jsonTokener);
			System.out.println(jsonArray);

			// 这个JSONArray数组只包含一个JSONObject对象，标为jsonObject1
			JSONObject jsonObject1 = jsonArray.getJSONObject(0);
			System.out.println(jsonObject1);
			/*********************************/
			// //jsonObject1只包含一个institute字段，这里获取这个字段内容赋给jsonObject2
			// JSONObject jsonObject2 = jsonObject1.getJSONObject("institute");
			// System.out.println(jsonObject2);
			//
			// //jsonObject2包含name字段和grade字段，grade字段对应的是一个JSONArray数组
			// String valueOfname = jsonObject2.getString("name");
			// System.out.println(valueOfname);
			// JSONArray jsonArray2 = jsonObject2.getJSONArray("grade");
			// System.out.println(jsonArray2);
			//
			// //jsonArray2数组包含3个对象，每个对象里面有name字段和class字段，这里获取第二个对象
			// JSONObject jsonObject3 = jsonArray2.getJSONObject(1);
			// System.out.println(jsonObject3);
			//
			// //然后从jsonObject3对象里获取class字段对应的JSONArray数组
			// JSONArray jsonArray3 = jsonObject3.getJSONArray("class");
			// System.out.println(jsonArray3);
			//
			// //下面直接获取no.等于3的students数量，过程都一样
			// int num = jsonArray3.getJSONObject(2).getInt("students");
			// System.out.println("最后获取的结果为：" + num);

			/*****************************************************/
			// 第一层 / /第二层的第二个元素（class有3个） /第三层的第三个元素 /拿出元素
			int num2 = jsonArray.getJSONObject(0).getJSONObject("institute").getJSONArray("grade").getJSONObject(1)
					.getJSONArray("class").getJSONObject(2).getInt("students");
			System.out.println("最后获取的结果为：" + num2);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}