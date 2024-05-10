package com.example.restaurant.repository;

import com.example.restaurant.dto.ReserveDTO;
import com.example.restaurant.entity.Reserve;
import jakarta.transaction.Transactional;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,Long> {

   @Query("select new com.example.restaurant.dto.ReserveDTO(r.reserve_id, r.reg_date,r.reserve_date,r.end_date,r.adults_count,r.children_count,r.infants_count, customer.username, customer.phone)  FROM Reserve r INNER JOIN r.customer customer  WHERE customer.customer_id = :id")
   List<ReserveDTO> selectReserve(Long id);

   @Query("select new com.example.restaurant.dto.ReserveDTO(r.reserve_id, r.reg_date,r.reserve_date,r.end_date,r.adults_count,r.children_count,r.infants_count, customer.username, customer.phone)  FROM Reserve r INNER JOIN r.customer customer  WHERE customer.customer_id = :id")
   Optional<ReserveDTO> selectReserveandreserveid(Long id);


   @Query("select r.reserve_id  FROM Reserve r INNER JOIN r.customer customer  WHERE customer.customer_id = :id ")
   List<Long> SelectreserveId(Long id);

   @Transactional
   @Modifying
   @Query("delete  FROM Reserve r WHERE r.reserve_id = :reserve_id")
   int deleteisreserveid(@Param("reserve_id") Long reserve_id);

   @Query("select r.reserve_id FROM Reserve r WHERE r.reserve_id = :reserve_id")
   Optional<Integer> selectdelete(@Param("reserve_id") Long reserve_id);
}



