package com.catmate.member.service;

import java.util.List;

import com.catmate.dto.User_profileDto;

public interface MemberService {

    User_profileDto getUser_profile(User_profileDto user_profileDto);
    void insertUser_profile(User_profileDto user_profileDto);
}