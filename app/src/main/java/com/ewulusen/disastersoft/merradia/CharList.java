package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CharList extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    DatabaseHelper userDB;
    ListView lista;
    List<String> initialList;
    private ArrayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_list);
        intent = getIntent();
        id = intent.getStringExtra("datas");
        userDB = new DatabaseHelper(this);
        Cursor localCursor=userDB.getChar(id);

        while (localCursor.moveToNext()) {
            // Append server response in string
            String name=localCursor.getString(localCursor.getColumnIndex("Name")).toString();String kaszt=localCursor.getString(localCursor.getColumnIndex("KASZT")).toString();
            String lvl=localCursor.getString(localCursor.getColumnIndex("LVL")).toString();

             //Log.d("lsit", line+ " "+localCursor.getCount());
        }
    }
}
