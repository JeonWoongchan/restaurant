package com.example.restaurant.service;

import com.example.restaurant.dto.RequestDataDTO;
import com.example.restaurant.repository.mybatis.CapacityMapper;
import com.example.restaurant.repository.mybatis.GreserveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CapacityService {

  @Autowired
  private CapacityMapper capacityMapper;

  @Autowired
  private GreserveMapper greserveMapper;

  public Map<String, Object> getAvailableSlots(RequestDataDTO requestData) {
    // capacity 데이터 가져오기
    List<Map<String, Object>> capacityList = capacityMapper.selectcapalist();

    // totalCount 데이터 가져오기
    String reserveDate = requestData.getReserve_date();
    String formattedDate = reserveDate.replace('.', '-');
    List<HashMap<String, Object>> totalCountList = greserveMapper.selectTotalCountByHour(formattedDate);

    // 시간대를 키로 하는 capacity 맵 생성
    Map<String, Integer> capacityMap = new HashMap<>();
    for (Map<String, Object> data : capacityList) {
      String hourTime = data.get("hour_time").toString();
      Integer capacity = (Integer) data.get("capacity");
      capacityMap.put(hourTime, capacity);
    }

    // 예약 가능한 시간대와 불가능한 시간대를 저장할 맵
    Map<String, Integer> availableSlots = new HashMap<>();
    Map<String, Integer> unavailableSlots = new HashMap<>();

    // capacityMap의 모든 시간대를 `availableSlots`에 초기화합니다.
    for (String hourTime : capacityMap.keySet()) {
      availableSlots.put(hourTime, capacityMap.get(hourTime));
    }

    // totalCountList를 기반으로 각 시간대의 예약 수를 계산하고
    // 예약 가능한 시간대와 불가능한 시간대를 분리합니다.
    for (Map<String, Object> data : totalCountList) {
      String reserveDateTime = data.get("reserve_date").toString();
      BigDecimal totalCount = (BigDecimal) data.get("total_count");
      int reservedCount = totalCount.intValue();

      // `capacityMap`에서 해당 시간대의 용량을 가져옵니다.
      if (capacityMap.containsKey(reserveDateTime)) {
        int capacity = capacityMap.get(reserveDateTime);

        // 남은 용량을 계산합니다.
        int remainingCapacity = capacity - reservedCount;

        // 요청된 예약 인원과 남은 용량을 비교합니다.
        int excess = requestData.getTotal_count() - remainingCapacity;

        if (excess > 0) {
          // 예약 불가능한 시간대
          unavailableSlots.put(reserveDateTime, excess);
          // 예약 불가능한 시간대를 availableSlots에서 제거
          availableSlots.remove(reserveDateTime);
        } else {
          // 예약 가능한 시간대
          availableSlots.put(reserveDateTime, remainingCapacity);
        }
      }
    }

    // 결과를 반환합니다.
    Map<String, Object> result = new HashMap<>();
    result.put("예약 가능", availableSlots);
    result.put("예약 불가", unavailableSlots);

    return result;
  }
}
