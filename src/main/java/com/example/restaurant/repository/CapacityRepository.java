package com.example.restaurant.repository;


import com.example.restaurant.entity.Capacity;
import jakarta.transaction.Transactional;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CapacityRepository extends JpaRepository<Capacity,Long> {


  @Transactional
  @Modifying

  @Query("UPDATE Capacity c SET c.capacity = :capacity WHERE c.hour_time = :hour_time")
  int updateCapacityByHourTime(@Param("capacity") String hour_time, @Param("hour_time") int capacity);
}
