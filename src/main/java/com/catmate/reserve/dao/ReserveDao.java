package com.catmate.reserve.dao;

import java.util.List;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.Room_photoDto;

public interface ReserveDao {

    public Pet_sitter_houseDto getPet_sitter_house(int idx);
    public List<Room_photoDto> getRoom_photoDto(int idx);
    public List<Pet_sitter_houseDto> getPet_sitter_houseList();
    public void insertReservation(ReservationDto reservationDto);
    public List<ReservationDto> getReservationDto(int idx);
    public List<ReservationDto> getReservation_search(ReservationDto reservationDto);
    public List<Pet_sitter_houseDto> getPet_sitter_house_not(List<ReservationDto> reservationList);
    public Pet_sitter_houseDto getPet_sitter_house_search(Pet_sitter_houseDto pet_sitter_houseDto);
    
}
