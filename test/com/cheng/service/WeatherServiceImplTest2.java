package com.cheng.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeatherServiceImplTest2 {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCheckCityNameIsValid() {
//		WeatherServiceImpl wsi = new WeatherServiceImpl();
		System.out.println(WeatherServiceImpl.checkCityNameIsValid("中三"));
	}

	@Test
	void testGetContent() {
	}

	@Test
	void testGetWeather() {
	}

	@Test
	void testGetWeatherFromUrl() {
	}

}
