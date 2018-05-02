package com.catmate.dto;

import org.apache.ibatis.type.Alias;

@Alias("Reservation")
public class ReservationDto {

    private int idx;
    private String user_email;
    private String start_date;
    private String last_date;
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
    public String getStart_date() {
        return start_date;
    }
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
    public String getLast_date() {
        return last_date;
    }
    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }
    public int getHow_many() {
        return how_many;
    }
    public void setHow_many(int how_many) {
        this.how_many = how_many;
    }
    
}
