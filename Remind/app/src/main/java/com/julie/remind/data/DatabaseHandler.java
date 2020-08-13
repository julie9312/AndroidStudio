package com.julie.remind.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.julie.remind.model.Memo;
import com.julie.remind.util.Util;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMO_TABLE = "create table " +
                Util.TABLE_NAME + "(" +
                Util.KEY_ID + " integer not null primary key autoincrement," +
                Util.KEY_TITLE + " text, " +
                Util.KEY_CONTENT + " text )";

        db.execSQL(CREATE_MEMO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "drop table " + Util.TABLE_NAME;
        db.execSQL(DROP_TABLE);

        onCreate(db);

    }

    //여기서부터는 기획에 맞게 데이터베이스에 넣고 업데이트 가져오고 삭제 하는거
    public void addMemo(Memo memo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Util.KEY_TITLE, memo.getTitle());
        values.put(Util.KEY_CONTENT, memo.getContent());
        db.insert(Util.TABLE_NAME, null, values);
        db.close();
    }

    public Memo getMemo(int id) {

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{"id", "title", "content"},
                Util.KEY_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        int selectedId = Integer.parseInt(cursor.getString(0));
        String selectedTitle = cursor.getString(1);
        String selectedContent = cursor.getString(2);

        Memo memo = new Memo();
        memo.setId(selectedId);
        memo.setTitle(selectedTitle);
        memo.setContent(selectedContent);

        return memo;
    }


    public ArrayList<Memo> getAllMemo() {

        ArrayList<Memo> memoList = new ArrayList<>();


        String selectAll = "select * from " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAll, null);


        if (cursor.moveToFirst()) {
            do {
                int selectedId = Integer.parseInt(cursor.getString(0));
                String selectedTitle = cursor.getString(1);
                String selectedContent = cursor.getString(2);


                Memo memo = new Memo();
                memo.setId(selectedId);
                memo.setTitle(selectedTitle);
                memo.setContent(selectedContent);

                memoList.add(memo);

            } while (cursor.moveToNext());
        }
        return memoList;
    }

    // 데이터를 업데이트 하는 메서드.
    public int updateMemo(Memo memo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_TITLE, memo.getTitle());
        values.put(Util.KEY_CONTENT, memo.getContent());

        // 데이터베이스 테이블 업데이트.
        // update contacts set name="홍길동", phone="010-2222-2222" where id = 3;
        int ret = db.update(Util.TABLE_NAME,    // 테이블명
                values,     // 업데이트할 값
                Util.KEY_ID + " = ?",   // where
                new String[]{String.valueOf(memo.getId())}); // ? 에 들어갈 값
        db.close();
        return ret;
    }

    // 데이터 삭제 메서드
    public void deleteMemo(Memo memo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME,  // 테이블 명
                Util.KEY_ID + " = ?",   // where id = ?
                new String[]{String.valueOf(memo.getId())});  // ? 에 해당하는 값.
        db.close();
    }

    // 테이블에 저장된 주소록 데이터의 전체 갯수를 리턴하는 메소드.
    public int getCount() {
        // select count(*) from contacts;
        String countQuery = "select * from " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        db.close();
        return count;
    }

    // 테이블에 저장된 데이터를 검색하는 메소드 검색용 메소드 추가
    public ArrayList<Memo> search(String keyword) {
        String searchQuery = "select id, title, content from memo where content like ? or title like ? ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(searchQuery, new String[]{"%"+keyword+"%", "%"+keyword+"%"});
        ArrayList<Memo> memoList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int selectedId = Integer.parseInt(cursor.getString(0));
                String selectedTitle = cursor.getString(1);
                String selectedContent = cursor.getString(2);


                Memo memo = new Memo();
                memo.setId(selectedId);
                memo.setTitle(selectedTitle);
                memo.setContent(selectedContent);

                memoList.add(memo);

            } while (cursor.moveToNext());
        }
        return memoList;
    }


}