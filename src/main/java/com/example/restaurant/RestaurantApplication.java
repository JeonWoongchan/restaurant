package com.example.restaurant;


import com.example.restaurant.service.SmsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;



import org.springframework.context.annotation.Bean;

import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	@Bean
	public CommandLineRunner smsRunner(SmsService smsService) {
		return args -> {
//			smsService.message("010893294818");
		};
	}
}
