package com.cheng.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IpServiceImplTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMainMehtod() {
	}

	@Test
	void testCheckIPIsValid() {
	}

	@Test
	void testGetSpNameFromContent() {
		String content = IpServiceImpl.getContentFromURL("192.168.11.12");
		String spName = IpServiceImpl.getSpNameFromContent(content);
		System.out.println(spName);
	}

	@Test
	void testGetContentFromURL() {
		String content = IpServiceImpl.getContentFromURL("192.168.20.111");
		System.out.println(content);
	}
	
	

}
