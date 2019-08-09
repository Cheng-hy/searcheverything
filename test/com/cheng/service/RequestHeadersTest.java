package com.cheng.service;

import static org.junit.jupiter.api.Assertions.*;

import org.jsoup.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RequestHeadersTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		System.out.println(RequestHeaders.requestHeaders());
	}

}
