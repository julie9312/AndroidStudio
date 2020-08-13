package com.julie.remind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.julie.remind.data.DatabaseHandler;
import com.julie.remind.model.Memo;

public class UpdateMemo extends AppCompatActivity {
    EditText editTitle;
    EditText editContent;
    Button btnUpdate;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_memo);

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        btnUpdate = findViewById(R.id.btnUpdate);

        id = getIntent().getIntExtra("id", -1);
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        //화면에 표시
        editTitle.setText(title);
        editContent.setText(content);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String content = editContent.getText().toString().trim();

                Memo memo = new Memo(id, title, content);
                DatabaseHandler dh = new DatabaseHandler(UpdateMemo.this);
                dh.updateMemo(memo);
                Toast.makeText(UpdateMemo.this, "수정 완료 되었습니다.",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
