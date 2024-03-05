package com.example.restaurant.web;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HomeController   {




    @GetMapping(value = {"", "/main", "/login/{value}" ,"/reservation" , "/payment" , "/about/{subMenu}" ,"/menu/{subMenu}" ,"/contact/{subMenu}"})
    public String forward() {
        return "forward:/index.html";
    }





}
