package com.example.restaurant.config;

import com.example.restaurant.repository.GReserveMybatis;
import com.example.restaurant.repository.mybatis.GreserveMapper;
import com.example.restaurant.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor


public class MyBatisConfig {


    private final GreserveMapper greserveMapper;



}
