package com.example.zkl.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zkl.coolweather.model.City;
import com.example.zkl.coolweather.model.County;
import com.example.zkl.coolweather.model.Provice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by ZKL on 2016/8/27.
 */
public class CoolWeatherDB {
    public static final String DB_NAME = "cool_weather";//数据库名字
    public static final int VERSION = 1;//数据库版本
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;
    //将构造方法私有化
    private CoolWeatherDB(Context context){
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }
    //获取CoolWeatherDB的实例。
    public synchronized static CoolWeatherDB getInstance(Context context){
        if (coolWeatherDB == null){
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }
    //将Province实例存储到数据库
    public void saveProvice(Provice province){
        if (province != null){
            ContentValues values = new ContentValues();
            values.put("provice_name",province.getProviceName());
            values.put("provice_code",province.getProviceCode());
            db.insert("Provice",null,values);
        }
    }
    //从数据库读取全国所有省份的信息
    public List<Provice> loadProvices(){
        List<Provice> list = new ArrayList<Provice>();
        Cursor cursor = db.query("Provice",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Provice provice = new Provice();
                provice.setId(cursor.getInt(cursor.getColumnIndex("id")));
                provice.setProviceName(cursor.getString(cursor.getColumnIndex("provice_name")));
                provice.setProviceCode(cursor.getString(cursor.getColumnIndex("provice_code")));
                list.add(provice);
            }while(cursor.moveToNext());
        }
        return list;

    }
    //将city实例存储到数据库
    public void saveCity(City city){
        if (city!=null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("provice_id",city.getProviceId());
            db.insert("City",null,values);
        }
    }
    //从数据库中读取某省下的所有城市信息
    public List<City> loadCities(int proviceId){
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City",null,"provice_Id = ?",
                new String[]{String.valueOf(proviceId)},null,null,null);
        if (cursor.moveToFirst()){
            do{
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProviceId(proviceId);
                list.add(city);
            }while (cursor.moveToNext());

        }
        return list;
    }
    //将乡镇实例存储到数据库
    public void saveCounty(County county){
        if (county != null){
            ContentValues values = new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            values.put("city_id",county.getCityId());
            db.insert("County",null,values);
        }
    }
    //从数据库读取某城市下的所有县信息
    public List<County> loadCounties(int cityId){
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County",null,"city_id = ?",
                new String[]{String.valueOf(cityId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);

            }while (cursor.moveToNext());
        }
        return list;
    }

}
