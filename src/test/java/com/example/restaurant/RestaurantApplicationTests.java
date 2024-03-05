package com.example.restaurant;

import com.example.restaurant.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantApplicationTests {

	@Autowired
	private EmailService emailService;

	// 이메일 주소
	String email = "laptopgyutti28@gmail.com";

	@Test
	void sendEmailTest() {
		try {

		} catch (Exception e) {
			// 예외 처리
		}
	}

	@Test
	void contextLoads() {
	}

}
