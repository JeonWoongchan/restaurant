package com.example.restaurant.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper

public interface CapacityMapper {


  int updateCapacities(List<Map<String, Object>> capacities);

  List<Map<String,Object>> selectcapalist();
}
