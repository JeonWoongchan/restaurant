package com.example.restaurant.repository;

import com.example.restaurant.entity.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
@Transactional
@Rollback(value = false)
class RestaurantRepositoryTest {

    @Autowired RestaurantRepository restaurantRepository;


    @Test
    public void createSelect()  {
        Restaurant restaurant = new Restaurant("짬뽕의 달인","경기도 수원시 장안구 이목로 24", "070-2344-2222",15);

        restaurantRepository.save(restaurant);




        Restaurant  findrest1 = restaurantRepository.findByRestname("짬뽕의 달인");


        assertThat(restaurant).isEqualTo(findrest1);
    }


    @Test
    public  void  findRestaurnat() {
        Restaurant restaurant = new Restaurant("짬뽕의 달인","경기도 수원시 장안구 이목로 24", "070-2344-2222",15);

        restaurantRepository.save(restaurant);

        String restname = "짬뽕의 달인";


        Optional<Integer> findname = restaurantRepository.findByRestIDRestname(restname);


        System.out.println("findname = " + findname);


    }

}