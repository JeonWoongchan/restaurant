package com.example.restaurant.web;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin("*")
@RequiredArgsConstructor
public class HomeController   {


    @GetMapping(value = {"", "/admin/**", "/main", "/login/{value}" ,"/reservation" , "/payment" , "/about/{subMenu}" ,"/menu/{subMenu}" ,"/contact/{subMenu}", "/my-page/{subMenu}"})
    public String forward() {
        return "forward:/index.html";
    }




    @ResponseBody
    @PostMapping("/user/logout")
    public ResponseEntity<HashMap<String,Object>> logout(HttpSession session) {

      HashMap<String,Object> map = new HashMap<String,Object>();

      String pritnemail = (String) session.getAttribute("email");

      System.out.println(pritnemail);
        if (pritnemail != null) {
            session.invalidate();
            map.put("stat",1);
        } else {
          map.put("stat",0);
        }
        return ResponseEntity.ok(map);
    }

}
