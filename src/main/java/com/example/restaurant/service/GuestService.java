package com.example.restaurant.service;


import com.example.restaurant.dto.GReserveDTO;
import com.example.restaurant.entity.GReserve;
import com.example.restaurant.entity.Guest;
import com.example.restaurant.repository.GreserveRepository;
import com.example.restaurant.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuestService {

  @Autowired
  private GreserveRepository gReserveRepository;

  @Autowired
  private GuestRepository guestRepository;



  public Page<GReserveDTO> getGuestsByGuestId(int pageNumber, int pageSize, String phone) {
    Optional<Long> guestOptional = guestRepository.findByPhone(phone); // 메소드 이름을 명확하게 변경

    if (guestOptional.isPresent()) {
      Pageable pageable = PageRequest.of(pageNumber, pageSize);
      Long guest = guestOptional.get();
      Page<GReserveDTO> guestsPage = gReserveRepository.info(guest, pageable);
      return guestsPage;
    } else {
      return Page.empty(); // null 대신 비어있는 Page 반환
    }
  }
}
