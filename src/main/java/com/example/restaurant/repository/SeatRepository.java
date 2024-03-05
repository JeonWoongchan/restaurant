//package com.example.restaurant.repository;
//
//import com.example.restaurant.entity.Seat;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.Optional;
//
//public interface SeatRepository extends JpaRepository<Seat,Long> {
//
//
//    @Query("SELECT SUM(s.guest_count) FROM Seat s WHERE s.restaurant.rest_id = :restId")
//    Optional<Long> findByGuestCount(@Param("restId") long restId);
//
//
//
//
//}
