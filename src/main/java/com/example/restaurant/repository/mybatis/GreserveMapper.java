package com.example.restaurant.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface GreserveMapper {
   public  Integer getcount(String reserve_date);

   public List<HashMap<String,Object>> selectTotalCountByHour(String reserve_date);

}
