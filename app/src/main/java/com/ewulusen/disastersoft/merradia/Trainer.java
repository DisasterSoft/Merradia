package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class Trainer extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    public static String idC,seged;
    public static String[] seged_a;
    DatabaseHelper userDB;
    int stri,agii,defi,dexi,intei,coni,refi,lucki,lvli;
    Button estri,eagi,edefi,edexi,eintei,econi,erefi,elucki,save;
    TextView text;
    String names;
    MediaPlayer sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);
        intent = getIntent();
        seged = intent.getStringExtra("datas");
        seged_a=seged.split(",");
        id=seged_a[0];
        idC=seged_a[1];
        sound = new MediaPlayer();
        userDB = new DatabaseHelper(this);
        Cursor localCursor=userDB.getChar(idC);
        localCursor.moveToNext();
        parkereso(localCursor);
    }
    public void parkereso(Cursor cursore) {
        stri = Integer.parseInt(cursore.getString(cursore.getColumnIndex("STR")).toString());
        agii = Integer.parseInt(cursore.getString(cursore.getColumnIndex("AGI")).toString());
        intei = Integer.parseInt(cursore.getString(cursore.getColumnIndex("INTE")).toString());
        defi = Integer.parseInt(cursore.getString(cursore.getColumnIndex("DEF")).toString());
        dexi = Integer.parseInt(cursore.getString(cursore.getColumnIndex("DEX")).toString());
        coni = Integer.parseInt(cursore.getString(cursore.getColumnIndex("CON")).toString());
        refi = Integer.parseInt(cursore.getString(cursore.getColumnIndex("REF")).toString());
        lucki = Integer.parseInt(cursore.getString(cursore.getColumnIndex("LUCK")).toString());
        lvli = Integer.parseInt(cursore.getString(cursore.getColumnIndex("MONEY")).toString());
        names = cursore.getString(cursore.getColumnIndex("Name")).toString();
        estri=findViewById(R.id.str);
        eagi=findViewById(R.id.agi);
        edexi=findViewById(R.id.dex);
        edefi=findViewById(R.id.craft1);
        eintei=findViewById(R.id.inte);
        erefi=findViewById(R.id.ref);
        elucki=findViewById(R.id.luck);
        econi=findViewById(R.id.con);
        save=findViewById(R.id.save);
        text=findViewById(R.id.text);
        text.setText(getString(R.string.seller1)+getString(R.string.youhave)+" "+lvli+" Trefu.");
       estri.setText(getString(R.string.str)+" : "+stri);
       eintei.setText(getString(R.string.inte)+" : "+intei);
       eagi.setText(getString(R.string.agi)+" : "+agii);
       edefi.setText(getString(R.string.def)+" : "+defi);
       erefi.setText(getString(R.string.ref)+" : "+refi);
       edexi.setText(getString(R.string.dex)+" : "+dexi);
       econi.setText(getString(R.string.con)+" : "+coni);
       elucki.setText(getString(R.string.luck)+" : "+lucki);
        estri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lvli-100>-1) {
                    Random rand = new Random();
                    int k = (rand.nextInt(20));
                    if (k % 9 == 0) {
                        if(stri--<0)
                        {}
                        else {
                            stri--;
                        }} else {
                        stri++;
                    }
                    playSound();
                    lvli=lvli-100;
                    text.setText(getString(R.string.seller1)+" You have "+lvli+" Trefu.");
                    estri.setText(getString(R.string.str) + " : " + stri);
                }
                else
                {
                    Toast.makeText(Trainer.this, R.string.no_money, Toast.LENGTH_SHORT).show();
                }
            }
        });
        elucki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lvli-100>-1) {
                    Random rand = new Random();
                    int k = (rand.nextInt(20));
                    if (k % 9 == 0) {
                         if(lucki--<0)
                            {}
                            else {
                             lucki--;
                         }
                    } else {
                        lucki++;
                    }
                    playSound();
                    lvli = lvli - 100;
                    text.setText(getString(R.string.seller1) + " You have " + lvli + " Trefu.");
                    elucki.setText(getString(R.string.luck) + " : " + lucki);
                }
                else
                {
                    Toast.makeText(Trainer.this, R.string.no_money, Toast.LENGTH_SHORT).show();
                }
            }
        });
        econi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(lvli-100>-1) {

                        Random rand = new Random();
                        int k = (rand.nextInt(20));
                        if (k % 9 == 0) {
                            if(coni--<0)
                            {}
                            else{
                            coni--;}
                        } else {
                            coni++;
                        }
                        playSound();
                        lvli = lvli - 100;
                        text.setText(getString(R.string.seller1) + " You have " + lvli + " Trefu.");
                        econi.setText(getString(R.string.con) + " : " + coni);
                    }
                    else
                    {
                        Toast.makeText(Trainer.this, R.string.no_money, Toast.LENGTH_SHORT).show();
                    }
            }
        });
        erefi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lvli-100>-1) {

                    Random rand = new Random();
                    int k = (rand.nextInt(20));
                    if (k % 9 == 0) {
                        if(refi--<0){}else{
                        refi--;}
                    } else {
                        refi++;
                    }
                    playSound();
                    lvli = lvli - 100;
                    text.setText(getString(R.string.seller1) + " You have " + lvli + " Trefu.");
                    erefi.setText(getString(R.string.ref) + " : " + refi);
                }
                else
                {
                    Toast.makeText(Trainer.this, R.string.no_money, Toast.LENGTH_SHORT).show();
                }
            }
        });
        edexi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(lvli-100>-1) {

                        Random rand = new Random();
                        int k = (rand.nextInt(20));
                        if (k % 9 == 0) {
                            if(dexi--<0){}else{dexi--;}
                        } else {
                            dexi++;
                        }
                        playSound();
                        lvli = lvli - 100;
                        text.setText(getString(R.string.seller1) + " You have " + lvli + " Trefu.");
                        edexi.setText(getString(R.string.dex) + " : " + dexi);
                    }
                    else
                    {
                        Toast.makeText(Trainer.this, R.string.no_money, Toast.LENGTH_SHORT).show();
                    }
            }
        });
        edefi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lvli-100>-1) {
                    Random rand = new Random();
                    int k = (rand.nextInt(20));
                    if (k % 9 == 0) {
                        if(defi--<0){}else{
                        defi--;}
                    } else {
                        defi++;
                    }
                    playSound();
                    lvli = lvli - 100;
                    text.setText(getString(R.string.seller1) + " You have " + lvli + " Trefu.");
                    edefi.setText(getString(R.string.def) + " : " + defi);
                }
                else
                {
                    Toast.makeText(Trainer.this, R.string.no_money, Toast.LENGTH_SHORT).show();
                }
            }
        });
        eintei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lvli-100>-1) {
                    Random rand = new Random();
                    int k = (rand.nextInt(20));
                    if (k % 9 == 0) {
                        if(intei--<0){}else{
                        intei--;}
                    } else {
                        intei++;
                    }
                    playSound();
                    lvli = lvli - 100;
                    text.setText(getString(R.string.seller1) + " You have " + lvli + " Trefu.");
                    eintei.setText(getString(R.string.inte) + " : " + intei);
                }
                else
                {
                    Toast.makeText(Trainer.this, R.string.no_money, Toast.LENGTH_SHORT).show();
                }
            }
        });
        eagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lvli-100>-1) {
                    Random rand = new Random();
                    int k = (rand.nextInt(20));
                    if (k % 9 == 0) {
                        if(agii--<0){}else{
                        agii--;}
                    } else {
                        agii++;
                    }
                    playSound();
                    lvli = lvli - 100;
                    text.setText(getString(R.string.seller1) + " You have " + lvli + " Trefu.");
                    eagi.setText(getString(R.string.agi) + " : " + agii);
                }
                else
                {
                    Toast.makeText(Trainer.this, R.string.no_money, Toast.LENGTH_SHORT).show();
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });


    }
    public void saveData() {
        String[] datas={Integer.toString(stri),Integer.toString(agii),
                Integer.toString(defi),Integer.toString(dexi),Integer.toString(intei),
                Integer.toString(coni),Integer.toString(refi),Integer.toString(lucki),
                Integer.toString(lvli),idC};
        userDB.upgradeDataSeller1(datas);
        Intent intent2 = null;
        intent2 = new Intent(Trainer.this, mainScreen.class);
        intent2.putExtra("datas", seged);
        startActivity(intent2);
        finish();
    }
    public void
 playSound()
    {
        if(sound.isPlaying())
        {
            sound.reset();
        }

        try {
            sound.reset();
            AssetFileDescriptor afd;
            afd = getAssets().openFd("ddrink.wav");
            sound.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            sound.prepare();
            sound.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
