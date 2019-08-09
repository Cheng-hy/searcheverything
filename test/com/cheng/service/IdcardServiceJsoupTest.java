package com.cheng.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IdcardServiceJsoupTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCheckIdCardIsValid() {                     //            012345
														//   123456789ABCDEF678
		boolean boo = IdcardServiceImpl.checkIdCardIsValid("440925459509155540");
		System.out.println(boo);
	}

	@Test
	void testGetIdcardContent() {
		String idCardContent = IdcardServiceImpl.getIdcardContent("440921199509190454");
		System.out.println(idCardContent+"\r\n");
	}

}
