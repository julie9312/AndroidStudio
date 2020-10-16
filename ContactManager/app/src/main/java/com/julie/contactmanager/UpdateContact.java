package com.julie.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.julie.contactmanager.data.DatabaseHandler;
import com.julie.contactmanager.model.Contact;

public class UpdateContact extends AppCompatActivity {

    EditText editName;
    EditText editPhone;
    Button btnUpdate;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        btnUpdate = findViewById(R.id.btnUpdate);
        //어뎁터에서 , 유저가 클리한 경우 데이터를 받아온다.
        id = getIntent().getIntExtra("id",-1);
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        // 위에서 받아온 데이터를 , 에디트텍스트에 보여준다.
        editName.setText(name);
        editPhone.setText(phone);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //데이터베이스에 업데이트.
                //유저가 변경했을수 있는 이름과 폰 번을, 에디트텍스트에서 가져온다.
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                //디비핸들러클래스 통해서 업데이트
                DatabaseHandler dh = new DatabaseHandler(UpdateContact.this);
                Contact contact = new Contact(id, name, phone);
                dh.updateContact(contact);
                //토스트보여준다.
                Toast.makeText(UpdateContact.this,"수정되었습니다.", Toast.LENGTH_SHORT).show();
                //메인화면이 다시 나오도록
                finish();
            }
        });



    }
}
