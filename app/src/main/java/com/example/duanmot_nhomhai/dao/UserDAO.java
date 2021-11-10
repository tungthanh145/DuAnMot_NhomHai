package com.example.duanmot_nhomhai.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmot_nhomhai.database.DbHelper;
import com.example.duanmot_nhomhai.model.Topic;
import com.example.duanmot_nhomhai.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUser{
    User user;

    DbHelper db;
    public UserDAO (Context ctx){
        db = db.getDbInstance(ctx);
    }

    @Override
    public User login(String _username, String _passWord) {

        User user = null;
        SQLiteDatabase database = db.getWritableDatabase();
        String q = "SELECT ID, PASS, NAME, SCORE FROM USERS" +
                " WHERE NAME = ? AND PASS = ?";
        Cursor cs = database.rawQuery(q, new String[]{_username, _passWord});
        try{
            if (cs.moveToFirst()){
                while (cs.isAfterLast() == false){
                    @SuppressLint("Range")
                    Integer id = cs.getInt(cs.getColumnIndex("ID"));
                    @SuppressLint("Range")
                    String password = cs.getString(cs.getColumnIndex("PASS"));
                    @SuppressLint("Range")
                    String name = cs.getString(cs.getColumnIndex("NAME"));
                    @SuppressLint("Range")
                    Integer score = cs.getInt(cs.getColumnIndex("SCORE"));

                    if (!password.equals(_passWord))break;
                    user = new User();
                    user.setId(id);
                    user.setName(name);
                    user.setPass(password);
                    user.setRole(score);

                    cs.moveToNext();
                }
            }

        }catch (Exception ex){
            Log.i("login()", ex.getMessage());
        }finally {
            if(cs != null && cs.isClosed()) cs.close();
        }
        return user;
    }

    @Override
    public boolean register(User user) {
        SQLiteDatabase database = db.getWritableDatabase();
        database.beginTransaction();
        long rows = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("NAME", user.getName());
            values.put("PASS", user.getPass());
            values.put("NAME", user.getName());
            values.put("SCORE", user.getScore());
            rows = database.insertOrThrow("USERS",null,values);
            database.setTransactionSuccessful();
        }catch (Exception e){
            Log.i("insert()",e.getMessage());
        }finally {
            database.endTransaction();
        }
        return  rows >= 1;
    }

    @Override
    public boolean update(User user) {
        SQLiteDatabase database = db.getWritableDatabase();
        database.beginTransaction();
        long rows = 0;
        try {
            ContentValues values = new ContentValues();
            //           values.put("USERNAME", user.getName());
            //           values.put("PASSWORD", user.getPass());
            values.put( "PASS", user.getPass() );
            values.put("NAME", user.getName());
            values.put("SCORE", user.getScore());
            rows = database.update("USERS",values, "ID=?", new String[]{String.valueOf(user.getId())});
            database.setTransactionSuccessful();
        }catch (Exception e){
            Log.i("update()",e.getMessage());
        }finally {
            database.endTransaction();
        }
        return  rows == 1;
    }

    @Override
    public boolean checkUsernameExists(String _name) {
        boolean exist = false;
        SQLiteDatabase database = db.getReadableDatabase();
        String q = "SELECT NAME FROM USERS WHERE NAME = ?";
        Cursor cs = database.rawQuery(q, new String[]{_name});
        try{

            if (cs.moveToFirst()){
                while (!cs.isAfterLast()){
                    exist = true;
                    cs.moveToNext();
                }
            }

        }catch (Exception ex){
            Log.i("checkUsernameExists()", ex.getMessage());
        }finally {
            if(cs != null && cs.isClosed()) cs.close();
        }
        Log.e( "exist", " "+exist );
        return exist;
    }
}
