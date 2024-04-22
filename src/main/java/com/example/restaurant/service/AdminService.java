package com.example.restaurant.service;


import com.example.restaurant.entity.Customer;
import com.example.restaurant.repository.mybatis.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {

  @Autowired
  private AdminMapper adminMapper;



  public Map<String,Object> selectUserPaging(String type, String word, int nowPage, int recordPerPage) {


    int startNum = (nowPage - 1) * recordPerPage;

    int totalCount = adminMapper.listSearchCount();
    ArrayList<Customer> selectuserpaging = adminMapper.selectuserpaging(type, word, startNum, recordPerPage);

    // 전체 페이지 수 계산
    int totalPages = (int) Math.ceil((double) totalCount / recordPerPage);

    // 응답 객체 생성
    Map<String, Object> response = new HashMap<>();
    response.put("users", selectuserpaging);
    response.put("totalPages", totalPages);





    // endNum을 사용하지 않고, recordPerPage를 직접 전달합니다.
    return response;
  }




}
