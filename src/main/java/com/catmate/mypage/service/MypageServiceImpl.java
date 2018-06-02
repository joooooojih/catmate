package com.catmate.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catmate.dto.MsgDto;
import com.catmate.dto.Pet_profileDto;
import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.ReviewDto;
import com.catmate.dto.User_profileDto;
import com.catmate.dto.Wish_listDto;
import com.catmate.mypage.dao.MypageDao;

@Service("MypageService")
public class MypageServiceImpl implements MypageService {

    @Autowired
    MypageDao mypageDao;
    
    @Override
    public User_profileDto getUser_profile(String user_email) {
        return mypageDao.getUser_profile(user_email);
    }

    @Override
    public void insertUser_profile(User_profileDto user_profileDto) {
        mypageDao.insertUser_profile(user_profileDto);
    }
    
    @Override
    public void updateUser_profile(User_profileDto user_profileDto) {
        mypageDao.updateUser_profile(user_profileDto);
    }

    @Override
    public void updateUser_profile_pass(User_profileDto user_profileDto) {
        mypageDao.updateUser_profile_pass(user_profileDto);
    }

    @Override
    public void deleteUser_profile(User_profileDto user_profileDto) {
        mypageDao.deleteUser_profile(user_profileDto);
    }

    @Override
    public List<ReservationDto> getReservation_mypageList(User_profileDto user_profileDto) {
        return mypageDao.getReservation_mypageList(user_profileDto);
    }

    @Override
    public List<Pet_sitter_houseDto> getMy_pet_sitter_houseList(User_profileDto user_profileDto) {
        return mypageDao.getMy_pet_sitter_houseList(user_profileDto);
    }

    @Override
    public List<ReservationDto> getMy_reservationList(List<Pet_sitter_houseDto> pet_sitter_houseList) {
        return mypageDao.getMy_reservationList(pet_sitter_houseList);
    }

    @Override
    public void insertPet_profile(Pet_profileDto pet_profileDto) {
        mypageDao.insertPet_profile(pet_profileDto);
    }

    @Override
    public List<Pet_profileDto> getPet_profileList(User_profileDto user_profileDto) {
        return mypageDao.getPet_profileList(user_profileDto);
    }

    @Override
    public Pet_profileDto getPet_profile(Pet_profileDto pet_profileDto) {
        return mypageDao.getPet_profile(pet_profileDto);
    }

    @Override
    public void updatePet_profile(Pet_profileDto pet_profileDto) {
        mypageDao.updatePet_profile(pet_profileDto);
    }

    @Override
    public int getWish_list(Wish_listDto wish_listDto) {
        return mypageDao.getWish_list(wish_listDto);
    }

    @Override
    public List<Wish_listDto> getWish_listList(User_profileDto user_profileDto) {
        return mypageDao.getWish_listList(user_profileDto);
    }

    @Override
    public void insertWish_list(Wish_listDto wish_listDto) {
        mypageDao.insertWish_list(wish_listDto);
    }

    @Override
    public void deleteWish_list(Wish_listDto wish_listDto) {
        mypageDao.deleteWish_list(wish_listDto);
    }

    @Override
    public void deleteReservation(ReservationDto reservationDto) {
        mypageDao.deleteReservation(reservationDto);
    }

    @Override
    public List<ReviewDto> getMyReviewList(List<Pet_sitter_houseDto> pet_sitter_houseList) {
        return mypageDao.getMyReviewList(pet_sitter_houseList);
    }

    @Override
    public void insertReview(ReservationDto reservationDto, User_profileDto user_profileDto, ReviewDto reviewDto) {
        mypageDao.insertReview(reviewDto);
        mypageDao.updateUser_profile_grade(user_profileDto);
        mypageDao.updateReservation_review(reservationDto);
    }

    @Override
    public void insertMsg(MsgDto msgDto) {
        mypageDao.insertMsg(msgDto);
    }

    @Override
    public List<MsgDto> getMsgList(Map<String, Object> search_map) {
        return mypageDao.getMsgList(search_map);
    }

    @Override
    public MsgDto getMsg_new(MsgDto msgDto) {
        return mypageDao.getMsg_new(msgDto);
    }
    

    
}
