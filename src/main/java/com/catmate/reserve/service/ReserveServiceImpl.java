package com.catmate.reserve.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.ReviewDto;
import com.catmate.dto.Room_photoDto;
import com.catmate.dto.User_profileDto;
import com.catmate.mypage.dao.MypageDao;
import com.catmate.reserve.dao.ReserveDao;

@Service("reserveService")
public class ReserveServiceImpl implements ReserveService{

    @Autowired
    ReserveDao reserveDao;
    @Autowired
    MypageDao mypageDao;

    @Override
    public void insertReservation(ReservationDto reservationDto) {
        reserveDao.insertReservation(reservationDto);
    }

    @Override
    public List<ReservationDto> getReservation(int idx) {
        return reserveDao.getReservation(idx);
    }

    @Override
    public int getAreaAllCount() {
        return reserveDao.getAreaAllCount();
    }
    
    @Override
    public int getAreaCount(String area) {
        return reserveDao.getAreaCount(area);
    }

    @Override
    public List<ReservationDto> getReservationList() {
        return reserveDao.getReservationList();
    }

    @Override
    public List<Pet_sitter_houseDto> getPet_sitter_house_not(List<ReservationDto> reservationList) {
        return reserveDao.getPet_sitter_house_not(reservationList);
    }

    @Override
    public List<Pet_sitter_houseDto> getPet_sitter_house_searchList(Map<String, Object> search_map) {
        return reserveDao.getPet_sitter_house_searchList(search_map);
    }

    @Override
    public List<Pet_sitter_houseDto> getPet_sitter_houseList() {
        return reserveDao.getPet_sitter_houseList();
    }

    @Override
    public List<Room_photoDto> getRoom_photo(int idx) {
        return reserveDao.getRoom_photo(idx);
    }

    @Override
    public Pet_sitter_houseDto getPet_sitter_house(int idx) {
        return reserveDao.getPet_sitter_house(idx);
    }

    @Override
    public int getPetCount(String user_email) {
        return reserveDao.getPetCount(user_email);
    }

    @Override
    public List<ReviewDto> getReviewList(int idx) {
        return reserveDao.getReviewList(idx);
    }

    @Override
    public int getReviewCount(int idx) {
        return reserveDao.getReviewCount(idx);
    }

    

}
