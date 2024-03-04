//package com.example.restaurant.repository;
//
//import com.example.restaurant.entity.Customer;
//import com.example.restaurant.entity.CustomerCount;
//import com.example.restaurant.entity.Reserve;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Transactional
//@Rollback(value = false)
//public class CustomerCountRepositoryTest {
//
//
//
//
//  @Autowired
//  private CustomerCountRepository customerCountRepository;
//
//  @Autowired
//  private ReserveRepository reserveRepository;
//
//  @Autowired
//  private CustomerRepository customerRepository;
//
//  @Test
//
//  public void testSaveCustomerCount() {
//    // 예약 생성
//    Customer customer = new Customer("김규식","rbtlr1234","kksos28@naver.com","010-4260-9481",100);
//
//
//    customerRepository.save(customer);
//
//    Reserve reserve = new Reserve(customer,"d","d","d","ㅎㅇ");
//
//    reserveRepository.save(reserve);
//
//    // CustomerCount 생성
//    CustomerCount customerCount = new CustomerCount(reserve,6,6,6);
//
//    customerCountRepository.save(customerCount);
//    // 저장된 CustomerCount 조회
//    CustomerCount foundCustomerCount = customerCountRepository.findById(customerCount.getCount_id()).orElse(null);
//    assertThat(foundCustomerCount).isNotNull();
//    assertThat(foundCustomerCount.getReserve()).isEqualTo(reserve);
//    assertThat(foundCustomerCount.getAdults_count()).isEqualTo(6);
//    assertThat(foundCustomerCount.getChildren_count()).isEqualTo(6);
//    assertThat(foundCustomerCount.getInfants_count()).isEqualTo(6);
//  }
//}
