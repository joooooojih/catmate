package com.catmate.sregister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.Room_photoDto;
import com.catmate.sregister.dao.SregisterDao;

@Service("sregisterService")
public class SregisterServiceImpl implements SregisterService {

    @Autowired
    SregisterDao sregisterDao;
    
    @Override
    public int insertPet_sitter01(Pet_sitter_houseDto pet_sitter_houseDto) {
        sregisterDao.insertPet_sitter01(pet_sitter_houseDto);
        return pet_sitter_houseDto.getIdx();
    }
    
    @Override
    public void updatePet_sitter01(Pet_sitter_houseDto pet_sitter_houseDto) {
        sregisterDao.updatePet_sitter01(pet_sitter_houseDto);
    }

    @Override
    public void updatePet_sitter02(Pet_sitter_houseDto pet_sitter_houseDto) {
        sregisterDao.updatePet_sitter02(pet_sitter_houseDto);
    }

    @Override
    public void updatePet_sitter03(Pet_sitter_houseDto pet_sitter_houseDto) {
        sregisterDao.updatePet_sitter03(pet_sitter_houseDto);
    }

    @Override
    public void insertRoom_photo(Room_photoDto room_photoDto) {
        sregisterDao.insertRoom_photo(room_photoDto);
    }

    @Override
    public void updateRoom_photo(Room_photoDto room_photoDto) {
        sregisterDao.updateRoom_photo(room_photoDto);
    }

    @Override
    public void updatePet_sitter05(Pet_sitter_houseDto pet_sitter_houseDto) {
        sregisterDao.updatePet_sitter05(pet_sitter_houseDto);
    }

}
