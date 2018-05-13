package com.catmate.reserve.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.Room_photoDto;
import com.catmate.dto.User_profileDto;
import com.catmate.member.dao.MemberDao;
import com.catmate.reserve.dao.ReserveDao;

@Service("reserveService")
public class ReserveServiceImpl implements ReserveService{

    @Autowired
    ReserveDao reserveDao;
    @Autowired
    MemberDao memberDao;

    @Override
    public Map<String, Object> getPet_sitter_house(int idx) {

        Pet_sitter_houseDto pet_sitter_houseDto = reserveDao.getPet_sitter_house(idx);
        List<Room_photoDto> room_photoList = reserveDao.getRoom_photoDto(idx);
        User_profileDto user_profileDto = memberDao.getUser_profile(pet_sitter_houseDto.getUser_email());

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("pet_sitter_house", pet_sitter_houseDto);
        map.put("room_photo", room_photoList);
        map.put("user_profile", user_profileDto);

        return map;
    }

    @Override
    public void insertReservation(ReservationDto reservationDto) {
        reserveDao.insertReservation(reservationDto);
    }

    @Override
    public List<ReservationDto> getReservation(int idx) {
        return reserveDao.getReservationDto(idx);
    }

    @Override
    public Map<String, Object> getPet_sitter_houseList(ReservationDto reservationDto,
            Pet_sitter_houseDto pet_sitter_houseDto) {
        List<ReservationDto> reservationList = reserveDao.getReservation_search(reservationDto);  // 예약일에 포함 안되는 게시판 idx
        List<Pet_sitter_houseDto> pet_sitter_house_notList = null;
        if(reservationList.isEmpty()) {
            pet_sitter_house_notList = reserveDao.getPet_sitter_houseList();
        } else {
            pet_sitter_house_notList = reserveDao.getPet_sitter_house_not(reservationList);  // 예약일에 포함 안되는 게시판
        }
        
        List<Pet_sitter_houseDto> pet_sitter_houseList = new ArrayList<Pet_sitter_houseDto>();
        for(Pet_sitter_houseDto pet_sitter_house_notDto : pet_sitter_house_notList) {
            pet_sitter_houseDto.setIdx(pet_sitter_house_notDto.getIdx());
            Pet_sitter_houseDto tmpPet_sitter_houseDto = reserveDao.getPet_sitter_house_search(pet_sitter_houseDto);
            
            if(tmpPet_sitter_houseDto != null) {
                pet_sitter_houseList.add(tmpPet_sitter_houseDto);
            }
        }

        List<List<Room_photoDto>> room_photoList = new ArrayList<>();
        List<User_profileDto> user_profileList = new ArrayList<>();

        for(Pet_sitter_houseDto pet_sitter_house : pet_sitter_houseList) {
            room_photoList.add(reserveDao.getRoom_photoDto(pet_sitter_house.getIdx()));
            user_profileList.add(memberDao.getUser_profile(pet_sitter_house.getUser_email()));
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pet_sitter_houseList", pet_sitter_houseList);
        map.put("room_photoList", room_photoList);
        map.put("user_profileList", user_profileList);

        return map;
    }

}
