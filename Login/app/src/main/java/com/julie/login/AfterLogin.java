package com.julie.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AfterLogin extends AppCompatActivity {

    TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        txtValue = findViewById(R.id.txtValue);
        String email = getIntent().getStringExtra("email");
        txtValue.setText(email+"님 회원가입을 환영합니다.");

    }
}