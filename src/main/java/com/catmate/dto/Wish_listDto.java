package com.catmate.dto;

import org.apache.ibatis.type.Alias;

@Alias("Wish_list")
public class Wish_listDto {

    private String user_email;
    private int idx;
    public String getUser_email() {
        return user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
    public int getIdx() {
        return idx;
    }
    public void setIdx(int idx) {
        this.idx = idx;
    }
    
    
}
