package com.example.restaurant.service;


import com.example.restaurant.entity.Guest;
import com.example.restaurant.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GuestService {
  @Autowired
  private GuestRepository guestRepository;
  public Page<Guest> getGuestsByGuestId(Long guestId, int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return guestRepository.findByGuestId(guestId, pageable);
  }
}
