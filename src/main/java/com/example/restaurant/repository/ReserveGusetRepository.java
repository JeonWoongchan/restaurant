package com.example.restaurant.repository;

import com.example.restaurant.entity.Reserve;
import com.example.restaurant.entity.ReserveGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveGusetRepository extends JpaRepository<ReserveGuest,Long> {




}



