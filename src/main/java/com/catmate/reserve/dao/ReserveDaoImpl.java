package com.catmate.reserve.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.catmate.dto.Pet_sitter_houseDto;
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
}
