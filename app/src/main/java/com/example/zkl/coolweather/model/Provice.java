package com.example.zkl.coolweather.model;

/**
 * Created by ZKL on 2016/8/27.
 */
public class Provice {
    private int id;
    private String proviceName;
    private String proviceCode;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getProviceName(){
        return proviceName;
    }

    public void setProviceName(String proviceName){
        this.proviceName = proviceName;
    }

    public void setProviceCode(String proviceCode){
       this.proviceCode = proviceCode;
    }

    public String getProviceCode(){
        return proviceCode;
    }
}
