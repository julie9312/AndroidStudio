package com.julie.showguess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ShowGuess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        txtRecv = findViewById(R.id.txtRecv);

        Intent i = getIntent();
        String.guess = getIntent().getStringExtra("name");
        int number = i.getIntExtra("number",0);
        String myStr = i.getStringExtra("str");
        boolean myboo = i.getBooleanExtra("boo",false);

        String all = guess + " " +number + " " +myStr +" "+myboo;

        txtRecv.setText(all);
    }
}
