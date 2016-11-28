package com.example.taher.maak_x_alseka.Model;

import java.io.Serializable;

/**
 * Created by taher on 25/11/16.
 */
public class User implements Serializable {

    private int user_id;
    private String name;//
    private String mobile_num;//
    private String national_id;//
    private String rate_total;
    private String positive_rate;
    private String image;//
    private String email;//
    private String password;

    public User(String email, String image, String mobile_num, String name, String national_id
            , String password, String positive_rate, String rate_total, int user_id) {
        this.email = email;
        this.image = image;
        this.mobile_num = mobile_num;
        this.name = name;
        this.national_id = national_id;
        this.password = password;
        this.positive_rate = positive_rate;
        this.rate_total = rate_total;
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobile_num() {
        return mobile_num;
    }

    public void setMobile_num(String mobile_num) {
        this.mobile_num = mobile_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPositive_rate() {
        return positive_rate;
    }

    public void setPositive_rate(String positive_rate) {
        this.positive_rate = positive_rate;
    }

    public String getRate_total() {
        return rate_total;
    }

    public void setRate_total(String rate_total) {
        this.rate_total = rate_total;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}