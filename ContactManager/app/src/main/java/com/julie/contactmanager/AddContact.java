package com.julie.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.julie.contactmanager.data.DatabaseHandler;
import com.julie.contactmanager.model.Contact;

public class AddContact extends AppCompatActivity {
    EditText editName;
    EditText editPhone;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        btnAdd = findViewById(R.id.btnAdd);

        //버튼을 누르면 주소록에 저장.
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.에디트텍스트에서 유저가 입력한, 이름과 폰 번호 가져온다.
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();

                if(name.isEmpty() || phone.isEmpty()){
                    Toast.makeText(AddContact.this,"이름이나 연락처는 필수입니다.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }


                //2.데이터베이스에 저장한다.
                //2.1 핸들러 클래스를 객체 생성한다.
                DatabaseHandler dh = new DatabaseHandler(AddContact.this);
                //2.2 Contact 객체 생성해서 이름과 폰 번호를 담는다.
                Contact new_contact = new Contact();
                new_contact.setName(name);
                new_contact.setPhoneNumber(phone);
                //2.3데이터베이스에 저장하는 메소드 호출
                dh.addContact(new_contact);
                //3.토스트 메세지 보여줘라.
                Toast.makeText(AddContact.this,"잘 저장되었습니다.",Toast.LENGTH_SHORT).show();
                //4.자동으로 메인 엑티비티로 돌아간다.
                //=> 지금의 엑티비티를 종료

                finish();


            }
        });


    }
}
