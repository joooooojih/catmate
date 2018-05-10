package com.catmate.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catmate.dto.User_profileDto;
import com.catmate.member.dao.MemberDao;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDao memberDao;
    
    @Override
    public User_profileDto getUser_profile(String user_email) {
        
        return memberDao.getUser_profile(user_email);
    }

    @Override
    public void insertUser_profile(User_profileDto user_profileDto) {
        memberDao.insertUser_profile(user_profileDto);
    }

}
