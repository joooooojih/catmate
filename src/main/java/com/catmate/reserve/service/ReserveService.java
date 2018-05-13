package com.catmate.reserve.service;

import java.util.List;
import java.util.Map;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;

public interface ReserveService {

    public Map<String, Object> getPet_sitter_house(int idx);
    public void insertReservation(ReservationDto reservationDto);
    public List<ReservationDto> getReservation(int idx);
    public Map<String, Object> getPet_sitter_houseList(ReservationDto reservationDto, Pet_sitter_houseDto pet_sitter_houseDto);
    
}
