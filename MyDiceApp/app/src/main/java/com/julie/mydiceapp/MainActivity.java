package com.julie.mydiceapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.julie.mytest.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 버튼 가져온다.
        Button btnRoll = findViewById(R.id.btnRollDice);
        // 2. 버튼에 이벤트를 정의한다.
        btnRoll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3. 이 안에다 동작시키고 싶은 코드를 작성한다.
                Log.i("MyDiceApp", "주사위 버튼 눌렸음!");

                Random rand = new Random();
                int firstDiceNumber = rand.nextInt(6);

                Log.i("MyDiceApp", ""+firstDiceNumber);
            }
        });


    }
}