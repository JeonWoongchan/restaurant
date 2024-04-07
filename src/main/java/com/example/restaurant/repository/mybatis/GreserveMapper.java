package com.example.restaurant.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GreserveMapper {
   public  Integer getcount(String reserve_date);

}
