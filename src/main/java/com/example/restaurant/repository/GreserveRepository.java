package com.example.restaurant.repository;

import com.example.restaurant.dto.GReserveDTO;
import com.example.restaurant.entity.GReserve;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface GreserveRepository extends JpaRepository<GReserve,Long> {

  @Query("select new com.example.restaurant.dto.GReserveDTO(g.reg_date,g.reserve_date,g.end_date,g.adults_count,g.children_count,g.infants_count) from GReserve g where g.guest = :guest_id")
  Page<GReserveDTO> info(Long guest_id, Pageable pageable);


}
