package com.julie.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editEmail;
    EditText editPasswd1;
    EditText editPasswd2;
    Button btnReg;
    Button btnLogin;

    EditText editTitle;
    EditText editPost;

    Button btnNo;
    Button btnYes;


    private AlertDialog dialog;

    Intent i;

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
                String email = editEmail.getText().toString().trim();
                String passwd1 = editPasswd1.getText().toString().trim();
                String passwd2 = editPasswd2.getText().toString().trim();

                if (email.contains("@") == false) {
                    Toast.makeText(MainActivity.this, "이메일을 바르게 입력하세요.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passwd1.length() < 6 || passwd1.length() > 12) {
                    Toast.makeText(MainActivity.this, "비밀번호는 6자리이상 12자리 이하로 " +
                            "입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passwd1.compareTo(passwd2) != 0) {
                    Toast.makeText(MainActivity.this, "비밀번호를 일치하여 주십시오.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(MainActivity.this, AfterLogin.class);
                i.putExtra("email", email);
                i.putExtra("passwd", passwd1);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createPopupDialog();

            }
        });
    }
    public void createPopupDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View alertView = getLayoutInflater().inflate(R.layout.alert, null);

        editTitle = alertView.findViewById(R.id.editTitle);
        editPost = alertView.findViewById(R.id.editPost);
        btnNo = alertView.findViewById(R.id.btnNo);
        btnYes = alertView.findViewById(R.id.btnYes);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTitle.getText().toString().trim();
                String pw = editPost.getText().toString().trim();

               if (email.isEmpty() || pw.isEmpty()) {
                Toast.makeText(MainActivity.this, "이메일이나 비번을 입력하세요.",
                   Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                dialog.cancel();
                                }
                            });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        alert.setNegativeButton("NO", null);
        alert.setView(alertView);
        alert.setCancelable(false);

        dialog = alert.create();
        dialog.show();
    }
}
