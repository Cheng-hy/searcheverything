package com.cheng.log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Helper {
	/**
	 * 生成日志记录返回内容
	 * 
	 * @param content 日志的内容信息
	 */
	public static void log(String content) {
		PrintWriter pwLogs;
		try {
			pwLogs = new PrintWriter(new OutputStreamWriter(new FileOutputStream("logs.txt", true)));
//			System.out.println(content);
			pwLogs.write(content + "\r\n");
			pwLogs.flush();
			pwLogs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
