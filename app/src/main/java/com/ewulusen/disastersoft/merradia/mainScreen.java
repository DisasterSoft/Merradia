package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class mainScreen extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    databaseHelper userDB;
    TextView kiir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        intent = getIntent();
        id = intent.getStringExtra("datas");
        userDB = new databaseHelper(this);
        Cursor localCursor=userDB.getName(id);
        localCursor.moveToNext();
        kiir=findViewById(R.id.welcome);
        String userName[] =localCursor.getString(0).split("@");
        kiir.setText("Wellcom dear "+userName[0]+"!" );
        localCursor=userDB.getChar(id);
        if(localCursor.getCount()==0)
        {
            findViewById(R.id.fight).setEnabled(false);
            findViewById(R.id.editChar).setEnabled(false);
        }

    }
}
