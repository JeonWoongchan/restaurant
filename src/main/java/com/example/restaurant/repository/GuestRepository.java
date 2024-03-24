package com.example.restaurant.repository;

import com.example.restaurant.entity.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {


  @Query("SELECT g.guest_id FROM Guest g WHERE g.phone = :phone")
  Optional<Guest> findByID(String phone);
}
