package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class Figt_Field extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    public static String ids;
    DatabaseHelper userDB;
    TextView datak;
    GifImageView a1, a2, a3, a4, a5, b1, b2, b3, b4, b5, c1, c2, c3, c4, c5, d1, d2, d3, d4, d5, e1, e2, e3, e4, e5,mainChar;
    GifImageView[] filds = new GifImageView[27];
    int stri, ac, mc, dmg, move, hp, mana;
    int[][] field=new int[6][6];
    int agii;
    int defi;
    int dexi;
    int intei;
    int coni;
    int refi;
    int lucki;
    int pointsz = 0;
    int kaszt;//1=knight,2=rouge,3=archer,4=ork,5=wizard;
    String names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figt__field);
        intent = getIntent();
        names = intent.getStringExtra("datas");
        String[] elper = names.split(",");
        id = elper[0];
        ids = elper[1];
        userDB = new DatabaseHelper(this);
        Cursor localCursor = userDB.getChar(ids);
        localCursor.moveToNext();
        parkereso(localCursor);
        makeField();
        drawField();
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
        names = cursore.getString(cursore.getColumnIndex("Name")).toString();
        kaszt = Integer.parseInt(cursore.getString(cursore.getColumnIndex("KASZT")).toString());
       filds[1]= a1 = findViewById(R.id.a1);
        filds[2] =a2 = findViewById(R.id.a2);
        filds[3] =a3 = findViewById(R.id.a3);
        filds[4] =a4 = findViewById(R.id.a4);
        filds[5] =a5 = findViewById(R.id.a5);
        filds[6] =b1 = findViewById(R.id.b1);
        filds[7] =b2 = findViewById(R.id.b2);
        filds[8]=b3 = findViewById(R.id.b3);
        filds[9]=b4 = findViewById(R.id.b4);
        filds[10]=b5 = findViewById(R.id.b5);
        filds[11]=c1 = findViewById(R.id.c1);
        filds[12]= c2 = findViewById(R.id.c2);
        filds[13]=c3 = findViewById(R.id.c3);
        filds[14]= c4 = findViewById(R.id.c4);
        filds[15]=c5 = findViewById(R.id.c5);
        filds[16]=d1 = findViewById(R.id.d1);
        filds[17]=d2 = findViewById(R.id.d2);
        filds[18]=d3 = findViewById(R.id.d3);
        filds[19]=d4 = findViewById(R.id.d4);
        filds[20]=d5 = findViewById(R.id.d5);
        filds[21]=e1 = findViewById(R.id.e1);
        filds[22]=e2 = findViewById(R.id.e2);
        filds[23]=e3 = findViewById(R.id.e3);
        filds[24] =e4 = findViewById(R.id.e4);
        filds[25]= e5 = findViewById(R.id.e5);
        mainChar=findViewById(R.id.mainChar);
        switch (kaszt) {
            case 1:
                mainChar.setImageResource(R.drawable.allk1);
                break;
            case 2:
                mainChar.setImageResource(R.drawable.rougeall);
                break;
            case 3:
                mainChar.setImageResource(R.drawable.archerall);
                break;
            case 4:
                mainChar.setImageResource(R.drawable.orkall);
                break;
            case 5:
                mainChar.setImageResource(R.drawable.wizardall);
                break;
        }
        szamol();
    }

    public void szamol() {
        int osszeg = 0;
        osszeg = stri + coni + defi;
        hp = osszeg;
        osszeg = intei + defi;
        mc = osszeg;
        osszeg = intei * 10;
        mana = osszeg;
        osszeg = refi + lucki;
        move = osszeg;
        if (kaszt == 3) {
            osszeg = dexi;
        } else {
            osszeg = stri;
        }
        dmg = osszeg;
        osszeg = coni + defi;
        ac = osszeg;

    }

    /**
     * léétrehozom a pályát egy 5x5 ös mátrixon
     * jelek: 0=fű,1=player,2=enemy,3=csapda,4=chest
     */
    public void makeField() {
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                if (i == 1 && j == 1) {
                    field[i][j] = 1;
                } else if (i == 5 && j == 5) {
                    field[i][j] = 2;
                } else {
                    Random r = new Random();
                    pointsz = r.nextInt(100);
                    if (pointsz % 34 == 0) {
                        field[i][j] = 4;

                    } else if (pointsz % 20 == 0) {
                        field[i][j] = 3;
                    } else {
                        field[i][j] = 0;
                    }
                }

            }
        }
    }

    public void drawField() {
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                int elem = (i * 5) + j;
               if(field[i][j]==1) {
                   switch (kaszt) {
                       case 1:
                       filds[elem].setImageResource(R.drawable.allk1);
                       break;
                       case 2:
                       filds[elem].setImageResource(R.drawable.rougeall);
                       break;
                       case 3:
                       filds[elem].setImageResource(R.drawable.archerall);
                       break;
                       case 4:
                       filds[elem].setImageResource(R.drawable.orkall);
                       break;
                       case 5:
                       filds[elem].setImageResource(R.drawable.wizardall);
                       break;
                   }
               }
               else
               {
                   filds[elem].setImageResource(R.drawable.green);
               }

            }
        }
    }
}

