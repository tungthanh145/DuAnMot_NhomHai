package com.example.duanmot_nhomhai.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmot_nhomhai.R;
import com.example.duanmot_nhomhai.dao.UserDAO;
import com.example.duanmot_nhomhai.model.User;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    ArrayList<User> list = new ArrayList<>();
    private EditText edtUser, edtPassword, edtCfPass;
    private Button btnSaveUser, btnCancelUser;
    UserDAO userDAO;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        edtCfPass = findViewById(R.id.edtCfPass);
        btnSaveUser = findViewById(R.id.btnSaveUser);
        btnCancelUser = findViewById(R.id.btnCancelUser);

        userDAO = new UserDAO( RegisterActivity.this );

        btnSaveUser.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUser.getText().toString();
                String password = edtPassword.getText().toString();

                user = new User( -1, username, password, -1 );

                if(userDAO.insertUser(user) == false){
                    new AlertDialog.Builder(RegisterActivity.this).setMessage("Created a new account !").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.left_in, R.anim.right_out);
                        }
                    }).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Can't create a new account ! ", Toast.LENGTH_SHORT).show();
                }
            }
        } );
        btnCancelUser.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        } );
    }
}