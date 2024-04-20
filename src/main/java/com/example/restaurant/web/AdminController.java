package com.example.restaurant.web;


import com.example.restaurant.entity.Customer;
import com.example.restaurant.repository.mybatis.AdminMapper;
import com.example.restaurant.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController


@RequestMapping("/api/admin")

public class AdminController {

  @Autowired
  AdminService adminService;
  @Autowired
  AdminMapper adminMapper;

  @GetMapping("/user")
  public ResponseEntity<Map<String,Object>> selectusers(@RequestParam(defaultValue = "") String type,
                                                         @RequestParam(defaultValue = "") String word,
                                                         @RequestParam(defaultValue = "1") int nowPage,
                                                         @RequestParam(defaultValue = "10") int recordPerPage) {



    return ResponseEntity.ok(adminService.selectUserPaging(type, word, nowPage, recordPerPage));




  }
//  @GetMapping("/reserv")
//  public ResponseEntity<ArrayList<Customer>> selectreserves(@RequestParam(required = false) String type,
//                                                         @RequestParam(required = false) String word,
//                                                         @RequestParam(defaultValue = "1") int nowPage,
//                                                         @RequestParam(defaultValue = "10") int recordPerPage) {
//
//
//    return new ResponseEntity<>(adminService.selectUserPaging(type, word, nowPage, recordPerPage), HttpStatus.OK);
//
//
//
//
//  }


}
