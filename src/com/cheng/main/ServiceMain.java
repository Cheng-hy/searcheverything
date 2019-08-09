package com.cheng.main;

import com.cheng.service.BusinessSocketService;

public class ServiceMain {
	/**
	 * 启动服务端的各种业务类
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("启动百事通服务端，端口：4455");
		BusinessSocketService.startServer();
	}

}
