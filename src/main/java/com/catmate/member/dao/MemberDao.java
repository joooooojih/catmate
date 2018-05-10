package com.catmate.member.dao;

import com.catmate.dto.User_profileDto;

public interface MemberDao {

    User_profileDto getUser_profile(String user_email);
    void insertUser_profile(User_profileDto user_profileDto);

}