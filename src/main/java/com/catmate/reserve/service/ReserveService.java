package com.catmate.reserve.service;

import java.util.List;
import java.util.Map;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.Room_photoDto;

public interface ReserveService {

    public Map<String, Object> getSitter_detail(int idx);
    public Pet_sitter_houseDto getPet_sitter_house(int idx);
    public void insertReservation(ReservationDto reservationDto);
    public List<Pet_sitter_houseDto> getPet_sitter_houseList();
    public List<Room_photoDto> getRoom_photo(int idx);
    public List<ReservationDto> getReservation(int idx);
    public List<ReservationDto> getReservationList();
    public List<Pet_sitter_houseDto> getPet_sitter_house_not(List<ReservationDto> reservationList);
    public List<Pet_sitter_houseDto> getPet_sitter_house_searchList(Map<String, Object> search_map);
    public int getAreaCount(String area);
    public int getPetCount(String user_email);
}
