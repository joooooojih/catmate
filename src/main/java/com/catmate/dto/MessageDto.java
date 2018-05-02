package com.catmate.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("Message")
public class MessageDto {

    private String user_email1;
    private String user_email2;
    private String msg_content;
    private Timestamp msg_date;
    public String getUser_email1() {
        return user_email1;
    }
    public void setUser_email1(String user_email1) {
        this.user_email1 = user_email1;
    }
    public String getUser_email2() {
        return user_email2;
    }
    public void setUser_email2(String user_email2) {
        this.user_email2 = user_email2;
    }
    public String getMsg_content() {
        return msg_content;
    }
    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }
    public Timestamp getMsg_date() {
        return msg_date;
    }
    public void setMsg_date(Timestamp msg_date) {
        this.msg_date = msg_date;
    }
    
}
