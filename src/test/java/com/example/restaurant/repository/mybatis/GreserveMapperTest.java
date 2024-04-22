package com.example.restaurant.repository.mybatis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class GreserveMapperTest {

  @Autowired
  private GreserveMapper greserveMapper;

  @Autowired
  private CapacityMapper capacityMapper;

  @Test
  public void Test() {
    List<Map<String, Object>> capacityList = capacityMapper.selectcapalist();
    List<HashMap<String, Object>> totalCountList = greserveMapper.selectTotalCountByHour("2024-04-03");

    // 용량 정보를 키로 하는 맵 생성
    Map<String, Integer> capacityMap = new HashMap<>();
    for (Map<String, Object> data : capacityList) {
      String hourTime = data.get("hour_time").toString(); // hour_time을 문자열로 변환
      Integer capacity = (Integer) data.get("capacity");
      capacityMap.put(hourTime, capacity);
    }

    // 예약 가능 여부 확인
    Map<String, Integer> availableSlots = new HashMap<>();
    for (Map<String, Object> data : totalCountList) {
      String reserveDateTime = data.get("reserve_date").toString(); // reserve_date를 문자열로 변환
      BigDecimal totalCount = (BigDecimal) data.get("total_count");
      int reservedCount = totalCount.intValue(); // 총 예약 수를 정수로 변환

      // 시간대를 기반으로 용량을 확인
      if (capacityMap.containsKey(reserveDateTime)) {
        int capacity = capacityMap.get(reserveDateTime);
        int remainingCapacity = capacity - reservedCount;

        // 잔여 용량이 0 이하이면 무시, 그렇지 않으면 예약 가능한 시간대로 추가
        if (remainingCapacity > 0) {
          availableSlots.put(reserveDateTime, remainingCapacity);
        }
      }
    }

    // 예약 가능한 시간대와 잔여 용량 출력
    for (Map.Entry<String, Integer> entry : availableSlots.entrySet()) {
      System.out.println("Time: " + entry.getKey() + ", Remaining capacity: " + entry.getValue());
    }
  }
}

