package com.topcoder.innovate.model;

/**
 * Created by Sdite on 2017/9/8.
 */

public class Locate {

    private double longitude;
    private double latitude;
    private String adress;
    private String name;

    public Locate() {
        longitude = 0;
        latitude = 0;
    }

    // 设置经度
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // 获取经度
    public double getLongitude() {
        return longitude;
    }

    // 设置纬度
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // 获取纬度
    public double getLatitude() {
        return latitude;
    }

    // 设置地点名字
    public void setName(String name){
        this.name = name;
    }

    // 获取地点名字
    public String getName(){
        return name;
    }

    // 设置地点地址
    public void setAdress(String adress){
        this.adress = adress;
    }

    // 获取地址
    public String getAdress(){
        return adress;
    }
}
