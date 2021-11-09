package com.example.duanmot_nhomhai.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmot_nhomhai.database.DbHelper;
import com.example.duanmot_nhomhai.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicDAO {
    DbHelper db;
    public TopicDAO(Context context){
        db = DbHelper.getDbInstance(context);
    }

    //Lấy dữ liệu của Topic
    public List<Topic> getTopic(){
        List<Topic> list = new ArrayList<>();
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM TOPIC",null);
        if(cursor.moveToFirst()){
            do {
                Topic topic = new Topic();
                topic.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                topic.setName(cursor.getString(cursor.getColumnIndex("OPTION_TOPIC")));
                list.add(topic);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
//    //lấy tất cả Topic
//    public List<Topic> getAllTopic() {
//        String q = "SELECT ID, OPTION_TOPIC FROM TOPIC";
//        return getTopic(q,null,"getAllTopic()");
//    }

//    //Lấy topic theo id
//    public Topic getTopicID(int topicID) {
//        String q = "SELECT ID, OPTION_TOPIC FROM TOPIC WHERE ID = ?";
//        String[] params = new String[]{String.valueOf(topicID)};
//        List<Topic> list = getTopic(q,params,"getTopicID()");
//        if(list.size()>0) return list.get(0);
//        return null;
//    }

//    //Thêm topic
//    public boolean insert(Topic topic) {
//        SQLiteDatabase database = db.getWritableDatabase();
//        database.beginTransaction();
//        long row = 0;
//        try{
//            ContentValues values = new ContentValues();
//            values.put("OPTION_TOPIC", topic.getName());
//            row = database.insertOrThrow("TOPIC",null,values);
//            database.setTransactionSuccessful();
//        }catch (Exception e){
//            Log.i("insert(): ",e.getMessage());
//        }finally {
//            database.endTransaction();
//        }
//        return row >= 1;
//    }

//    //Cập nhật topic
//    public boolean update(Topic topic) {
//        SQLiteDatabase database = db.getWritableDatabase();
//        database.beginTransaction();
//        long row = 0;
//        try{
//            ContentValues values = new ContentValues();
//            values.put("OPTION_TOPIC", topic.getName());
//
//            row = database.update("TOPIC", values, "ID = ?",
//                    new String[]{String.valueOf(topic.getId())});
//            database.setTransactionSuccessful();
//        }catch (Exception e){
//            Log.i("update(): ",e.getMessage());
//        }finally {
//            database.endTransaction();
//        }
//        return row == 1;
//    }

//    //Xóa topic
//    public boolean delete(int id) {
//        SQLiteDatabase database = db.getWritableDatabase();
//        database.beginTransaction();
//        long row = 0;
//        try{
//            row = database.delete("TOPIC", "ID = ?",
//                    new String[]{String.valueOf(id)});
//            database.setTransactionSuccessful();
//        }catch (Exception e){
//            Log.i("delete(): ",e.getMessage());
//        }finally {
//            database.endTransaction();
//        }
//        return row == 1;
//    }


}
