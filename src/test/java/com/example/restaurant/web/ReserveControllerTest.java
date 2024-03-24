//package com.example.restaurant.web;
//
//import com.example.restaurant.entity.Customer;
//import com.example.restaurant.service.CustomerService;
//import jakarta.servlet.http.HttpSession;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpSession;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//public class ReserveControllerTest {
//
//
//
//
//    @Mock
//    private CustomerService customerService;
//
//
//
//    @InjectMocks
//    private ReserveController reserveController;
//
//    @BeforeEach
//    public void setup() {
//      MockitoAnnotations.openMocks(this);
//    }
//
//  @Test
//  public void testUserData() {
//    // HttpSession 객체 생성
//    MockHttpSession session = new MockHttpSession();
//
//    // 이메일 값 설정
//    session.setAttribute("email", "dsds@naver.com");
//
//    // 이메일 값이 일치하는 것으로 가정한 MockCustomer 객체 생성
//    Customer mockCustomer = new Customer(1L, "kksos28", "rbtlr", "kksos28@naver.com", "010-4260-9481", 1);
//
//
//   String email = (String) session.getAttribute("email");
//    // session.getAttribute("email")이 "dsds@naver.com"을 반환하도록 설정
//    when(session.getAttribute("email")).thenReturn("dsds@naver.com");
//
//    // customerService.selectMember(session)이 Optional.of(mockCustomer)를 반환하도록 설정
//    when(customerService.selectMember(session)).thenReturn(Optional.of(mockCustomer));
//
//    // reserveController.reserve(session) 호출
//    ResponseEntity<Optional<Customer>> responseEntity = reserveController.reserve(session);
//
//    Optional<Customer> customerOptional = responseEntity.getBody();
//    if (email.equals(mockCustomer.getEmail())) {
//      Customer customer = customerOptional.get();
//      System.out.println(customer);
//    } else {
//      System.out.println("No customer found");
//    }
//
//    // HTTP 응답의 상태 코드가 HttpStatus.OK인지 검증
//    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//  }
//
//
//}
//
//
