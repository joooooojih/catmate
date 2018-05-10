package com.catmate.reserve.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.Room_photoDto;
import com.catmate.reserve.dao.ReserveDao;

@Service("reserveService")
public class ReserveServiceImpl implements ReserveService{

    @Autowired
    ReserveDao reserveDao;
    
    @Override
    public Map<String, Object> getPet_sitter_house(int idx) {
        
        Pet_sitter_houseDto pet_sitter_houseDto = reserveDao.getPet_sitter_house(idx);
        List<Room_photoDto> room_photoList = reserveDao.getRoom_photoDto(idx);
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("pet_sitter_house", pet_sitter_houseDto);
        map.put("room_photo", room_photoList);
        
        return map;
    }
    
}
