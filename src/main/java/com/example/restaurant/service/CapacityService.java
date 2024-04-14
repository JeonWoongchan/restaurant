package com.example.restaurant.service;

import com.example.restaurant.dto.RequestDataDTO;
import com.example.restaurant.repository.mybatis.CapacityMapper;
import com.example.restaurant.repository.mybatis.GreserveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CapacityService {

  @Autowired
  private CapacityMapper capacityMapper;

  @Autowired
  private GreserveMapper greserveMapper;

  public Map<String, Integer> getAvailableSlots(RequestDataDTO dto) {
    // capacity 데이터 가져오기
    List<Map<String, Object>> capacityList = capacityMapper.selectcapalist();
    // totalCount 데이터 가져오기
    List<HashMap<String, Object>> totalCountList = greserveMapper.selectTotalCountByHour(dto.getDate());

    // 시간대를 키로 하는 capacity 맵 생성
    Map<String, Integer> capacityMap = new HashMap<>();
    for (Map<String, Object> data : capacityList) {
      String hourTime = data.get("hour_time").toString(); // hour_time을 문자열로 변환
      Integer capacity = (Integer) data.get("capacity");
      capacityMap.put(hourTime, capacity);
    }

    // 예약 가능한 시간대와 잔여 용량 계산
    Map<String, Integer> availableSlots = new HashMap<>();
    for (Map<String, Object> data : totalCountList) {
      String reserveDateTime = data.get("reserve_date").toString(); // reserve_date를 문자열로 변환
      BigDecimal totalCount = (BigDecimal) data.get("total_count");
      int reservedCount = totalCount.intValue(); // 총 예약 수를 정수로 변환

      // 시간대를 기반으로 용량 확인
      if (capacityMap.containsKey(reserveDateTime)) {
        int capacity = capacityMap.get(reserveDateTime);
        int remainingCapacity = capacity - reservedCount - dto.getTotalCount();

        // 잔여 용량이 0 이하이면 무시, 그렇지 않으면 예약 가능한 시간대로 추가
        if (remainingCapacity >= 0) {
          availableSlots.put(reserveDateTime, remainingCapacity);
        }
      }
    }
    return availableSlots;
  }
}
