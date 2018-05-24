package com.catmate.mypage.service;

import java.util.List;

import com.catmate.dto.Pet_profileDto;
import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.User_profileDto;
import com.catmate.dto.Wish_listDto;

public interface MypageService {

    public User_profileDto getUser_profile(String user_email);
    public void insertUser_profile(User_profileDto user_profileDto);
    
    /*개인정보*/
    public void updateUser_profile(User_profileDto user_profileDto);
    public void updateUser_profile_pass(User_profileDto user_profileDto);
    public void deleteUser_profile(User_profileDto user_profileDto);
    
    /*예약내역*/
    public List<ReservationDto> getReservation_mypageList(User_profileDto user_profileDto);
    public List<Pet_sitter_houseDto> getMy_pet_sitter_houseList(User_profileDto user_profileDto);
    public List<ReservationDto> getMy_reservationList(List<Pet_sitter_houseDto> pet_sitter_houseList);
    public void deleteReservation(ReservationDto reservationDto);
    
    /*강아지 정보 관련*/
    public void insertPet_profile(Pet_profileDto pet_profileDto);
    public List<Pet_profileDto> getPet_profileList(User_profileDto user_profileDto);
    public Pet_profileDto getPet_profile(Pet_profileDto pet_profileDto);
    public void updatePet_profile(Pet_profileDto pet_profileDto);
    
    /*위시리스트*/
    public int getWish_list(Wish_listDto wish_listDto);
    public List<Wish_listDto> getWish_listList(User_profileDto user_profileDto);
    public void insertWish_list(Wish_listDto wish_listDto);
    public void deleteWish_list(Wish_listDto wish_listDto);

}
