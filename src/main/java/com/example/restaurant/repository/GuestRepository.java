package com.example.restaurant.repository;

import com.example.restaurant.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Long> {



  @Query("SELECT g.guest_id FROM Guest g WHERE g.phone = :phone")
  Optional<Long> findByID(String phone);
}
