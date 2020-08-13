package com.julie.cat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editYear;
    TextView txtAge;
    Button btnCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editYear = findViewById(R.id.editYear);
        txtAge = findViewById(R.id.txtAge);
        btnCal = findViewById(R.id.btnCal);
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // 버튼 눌렸을때 해야 할 일을 여기에 작성
                //1. 에디트텍스트에 적혀있는 글자 가져오기
                String catYear = editYear.getText().toString();
                Log.i("MyCat","유저가 입력한 값은 : " + catYear);

                //2.이번년도에서, 가지고온 년도를 뺀다.
                int catAge = 2020-Integer.parseInt(catYear);
                Log.i("MyCat","계산한 나이는 : " + catAge);
                //3.텍스트뷰에 표시해야 겠다.
                txtAge.setText(""+catAge);

                //4.에디트텍스트에서 유저가 쓴 글자 지운다.
                editYear.setText("");

            }
        });


    }
}
