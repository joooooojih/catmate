package com.catmate.reserve.dao;

import java.util.List;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.Room_photoDto;

public interface ReserveDao {

    public Pet_sitter_houseDto getPet_sitter_house(int idx);
    public List<Room_photoDto> getRoom_photoDto(int idx);
    
}
