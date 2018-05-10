package com.catmate.member.service;

import com.catmate.dto.User_profileDto;

public interface MemberService {

    User_profileDto getUser_profile(String user_email);
    void insertUser_profile(User_profileDto user_profileDto);
}