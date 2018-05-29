package com.catmate.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("Review")
public class ReviewDto {

    private int idx;
    private String user_email;
    private String review_kind;
    private int review_scope;
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
    public String getReview_kind() {
        return review_kind;
    }
    public void setReview_kind(String review_kind) {
        this.review_kind = review_kind;
    }
    public int getReview_scope() {
        return review_scope;
    }
    public void setReview_scope(int review_scope) {
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
