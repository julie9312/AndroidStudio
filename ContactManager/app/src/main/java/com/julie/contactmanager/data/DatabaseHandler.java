package com.julie.contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

import com.julie.contactmanager.model.Contact;
import com.julie.contactmanager.util.Util;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME,null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //테이블 생성문 SQLite 문법에 맞게 작성 공백 확인 필
        String CREATE_CONTACT_TABLE = "create table " +
                Util.TABLE_NAME + "(" +
                Util.KEY_ID + " integer not null primary key autoincrement," +
                Util.KEY_NAME + " text, " +
                Util.KEY_PHONE_NUMBER + " text )";
        //create table contacts
        //(id integer not null autoincrement primary key,
        //name text,
        // phone_number)

        //2.쿼리 실행
        db.execSQL(CREATE_CONTACT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String DROP_TABLE = "drop table " + Util.TABLE_NAME;
    db.execSQL(DROP_TABLE);

    //테이블 새로 생성
     onCreate(db);
    }

    //주소 저장하는 메소드 : 오버라이딩이 아니라 , 우리가 만들어줘야 하는 메소드
    public void  addContact(Contact contact){
        //1.주소를 저장하기 위해서 writable를 가져온다
        SQLiteDatabase db = this.getWritableDatabase();
        //2.db에 저장하기 위해서는,ContentValues를 이용한다.
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());
        //3. db에 실제로 저장한다.
        db.insert(Util.TABLE_NAME, null, values);
        db.close();
        Log.i("myDB", "inserted.");

    }
    // 주소 1개 가져오는 메소드
    // select * from contacts where id = 3;

    public Contact getContact(int id){
        // 1. 데이터베이스 가져온다. 조회니까, readable 한 db로 가져온다.
        SQLiteDatabase db = this.getWritableDatabase();
        // select id, name, phone_number from contacts where id = 3;
        // 2. 데이터를 조회할때는 ,Cursor 를 이용해야 한다.
        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[] {"id", "name", "phone_number"},
                Util.KEY_ID + " = ?", new String[]{String.valueOf(id)},
                null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        int selectedId = Integer.parseInt(cursor.getString(0));
        String selectedName = cursor.getString(1);
        String selectedPhoneNumber = cursor.getString(2);
        //db에서 읽어온 데이터를 , 자바클래스로 처리한다.
        Contact contact = new Contact();
        contact.setId(selectedId);
        contact.setName(selectedName);
        contact.setPhoneNumber(selectedPhoneNumber);

        return contact;
    }
    //디비에 저장된 모든 주소곡 정보를 불러오는 메소드
     public ArrayList<Contact> getAllContacts(){
        //1.비어있는 어레이 리스트를 먼저 한개 만든다.
         ArrayList<Contact> contactList = new ArrayList<>();
         //2. 데이터베이스에 select(조회)해서,
         String selectAll = "select * from " + Util.TABLE_NAME;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery(selectAll,null);

         //3.여러개의 데이터를 루프 돌면서,Contact클레스에 정보를 하나씩 담고
         if(cursor.moveToFirst()){
             do{
                 int selectedId = Integer.parseInt(cursor.getString(0));
                 String selectedName = cursor.getString(1);
                 String selectedPhoneNumber = cursor.getString(2);
                 //db에서 읽어온 데이터를, 자바클래스로 처리한다.
                 Contact contact = new Contact();
                 contact.setId(selectedId);
                 contact.setName(selectedName);
                 contact.setPhoneNumber(selectedPhoneNumber);

                 //4.위의 빈 어레이리스트에 하나씩 추가를 시킨다.
             }while(cursor.moveToFirst());
         }
         return contactList;

     }
     //데이터를 업데이트 하는 메소드
     public int updateContact(Contact contact){
         SQLiteDatabase db = this.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(Util.KEY_NAME, contact.getName());
         values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

         //데이터베이스 테이블 업데이트
         // update contacts set name = "홍길동",phone= "010-2222-22222" where id = 3;
         int ret = db.update(Util.TABLE_NAME,//테이블명
                 values,//업데이트할 값
                 Util.KEY_ID + " = ?", // where
                 new String[]{String.valueOf(contact.getId())});// ? 에 들어갈 값
         db.close();
         return ret;
     }
      //데이터 삭제하는 메서드
      public void deleteContact(Contact contact){
          SQLiteDatabase db = this.getWritableDatabase();
          db.delete(Util.TABLE_NAME,
                  Util.KEY_ID + " = ?",
                  new String[]{String.valueOf(contact.getId())});

          db.close();

         }
        //테이블에 저장된 주소록 데이터의 전체 객수를 리턴하는 메소드.
        public int getCount(){
        //select count (*) from contacts;
            String countQuery = "select * from " + Util.TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            int count = cursor.getCount();
            db.close();
            return count;

        }

     }




































