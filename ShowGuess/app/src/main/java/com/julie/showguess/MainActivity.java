package com.julie.showguess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editGuess;
    Button btnGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editGuess = findViewById(R.id.editGuess);
        btnGuess = findViewById(R.id.btnGuess);

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = editGuess.getText().toString().trim();
                Intent i = new Intent(MainActivity.this,ShowGuess.class);
                i.putExtra("name",guess);
                i.putExtra("number",100);
                i.putExtra("str","Hello");
                i.putExtra("boo","false");
                startActivity(i);
            }
        });
    }
}
