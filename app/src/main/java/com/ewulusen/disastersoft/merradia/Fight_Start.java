package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class Fight_Start extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    DatabaseHelper userDB;
    TextView nameV,lvlV;
    GifImageView charakter;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight__start);
                intent = getIntent();
                id = intent.getStringExtra("datas");
                userDB = new DatabaseHelper(this);
                Cursor localCursor=userDB.getCharacters(id);
                int i=1;
                /**
                 * végig futunk a kapott karakter listán és beállítjuk a kinézete hozzá+ azt hogy mi történjen ha rákattintunk
                 * Gyuri ezt még lehet majd változtatni ha  te tudsz olyat hogy egyik fileba vannak a sorok
                 * és azt beolvassuk egy lsitviewbe a másik fileba
                 */
                while (localCursor.moveToNext()) {
                    // Append server response in string
                    String name=localCursor.getString(localCursor.getColumnIndex("Name")).toString();
                    String kaszt=localCursor.getString(localCursor.getColumnIndex("KASZT")).toString();
                    String lvl=localCursor.getString(localCursor.getColumnIndex("LVL")).toString();
                    final String idC=localCursor.getString(localCursor.getColumnIndex("ID")).toString();
                    switch (i)
                    {
                        case 1:
                            nameV=findViewById(R.id.Name1);
                            nameV.setText(name);
                            lvlV=findViewById(R.id.lvl1);
                            lvlV.setText(lvl);
                            edit=findViewById(R.id.edit1);
                            edit.setEnabled(true);
                            charakter=findViewById(R.id.ork1);
                            switch (kaszt)
                            {
                                case "1":
                                    charakter.setImageResource(R.drawable.allk1);
                                    break;
                                case "2":
                                    charakter.setImageResource(R.drawable.rougeall);
                                    break;
                                case "3":
                                    charakter.setImageResource(R.drawable.archerall);
                                    break;
                                case "4":
                                    charakter.setImageResource(R.drawable.orkall);
                                    break;
                                case "5":
                                    charakter.setImageResource(R.drawable.wizardall);
                                    break;
                            }
                            edit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent2 = null;
                                    intent2 = new Intent(Fight_Start.this, Figt_Field.class);
                                    intent2.putExtra("datas", id+","+idC);
                                    startActivity(intent2);
                                    finish();

                                }
                            });
                            break;
                        case 2:
                            nameV=findViewById(R.id.Name2);
                            nameV.setText(name);
                            lvlV=findViewById(R.id.lvl2);
                            lvlV.setText(lvl);
                            edit=findViewById(R.id.edit2);
                            edit.setEnabled(true);
                            charakter=findViewById(R.id.ork2);
                            switch (kaszt)
                            {
                                case "1":
                                    charakter.setImageResource(R.drawable.allk1);
                                    break;
                                case "2":
                                    charakter.setImageResource(R.drawable.rougeall);
                                    break;
                                case "3":
                                    charakter.setImageResource(R.drawable.archerall);
                                    break;
                                case "4":
                                    charakter.setImageResource(R.drawable.orkall);
                                    break;
                                case "5":
                                    charakter.setImageResource(R.drawable.wizardall);
                                    break;
                            }
                            edit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent2 = null;
                                    intent2 = new Intent(Fight_Start.this, Figt_Field.class);
                                    intent2.putExtra("datas", id+","+idC);
                                    startActivity(intent2);
                                    finish();
                                }
                            });
                           
                            break;
                        case 3:
                            nameV=findViewById(R.id.Name3);
                            nameV.setText(name);
                            lvlV=findViewById(R.id.lvl3);
                            lvlV.setText(lvl);
                            edit=findViewById(R.id.edit3);
                            edit.setEnabled(true);
                            charakter=findViewById(R.id.ork3);
                            switch (kaszt)
                            {
                                case "1":
                                    charakter.setImageResource(R.drawable.allk1);
                                    break;
                                case "2":
                                    charakter.setImageResource(R.drawable.rougeall);
                                    break;
                                case "3":
                                    charakter.setImageResource(R.drawable.archerall);
                                    break;
                                case "4":
                                    charakter.setImageResource(R.drawable.orkall);
                                    break;
                                case "5":
                                    charakter.setImageResource(R.drawable.wizardall);
                                    break;
                            }
                            edit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent2 = null;
                                    intent2 = new Intent(Fight_Start.this, Figt_Field.class);
                                    intent2.putExtra("datas", id+","+idC);
                                    startActivity(intent2);
                                    finish();
                                }
                            });
                            break;
                        case 4:
                            nameV=findViewById(R.id.Name4);
                            nameV.setText(name);
                            lvlV=findViewById(R.id.lvl4);
                            lvlV.setText(lvl);
                            edit=findViewById(R.id.edit4);
                            edit.setEnabled(true);
                            charakter=findViewById(R.id.ork4);
                            switch (kaszt)
                            {
                                case "1":
                                    charakter.setImageResource(R.drawable.allk1);
                                    break;
                                case "2":
                                    charakter.setImageResource(R.drawable.rougeall);
                                    break;
                                case "3":
                                    charakter.setImageResource(R.drawable.archerall);
                                    break;
                                case "4":
                                    charakter.setImageResource(R.drawable.orkall);
                                    break;
                                case "5":
                                    charakter.setImageResource(R.drawable.wizardall);
                                    break;
                            }
                            edit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent2 = null;
                                    intent2 = new Intent(Fight_Start.this, Figt_Field.class);
                                    intent2.putExtra("datas", id+","+idC);
                                    startActivity(intent2);
                                    finish();
                                }
                            });
                            break;
                        case 5:
                            nameV=findViewById(R.id.Name5);
                            nameV.setText(name);
                            lvlV=findViewById(R.id.lvl5);
                            lvlV.setText(lvl);
                            edit=findViewById(R.id.edit5);
                            edit.setEnabled(true);
                            charakter=findViewById(R.id.ork5);
                            switch (kaszt)
                            {
                                case "1":
                                    charakter.setImageResource(R.drawable.allk1);
                                    break;
                                case "2":
                                    charakter.setImageResource(R.drawable.rougeall);
                                    break;
                                case "3":
                                    charakter.setImageResource(R.drawable.archerall);
                                    break;
                                case "4":
                                    charakter.setImageResource(R.drawable.orkall);
                                    break;
                                case "5":
                                    charakter.setImageResource(R.drawable.wizardall);
                                    break;
                            }
                            edit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent2 = null;
                                    intent2 = new Intent(Fight_Start.this, Figt_Field.class);
                                    intent2.putExtra("datas", id+","+idC);
                                    startActivity(intent2);
                                    finish();
                                }
                            });
                            break;


                    }
                    i++;
                    //Log.d("lsit", line+ " "+localCursor.getCount());
                }
            }
        }
