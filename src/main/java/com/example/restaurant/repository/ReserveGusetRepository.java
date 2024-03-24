package com.example.restaurant.repository;

import com.example.restaurant.entity.GReserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveGusetRepository extends JpaRepository<GReserve,Long> {




}



