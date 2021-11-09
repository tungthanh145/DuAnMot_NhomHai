package com.example.duanmot_nhomhai.dao;

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

public class UserDAO {
    DbHelper db;
    public UserDAO(Context context){
        db = DbHelper.getDbInstance(context);
    }

    //lấy tất cả User
    public List<User> getAllUser() {
        String q = "SELECT USERNAME, PASSWORD FROM USER";
        return getUser(q,null,"getAllUser()");
    }

    //Lấy user theo id
    public User getUserID(int userID) {
        String q = "SELECT * FROM USER WHERE ID = ?";
        String[] params = new String[]{String.valueOf(userID)};
        List<User> list = getUser(q,params,"getUserID()");
        if(list.size()>0) return list.get(0);
        return null;
    }

    public User login(String username, String password) {
        User user = null;
        String q = "SELECT ID, USERNAME, PASSWORD FROM USER WHERE USERNAME = ?";
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(q, new String[]{username});
        try {
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    Integer _id = cursor.getInt(cursor.getColumnIndex("ID"));
                    String _username = cursor.getString(cursor.getColumnIndex("USERNAME"));
                    String _password = cursor.getString(cursor.getColumnIndex("PASSWORD"));

                    if(!_password.equals(password)) break;
                    user = new User();
                    user.setId(_id);
                    user.setUsername(_username);

                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.i("login()",e.getMessage());
        }finally {
            if(cursor != null && !cursor.isClosed()) cursor.close();
        }
        return user;
    }

    public boolean register(User user) {
        SQLiteDatabase database = db.getWritableDatabase();
        database.beginTransaction();
        long row = 0;
        try{
            ContentValues values = new ContentValues();
            values.put("USERNAME", user.getUsername());
            values.put("PASSWORD", user.getPassword());
            row = database.insertOrThrow("USER",null,values);
            database.setTransactionSuccessful();
        }catch (Exception e){
            Log.i("register(): ",e.getMessage());
        }finally {
            database.endTransaction();
        }
        return row >= 1;
    }

    public boolean update(User user) {
        SQLiteDatabase database = db.getWritableDatabase();
        database.beginTransaction();
        long row = 0;
        try{
            ContentValues values = new ContentValues();
            values.put("USERNAME", user.getUsername());
            values.put("PASSWORD", user.getPassword());
            row = database.update("USER", values, "ID = ?",
                    new String[]{String.valueOf(user.getId())});
            database.setTransactionSuccessful();
        }catch (Exception e){
            Log.i("update(): ",e.getMessage());
        }finally {
            database.endTransaction();
        }
        return row == 1;
    }

    public boolean checkUsernameExist(String username) {
        boolean exist = false;
        String q = "SELECT USERNAME FROM USER WHERE USERNAME = ?";
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(q, new String[]{username});
        try {
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    exist = true;
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.i("checkUsernameExist()",e.getMessage());
        }finally {
            if(cursor != null && !cursor.isClosed()) cursor.close();
        }
        return exist;
    }

    //Lấy dữ liệu của user
    private List<User> getUser(String q, String[] params, String ex){
        List<User> list = new ArrayList<>();
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(q,params);
        try{
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    Integer id = cursor.getInt(cursor.getColumnIndex("ID"));
                    String username = cursor.getString(cursor.getColumnIndex("USERNAME"));
                    String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
                    Integer score = cursor.getInt(cursor.getColumnIndex("SCORE"));

                    User user = new User(id, username, password, score);
                    list.add(user);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.i(ex,e.getMessage());
        }finally {
            if(cursor != null && !cursor.isClosed()) cursor.close();
        }
        return list;
    }
    public boolean insertUser(User user){
        ContentValues values = new ContentValues();
        values.put("ID", user.getId());
        values.put("userName", user.getUsername());
        values.put("passWord", user.getPassword());
        values.put("Score", user.getScore());
        db.insert("User", null, values);
        return false;
    }
}
