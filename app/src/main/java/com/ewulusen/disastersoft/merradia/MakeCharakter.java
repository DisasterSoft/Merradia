package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MakeCharakter extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    databaseHelper userDB;
    TextView str,agi,dex,def,con,inte,ref,luck,ac,mc,dmg,move,point,name,hp,mana;
    Button strm,strp,agim,agip,dexm,dexp,defm,defp,conm,conp,intem,intep,refm,refp,luckm,luckp,save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_charakter);
        parkereso();
        intent = getIntent();
        id = intent.getStringExtra("datas");
        userDB = new databaseHelper(this);
        Random r = new Random();
        int points = r.nextInt(48 - 6) + 6;
        point.setText("points:"+points);
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
     def=findViewById(R.id.def);
     agi=findViewById(R.id.agi);
     con=findViewById(R.id.con);
     inte=findViewById(R.id.inte);
     ref=findViewById(R.id.ref);
     luck=findViewById(R.id.luck);
     ac=findViewById(R.id.ac);
     mc=findViewById(R.id.mc);
     dmg=findViewById(R.id.dmg);
     move=findViewById(R.id.move);
     name=findViewById(R.id.name);
     hp=findViewById(R.id.hp);
     mana=findViewById(R.id.mana);
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
     luckm=findViewById(R.id.luckp);
     luckp=findViewById(R.id.luckp);
     refm=findViewById(R.id.refm);
     refp=findViewById(R.id.refp);
     save=findViewById(R.id.saves);

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
        int pointsz=0;
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
        }
    }
}
