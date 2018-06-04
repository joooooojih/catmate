package com.catmate.mypage.dao;

import java.util.List;
import java.util.Map;

import com.catmate.dto.MsgDto;
import com.catmate.dto.Pet_profileDto;
import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.ReviewDto;
import com.catmate.dto.User_profileDto;
import com.catmate.dto.Wish_listDto;

public interface MypageDao {
    
    public User_profileDto getUser_profile(String user_email);
    public void insertUser_profile(User_profileDto user_profileDto);
    
    /*개인정보*/
    public void updateUser_profile(User_profileDto user_profileDto);
    public void updateUser_profile_pass(User_profileDto user_profileDto);
    public void deleteUser_profile(User_profileDto user_profileDto);
    
    /*예약 내역*/
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
    
    /*후기*/
    public List<ReviewDto> getMyReviewList(List<Pet_sitter_houseDto> pet_sitter_houseList);
    public void updateReservation_review(ReservationDto reservationDto);
    public void updateUser_profile_grade(User_profileDto user_profileDto);
    public void insertReview(ReviewDto reviewDto);
    
    /*메시지*/
    public void insertMsg(MsgDto msgDto);
    public List<MsgDto> getMsgList(Map<String, Object> search_map);
    public MsgDto getMsg_new(MsgDto msgDto);
    public MsgDto getMsgLately(MsgDto msgDto);
    public List<User_profileDto> getUser_profileList();
    public int getMsgCount(MsgDto msgDto);
    public List<MsgDto> getMsgLatelyList(List<MsgDto> msgList);
}
