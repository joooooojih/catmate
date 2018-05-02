package com.catmate.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.catmate.dto.User_profileDto;

@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public User_profileDto getUser_profile(User_profileDto user_profileDto) {
        
        return sqlSession.selectOne("Member.getUser_profile", user_profileDto);
    }

    @Override
    public void insertUser_profile(User_profileDto user_profileDto) {
        
        sqlSession.insert("Member.insertUser_profile", user_profileDto);
    }
}
