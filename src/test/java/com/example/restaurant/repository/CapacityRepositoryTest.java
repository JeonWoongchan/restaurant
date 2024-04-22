package com.example.restaurant.repository;

import com.example.restaurant.entity.Capacity;
import com.example.restaurant.repository.mybatis.CapacityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CapacityRepositoryTest {
  @Autowired
  private CapacityMapper capacityMapper;

  @Test
  public void testUpdateCapacity() {
    // 테스트를 위한 초기 데이터 설정
    List<Map<String, Object>> mapList = new ArrayList<>();
    for (int hour = 1; hour <= 20; hour++) {
      Map<String, Object> data = new HashMap<>();
      String hourTime = String.format("%02d:00:00", hour); // 시간 형식 예: "11:00"
      int capacity = hour * 10; // 예시로 시간에 따라 capacity를 설정 (예: 11:00 => 110)

      data.put("id", hour);
      data.put("capacity", 50);
      mapList.add(data);
    }
    capacityMapper.updateCapacities(mapList);
  }
}