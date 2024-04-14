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

  @Test
  public void Test() {
    List<HashMap<String, Object>> transformedList = new ArrayList<>();
    List<HashMap<String, Object>> totalCountList = greserveMapper.selectTotalCountByHour("2024-04-03");

    HashMap<String, Integer> capacity = new HashMap<>();
    String[] hours = {"11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"};
    Integer[] capacities = {20, 30, 50, 40, 25, 75, 35, 32, 40, 40};
    for (int i = 0; i < hours.length; i++) {
      capacity.put(hours[i], capacities[i]);
    }

    // 가정: DB에서 받아온 예약 정보 리스트

    // 여기에 DB 쿼리 결과를 가져오는 코드를 넣어주세요.

    // 예약 가능 여부 계산
    List<String> result = new ArrayList<>();
    for (String key : capacity.keySet()) {
      int availableSeats = capacity.get(key); // 해당 시간대의 남은 좌석 수

      for (HashMap<String, Object> map : totalCountList) {
        String reserveDateTime = (String) map.get("reserve_date");
        String reserveTime = reserveDateTime.split(" ")[1]; // 시간 정보만 추출

        String[] timeParts = reserveTime.split(":");
        if (timeParts.length < 2) {
          continue;
        }
        int hour = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        // 예약된 시간대의 좌석 수를 감소시킵니다.
        if (key.equals(String.format("%02d:%02d", hour, minutes))) {
          BigDecimal totalCount = (BigDecimal) map.get("total_count");
          availableSeats -= totalCount.intValue();
        }
      }

      if (availableSeats > 0) {
        result.add(key + " : 예약 가능 (" + availableSeats + " 자리 남음)");
      } else {
        result.add(key + " : 예약 불가 (모든 자리 예약됨)");
      }
    }

    // 결과를 시간대 순으로 정렬
    Collections.sort(result, Comparator.naturalOrder());

    // 결과 출력
    for (String entry : result) {
      System.out.println(entry);
    }
  }

}