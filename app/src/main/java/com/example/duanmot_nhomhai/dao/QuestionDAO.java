package com.example.duanmot_nhomhai.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmot_nhomhai.database.DbHelper;
import com.example.duanmot_nhomhai.model.Question;
import com.example.duanmot_nhomhai.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    DbHelper db;
    public QuestionDAO(Context context){
        db = DbHelper.getDbInstance(context);
    }

    //lấy tất cả Question
    public List<Question> getAllQuestion() {
        String q = "SELECT ID,QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID FROM QUESTION";
        return getQuestion(q,null,"getAllQuestion()");
    }

    //Lấy question theo id
    public Question getQuestionID(int questionID) {
        String q = "SELECT ID,QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID FROM QUESTION WHERE ID = ?";
        String[] params = new String[]{String.valueOf(questionID)};
        List<Question> list = getQuestion(q,params,"getTopicID()");
        if(list.size()>0) return list.get(0);
        return null;
    }

    //Thêm question
    public boolean insert(Question question) {
        SQLiteDatabase database = db.getWritableDatabase();
        database.beginTransaction();
        long row = 0;
        try{
            ContentValues values = new ContentValues();
            values.put("QUES", question.getQuestion());
            values.put("OPTION1", question.getAnswer1());
            values.put("OPTION2", question.getAnswer2());
            values.put("OPTION3", question.getAnswer3());
            values.put("OPTION4", question.getAnswer4());
            values.put("ANSWER", question.getAnswer());
            values.put("TOPIC_ID", question.getTopicID());
            row = database.insertOrThrow("QUESTION",null,values);
            database.setTransactionSuccessful();
        }catch (Exception e){
            Log.i("insert(): ",e.getMessage());
        }finally {
            database.endTransaction();
        }
        return row >= 1;
    }

    //Cập nhật question
    public boolean update(Question question) {
        SQLiteDatabase database = db.getWritableDatabase();
        database.beginTransaction();
        long row = 0;
        try{
            ContentValues values = new ContentValues();
            values.put("QUES", question.getQuestion());
            values.put("OPTION1", question.getAnswer1());
            values.put("OPTION2", question.getAnswer2());
            values.put("OPTION3", question.getAnswer3());
            values.put("OPTION4", question.getAnswer4());
            values.put("ANSWER", question.getAnswer());
            values.put("TOPIC_ID", question.getTopicID());

            row = database.update("QUESTION", values, "ID = ?",
                    new String[]{String.valueOf(question.getId())});
            database.setTransactionSuccessful();
        }catch (Exception e){
            Log.i("update(): ",e.getMessage());
        }finally {
            database.endTransaction();
        }
        return row == 1;
    }

    //Xóa question
    public boolean delete(int id) {
        SQLiteDatabase database = db.getWritableDatabase();
        database.beginTransaction();
        long row = 0;
        try{
            row = database.delete("QUESTION", "ID = ?",
                    new String[]{String.valueOf(id)});
            database.setTransactionSuccessful();
        }catch (Exception e){
            Log.i("delete(): ",e.getMessage());
        }finally {
            database.endTransaction();
        }
        return row == 1;
    }

    //Lấy dữ liệu của Question
    private List<Question> getQuestion(String q, String[] params, String ex){
        List<Question> list = new ArrayList<>();
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(q,params);
        try{
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    Integer id = cursor.getInt(cursor.getColumnIndex("ID"));
                    String ques = cursor.getString(cursor.getColumnIndex("QUES"));
                    String answer1 = cursor.getString(cursor.getColumnIndex("OPTION1"));
                    String answer2 = cursor.getString(cursor.getColumnIndex("OPTION2"));
                    String answer3 = cursor.getString(cursor.getColumnIndex("OPTION3"));
                    String answer4 = cursor.getString(cursor.getColumnIndex("OPTION4"));
                    Integer answer = cursor.getInt(cursor.getColumnIndex("ANSWER"));
                    Integer topic_id = cursor.getInt(cursor.getColumnIndex("TOPIC_ID"));

                    Question question = new Question(id, ques, answer1, answer2, answer3, answer4, answer, topic_id);
                    list.add(question);
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
}
