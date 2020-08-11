package com.example.lottery_final.Module;

import java.io.Serializable;

public class RequireWebModule implements Serializable {
    String Url,  Province, Day, Time, Code, Prize, NumberPrize;

    public RequireWebModule(String province, String day, String time, String numberPrize) {
        Province = province;
        Day = day;
        Time = time;
        NumberPrize = numberPrize;
    }


    public RequireWebModule(String url, String province, String day, String time, String code, String prize, String numberPrize) {
        Url = url;
        Province = province;
        Day= day;
        Time = time;
        Code = code;
        Prize = prize;
        NumberPrize = numberPrize;
    }

    public RequireWebModule() {
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String date) {
        Day = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getPrize() {
        return Prize;
    }

    public void setPrize(String prize) {
        Prize = prize;
    }

    public String getNumberPrize() {
        return NumberPrize;
    }

    public void setNumberPrize(String numberPrize) {
        NumberPrize = numberPrize;
    }

}
