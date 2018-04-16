package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Battle extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    public static String ids;
    DatabaseHelper userDB;
    TextView str,agi,dex,def,con,inte,ref,luck,ac,mc,dmg,move,point,name,hp,mana;
    Button strm,strp,agim,agip,dexm,dexp,defm,defp,conm,conp,intem,intep,refm,refp,luckm,luckp,save;
    int stri;
    int agii;
    int defi;
    int dexi;
    int intei;
    int coni;
    int refi;
    int lucki;
    int hpi;
    int pointsz=0;
    int kaszt;//1=knight,2=rouge,3=archer,4=ork,5=wizard;
    String names,datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        intent = getIntent();
        datas = intent.getStringExtra("datas");
        String[] elper=  datas.split(",");
        ids=elper[0];
        hpi=Integer.parseInt(elper[1]);
        userDB = new DatabaseHelper(this);
        Cursor localCursor=userDB.getChar(ids);
        localCursor.moveToNext();
    }
}
