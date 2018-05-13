package com.catmate.reserve.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.Room_photoDto;

@Repository("reserveDao")
public class ReserveDaoImpl implements ReserveDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public Pet_sitter_houseDto getPet_sitter_house(int idx) {
        return sqlSession.selectOne("reserve.getPet_sitter_house", idx);
    }

    @Override
    public List<Room_photoDto> getRoom_photoDto(int idx) {
        return sqlSession.selectList("reserve.getRoom_photo", idx);
    }

    @Override
    public List<Pet_sitter_houseDto> getPet_sitter_houseList() {
        return sqlSession.selectList("reserve.getPet_sitter_houseList");
    }

    @Override
    public void insertReservation(ReservationDto reservationDto) {
        sqlSession.insert("reserve.insertReservation", reservationDto);
    }

    @Override
    public List<ReservationDto> getReservationDto(int idx) {
        return sqlSession.selectList("reserve.getReservation", idx);
    }

    @Override
    public List<ReservationDto> getReservation_search(ReservationDto reservationDto) {
        return sqlSession.selectList("reserve.getReservation_search", reservationDto);
    }

    @Override
    public List<Pet_sitter_houseDto> getPet_sitter_house_not(List<ReservationDto> reservationList) {
        return sqlSession.selectList("reserve.getPet_sitter_house_not", reservationList);
    }

    @Override
    public Pet_sitter_houseDto getPet_sitter_house_search(Pet_sitter_houseDto pet_sitter_houseDto) {
        Pet_sitter_houseDto pet_sitter_house_searchDto = null;
        try {
        pet_sitter_house_searchDto = sqlSession.selectOne("reserve.getPet_sitter_house_search", pet_sitter_houseDto);
        } catch(Exception e) {e.printStackTrace();}
        return pet_sitter_house_searchDto;
    }

}
