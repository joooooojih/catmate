package com.catmate.mypage.dao;

import java.util.List;

import com.catmate.dto.Pet_profileDto;
import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.User_profileDto;

public interface MypageDao {
    
    public User_profileDto getUser_profile(String user_email);
    public void insertUser_profile(User_profileDto user_profileDto);
    
    public void updateUser_profile(User_profileDto user_profileDto);
    public void updateUser_profile_pass(User_profileDto user_profileDto);
    public void deleteUser_profile(User_profileDto user_profileDto);
    
    public List<ReservationDto> getReservation_mypageList(User_profileDto user_profileDto);
    public List<Pet_sitter_houseDto> getMy_pet_sitter_houseList(User_profileDto user_profileDto);
    public List<ReservationDto> getMy_reservationList(List<Pet_sitter_houseDto> pet_sitter_houseList);
    
    public void insertPet_profile(Pet_profileDto pet_profileDto);
    public List<Pet_profileDto> getPet_profileList(User_profileDto user_profileDto);
    public Pet_profileDto getPet_profile(Pet_profileDto pet_profileDto);
    public void updatePet_profile(Pet_profileDto pet_profileDto);
}
