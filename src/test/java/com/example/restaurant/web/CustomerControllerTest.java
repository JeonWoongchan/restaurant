//package com.example.restaurant.web;
//
//import com.example.restaurant.dto.CustomerDto;
//import com.example.restaurant.entity.Customer;
//import com.example.restaurant.service.CustomerService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import jakarta.servlet.http.HttpSession;
//
//import java.util.HashMap;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class CustomerControllerTest {
//
//  @Mock
//  private CustomerService customerService;
//
//  @Mock
//  private HttpSession session;
//
//  @InjectMocks
//  private CustomerController customerController;
//
//  @Test
//  public void testLogin() {
//    // Mocking behavior of the service
//    CustomerDto dto = new CustomerDto();
//    dto.setEmail("test@example.com");
//    dto.setPassword("password");
//
//    HashMap<String, Optional<String>> expectedResult = new HashMap<>();
//    expectedResult.put("userId", Optional.of("12345"));
//    when(customerService.login(dto.getEmail(), dto.getPassword(), session)).thenReturn(expectedResult);
//
//    // Performing the test
//    ResponseEntity<HashMap<String, Optional<String>>> responseEntity = customerController.login(dto, session);
//
//    // Assertions
//    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    assertEquals(expectedResult, responseEntity.getBody());
//  }
//
//  @Test
//  public void testSignUp() throws Exception {
//    // Mocking behavior of the service
//    Customer customer = new Customer("규식", "rbtlr1234","kksos28@naver.com","010-4260-9481",1); // Initialize with necessary data
//    when(customerService.joinCustomer(any(Customer.class))).thenReturn("Success"); // Mock service response
//
//    // Performing the test
//    ResponseEntity<String> responseEntity = customerController.signup(customer);
//
//    // Assertions
//    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    assertEquals("Success", responseEntity.getBody());
//  }
//
//  @Test
//  public void testDuplicate() throws Exception {
//    // Mocking behavior of the service
//    CustomerDto dto = new CustomerDto("kksos28@naver.com"); // Initialize with necessary data
//    when(customerService.duplicateCustomer(any(String.class))).thenReturn(true); // Mock service response
//
//    // Performing the test
//    ResponseEntity<Boolean> responseEntity = customerController.duplicate(dto);
//
//    // Assertions
//    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    assertEquals(true, responseEntity.getBody());
//  }
//
//
//
//}
