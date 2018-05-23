package com.catmate.mypage.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.catmate.dto.Pet_profileDto;
import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.User_profileDto;

@Repository("MypageDao")
public class MypageDaoImpl implements MypageDao {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public User_profileDto getUser_profile(String user_email) {
        return sqlSession.selectOne("Mypage.getUser_profile", user_email);
    }

    @Override
    public void insertUser_profile(User_profileDto user_profileDto) {
        
        sqlSession.insert("Mypage.insertUser_profile", user_profileDto);
    }
    
    @Override
    public void updateUser_profile(User_profileDto user_profileDto) {
        sqlSession.update("Mypage.updateUser_profile", user_profileDto);
    }

    @Override
    public void updateUser_profile_pass(User_profileDto user_profileDto) {
        sqlSession.update("Mypage.updateUser_profile_pass", user_profileDto);
    }

    @Override
    public void deleteUser_profile(User_profileDto user_profileDto) {
        sqlSession.delete("Mypage.deleteUser_profile", user_profileDto);
    }

    @Override
    public List<ReservationDto> getReservation_mypageList(User_profileDto user_profileDto) {
        return sqlSession.selectList("Mypage.getReservation_mypageList", user_profileDto);
    }

    @Override
    public List<Pet_sitter_houseDto> getMy_pet_sitter_houseList(User_profileDto user_profileDto) {
        return sqlSession.selectList("Mypage.getMy_pet_sitter_houseList", user_profileDto);
    }

    @Override
    public List<ReservationDto> getMy_reservationList(List<Pet_sitter_houseDto> pet_sitter_houseList) {
        return sqlSession.selectList("Mypage.getMy_reservationList", pet_sitter_houseList);
    }

    @Override
    public void insertPet_profile(Pet_profileDto pet_profileDto) {
        sqlSession.insert("Mypage.insertPet_profile", pet_profileDto);
    }

    @Override
    public List<Pet_profileDto> getPet_profileList(User_profileDto user_profileDto) {
        return sqlSession.selectList("Mypage.getPet_profileList", user_profileDto);
    }

    @Override
    public Pet_profileDto getPet_profile(Pet_profileDto pet_profileDto) {
        return sqlSession.selectOne("Mypage.getPet_profile", pet_profileDto);
    }

    @Override
    public void updatePet_profile(Pet_profileDto pet_profileDto) {
        sqlSession.update("Mypage.updatePet_profile", pet_profileDto);
    }

}
