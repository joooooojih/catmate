package com.catmate.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

@Alias("Pet_profile")
public class Pet_profileDto {

    private int idx;
    private String user_email;
    private String pet_photo;
    private String pet_name;
    private String pet_gender;
    private String pet_kind;
    private int pet_weight;
    private String pet_birth;
    private String registration;
    private String neutralization;
    private String friendly;
    private String barks;
    private String disease;
    private String feces;
    private String mark;
    private String separation_anxiety;
    private String heartworm;
    private String vaccine;
    private String corona_enteritis;
    private String kennelkov;
    private String animal_hospital_name;
    private String animal_hospital_telephone;
    private String animal_hospital_zip_code;
    private String animal_hospital_address;
    private String animal_hospital_daddress;
    private MultipartFile uploadFile;
    
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
    public String getPet_photo() {
        return pet_photo;
    }
    public void setPet_photo(String pet_photo) {
        this.pet_photo = pet_photo;
    }
    public String getPet_name() {
        return pet_name;
    }
    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }
    public String getPet_gender() {
        return pet_gender;
    }
    public void setPet_gender(String pet_gender) {
        this.pet_gender = pet_gender;
    }
    public String getPet_kind() {
        return pet_kind;
    }
    public void setPet_kind(String pet_kind) {
        this.pet_kind = pet_kind;
    }
    public int getPet_weight() {
        return pet_weight;
    }
    public void setPet_weight(int pet_weight) {
        this.pet_weight = pet_weight;
    }
    public String getPet_birth() {
        return pet_birth;
    }
    public void setPet_birth(String pet_birth) {
        this.pet_birth = pet_birth;
    }
    public String getRegistration() {
        return registration;
    }
    public void setRegistration(String registration) {
        this.registration = registration;
    }
    public String getNeutralization() {
        return neutralization;
    }
    public void setNeutralization(String neutralization) {
        this.neutralization = neutralization;
    }
    public String getFriendly() {
        return friendly;
    }
    public void setFriendly(String friendly) {
        this.friendly = friendly;
    }
    public String getBarks() {
        return barks;
    }
    public void setBarks(String barks) {
        this.barks = barks;
    }
    public String getDisease() {
        return disease;
    }
    public void setDisease(String disease) {
        this.disease = disease;
    }
    public String getFeces() {
        return feces;
    }
    public void setFeces(String feces) {
        this.feces = feces;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public String getSeparation_anxiety() {
        return separation_anxiety;
    }
    public void setSeparation_anxiety(String separation_anxiety) {
        this.separation_anxiety = separation_anxiety;
    }
    public String getHeartworm() {
        return heartworm;
    }
    public void setHeartworm(String heartworm) {
        this.heartworm = heartworm;
    }
    public String getVaccine() {
        return vaccine;
    }
    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }
    public String getCorona_enteritis() {
        return corona_enteritis;
    }
    public void setCorona_enteritis(String corona_enteritis) {
        this.corona_enteritis = corona_enteritis;
    }
    public String getKennelkov() {
        return kennelkov;
    }
    public void setKennelkov(String kennelkov) {
        this.kennelkov = kennelkov;
    }
    public String getAnimal_hospital_name() {
        return animal_hospital_name;
    }
    public void setAnimal_hospital_name(String animal_hospital_name) {
        this.animal_hospital_name = animal_hospital_name;
    }
    public String getAnimal_hospital_telephone() {
        return animal_hospital_telephone;
    }
    public void setAnimal_hospital_telephone(String animal_hospital_telephone) {
        this.animal_hospital_telephone = animal_hospital_telephone;
    }
    public String getAnimal_hospital_zip_code() {
        return animal_hospital_zip_code;
    }
    public void setAnimal_hospital_zip_code(String animal_hospital_zip_code) {
        this.animal_hospital_zip_code = animal_hospital_zip_code;
    }
    public String getAnimal_hospital_address() {
        return animal_hospital_address;
    }
    public void setAnimal_hospital_address(String animal_hospital_address) {
        this.animal_hospital_address = animal_hospital_address;
    }
    public String getAnimal_hospital_daddress() {
        return animal_hospital_daddress;
    }
    public void setAnimal_hospital_daddress(String animal_hospital_daddress) {
        this.animal_hospital_daddress = animal_hospital_daddress;
    }
    public MultipartFile getUploadFile() {
        return uploadFile;
    }
    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }
    
}
