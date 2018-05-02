package com.catmate.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("Review")
public class ReviewDto {

    private int idx;
    private String user_email;
    private String review_kidn;
    private String review_scope;
    private String review_content;
    private Timestamp review_date;
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
    public String getReview_kidn() {
        return review_kidn;
    }
    public void setReview_kidn(String review_kidn) {
        this.review_kidn = review_kidn;
    }
    public String getReview_scope() {
        return review_scope;
    }
    public void setReview_scope(String review_scope) {
        this.review_scope = review_scope;
    }
    public String getReview_content() {
        return review_content;
    }
    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }
    public Timestamp getReview_date() {
        return review_date;
    }
    public void setReview_date(Timestamp review_date) {
        this.review_date = review_date;
    }
    
}
