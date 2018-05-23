package com.catmate.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("Reservation")
public class ReservationDto {

    private int idx;
    private String user_email;
    private Date start_day;
    private Date end_day;
    private int how_many;
    public int getIdx() {
        return idx;
    }
    public void setIdx(int idx) {
        this.idx = idx;
    }
    public String getUser_email() {
        return user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
    public Date getStart_day() {
        return start_day;
    }
    public void setStart_day(Date start_day) {
        this.start_day = start_day;
    }
    public Date getEnd_day() {
        return end_day;
    }
    public void setEnd_day(Date end_day) {
        this.end_day = end_day;
    }
    public int getHow_many() {
        return how_many;
    }
    public void setHow_many(int how_many) {
        this.how_many = how_many;
    }
    
}
