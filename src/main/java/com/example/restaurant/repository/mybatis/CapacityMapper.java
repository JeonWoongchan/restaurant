package com.example.restaurant.repository.mybatis;

import java.util.List;
import java.util.Map;

public interface CapacityMapper {


  int updateCapacities(List<Map<String, Object>> capacities);
}
