package com.catmate.member.dao;

import java.util.List;

import com.catmate.dto.User_profileDto;

public interface MemberDao {

    User_profileDto getUser_profile(User_profileDto user_profileDto);
    void insertUser_profile(User_profileDto user_profileDto);

}