package com.catmate.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catmate.dto.Pet_profileDto;
import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.User_profileDto;
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

    
}
