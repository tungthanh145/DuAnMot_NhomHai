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

        q = "CREATE TABLE USERS (" +
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
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Cho tới thời điểm hiện nay, vườn quốc gia nào của nước ta chưa được công nhận là Vườn Di sản ASEAN ?', 'A. Vườn quốc gia Kon Ka Kinh', 'B. Vườn quốc gia Tam Đảo', 'C. Vườn quốc gia Chư Mom Ray', 'D. Vườn quốc gia Bái Tử Long', 2, 1)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Hoa hậu Hòa bình Quốc tế 2017 đã được tổ chức tại quốc gia nào ?', 'A. Thái Lan', 'B. Việt Nam', 'C. Lào', 'D. Campuchia', 2, 1)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Bộ phim Chị Dậu được chuyển thể từ tác phẩm nào ?', 'A. : Người mẹ cầm súng', 'B. Vợ chồng A Phủ', 'C. Tắt đèn', 'D. Tuổi thơ dữ dội', 3, 1)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Đâu là một loại hình chợ tạm tự phát thường xuất hiện trong các khu dân cư ?', 'A. Ếch', 'B. Cóc', 'C. Thằn lằn', 'D. Nhái', 2, 1)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Đâu là tên một bãi biển ở Quảng Bình ?', 'A. Đá Lăn', 'B. Đá Nhảy', 'C. Đá Chạy', 'D. Đá Bò', 2, 1)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Haiku là thể thơ truyền thống của nước nào ?', 'A. Nhật Bản', 'B. Mông Cổ', 'C. Trung Quốc', 'D. Hàn Quốc', 1, 1)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Địa danh Đắk Tô với những trận đánh nổi tiếng trong kháng chiến chống Mỹ thuộc tỉnh nào của khu vực Tây Nguyên ?', 'A. Đắk Lắk', 'B. Gia Lai', 'C. Kon Tum', 'D. Đắk Nông', 3, 1)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Đâu là tên một loại bánh Huế ?', 'A. Khoái', 'B. Sướng', 'C. Thích', 'D. Vui', 1, 1)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Nhạc sĩ nào là người sáng tác ca khúc Cây đàn sinh viên ?', 'A. Bảo Chấn', 'B. Trịnh Công Sơn', 'C. Quốc An', 'D. Trần Tiến', 3, 1)";
        db.execSQL(q);
        //Trung bình
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Quần đảo Hoàng Sa thuộc tỉnh nào của nước ta ?', 'A. Khánh Hòa', 'B. Đà Nẵng', 'C. Lào Cai', 'D. Thanh Hóa', 2, 2)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Nhà văn Kim Dung (Trung Quốc) nổi tiếng với thể loại văn học gì ?', 'A. Truyện trinh thám', 'B. Tiểu thuyết kiếm hiệp', 'C. Sử thi', 'D. Thơ lãng mạn', 2, 2)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Festival diều quốc tế là hoạt động văn hóa của thành phố nào ?', 'A. Vũng Tàu', 'B. Quy Nhơn', 'C. Đà Nẵng', 'D. Nha Trang', 1, 2)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Ngày 24/3 là ngày thế giới phòng chống...?', 'A. Lao', 'B. Phong', 'C. Ung thư', 'D. Cổ chướng', 1, 2)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Địa danh nào còn thiếu trong câu ca dao: .... có núi Vọng Phu, có đầm Thị Nại, có cù lao xanh', 'A. Phú Yên', 'B. Quảng Nam', 'C. Long An', 'D. Bình Định', 4, 2)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Nhà thơ … đã ví: “Thân em như quả mít trên cây” ?', 'A. : Xuân Quỳnh', 'B. Bà Huyện Thanh Quan', 'C. Hồ Xuân Hương', 'D. Sương Nguyệt Anh', 3, 2)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Trong “Truyện Kiều”, Nguyễn Du đã mượn hình ảnh loài hoa nào để miêu tả nàng Kiều?', 'A. Hoa ly', 'B. Hoa dại', 'C. Hoa hải đường', 'D. Hoa sen', 3, 2)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Tài nguyên giữ vị trí quan trọng nhất Việt Nam hiện nay là:', 'A. Tài nguyên đất', 'B. Tài nguyên sinh vật', 'C. Tài nguyên nước', 'D. Tài nguyên khoáng sản', 1, 2)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Vùng chuyên môn hóa về lương thực thực phẩm lớn nhất nước ta là', 'A. Đồng bằng sông Hồng', 'B. Đông Nam Bộ', 'C. Tây Nguyên', 'D. Đồng bằng sông Cửu Long', 4, 2)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Nguyên nhân nào dẫn đến sự yếu kém, khó khăn của nền kinh tế nước ta trước đây ?', 'A. Nước ta xây dựng nền kinh tế từ điểm xuất phát thấp', 'B. Nền kinh tế chịu hậu quả nặng nề của các cuộc chiến tranh kéo dài', 'C. Mô hình kinh tế thời chiến kéo dài quá lâu', 'D. Cả 3 nguyên nhân', 4, 2)";
        db.execSQL(q);
        //Khó
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Cách mạng tháng 8 diễn ra vào năm nào ?', 'A. 1978', 'B. 1965', 'C. 1954', 'D. 1945', 4, 3)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('“Tắt đèn” được xuất bản lần đầu tiên vào năm nào ?', 'A. 1936', 'B. 1939', 'C. 1941', 'D. 1946', 2, 3)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Điểm xuất phát của nền kinh tế nước ta:', 'A. Nền nông nghiệp nhỏ bé', 'B. Nền công nghiệp hiện đại', 'C. Trình độ khoa học kỹ thuật hiện đại', 'D. Nền nông nghiệp hiện đại', 1, 3)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('World Cup được tổ chức mấy năm một lần ?', 'A. 1', 'B. 2', 'C. 3', 'D. 4', 4, 3)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Nơi thu nhập bình quân đầu người cao nhất nước ta là:', 'A. Miền núi và trung du phía Bắc', 'B. Đồng Bằng Sông Hậu', 'C. Đông Nam Bộ', 'D. Tây Nguyên', 3, 3)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Quốc gia nào vô địch vô cup nhiều nhất tính đến nay ?', 'A. Anh', 'B. Brazil', 'C. Achentina', 'D. Ý', 2, 3)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Nguyên nhân dẫn đến sự bùng nổ dân số là:', 'A. Tỷ lệ sinh cao', 'B. Số người nhập cư nhiều', 'C. Dân số tăng quá nhanh', 'D. Tuổi thọ trung bình cao', 3, 3)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Chủ tich nước Việt Nam hiện nay là ai:', 'A. Nguyễn Thị Kim Ngân', 'B. Nguyễn Xuân Phúc', 'C. Trần Đại Quang', 'D. Nguyễn Phú Trọng', 2, 3)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Đội tuyển bóng đá nam Việt Nam vô đich Sea Game vào năm nào ?', 'A. 2019', 'B. 2015', 'C. 2009', 'D. 2008', 1, 3)";
        db.execSQL(q);
        q = "INSERT INTO QUESTION(QUES, OPTION1, OPTION2, OPTION3, OPTION4, ANSWER, TOPIC_ID) VALUES ('Diện tích rừng ở Việt Nam năm 1990 là', 'A. 14 triệu ha', 'B. 10 triệu ha', 'C. 9 triệu ha', 'D. 9,5 triệu ha', 3, 3)";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion) {
            db.execSQL("DROP TABLE IF EXISTS TOPIC");
            db.execSQL("DROP TABLE IF EXISTS QUESTION");
            db.execSQL("DROP TABLE IF EXISTS USERS");
            onCreate(db);
        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
