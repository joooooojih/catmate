package com.catmate.sregister.dao;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.Room_photoDto;

public interface SregisterDao {

    public int insertPet_sitter01(Pet_sitter_houseDto pet_sitter_houseDto);
    public void updatePet_sitter01(Pet_sitter_houseDto pet_sitter_houseDto);
    public void updatePet_sitter02(Pet_sitter_houseDto pet_sitter_houseDto);
    public void updatePet_sitter03(Pet_sitter_houseDto pet_sitter_houseDto);
    public void insertRoom_photo(Room_photoDto room_photoDto);
    public void updateRoom_photo(Room_photoDto room_photoDto);
    public void updatePet_sitter05(Pet_sitter_houseDto pet_sitter_houseDto);
    
}
