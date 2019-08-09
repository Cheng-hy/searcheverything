package com.cheng.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieServiceImplTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetContent() {
	}

	@Test
	void testGetMovieList() {
		MovieServiceImpl ms = new MovieServiceImpl();
		System.out.println(ms.getMovieList("证人"));
	}

	@Test
	void testGetMovieDownloadUrl() {
		MovieServiceImpl ms = new MovieServiceImpl();
		System.out.println(ms.getMovieDownloadUrl("https://www.ygdy8.com/html/gndy/dyzz/20151216/49774.html"));
	}

}
