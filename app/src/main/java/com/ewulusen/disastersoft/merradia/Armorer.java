package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class Armorer extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    public static String idC,seged;
    public static String[] seged_a;
    DatabaseHelper userDB;
    int stri,stri2,lvli;
    Button estri,emac,save;
    TextView text;
    MediaPlayer sound;
    String names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armorer);
        intent = getIntent();
        seged = intent.getStringExtra("datas");
        //Log.d("ss",seged);
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
        stri = Integer.parseInt(cursore.getString(cursore.getColumnIndex("AC")).toString());
        stri2 = Integer.parseInt(cursore.getString(cursore.getColumnIndex("MAC")).toString());
        lvli = Integer.parseInt(cursore.getString(cursore.getColumnIndex("MONEY")).toString());
        names = cursore.getString(cursore.getColumnIndex("Name")).toString();
        estri=findViewById(R.id.craft1);
        emac=findViewById(R.id.craft2);
        save=findViewById(R.id.save);
        text=findViewById(R.id.text);
        text.setText(getString(R.string.seller3)+getString(R.string.youhave)+" "+lvli+" Trefu.");
        estri.setText(getString(R.string.craftarmor)+" : "+stri);
        estri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lvli-500>-1) {
                    Random rand = new Random();
                    int k = (rand.nextInt(20));
                    if (k % 9 == 0) {
                        if(stri--<0)
                        {}
                        else {
                            stri--;
                        }}
                        else {
                        stri++;
                    }
                    if(sound.isPlaying())
                    {
                        sound.reset();
                    }

                    try {
                        sound.reset();
                        AssetFileDescriptor afd;
                        afd = getAssets().openFd("armorer.wav");
                        sound.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        sound.prepare();
                        sound.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lvli=lvli-500;
                    text.setText(getString(R.string.seller3)+" You have "+lvli+" Trefu.");
                    estri.setText(getString(R.string.craftarmor) + " : " + stri);
                }
                else
                {
                    Toast.makeText(Armorer.this, R.string.no_money, Toast.LENGTH_SHORT).show();
                }
            }
        });
        emac.setText(getString(R.string.craftmarmor)+" : "+stri2);
        emac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lvli-500>-1) {
                    Random rand = new Random();
                    int k = (rand.nextInt(20));
                    if (k % 9 == 0) {
                        if(stri2--<0)
                        {}
                        else {
                            stri2--;
                        }
                    } else {
                        stri2++;
                    }
                    if(sound.isPlaying())
                    {
                        sound.reset();
                    }

                    try {
                    sound.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("armorer.wav");
                    sound.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    sound.prepare();
                    sound.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    lvli=lvli-500;
                    text.setText(getString(R.string.seller3)+" You have "+lvli+" Trefu.");
                    emac.setText(getString(R.string.craftmarmor) + " : " + stri2);
                }
                else
                {
                    Toast.makeText(Armorer.this, R.string.no_money, Toast.LENGTH_SHORT).show();
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
        String[] datas={Integer.toString(stri),Integer.toString(stri2),Integer.toString(lvli),idC};
        userDB.upgradeDataSeller3(datas);
        Intent intent2 = null;
        intent2 = new Intent(Armorer.this, mainScreen.class);
        intent2.putExtra("datas", seged);
        startActivity(intent2);
        finish();
    }
}
