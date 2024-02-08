package com.example.restaurant.repository;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {


    Restaurant findByRestname(String restname);

    @Query("SELECT r.rest_id FROM Restaurant r WHERE r.restname = :restname")
    Optional<Integer> findByRestIDRestname(@Param("restname") String restname);
    @Query("SELECT r FROM Restaurant r WHERE r.restname = :restname")
    Restaurant findByRestIDRestnameNew(@Param("restname") String restname);

    @Query("SELECT r.maxSeat FROM Restaurant r WHERE r.restname = :restname")
    Optional<Long> findByMaxSeat(@Param("restname") String restname);

}
