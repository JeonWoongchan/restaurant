package com.example.restaurant.repository;

import com.example.restaurant.dto.ReserveDTO;
import com.example.restaurant.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,Long> {

   @Query("select new com.example.restaurant.dto.ReserveDTO( r.reg_date,r.reserve_date,r.end_date,r.adults_count,r.children_count,r.infants_count, customer.username, customer.phone)  FROM Reserve r INNER JOIN r.customer customer  WHERE customer.id = :id")
   List<ReserveDTO> selectReserve(Long id);


}



