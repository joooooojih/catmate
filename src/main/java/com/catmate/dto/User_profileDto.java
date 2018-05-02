package com.catmate.dto;

import org.apache.ibatis.type.Alias;

@Alias("User_profile")
public class User_profileDto {

    private String user_email;
    private String user_password;
    private String user_photo;
    private String user_name;
    private String user_birth;
    private String user_phone;
    private String user_zip_code;
    private String user_address;
    private String user_daddress;
    private int user_grade;
    
    public String getUser_phone() {
        return user_phone;
    }
    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }
    public String getUser_email() {
        return user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
    public String getUser_password() {
        return user_password;
    }
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
    public String getUser_photo() {
        return user_photo;
    }
    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getUser_birth() {
        return user_birth;
    }
    public void setUser_birth(String user_birth) {
        this.user_birth = user_birth;
    }
    public String getUser_zip_code() {
        return user_zip_code;
    }
    public void setUser_zip_code(String user_zip_code) {
        this.user_zip_code = user_zip_code;
    }
    public String getUser_address() {
        return user_address;
    }
    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }
    public String getUser_daddress() {
        return user_daddress;
    }
    public void setUser_daddress(String user_daddress) {
        this.user_daddress = user_daddress;
    }
    public int getUser_grade() {
        return user_grade;
    }
    public void setUser_grade(int user_grade) {
        this.user_grade = user_grade;
    }
    
}
