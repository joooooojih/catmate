package com.catmate.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("Msg")
public class MsgDto {

    private String to_user_email;
    private String from_user_email;
    private String msg_content;
    private Timestamp msg_date;
    
    public String getTo_user_email() {
        return to_user_email;
    }
    public void setTo_user_email(String to_user_email) {
        this.to_user_email = to_user_email;
    }
    public String getFrom_user_email() {
        return from_user_email;
    }
    public void setFrom_user_email(String from_user_email) {
        this.from_user_email = from_user_email;
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
