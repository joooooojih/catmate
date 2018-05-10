package com.catmate.sregister.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.Room_photoDto;

@Repository("sregisterDao")
public class SregisterDaoImpl implements SregisterDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int insertPet_sitter01(Pet_sitter_houseDto pet_sitter_houseDto) {
        return sqlSession.insert("sregister.insertPet_sitter01", pet_sitter_houseDto);
    }
    
    @Override
    public void updatePet_sitter01(Pet_sitter_houseDto pet_sitter_houseDto) {
        sqlSession.update("sregister.updatePet_sitter01", pet_sitter_houseDto);
    }

    @Override
    public void updatePet_sitter02(Pet_sitter_houseDto pet_sitter_houseDto) {
        sqlSession.update("sregister.updatePet_sitter02", pet_sitter_houseDto);
    }

    @Override
    public void updatePet_sitter03(Pet_sitter_houseDto pet_sitter_houseDto) {
        sqlSession.update("sregister.updatePet_sitter03", pet_sitter_houseDto);
    }

    @Override
    public void insertRoom_photo(Room_photoDto room_photoDto) {
        sqlSession.insert("sregister.insertRoom_photo", room_photoDto);
    }

    @Override
    public void updateRoom_photo(Room_photoDto room_photoDto) {
        sqlSession.update("sregister.updateRoom_photo", room_photoDto);
    }

    @Override
    public void updatePet_sitter05(Pet_sitter_houseDto pet_sitter_houseDto) {
        sqlSession.update("sregister.updatePet_sitter05", pet_sitter_houseDto);
    }

}
