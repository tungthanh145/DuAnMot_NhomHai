package com.example.duanmot_nhomhai.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmot_nhomhai.R;
import com.example.duanmot_nhomhai.dao.UserDAO;
import com.example.duanmot_nhomhai.model.User;
import com.example.duanmot_nhomhai.services.UserService;

import java.util.ArrayList;
//Màn hình đăng kí
public class RegisterActivity extends AppCompatActivity {
    EditText edtUsernameRegister, edtPasswordRegister, edtCfPasswordRegister, edtName;
    Button btnSave, btnCancel;
    TextView tvHaveAnAccount, tvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsernameRegister = findViewById(R.id.edtUserNameAdd);
        edtPasswordRegister = findViewById(R.id.edtPasswordAdd);
        edtCfPasswordRegister = findViewById(R.id.edtRePasswordAdd);
        edtName = findViewById(R.id.edtFullNameAdd);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        tvHaveAnAccount = findViewById(R.id.tvHaveAnAccount);
        tvRegister = findViewById(R.id.tvRegister);

        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(tvRegister, "alpha",0.3f);
        scaleAnimator.setDuration(5000);
        scaleAnimator.setRepeatCount( ValueAnimator.INFINITE);
        scaleAnimator.setRepeatMode(ValueAnimator.RESTART);
        scaleAnimator.start();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsernameRegister.getText().toString().trim();
                String password = edtPasswordRegister.getText().toString().trim();
                String name = edtName.getText().toString().trim();

                User user = new User(-1,-1,username,password,"");

                Intent intent = new Intent(RegisterActivity.this, UserService.class);
                intent.setAction(UserService.ACTION_REGISTER);
                intent.putExtra("user", user);
                startService(intent);
                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent1);
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });
        btnCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        } );

        tvHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(UserService.EVENT_COURSE_SERVICE);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resultCode = intent.getIntExtra("resultCode", RESULT_CANCELED);
            if (resultCode == RESULT_OK) {
                String action = intent.getStringExtra("action");
                Boolean result = intent.getBooleanExtra("result", false);
                if (UserService.ACTION_REGISTER.equals(action)){
                    if (result == false){
                        Toast.makeText(context, "Username exists", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent1);
                    }
                }
            }
        }
    };
}