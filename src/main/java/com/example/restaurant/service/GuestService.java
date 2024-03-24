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



  public Page<GReserveDTO> getGuestsByGuestId( int pageNumber, int pageSize,String phone) {


    Optional<Guest> guestIdOptional = guestRepository.findByID(phone);

    if (guestIdOptional.isPresent()) {

      Pageable pageable = PageRequest.of(pageNumber, pageSize);

      // 여기서 데이터베이스에서 해당 guestId에 대한 Guest 개체를 가져오는 작업을 수행합니다.
      Guest guest = guestIdOptional.get();

      Page<GReserveDTO> guestsPage = gReserveRepository.info(guest,pageable);

      return guestsPage;

    } else {
      return  null;
    }
  }
}
