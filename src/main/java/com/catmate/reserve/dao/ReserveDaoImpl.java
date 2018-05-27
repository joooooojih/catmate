package com.catmate.reserve.dao;

import java.util.List;
import java.util.Map;

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
    public List<Room_photoDto> getRoom_photo(int idx) {
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
    public List<ReservationDto> getReservation(int idx) {
        return sqlSession.selectList("reserve.getReservation", idx);
    }

    @Override
    public List<ReservationDto> getReservationList() {
        return sqlSession.selectList("reserve.getReservationList");
    }

    @Override
    public List<Pet_sitter_houseDto> getPet_sitter_house_not(List<ReservationDto> reservationList) {
        return sqlSession.selectList("reserve.getPet_sitter_house_not", reservationList);
    }

    @Override
    public List<Pet_sitter_houseDto> getPet_sitter_house_searchList(Map<String, Object> search_map) {
        return sqlSession.selectList("reserve.getPet_sitter_house_searchList", search_map);
    }

    @Override
    public int getAreaAllCount() {
        return sqlSession.selectOne("reserve.getAreaAllCount");
    }

    @Override
    public int getAreaCount(String area) {
        return sqlSession.selectOne("reserve.getAreaCount", area);
    }

    @Override
    public int getPetCount(String user_email) {
        return sqlSession.selectOne("reserve.getPetCount", user_email);
    }

}
