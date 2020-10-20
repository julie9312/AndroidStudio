package com.julie.mydiceapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.julie.mytest.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView diceImg1;
    ImageView diceImg2;

    // 5. 주사위 실제 이미지 (1 ~ 6 ) 를 코드로 가져온다.
    // 6. 이미지를 int 로 가져온 이유는??  안드로이드에서 자동으로
    //     우리가 넣은 이미지를, 정수로 바꿔서 관리합니다.
    int[] diceImages = {R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,
            R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //0. 주사위 이미지 2개를 가져온다.
        diceImg1 = findViewById(R.id.imgDice1);
        diceImg2 = findViewById(R.id.imgDice2);



        // 1. 버튼 가져온다.
        Button btnRoll = findViewById(R.id.btnRollDice);
        // 2. 버튼에 이벤트를 정의한다.
        btnRoll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3. 이 안에다 동작시키고 싶은 코드를 작성한다.
                //3-1 버튼이 눌렸을떄 실행순서
                Log.i("MyDiceApp", "주사위 버튼 눌렸음!");
                Random rand = new Random();
                int diceNumber = rand.nextInt(6);

                Log.i("MyDiceApp", ""+diceNumber);
                //3-3 가져온 숫자에 맞는 주사위 이미지를 셋팅한다.
                //diceNumber 는 0~5 까지 나온다.
                // 따라서 다이스넘버에 해당하는 숫자가, 이미지배열에 인덱스와 같다.
                diceImg1.setImageResource(diceImages[diceNumber]);
                //3-4 오른쪽 주사위 이미지도 랜덤숫자 설정
                diceNumber = rand.nextInt(6);
                diceImg2.setImageResource(diceImages[diceNumber]);


            }
        });


    }
}