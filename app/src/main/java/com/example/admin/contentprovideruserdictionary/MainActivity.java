package com.example.admin.contentprovideruserdictionary;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = ((TextView) findViewById(R.id.dictionary_text_view));
        
        //Get the content resolver which will send a message to the content provider
        ContentResolver resolver = getContentResolver();

        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);
        StringBuilder data = new StringBuilder(100);
        try{
            int idIndex = cursor.getColumnIndex(UserDictionary.Words._ID);
            int wordIndex = cursor.getColumnIndex(UserDictionary.Words.WORD);
            int frequencyIndex = cursor.getColumnIndex(UserDictionary.Words.FREQUENCY);
            while (cursor.moveToNext()){
                data.append(cursor.getInt(idIndex)).append(" - ").append(cursor.getInt(frequencyIndex))
                        .append(" - ").append(cursor.getString(wordIndex)).append("/n");
            }
        }finally {
            cursor.close();
        }

        tv.setText(data.toString());



    }
}
