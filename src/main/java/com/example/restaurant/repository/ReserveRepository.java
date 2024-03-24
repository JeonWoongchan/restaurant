package com.example.restaurant.repository;

import com.example.restaurant.dto.ReserveDTO;
import com.example.restaurant.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,Long> {

   @Query("select new com.example.restaurant.dto.ReserveDTO( r.reg_date,r.reserve_date,r.end_date,r.adults_count,r.children_count,r.infants_count) from Reserve  r where r.customer=:customer_id ")
   List<ReserveDTO> selectReserve(Long customer_id);


}



