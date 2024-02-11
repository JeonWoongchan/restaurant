package com.example.restaurant.repository;

import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.entity.Seat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Rollback(value = false)
@Transactional
@SpringBootTest

public class SeatRestaurantTest {
   @Autowired  RestaurantRepository restaurantRepository;
   @Autowired SeatRepository seatRepository;
//    @Test
//    public void testChangeRestaurant() {
//        // 테스트용 레스토랑 객체 생성
//        Restaurant restaurant = new Restaurant("짬뽕의 달인","경기도 수원시 장안구 이목로 24", "070-2344-2222",10);
//        Restaurant restaurant2 = new Restaurant("국물의 달인","경기도 수원시 장안구 이목로 24", "070-2344-2222", 10);
//        restaurantRepository.save(restaurant);
//        restaurantRepository.save(restaurant2);
//        // 테스트용 좌석 객체 생성
//        Seat seat = new Seat(1, 4,restaurant); // 좌석 번호: 1, 수용 인원: 4
//        Seat seat2 = new Seat(1, 4,restaurant2);
//        seatRepository.save(seat);
//
//        // 좌석과 레스토랑 연결
//        seat.changeRestaurant(restaurant);
//        seat2.changeRestaurant(restaurant2);
//
//
//
//        // 좌석의 레스토랑과 연결된 레스토랑의 좌석 목록을 확인하여 상호 연결 여부 확인
//
//    }
     @Test
    public  void reserveTest() {
         String restname = "국물의 달인";
         String answer = "yes";
         Restaurant restaurant  = restaurantRepository.findByRestIDRestnameNew(restname);
         if (restaurant == null) {
             System.out.println("해당하는 레스토랑이 없습니다.");
             return;
         }

         Optional<Long> resevercount = seatRepository.findByGuestCount(restaurant.getRest_id());
         Optional<Long> maxcount = restaurantRepository.findByMaxSeat(restname);

         
         if (resevercount.isPresent() && maxcount.isPresent()) {
             if (maxcount.get() <= resevercount.get()) {
                 System.out.println("예약할 수 없습니다.");
             } else {
                 System.out.println("예약 가능");
                 System.out.println("예약하시겠습니까?");
                 if (answer.equalsIgnoreCase("yes")) {
                     System.out.println("예약되었습니다.");
                     Seat seat = new Seat(1, 1, restaurant);
                     seatRepository.save(seat);
                 } else {
                     System.out.println("예약이 취소되었습니다.");
                 }
             }
         } else {
             System.out.println("없어");
         }
         System.out.println(resevercount);
     }





}