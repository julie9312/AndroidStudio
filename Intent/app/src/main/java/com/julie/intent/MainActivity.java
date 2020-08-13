package com.julie.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.ContactsContract;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        composeMmsMessage("안녕하세요", null);
        composeEmail(new String[]{"abc@gmail.com"}, "안녕하세요");

        openWebpage("http://naver.com");
    }
    // 연락처 선택
    public void selectedContact(){
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
        if( i.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(i, 1);
        }

    }
    public void composeMmsMessage(String message, Uri attachment){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("smsto: 010-5555-5555"));
        i.putExtra(Intent.EXTRA_STREAM, attachment);
        if(i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
    }
}
     public void openWebpage(String url) {
         Uri webpage = Uri.parse(url);
         Intent i = new Intent(Intent.ACTION_VIEW, webpage);
         if (i.resolveActivity(getPackageManager()) != null) {
             startActivity(i);

         }
     }
    public void createAlarm(String message, int hour, int minutes){
        Intent i = new Intent(AlarmClock.ACTION_DISMISS_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);

             if(i.resolveActivity(getPackageManager()) != null){
                 startActivity(i);
             }
    }

    public void composeEmail(String[] addresses, String subject){
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:"));
        i.putExtra(Intent.EXTRA_EMAIL, addresses);
        i.putExtra(Intent,)

    }
}















