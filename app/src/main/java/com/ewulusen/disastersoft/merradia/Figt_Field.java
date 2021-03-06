package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class Figt_Field extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    public static String ids;
    DatabaseHelper userDB;
    MediaPlayer sound;
    TextView name;
    GifImageView a1, a2, a3, a4, a5, b1, b2, b3, b4, b5, c1, c2, c3, c4, c5, d1, d2, d3, d4, d5, e1, e2, e3, e4, e5,mainChar;
    GifImageView[] filds = new GifImageView[25];
    int stri, ac, mc, dmg, move, hp, mana;
    int[][] field=new int[6][6];
    int agii,moves;
    int defi;
    int dexi;
    int intei;
    int coni;
    int refi;
    int lucki;
    int pointsz = 0;
    int kaszt;//1=knight,2=rouge,3=archer,4=ork,5=wizard;
    String names;
    List<String> initialList;
    private ArrayAdapter mAdapter;
    ListView display_events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figt__field);
        intent = getIntent();
        names = intent.getStringExtra("datas");
        String[] elper = names.split(",");
        id = elper[0];
        ids = elper[1];
        sound = new MediaPlayer();
        userDB = new DatabaseHelper(this);
        Cursor localCursor = userDB.getChar(ids);
        localCursor.moveToNext();
        initialList = new ArrayList<String>();
        display_events=(ListView) findViewById(R.id.list_);
        addItems(getString(R.string.welcome_fightfield));
        parkereso(localCursor);
        makeField();
        drawField();
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, initialList);
        mAdapter.notifyDataSetChanged();
        display_events.setAdapter(mAdapter);
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
       filds[0]= a1 = findViewById(R.id.a1);
        filds[1] =a2 = findViewById(R.id.a2);
        filds[2] =a3 = findViewById(R.id.a3);
        filds[3] =a4 = findViewById(R.id.a4);
        filds[4] =a5 = findViewById(R.id.a5);
        filds[5] =b1 = findViewById(R.id.b1);
        filds[6] =b2 = findViewById(R.id.b2);
        filds[7]=b3 = findViewById(R.id.b3);
        filds[8]=b4 = findViewById(R.id.b4);
        filds[9]=b5 = findViewById(R.id.b5);
        filds[10]=c1 = findViewById(R.id.c1);
        filds[11]= c2 = findViewById(R.id.c2);
        filds[12]=c3 = findViewById(R.id.c3);
        filds[13]= c4 = findViewById(R.id.c4);
        filds[14]=c5 = findViewById(R.id.c5);
        filds[15]=d1 = findViewById(R.id.d1);
        filds[16]=d2 = findViewById(R.id.d2);
        filds[17]=d3 = findViewById(R.id.d3);
        filds[18]=d4 = findViewById(R.id.d4);
        filds[19]=d5 = findViewById(R.id.d5);
        filds[20]=e1 = findViewById(R.id.e1);
        filds[21]=e2 = findViewById(R.id.e2);
        filds[22]=e3 = findViewById(R.id.e3);
        filds[23] =e4 = findViewById(R.id.e4);
        filds[24]= e5 = findViewById(R.id.e5);
        mainChar=findViewById(R.id.mainChar);
        moves=agii;
        name=findViewById(R.id.charName);
        charAnimation(kaszt);
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
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 0 && j == 0) {
                    field[i][j] = 1;
                } else if (i == 4 && j == 4) {
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

    /**
     * ez rajzolja ki a képernyőre hogy néz ki a pálya.
     */
    public void drawField() {
        name.setText(names+" you have "+moves+" moves");
        for (int i = 0; i < 5; i++) {
           for (int j = 0; j < 5; j++) {
                final int elem = (i * 5) + j;
             //   Log.d("elem",""+field[i][j]) ;
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
               {kiinnen:
               {

                   if (j - 1 > -1) {
                       if (field[i][j - 1] == 1) {
                           getInfo(field[i][j]);
                           filds[elem].setImageResource(R.drawable.green_j);
                           final int finalI4 = i;
                           final int finalJ4 = j;
                           final int finalI5 = i;
                           final int finalJ5 = j;
                           filds[elem].setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   moveCharTo(finalI4, finalJ4, finalI5,(finalJ5 -1),elem);
                               }
                           });
                           break kiinnen;

                       } else {
                           filds[elem].setImageResource(R.drawable.green);

                       }
                   }
                   if (j + 1 < 5) {
                       if (field[i][j + 1] == 1) {
                           getInfo(field[i][j]);
                           filds[elem].setImageResource(R.drawable.green_j);
                           final int finalI = i;
                           final int finalJ = j;
                           final int finalI1 = i;
                           final int finalJ1 = j;
                           filds[elem].setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   moveCharTo(finalI, finalJ, finalI1,(finalJ1 +1),elem);
                               }
                           });
                           break kiinnen;
                       } else {
                           filds[elem].setImageResource(R.drawable.green);

                       }
                   }
                   if (i - 1 > -1) {
                       if (field[i - 1][j] == 1) {
                           getInfo(field[i][j]);
                           filds[elem].setImageResource(R.drawable.green_j);
                           final int finalI2 = i;
                           final int finalJ2 = j;
                           final int finalI3 = i;
                           final int finalJ3 = j;
                           filds[elem].setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   moveCharTo(finalI2, finalJ2,(finalI3 -1), finalJ3,elem);
                               }
                           });
                           break kiinnen;
                       } else {
                           filds[elem].setImageResource(R.drawable.green);

                       }
                   }
                   if (i + 1 < 5) {
                       if (field[i + 1][j] == 1) {
                           getInfo(field[i][j]);
                           filds[elem].setImageResource(R.drawable.green_j);
                           final int finalI6 = i;
                           final int finalJ6 = j;
                           final int finalI7 = i;
                           final int finalJ7 = j;
                           filds[elem].setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   moveCharTo(finalI6, finalJ6,(finalI7 +1), finalJ7,elem);
                               }
                           });
                           break kiinnen;
                       } else {
                           filds[elem].setImageResource(R.drawable.green);
                       }
                   }
               }
               }

            }
        }
    }
    public void moveCharTo(final int i, int j, int x, int y, final int elem)
    {
        moves--;
        if(moves!=-1) {
            if (sound.isPlaying()) {
                sound.reset();
            }

            try {
                sound.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("walk.wav");
                sound.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                sound.prepare();
                sound.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int k = 0; k < 25; k++) {
                filds[k].setOnClickListener(null);
            }


            //      Log.d("moveto","bejött");
            if (field[i][j] == 2) {
                attackTarget();

            } else {
                if (field[i][j] == 4) {
                    Random rand = new Random();
                    int k = (rand.nextInt(300));
                    addItems(getText(R.string.chesFound).toString() + " " + k + " Trefu");
                    userDB.chesFound(ids, k);
                }
                if (field[i][j] == 3) {
                    addItems(getText(R.string.trappFound).toString());
                    Random rand = new Random();
                    int trap = (rand.nextInt(20) + 5);
                    int you = (rand.nextInt(20) + refi);
                    if (you > trap) {
                        addItems(getText(R.string.trappAvoid).toString());
                    } else {
                        int dmg = rand.nextInt(10) + 1;
                        if (sound.isPlaying()) {
                            sound.reset();
                        }

                        try {
                            sound.reset();
                            AssetFileDescriptor afd;
                            afd = getAssets().openFd("trap.wav");
                            sound.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                            sound.prepare();
                            sound.start();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        addItems(getText(R.string.trappHit).toString() + " " + dmg + " dmg");
                        hp = hp - dmg;
                        if (hp < 0) {
                            userDB.deleteChar(ids);
                            Toast.makeText(Figt_Field.this, R.string.you_lose, Toast.LENGTH_LONG).show();
                            Intent intent2 = null;
                            intent2 = new Intent(Figt_Field.this, CharList.class);
                            intent2.putExtra("datas", id);
                            startActivity(intent2);
                            finish();
                        }
                    }
                }
                field[i][j] = 1;
                field[x][y] = 0;
                switch (kaszt) {
                    case 1:
                        mainChar.setImageResource(R.drawable.k1wallk);
                        break;
                    case 2:
                        mainChar.setImageResource(R.drawable.rougeall);
                        break;
                    case 3:
                        mainChar.setImageResource(R.drawable.archerwalk);
                        break;
                    case 4:
                        mainChar.setImageResource(R.drawable.orkwalk);
                        break;
                    case 5:
                        mainChar.setImageResource(R.drawable.wizzardwalk);
                        break;
                }


                final Handler mHandler = new Handler();

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        charAnimation(kaszt);
                        mHandler.removeCallbacks(this);
                    }
                }, 2000);

                filds[elem].setImageResource(R.drawable.green_j);
                drawField();
            }
        }
                if (moves == 0) {
                    enemyMove(5);
                }



    }
    public void charAnimation(int kaszt)
    {
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
    }

    /**
     * véletlen szám eldönti hogy merre mozogjon az ellenfél (ez most egyn agyon buta ellenfél
     * paraméterbe a lépései számát kapja me, ez azért fontos, mert így újra lehet hívni addig míg el nem fogy a lépése
     * @param emove
     */
    public void enemyMove(int emove)
    {

        int[] irany={1,2,3,4};
        Random rand = new Random();
        int k = (rand.nextInt(3));
        int x=0;
        int y=0;
      for (int i = 0; i < 5; i++) {
          for (int j = 0; j < 5; j++) {
              if (field[i][j] == 2) {
                  y = i;
                  x = j;
              }

          }
      }
        if(irany[k]==1) {
            if ((y - 1) > -1) {
                korbeNez((y - 1),x);
                    field[y - 1][x] = 2;
                    field[y][x] = 0;
                    emove--;

            }
        }
        if(irany[k]==2) {
            if ((y + 1) < 5) {
                korbeNez((y + 1),x);
                    field[y + 1][x] = 2;
                    field[y][x] = 0;
                    emove--;

                }
            }
        if(irany[k]==3) {
            if ((x - 1) > -1) {
                korbeNez(y,(x-1));

                    field[y ][x-1] = 2;
                    field[y][x] = 0;
                    emove--;

            }
        }
        if(irany[k]==4) {
            if ((x+ 1)< 5) {
                korbeNez(y,(x+1));
                    field[y ][x+1] = 2;
                    field[y][x] = 0;
                    emove--;

            }
        }
        if(emove>0)
        {
            final Handler mHandler = new Handler();
            final int finalEmove = emove;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
            enemyMove(finalEmove);
                }
        }, 200);
        }
        else {
          addItems(getString(R.string.enemyMove).toString());
          addItems(getString(R.string.you_turn).toString());
            moves = agii;
            drawField();

    }
}

    public void attackTarget()
        {
            Intent intent2 = null;
            intent2 = new Intent(Figt_Field.this, Battle.class);
            String datas;
            datas=ids+","+hp+","+id;
            intent2.putExtra("datas",datas);
            startActivity(intent2);
            finish();
        }
    public void addItems(String szoveg) {
        initialList.add(szoveg);
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, initialList);
        mAdapter.notifyDataSetChanged();
        display_events.setAdapter(mAdapter);
        display_events.setSelection(mAdapter.getCount()-1);
    }
    public void getInfo(int szam)
    {
        switch (szam)
        {
            case 2:
                addItems(getString(R.string.sense2).toString());
            break;
            case 3:
                addItems(getString(R.string.sense3).toString());
            break;
            case 4:
                addItems(getString(R.string.sense4).toString());
            break;
        }

    }
public void korbeNez(int x,int y)
{
   if(field[x][y]==1)
    {
        field[x][y]=0;
        attackTarget();
    }
    if ((x + 1) < 6) {
        if (field[x + 1][y] == 1) {
            field[x + 1][y] = 0;
            attackTarget();
        }
    }
    if ((x - 1) > -1) {
        if (field[x - 1][y] == 1) {
            field[x - 1][y] =0;
            attackTarget();
        }
    }
    if ((y + 1) < 6) {
        if (field[x][y + 1] == 1) {
            field[x][y + 1] =0;
            attackTarget();
        }
    }
        if ((y - 1) > -1) {
            if (field[x][y - 1] == 1) {
                field[x][y - 1] =0;
                attackTarget();
            }
        }


}
}

