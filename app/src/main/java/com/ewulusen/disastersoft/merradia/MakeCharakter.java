package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class MakeCharakter extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    DatabaseHelper userDB;
    TextView str,agi,dex,def,con,inte,ref,luck,ac,mc,dmg,move,point,name,hp,mana;
    GifImageView kinght,ork,archer,rouge,wizard;
    Button strm,strp,agim,agip,dexm,dexp,defm,defp,conm,conp,intem,intep,refm,refp,luckm,luckp,save;
    int stri;
    int agii;
    int defi;
    int dexi;
    int intei;
    int coni;
    int refi;
    int lucki;
    int pointsz=0;
    int kaszt;//1=knight,2=rouge,3=archer,4=ork,5=wizard;
    String names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_charakter);
        parkereso();
        intent = getIntent();
        id = intent.getStringExtra("datas");
        userDB = new DatabaseHelper(this);
        Random r = new Random();
        pointsz = r.nextInt(48 - 6) + 6;
        point.setText("skillpoints:"+pointsz);
        kaszt=0;
        lisztenerek();
    }
    /**
     * a változókat összekapcsolom a megfelelő gomb/texviewel
     */
    public  void parkereso()
    {
     point=findViewById(R.id.points);
     str=findViewById(R.id.str);
     str.setText(getString(R.string.str)+":0");
     dex=findViewById(R.id.dex);
        dex.setText(getString(R.string.dex)+":0");
     def=findViewById(R.id.craft1);
        def.setText(getString(R.string.def)+":0");
     agi=findViewById(R.id.agi);
        agi.setText(getString(R.string.agi)+":0");
     con=findViewById(R.id.con);
        con.setText(getString(R.string.con)+":0");
     inte=findViewById(R.id.inte);
        inte.setText(getString(R.string.inte)+":0");
     ref=findViewById(R.id.ref);
        ref.setText(getString(R.string.ref)+":0");
     luck=findViewById(R.id.luck);
        luck.setText(getString(R.string.luck)+":0");
     ac=findViewById(R.id.ac);
        ac.setText(getString(R.string.ac)+":0");
     mc=findViewById(R.id.mc);
        mc.setText(getString(R.string.mc)+":0");
     dmg=findViewById(R.id.dmg);
        dmg.setText(getString(R.string.dmg)+":0");
     move=findViewById(R.id.move);
        move.setText(getString(R.string.move)+":0");
     name=findViewById(R.id.name);
        hp=findViewById(R.id.yourName);
        hp.setText(getString(R.string.hp)+":0");
     mana=findViewById(R.id.mana);
        mana.setText(getString(R.string.mana)+":0");
     strm=findViewById(R.id.strm);
     strp=findViewById(R.id.strp);
     dexm=findViewById(R.id.dexm);
     dexp=findViewById(R.id.dexp);
     agim=findViewById(R.id.agim);
     agip=findViewById(R.id.agip);
     defm=findViewById(R.id.defm);
     defp=findViewById(R.id.defp);
     conm=findViewById(R.id.conm);
     conp=findViewById(R.id.conp);
     intem=findViewById(R.id.intem);
     intep=findViewById(R.id.intep);
     luckm=findViewById(R.id.luckm);
     luckp=findViewById(R.id.luckp);
     refm=findViewById(R.id.refm);
     refp=findViewById(R.id.refp);
     save=findViewById(R.id.saves);
    kinght=findViewById(R.id.knight);
    rouge=findViewById(R.id.rouge);
    ork=findViewById(R.id.ork);
    archer=findViewById(R.id.archer);
    wizard=findViewById(R.id.wizard);
    }

    /**
     * Beállítom minden egyes gomb/képnek az eseményét, mi történik ha rákattintok
     * a gombokat id-vel fogom jelőlni amit egy másik fügvény meg kap
     */
    public void lisztenerek()
    {
        strp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statnovel(1);
            }
        });
        agip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statnovel(2);
            }
        });
        dexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statnovel(3);
            }
        });
        defp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statnovel(4);
            }
        });
        conp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statnovel(5);
            }
        });
        intep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statnovel(6);
            }
        });
        refp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statnovel(7);
            }
        });
        luckp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statnovel(8);
            }
        });
        strm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statcsokkent(1);
            }
        });
        agim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statcsokkent(2);
            }
        });
        dexm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statcsokkent(3);
            }
        });
        defm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statcsokkent(4);
            }
        });
        conm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statcsokkent(5);
            }
        });
        intem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statcsokkent(6);
            }
        });
        refm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statcsokkent(7);
            }
        });
        luckm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statcsokkent(8);
            }
        });
        kinght.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kaszt=1;
                Toast.makeText(MakeCharakter.this, R.string.knigt_select, Toast.LENGTH_LONG).show();

            }
        });
        rouge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kaszt=2;
                Toast.makeText(MakeCharakter.this, R.string.rouge_select, Toast.LENGTH_LONG).show();

            }
        });
        archer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kaszt=3;
                Toast.makeText(MakeCharakter.this, R.string.archer_select, Toast.LENGTH_LONG).show();
            }
        });
        ork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kaszt=4;
                Toast.makeText(MakeCharakter.this, R.string.ork_select, Toast.LENGTH_LONG).show();

            }
        });
        wizard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kaszt=5;
                Toast.makeText(MakeCharakter.this, R.string.wiz_select, Toast.LENGTH_LONG).show();

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    /**
     * Paraméterbe megkapott id szerint fogom szétválogatni ,hogym ilyen gombot nyomott az illető.
     * ruton ellőnőrzés fog rajta folytatni ogy akkor érvenyesülhet az akció ha pozitív szám lesz a végeredmény.
     * növeli az adott statot+ kiszámolja mi növelődik ezzel együtt és csökketi az elosztható pontok számát.
     * @param id
     */
    public void statnovel(int id)
    {
        int szam=0;

        String szoveg;
        switch(id)
        {
            case 1:
                szoveg=str.getText().toString();
                String seged[] =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                if(pointsz>=1) {
                    szam++;
                    pointsz--;
                    str.setText(getString(R.string.str) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
                }
                break;
                case 2:
                szoveg=agi.getText().toString();
                 seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                if(pointsz>=1) {
                    szam++;
                    pointsz--;
                    agi.setText(getString(R.string.agi) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
                }
                break;
                case 3:
                szoveg=dex.getText().toString();
                seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                if(pointsz>=1) {
                    szam++;
                    pointsz--;
                   dex.setText(getString(R.string.dex) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
                }
                break;
                case 4:
                szoveg=def.getText().toString();
                 seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                if(pointsz>=1) {
                    szam++;
                    pointsz--;
                    def.setText(getString(R.string.def) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
                }
                break;
                case 5:
                szoveg=con.getText().toString();
                seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                if(pointsz>=1) {
                    szam++;
                    pointsz--;
                    con.setText(getString(R.string.con) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
                }
                break;
                case 6:
                szoveg=inte.getText().toString();
                seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                if(pointsz>=1) {
                    szam++;
                    pointsz--;
                    inte.setText(getString(R.string.inte) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
                }
                break;
                case 7:
                szoveg=ref.getText().toString();
                seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                if(pointsz>=1) {
                    szam++;
                    pointsz--;
                    ref.setText(getString(R.string.ref) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
                }
                break;
                case 8:
                szoveg=luck.getText().toString();
                seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                if(pointsz>=1) {
                    szam++;
                    pointsz--;
                    luck.setText(getString(R.string.luck) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
                }
                break;
        }
        szamol();
    }
    /**
     * Paraméterbe megkapott id szerint fogom szétválogatni ,hogym ilyen gombot nyomott az illető.
     * ruton ellőnőrzés fog rajta folytatni ogy akkor érvenyesülhet az akció ha pozitív szám lesz a végeredmény.
     * csökkenti az adott statot+ kiszámolja mi növelődik ezzel együtt és csökketi az elosztható pontok számát.
     * @param id
     */
    public void statcsokkent(int id)
    {
        int szam=0;

        String szoveg;
        switch(id)
        {
            case 1:
                szoveg=str.getText().toString();
                String seged[] =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                if(szam>0) {
                    szam--;
                    pointsz++;
                    str.setText(getString(R.string.str) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
                }
                break;
                case 2:
                szoveg=agi.getText().toString();
                 seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                    if(szam>0) {
                        szam--;
                        pointsz++;
                        agi.setText(getString(R.string.agi) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
                }
                break;
                case 3:
                szoveg=dex.getText().toString();
                seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                    if(szam>0) {
                        szam--;
                        pointsz++;
                        dex.setText(getString(R.string.dex) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
                }
                break;
                case 4:
                szoveg=def.getText().toString();
                 seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                    if(szam>0) {
                        szam--;
                        pointsz++;
                    def.setText(getString(R.string.def) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
                }
                break;
                case 5:
                szoveg=con.getText().toString();
                seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                    if(szam>0) {
                        szam--;
                        pointsz++;
                        con.setText(getString(R.string.con) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
                }
                break;
                case 6:
                szoveg=inte.getText().toString();
                seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                    if(szam>0) {
                        szam--;
                        pointsz++;
                        inte.setText(getString(R.string.inte) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
                }
                break;
                case 7:
                szoveg=ref.getText().toString();
                seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                    if(szam>0) {
                        szam--;
                        pointsz++;
                    ref.setText(getString(R.string.ref) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
                }
                break;
                case 8:
                szoveg=luck.getText().toString();
                seged =szoveg.split(":");
                szam=Integer.parseInt(seged[1]);
                //Log.d("szám",""+szam);
                szoveg=point.getText().toString();
                seged=szoveg.split(":");
                pointsz=Integer.parseInt(seged[1]);
                    if(szam>0) {
                        szam--;
                        pointsz++;
                    luck.setText(getString(R.string.luck) + ":" + szam);
                    point.setText("points :" + pointsz);
                }
                else
                { Toast.makeText(MakeCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
                }
                break;
        }
        szamol();
    }

    /**
     * minen tulajdonság változtat valamit a mellék tulajdonságokoné s azt
     */
    public void szamol()
    {
        String seged[];
        String szoveg;
        int osszeg=0;
       szoveg=str.getText().toString();
       seged=szoveg.split(":");
       stri=Integer.parseInt(seged[1]);
       szoveg=agi.getText().toString();
       seged=szoveg.split(":");
       agii=Integer.parseInt(seged[1]);
       szoveg=con.getText().toString();
       seged=szoveg.split(":");
       coni=Integer.parseInt(seged[1]);
       szoveg=inte.getText().toString();
       seged=szoveg.split(":");
       intei=Integer.parseInt(seged[1]);
       szoveg=dex.getText().toString();
       seged=szoveg.split(":");
       dexi=Integer.parseInt(seged[1]);
       szoveg=def.getText().toString();
       seged=szoveg.split(":");
       defi=Integer.parseInt(seged[1]);
       szoveg=ref.getText().toString();
       seged=szoveg.split(":");
       refi=Integer.parseInt(seged[1]);
       szoveg=luck.getText().toString();
       seged=szoveg.split(":");
       lucki=Integer.parseInt(seged[1]);
       osszeg=stri+coni+defi;
       hp.setText(getString(R.string.hp)+":"+osszeg);
       osszeg=intei+defi;
       mc.setText(getString(R.string.mc)+":"+osszeg);
       osszeg=intei*10;
       mana.setText(getString(R.string.mana)+":"+osszeg);
       osszeg=refi+lucki;
       move.setText(getString(R.string.move)+":"+osszeg);
       osszeg=stri;
       if(kaszt==3)
       {
           osszeg=dexi;
       }
       else
       {
           osszeg=stri;
       }
       dmg.setText(getString(R.string.dmg)+":"+osszeg);
       osszeg=coni+defi;
       ac.setText(getString(R.string.ac)+":"+osszeg);

    }

    /**
     * elmenti az oldalon látható karakter minden tulajdonságát.
     */
    public void saveData() {
name=findViewById(R.id.name) ;
    if (kaszt == 0) {
            Toast.makeText(MakeCharakter.this, R.string.no_kaszt, Toast.LENGTH_LONG).show();
            if (name.getText().toString().isEmpty())
                Toast.makeText(MakeCharakter.this, R.string.no_name, Toast.LENGTH_LONG).show();
        } else {
            names=name.getText().toString();
            String[] datas={names,Integer.toString(stri),Integer.toString(agii),
                    Integer.toString(defi),Integer.toString(dexi),Integer.toString(intei),
                    Integer.toString(coni),Integer.toString(refi),Integer.toString(lucki),
                    Integer.toString(kaszt),id,Integer.toString(pointsz),"1","100"};
            
                    userDB.saveData(datas);
        Intent intent2 = null;
        intent2 = new Intent(MakeCharakter.this, CharList.class);
        intent2.putExtra("datas", id);
        startActivity(intent2);
        finish();
        }
    }

}
