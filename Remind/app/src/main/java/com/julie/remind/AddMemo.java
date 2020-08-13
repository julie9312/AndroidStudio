package com.julie.remind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.julie.remind.data.DatabaseHandler;
import com.julie.remind.model.Memo;


public class AddMemo extends AppCompatActivity {
EditText editTitle;
EditText editContent;
Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTitle.getText().toString().trim();
                String content = editContent.getText().toString().trim();
                if(title.isEmpty() || content.isEmpty()){
                    Toast.makeText(AddMemo.this, "제목과 내용은 필수 입니다.", Toast.LENGTH_SHORT).show();

                return;
            }
                DatabaseHandler dh = new DatabaseHandler(AddMemo.this);
                Memo memo = new Memo();
                memo.setTitle(title);
                memo.setContent(content);
                dh.addMemo(memo);


                Toast.makeText(AddMemo.this, "잘 저장되었습니다.",
                        Toast.LENGTH_SHORT).show();

            finish();
        }

        });

    }
}
