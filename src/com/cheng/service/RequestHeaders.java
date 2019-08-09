package com.cheng.service;

import java.util.HashMap;
import java.util.Map;

public class RequestHeaders {

	/**
	 * 伪装成浏览器，发起一个请求头
	 * 
	 * @return Request Headers 请求头内容 <br>
	 *         Accept:
	 *         text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*\/*;q=0.8 <br>
	 *         Accept-Encoding: gzip, deflate <br>
	 *         Accept-Language: zh-CN,zh;q=0.9,zh-TW;q=0.8 <br>
	 *         Connection: keep-alive <br>
	 *         Cookie: null <br>
	 *         Upgrade-Insecure-Requests: 1 <br>
	 *         User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36
	 *         (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36 <br>
	 */
	public static Map<String, String> requestHeaders() {
//		Document headersDoc = null;
//		Connection connect = Jsoup.connect(url);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Accept-Language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
		headers.put("Connection", "keep-alive");
		headers.put("Upgrade-Insecure-Requests", "1");
		headers.put("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");

//		Connection data = connect.data(headers);
//		try {
//			headersDoc = data.get();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		return headers;
	}
}
