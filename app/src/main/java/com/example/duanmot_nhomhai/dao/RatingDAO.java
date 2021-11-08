package com.example.duanmot_nhomhai.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmot_nhomhai.database.DbHelper;
import com.example.duanmot_nhomhai.model.Rating;
import com.example.duanmot_nhomhai.model.User;

import java.util.ArrayList;
import java.util.List;

public class RatingDAO {
    DbHelper db;
    private Context context;
    public RatingDAO(Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = DbHelper.getDbInstance(context);
    }

    public List<Rating> getRating(){
        String q = "SELECT USERNAME, SCORE FROM USER ORDER BY SCORE DESC LIMIT 3";
        List<Rating> list = new ArrayList<Rating>();
        SQLiteDatabase database = db.getReadableDatabase();
        UserDAO userDAO = new UserDAO(context);
        Cursor c = database.rawQuery(q, null);
        while (c.moveToNext()){
            Rating rating = new Rating();
            User user = userDAO.getUserID(c.getInt(c.getColumnIndex("ID")));
            rating.name = user.username;
            rating.score = Integer.parseInt(c.getString(c.getColumnIndex("SCORE")));
            list.add(rating);
        }
        return list;
    }
}
