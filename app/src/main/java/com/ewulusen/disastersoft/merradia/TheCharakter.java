package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TheCharakter extends AppCompatActivity {
        public static Intent intent;
        public static String id;
        public static String ids;
        DatabaseHelper userDB;
        TextView str,agi,dex,def,con,inte,ref,luck,ac,mc,dmg,move,point,name,hp,mana;
        Button strm,strp,agim,agip,dexm,dexp,defm,defp,conm,conp,intem,intep,refm,refp,luckm,luckp,save;
        int stri,aci,dmgi,maci;
        int agii;
        int defi;
        int dexi;
        int intei;
        int coni;
        int refi;
        int lucki;
        int pointsz=0;
        int kaszt;//1=knight,2=rouge,3=archer,4=ork,5=wizard;
        String names,names1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_the_charakter);
            intent = getIntent();
            names1 = intent.getStringExtra("datas");
          String[] elper=  names1.split(",");
          id=elper[0];
          ids=elper[1];
            userDB = new DatabaseHelper(this);
            Cursor localCursor=userDB.getChar(ids);
            localCursor.moveToNext();
            parkereso(localCursor);
            lisztenerek();
        }
        /**
         * a változókat összekapcsolom a megfelelő gomb/texviewel
         */
        public  void parkereso(Cursor cursore)
        {
            stri=Integer.parseInt(cursore.getString(cursore.getColumnIndex("STR")).toString());
            agii=Integer.parseInt(cursore.getString(cursore.getColumnIndex("AGI")).toString());
            intei=Integer.parseInt(cursore.getString(cursore.getColumnIndex("INTE")).toString());
            defi=Integer.parseInt(cursore.getString(cursore.getColumnIndex("DEF")).toString());
            dexi=Integer.parseInt(cursore.getString(cursore.getColumnIndex("DEX")).toString());
            coni=Integer.parseInt(cursore.getString(cursore.getColumnIndex("CON")).toString());
            refi=Integer.parseInt(cursore.getString(cursore.getColumnIndex("REF")).toString());
            lucki=Integer.parseInt(cursore.getString(cursore.getColumnIndex("LUCK")).toString());
            pointsz=Integer.parseInt(cursore.getString(cursore.getColumnIndex("POINT")).toString());
            kaszt=Integer.parseInt(cursore.getString(cursore.getColumnIndex("KASZT")).toString());
            aci=Integer.parseInt(cursore.getString(cursore.getColumnIndex("AC")).toString());
            maci=Integer.parseInt(cursore.getString(cursore.getColumnIndex("MAC")).toString());
            dmgi=Integer.parseInt(cursore.getString(cursore.getColumnIndex("DMG")).toString());
            names=cursore.getString(cursore.getColumnIndex("Name")).toString();
            point=findViewById(R.id.points);
            point.setText("skillpoints:"+pointsz);
            str=findViewById(R.id.str);
            str.setText(getString(R.string.str)+":"+stri);
            dex=findViewById(R.id.dex);
            dex.setText(getString(R.string.dex)+":"+dexi);
            def=findViewById(R.id.craft1);
            def.setText(getString(R.string.def)+":"+defi);
            agi=findViewById(R.id.agi);
            agi.setText(getString(R.string.agi)+":"+agii);
            con=findViewById(R.id.con);
            con.setText(getString(R.string.con)+":"+coni);
            inte=findViewById(R.id.inte);
            inte.setText(getString(R.string.inte)+":"+intei);
            ref=findViewById(R.id.ref);
            ref.setText(getString(R.string.ref)+":"+refi);
            luck=findViewById(R.id.luck);
            luck.setText(getString(R.string.luck)+":"+lucki);
            ac=findViewById(R.id.ac);
            mc=findViewById(R.id.mc);
            dmg=findViewById(R.id.dmg);
            move=findViewById(R.id.move);
            name=findViewById(R.id.name);
            name.setText(names);
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
            hp=findViewById(R.id.yourName);
            mana=findViewById(R.id.mana);
            save=findViewById(R.id.saves);
            szamol();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
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
                    { Toast.makeText(TheCharakter.this, R.string.no_point1, Toast.LENGTH_LONG).show();
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
            osszeg=intei+defi+maci;
            mc.setText(getString(R.string.mc)+":"+osszeg);
            osszeg=intei*10;
            mana.setText(getString(R.string.mana)+":"+osszeg);
            osszeg=refi+lucki;
            move.setText(getString(R.string.move)+":"+osszeg);
            if(kaszt==3)
            {
                osszeg=dexi+dmgi;
            }
            else
            {
                osszeg=stri +dmgi;
            }
            dmg.setText(getString(R.string.dmg)+":"+osszeg);
            osszeg=coni+defi+aci;
            ac.setText(getString(R.string.ac)+":"+osszeg);

        }

        /**
         * elmenti az oldalon látható karakter minden tulajdonságát.
         */
        public void saveData() {
                String[] datas={Integer.toString(stri),Integer.toString(agii),
                        Integer.toString(defi),Integer.toString(dexi),Integer.toString(intei),
                        Integer.toString(coni),Integer.toString(refi),Integer.toString(lucki),
                       ids,Integer.toString(pointsz)};
                userDB.upgradeData(datas);
                Intent intent2 = null;
                intent2 = new Intent(TheCharakter.this, mainScreen.class);
                intent2.putExtra("datas", names1);
                startActivity(intent2);
                finish();
            }

    }
