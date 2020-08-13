package com.julie.registfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText editEmail;
EditText editPasswd1;
EditText editPasswd2;
Button btnReg;
Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmail);
        editPasswd1 = findViewById(R.id.editPasswd1);
        editPasswd2 = findViewById(R.id.editPasswd2);
        btnReg = findViewById(R.id.btnReg);
        btnLogin = findViewById(R.id.btnLogin);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.이메일 주소 ,비번12 값 다가져온다
                //2.이메일 체크
                //3.비번 체크
                //4.비번 12 똑같은지 체크
                //5.다 이상없으면 다음 엑티비티로
                String email = editEmail.getText().toString();
                String passwd1 = editPasswd1.getText().toString();
                String passwd2 = editPasswd2.getText().toString();

                if(email.contains("@") == false){
                    Toast.makeText(MainActivity.this,"이메일 맞게 작성해주세요",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(passwd1.length() <6 || passwd1.length() >12 ){

                }


            }
        });















    }
}
