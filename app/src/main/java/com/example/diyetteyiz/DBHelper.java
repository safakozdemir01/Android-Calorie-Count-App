package com.example.diyetteyiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName ="Diet.db";

    public DBHelper( Context context) {
        super(context,"Diet.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, password TEXT, firstEntry INT)");
        db.execSQL("create table yediklerim(neYedim TEXT primary key,kalori TEXT)");
        db.execSQL("create table userInfo(username TEXT primary key, gender TEXT,age INT, height INT, weight INT,goal TEXT,bazal INT)");
        db.execSQL("create table dailyCalories(date TEXT primary key, calories TEXT)");

        db.execSQL("create table loglar(tarih TEXT primary key,kalori int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
    }



    public boolean InsertLoglar(String tarih, int kalori )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tarih",tarih);
        values.put("kalori",kalori);
        long result = db.insert("loglar",null,values);

        if (result==-1) return false;
        else
            return true;
    }

    public Cursor viewYediklerim(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from yediklerim", null);

        return cursor;

    }
    public Boolean deleteYediklerim(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from yediklerim", null);
        if (cursor.getCount()>0)
        {
            long result = db.delete("yediklerim",null,null);
            if (result==-1){return false;}
            else{return true;}
        }
        else{return false;}

    }
    public Cursor bazalMetabolizma(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select bazal from userInfo", null);

        return cursor;

    }
    public boolean InsertYediklerim(String neYedim, String kalori )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("neYedim",neYedim);
        values.put("kalori",kalori);
        long result = db.insert("yediklerim",null,values);

        if (result==-1) return false;
        else
            return true;
    }

    public boolean InsertUser(String username, String password)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("username",username);
            values.put("password",password);
            //values.put("firstEntry",0);
            long result = db.insert("users",null,values);

        if (result==-1) return false;
        else
        return true;
    }
    public void UpdatePassword(String username, String password )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE users SET password = ? WHERE username=?";

        db.execSQL(strSQL,new String[]{password,username});
    }

    public boolean InsertDailyCalories(String date, int calories)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date",date);
        values.put("calories",calories);

        long result = db.insert("dailyCalories",null,values);
        if (result==-1) return false;
        else
            return true;
    }

    public boolean InsertUserInfo(String username, String gender, int age, int height, int weight, String goal,double bazal)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("gender",gender);
        values.put("age",age);
        values.put("height",height);
        values.put("weight",weight);
        values.put("goal",goal);
        values.put("bazal",bazal);

        long result = db.insert("userInfo",null,values);
        if (result==-1) return false;
        else
            return true;
    }

    public boolean UpdateUserInfo(String username, String gender, int age, int height, int weight, String goal,double bazal)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("gender",gender);
        values.put("age",age);
        values.put("height",height);
        values.put("weight",weight);
        values.put("goal",goal);
        values.put("bazal",bazal);

        long result = db.update("userInfo",values,"username=?",new String[]{username});
        if (result==-1) return false;
        else
            return true;
    }


    public boolean checkUsernamePassword(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?",new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkUsername(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?",new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public void yemekleriGoruntule(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from neYedim";
    }
}
