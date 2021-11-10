package com.example.duanmot_nhomhai.database;

import static com.example.duanmot_nhomhai.uitilities.Constants.DB_NAME;
import static com.example.duanmot_nhomhai.uitilities.Constants.DB_VERSION;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static DbHelper dbInstance;
    public static synchronized DbHelper getDbInstance(Context context){
        if(dbInstance==null) dbInstance = new DbHelper(context);
        return dbInstance;
    }

    public DbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "CREATE TABLE TOPIC(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "OPTION_TOPIC TEXT)";
        db.execSQL(q);

        q = "CREATE TABLE QUESTION(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "QUES TEXT, " +
                "OPTION1 TEXT, OPTION2 TEXT, OPTION3 TEXT, OPTION4 TEXT, " +
                "ANSWER INTEGER, " +
                "TOPIC_ID INTEGER, FOREIGN KEY(TOPIC_ID) REFERENCES TOPIC(ID))";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS USERS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "PASS TEXT, " +
                "EMAIL TEXT, " +
                "SCORE INTEGER)";
        db.execSQL(q);

        //insert table topic
        q = "INSERT INTO TOPIC(OPTION_TOPIC) VALUES ('Dễ')";
        db.execSQL(q);
        q = "INSERT INTO TOPIC(OPTION_TOPIC) VALUES ('Trung bình')";
        db.execSQL(q);
        q = "INSERT INTO TOPIC(OPTION_TOPIC) VALUES ('Khó')";
        db.execSQL(q);

        //insert table user
        q = "INSERT INTO USERS(NAME, PASS, EMAIL, SCORE) VALUES ('thanhtung','1','thanhtung@gmail.com',7)";
        db.execSQL(q);
        q = "INSERT INTO USERS(NAME, PASS, EMAIL, SCORE) VALUES ('phuockhoa','2','phuockhoa@gmail.com',8)";
        db.execSQL(q);
        q = "INSERT INTO USERS(NAME, PASS, EMAIL, SCORE) VALUES ('thanhluan','3','thanhluan@gmail.com',9)";
        db.execSQL(q);
        q = "INSERT INTO USERS(NAME, PASS, EMAIL, SCORE) VALUES ('minhhoang','4','minhhoang@gmail.com',5)";
        db.execSQL(q);
        q = "INSERT INTO USERS(NAME, PASS, EMAIL, SCORE) VALUES ('phuocthien','5','phuocthien@gmail.com',6)";
        db.execSQL(q);

        //Dễ
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Bài thơ Bánh trôi nước là tác phẩm của nhà thơ nào ?', 'A. Hồ Xuân Hương', 'B. Xuân Quỳnh', 'C. Xuân Diệu', 'D. Nam Cao', 1, 1)";
        db.execSQL(q);
        //Trung bình
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Quần đảo Hoàng Sa thuộc tỉnh nào của nước ta ?', 'A. Khánh Hòa', 'B. Đà Nẵng', 'C. Lào Cai', 'D. Thanh Hóa', 2, 2)";
        db.execSQL(q);
        //Khó
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Cách mạng tháng 8 diễn ra vào năm nào ?', 'A. 1978', 'B. 1965', 'C. 1954', 'D. 1945', 4, 3)";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion) {
            db.execSQL("DROP TABLE IF EXISTS TOPIC");
            db.execSQL("DROP TABLE IF EXISTS QUESTION");
            db.execSQL("DROP TABLE IF EXISTS USER");
            onCreate(db);
        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
