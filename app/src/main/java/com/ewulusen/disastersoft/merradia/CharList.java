package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.bloder.magic.view.MagicButton;
import pl.droidsonroids.gif.GifImageView;

public class CharList extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    DatabaseHelper userDB;
    TextView nameV,lvlV;
    GifImageView charakter;
    Button edit,delete;
    MagicButton mkchar,hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_list);
        intent = getIntent();
        id = intent.getStringExtra("datas");
        userDB = new DatabaseHelper(this);
        Cursor localCursor = userDB.getCharacters(id);
        mkchar=findViewById(R.id.makechar);
        hint=findViewById(R.id.hint);
        if(localCursor.getCount()==5)
        {
            mkchar.setEnabled(false);

        }
        else {
            mkchar.setMagicButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = null;
                    intent2 = new Intent(CharList.this, MakeCharakter.class);
                    intent2.putExtra("datas", id);
                    startActivity(intent2);

                }
            });
        }
        hint.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = null;
                intent2 = new Intent(CharList.this, hint.class);
                intent2.putExtra("datas", id);
                startActivity(intent2);

            }
        });
        int i = 1;
        /**
         * végig futunk a kapott karakter listán és beállítjuk a kinézete hozzá+ azt hogy mi történjen ha rákattintunk
         * Gyuri ezt még lehet majd változtatni ha  te tudsz olyat hogy egyik fileba vannak a sorok
         * és azt beolvassuk egy lsitviewbe a másik fileba. ha 0 akkor átdobjuk a karakter készítésre
         */
        if (localCursor.getCount() == 0) {
            Intent intent2 = null;
            intent2 = new Intent(CharList.this, MakeCharakter.class);
            intent2.putExtra("datas", id );
            startActivity(intent2);
        } else {
            while (localCursor.moveToNext()) {
                // Append server response in string
                String name = localCursor.getString(localCursor.getColumnIndex("Name")).toString();
                String kaszt = localCursor.getString(localCursor.getColumnIndex("KASZT")).toString();
                String lvl = localCursor.getString(localCursor.getColumnIndex("LVL")).toString();
                final String idC = localCursor.getString(localCursor.getColumnIndex("ID")).toString();
                switch (i) {
                    case 1:
                        nameV = findViewById(R.id.Name1);
                        nameV.setText(name);
                        lvlV = findViewById(R.id.lvl1);
                        lvlV.setText(lvl);
                        delete = findViewById(R.id.delete1);
                        edit = findViewById(R.id.edit1);
                        edit.setEnabled(true);
                        delete.setEnabled(true);
                        charakter = findViewById(R.id.ork1);
                        switch (kaszt) {
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
                                intent2 = new Intent(CharList.this, mainScreen.class);
                                intent2.putExtra("datas", id + "," + idC);
                                startActivity(intent2);

                            }
                        });
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                userDB.deleteChar(idC);
                                Intent intent2 = null;
                                intent2 = new Intent(CharList.this, CharList.class);
                                intent2.putExtra("datas", id);
                                startActivity(intent2);
                                finish();
                            }
                        });
                        break;
                    case 2:
                        nameV = findViewById(R.id.Name2);
                        nameV.setText(name);
                        lvlV = findViewById(R.id.lvl2);
                        lvlV.setText(lvl);
                        delete = findViewById(R.id.delete2);
                        edit = findViewById(R.id.edit2);
                        edit.setEnabled(true);
                        delete.setEnabled(true);
                        charakter = findViewById(R.id.ork2);
                        switch (kaszt) {
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
                                intent2 = new Intent(CharList.this, mainScreen.class);
                                intent2.putExtra("datas", id + "," + idC);
                                startActivity(intent2);

                            }
                        });
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                userDB.deleteChar(idC);
                                Intent intent2 = null;
                                intent2 = new Intent(CharList.this, CharList.class);
                                intent2.putExtra("datas", id);
                                startActivity(intent2);
                                finish();
                            }
                        });
                        break;
                    case 3:
                        nameV = findViewById(R.id.Name3);
                        nameV.setText(name);
                        lvlV = findViewById(R.id.lvl3);
                        lvlV.setText(lvl);
                        delete = findViewById(R.id.delete3);
                        edit = findViewById(R.id.edit3);
                        edit.setEnabled(true);
                        delete.setEnabled(true);
                        charakter = findViewById(R.id.ork3);
                        switch (kaszt) {
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
                                intent2 = new Intent(CharList.this, mainScreen.class);
                                intent2.putExtra("datas", id + "," + idC);
                                startActivity(intent2);

                            }
                        });
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                userDB.deleteChar(idC);
                                Intent intent2 = null;
                                intent2 = new Intent(CharList.this, CharList.class);
                                intent2.putExtra("datas", id);
                                startActivity(intent2);
                                finish();
                            }
                        });
                        break;
                    case 4:
                        nameV = findViewById(R.id.Name4);
                        nameV.setText(name);
                        lvlV = findViewById(R.id.lvl4);
                        lvlV.setText(lvl);
                        delete = findViewById(R.id.delete4);
                        edit = findViewById(R.id.edit4);
                        edit.setEnabled(true);
                        delete.setEnabled(true);
                        charakter = findViewById(R.id.ork4);
                        switch (kaszt) {
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
                                intent2 = new Intent(CharList.this, mainScreen.class);
                                intent2.putExtra("datas", id + "," + idC);
                                startActivity(intent2);

                            }
                        });
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                userDB.deleteChar(idC);
                                Intent intent2 = null;
                                intent2 = new Intent(CharList.this, CharList.class);
                                intent2.putExtra("datas", id);
                                startActivity(intent2);
                                finish();
                            }
                        });
                        break;
                    case 5:
                        nameV = findViewById(R.id.Name5);
                        nameV.setText(name);
                        lvlV = findViewById(R.id.lvl5);
                        lvlV.setText(lvl);
                        delete = findViewById(R.id.delete5);
                        edit = findViewById(R.id.edit5);
                        edit.setEnabled(true);
                        delete.setEnabled(true);
                        charakter = findViewById(R.id.ork5);
                        switch (kaszt) {
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
                                intent2 = new Intent(CharList.this, mainScreen.class);
                                intent2.putExtra("datas", id + "," + idC);
                                startActivity(intent2);

                            }
                        });
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                userDB.deleteChar(idC);
                                Intent intent2 = null;
                                intent2 = new Intent(CharList.this, CharList.class);
                                intent2.putExtra("datas", id);
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
}
