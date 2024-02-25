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




    @GetMapping(value = {"", "/main", "/login/{loginStep}", "/login/{userId}/{loginStep}", "/profile/{userId}/{nowProfileCode}/{profileMenu}", "/profile/{profileMenu}", "/profile/{profileMenu}", "/content/{contentType}/{contentGenre}/{contentId}", "/brand/{brandName}", "/search"
                , "/category/{mediaType}/{categoryType}", "/interest","/board/{nowProfileCode}","/board/{nowProfileCode}/{content}"})
    public String forward() {
        return "forward:/index.html";
    }

    @GetMapping("/profile/{profileMenu}/{profileSubMenu}")
    public String forwardProfileSubMenu() {
        return "forward:/index.html";
    }

    @GetMapping("/profile/{userId}/{nowProfileCode}/{profileMenu}/{profileSubMenu}")
    public String forwardProfileSubMenu2() {
        return "forward:/index.html";
    }




}
