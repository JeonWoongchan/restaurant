package com.example.restaurant.service;


import com.example.restaurant.entity.Reserve;
import com.example.restaurant.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class ReserveService {


  ReserveRepository reserveRepository;



  public String addreserve(Reserve reserve) {

      reserveRepository.save(reserve);
      return "성공";
  }


}
