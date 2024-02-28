package com.example.restaurant.service;


import com.example.restaurant.dto.ReserveAndCount;
import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.CustomerCount;
import com.example.restaurant.entity.Reserve;
import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.repository.ReserveRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReserveService {


  ReserveRepository reserveRepository;
  CustomerRepository customerRepository;

  CustomerService customerService;




// 다른 코드들...



// 다른 코드들...

  // addReserveAndCount 메서드에서 세션에서 고객 ID를 가져와서 사용하는 코드
  public  String addReserveAndCount(ReserveAndCount data, HttpSession session) {
    Reserve reserve = data.getReserve();
    CustomerCount customerCount = data.getCustomerCount();

    // 세션에서 고객 정보를 가져옵니다.
    Optional<Customer> customerOptional = customerService.selectMember(session);

    if (customerOptional.isPresent()) {
      // 사용자가 로그인한 경우
      Customer customer = customerOptional.get();
      reserve.setCustomer(customer);
      customerCount.setReserve(reserve); // 연관관계 설정

      // Reserve와 CustomerCount를 저장하고자 하는 로직을 여기에 구현합니다.
      reserve.getCustomerCounts().add(customerCount);
      reserveRepository.save(reserve);
      return "회원";
    } else {
      // 사용자가 비회원인 경우
      // 비회원 처리 로직을 추가하거나 예외를 던지거나, 다른 처리를 하십시오.
      return "비회원";
    }




  }



}
