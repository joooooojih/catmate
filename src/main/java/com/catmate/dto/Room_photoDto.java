package com.catmate.dto;

import org.apache.ibatis.type.Alias;

@Alias("Room_photo")
public class Room_photoDto {

    private int idx;
    private int photo_sequence;
    private String room_photo;
    public int getIdx() {
        return idx;
    }
    public void setIdx(int idx) {
        this.idx = idx;
    }
    public int getPhoto_sequence() {
        return photo_sequence;
    }
    public void setPhoto_sequence(int photo_sequence) {
        this.photo_sequence = photo_sequence;
    }
    public String getRoom_photo() {
        return room_photo;
    }
    public void setRoom_photo(String room_photo) {
        this.room_photo = room_photo;
    }
    
}
