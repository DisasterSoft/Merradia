package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    DatabaseHelper userDB;
    TextView kiir;
    Button mkchar,editChar,fight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        intent = getIntent();
        id = intent.getStringExtra("datas");
        userDB = new DatabaseHelper(this);
        Cursor localCursor=userDB.getName(id);
        localCursor.moveToNext();
        kiir=findViewById(R.id.welcome);
        String userName[] =localCursor.getString(0).split("@");
        kiir.setText("Wellcom dear "+userName[0]+"!" );
        localCursor=userDB.getCharacters(id);
        editChar=findViewById(R.id.editChar);
        fight=findViewById(R.id.fight);
        if(localCursor.getCount()==0)
        {
           fight.setEnabled(false);
           editChar.setEnabled(false);
        }
        mkchar=findViewById(R.id.makechar);
        if(localCursor.getCount()==5)
        {
            mkchar.setEnabled(false);
        }
        mkchar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = null;
                intent2 = new Intent(MainScreen.this, MakeCharakter.class);
                intent2.putExtra("datas", id);
                startActivity(intent2);

            }
        });
        editChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = null;
                intent2 = new Intent(MainScreen.this, CharList.class);
                intent2.putExtra("datas", id);
                startActivity(intent2);

            }
        });
      fight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = null;
                intent2 = new Intent(MainScreen.this, Fight_Start.class);
                intent2.putExtra("datas", id);
                startActivity(intent2);

            }
        });


    }
}
