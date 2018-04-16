package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class Battle extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    public static String ids;
    DatabaseHelper userDB;
    TextView str,agi,dex,def,con,inte,ref,luck,ac,mc,dmg,move,point,name,hp,mana;
    TextView estr,eagi,edex,edef,econ,einte,eref,eluck,eac,emc,edmg,emove,ename,ehp,emana;
    Button strm,strp,agim,agip,dexm,dexp,defm,defp,conm,conp,intem,intep,refm,refp,luckm,luckp,save;
    int stri,hpi,mci,aci,movei,manai,dmgi,agii,defi,dexi,intei,coni,refi,lucki,lvli;
    int estri,ehpi,emci,eaci,emanai,edmgi,eagii,edefi,edexi,eintei,econi,erefi,elucki,emovi;
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
        parkereso(localCursor);
        getEnemy();
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
        Log.d("name",names);
        kaszt = Integer.parseInt(cursore.getString(cursore.getColumnIndex("KASZT")).toString());
        int osszeg = 0;
        osszeg = intei + defi;
        mci = osszeg;
        osszeg = intei * 10;
        manai = osszeg;
        osszeg = refi + lucki;
        movei = osszeg;
        if (kaszt == 3) {
            osszeg = dexi;
        } else {
            osszeg = stri;
        }
        dmgi = osszeg;
        osszeg = coni + defi;
        aci = osszeg;
        name=findViewById(R.id.yourName);
        hp=findViewById(R.id.yhp);
        mc=findViewById(R.id.ymc);
        ac=findViewById(R.id.yAC);
        mana=findViewById(R.id.mana);
        dmg=findViewById(R.id.yDMG);
        name.setText(names);
        hp.setText(Integer.toString(hpi));
        mc.setText(Integer.toString(mci));
        ac.setText(Integer.toString(aci));
        mana.setText(Integer.toString(manai));
        dmg.setText(Integer.toString(dmgi));




    }
    public void getEnemy()
    {
        String[] irany={"Fair","Elf Archer","Blue Fair","Elf Warrior","Green Fair","Elf Mage",
                "Knight","Cooper Knight","Golden Knight","Common Ork","Pirate Ork","Expert Ork","Wizzard",
                "Fire Wizzard","Ice Wizzard","Green Troll","Gray Troll","Brown Troll","Amazon Warrior",
                "Amazon Rouge","Amazon Archer"};
        Random rand = new Random();
        int k = (rand.nextInt(21));
       String  enames=irany[k];
        estri=rand.nextInt(20)+lvli;
        eagii=rand.nextInt(20)+lvli;
        econi=rand.nextInt(20)+lvli;
        edexi=rand.nextInt(20)+lvli;
        edefi=rand.nextInt(20)+lvli;
        elucki=rand.nextInt(20)+lvli;
        eintei=rand.nextInt(20)+lvli;
        erefi=rand.nextInt(20)+lvli;
        int osszeg = 0;
        osszeg = estri + econi + edefi;
        ehpi = osszeg;
        osszeg = eintei + edefi;
        emci = osszeg;
        osszeg = eintei * 10;
        emanai = osszeg;
        osszeg = erefi + elucki;
        emovi = osszeg;
        osszeg = estri;
        edmgi = osszeg;
        osszeg = econi + edefi;
        eaci = osszeg;
        ename=findViewById(R.id.ename);
        ehp=findViewById(R.id.ehp);
        emc=findViewById(R.id.eMC);
        eac=findViewById(R.id.eAC);
        emana=findViewById(R.id.eMana);
        edmg=findViewById(R.id.eDMG);
        ename.setText(enames);
        ehp.setText(Integer.toString(ehpi));
        emc.setText(Integer.toString(emci));
        eac.setText(Integer.toString(eaci));
        emana.setText(Integer.toString(emanai));
        edmg.setText(Integer.toString(edmgi));
    }


}
