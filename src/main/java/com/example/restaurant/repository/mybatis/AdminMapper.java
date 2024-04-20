package com.example.restaurant.repository.mybatis;


import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface AdminMapper {


  ArrayList<Customer> selectuserpaging(@Param("type") String type,
                                       @Param("word") String word,
                                       @Param("start_num") int startNum,
                                       @Param("record_per_page") int record_per_page);


  public int listSearchCount();

}
