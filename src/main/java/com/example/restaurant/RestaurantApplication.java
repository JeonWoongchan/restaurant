package com.example.restaurant;


import com.example.restaurant.config.MyBatisConfig;
import com.example.restaurant.service.SmsService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;



import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@Import(MyBatisConfig.class)
@MapperScan("com.example.restaurant.repository.mybatis")
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	@Bean
	public CommandLineRunner smsRunner(SmsService smsService) {
		return args -> {
//			smsService.send_message("01022222222");
		};
	}
}
