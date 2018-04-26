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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class Battle extends AppCompatActivity {
    public static Intent intent;
    public static String id;
    public static String ids;
    DatabaseHelper userDB;
    Spinner magice;
    TextView ac,mc,dmg,name,hp,mana;
    TextView eac,emc,edmg,ename,ehp,emana;
    MediaPlayer sound;
    Button attack,magiceB;
    int stri,hpi,maci,mci,aci,acii,movei,manai,dmgi,dmgii,agii,defi,dexi,intei,coni,refi,lucki,lvli;
    int estri,ehpi,emci,eaci,emanai,edmgi,eagii,edefi,edexi,eintei,econi,erefi,elucki,emovi;
    int kaszt,ekaszt;//1=knight,2=rouge,3=archer,4=ork,5=wizard;
    List<String> items=new ArrayList<String>();
    GifImageView enemy,youChar;
    String names,datas,youID;
    ArrayAdapter<String> adapter;
    List<String> initialList;
    private ArrayAdapter mAdapter;
    ListView display_events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        intent = getIntent();
        datas = intent.getStringExtra("datas");
        String[] elper=  datas.split(",");
        ids=elper[0];
        youID=elper[2];
        datas=youID+","+ids;
        sound = new MediaPlayer();
        hpi=Integer.parseInt(elper[1]);
        userDB = new DatabaseHelper(this);
        Cursor localCursor=userDB.getChar(ids);
        localCursor.moveToNext();
        magiceB=findViewById(R.id.magice);
        parkereso(localCursor);
        getEnemy();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        magice.setAdapter(adapter);
        initialList = new ArrayList<String>();
        display_events=(ListView) findViewById(R.id.evensts);
        addText("C'om lets kill this thing!" + "\n");
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
        lvli = Integer.parseInt(cursore.getString(cursore.getColumnIndex("LVL")).toString());
        acii=Integer.parseInt(cursore.getString(cursore.getColumnIndex("AC")).toString());
        maci=Integer.parseInt(cursore.getString(cursore.getColumnIndex("MAC")).toString());
        dmgii=Integer.parseInt(cursore.getString(cursore.getColumnIndex("DMG")).toString());
        names = cursore.getString(cursore.getColumnIndex("Name")).toString();
       // Log.d("name",names);
        kaszt = Integer.parseInt(cursore.getString(cursore.getColumnIndex("KASZT")).toString());
        int osszeg;
        osszeg = intei + defi+maci;
        mci = osszeg;
        osszeg = intei * 10;
        manai = osszeg;
        osszeg = refi + lucki;
        movei = osszeg;
        if (kaszt == 3) {
            osszeg = dexi+dmgii;
        } else {
            osszeg = stri+dmgii;
        }
        dmgi = osszeg;
        osszeg = coni + defi+acii;
        aci = osszeg;
        name=findViewById(R.id.yourName);
        hp=findViewById(R.id.yhp);
        mc=findViewById(R.id.ymc);
        ac=findViewById(R.id.yAC);
        mana=findViewById(R.id.mana);
        dmg=findViewById(R.id.yDMG);
        name.setText(names);
        display_events=findViewById(R.id.evensts);
        hp.setText(Integer.toString(hpi));
        mc.setText(Integer.toString(mci));
        ac.setText(Integer.toString(aci));
        mana.setText(Integer.toString(manai));
        dmg.setText(Integer.toString(dmgi));
        youChar=findViewById(R.id.you);
        switch (kaszt)
        {
            case 1:
                youChar.setImageResource(R.drawable.allk1);
                break;
            case 2:
                youChar.setImageResource(R.drawable.rougeall);
                break;
            case 3:
                youChar.setImageResource(R.drawable.archerall);
                break;
            case 4:
                youChar.setImageResource(R.drawable.orkall);
                break;
            case 5:
                youChar.setImageResource(R.drawable.wizardall);
                break;
        }
        magice=findViewById(R.id.spinner);
        attack=findViewById(R.id.attack);
        addMagiceItems(kaszt);
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attackEnemy();
            }
        });
        magiceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attackMagice();
            }
        });


    }

    /**
     * Random kiválasztok egy ellenséget, melyet random statokkal töltök fel. képet is hozzá teszem.
     */
    public void getEnemy()
    {
        String[] irany={"Fair","Elf Archer","Blue Fair","Elf Warrior","Green Fair","Elf Mage",
                "Knight","Cooper Knight","Golden Knight","Common Ork","Pirate Ork","Expert Ork","Wizzard",
                "Fire Wizzard","Ice Wizzard","Green Troll","Gray Troll","Brown Troll","Amazon Warrior",
                "Amazon Rouge","Amazon Archer"};
        Random rand = new Random();
        int k = (rand.nextInt(21));
        ekaszt=k;
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
        enemy=findViewById(R.id.enemy);
        switch (k)
        {
            case 0:
                enemy.setImageResource(R.drawable.fairall);
                break;
                case 1:
                enemy.setImageResource(R.drawable.archerall);
                break;
                case 2:
                enemy.setImageResource(R.drawable.bfairall);
                break;
                case 3:
                enemy.setImageResource(R.drawable.elfwarriorall);
                break;
                case 4:
                enemy.setImageResource(R.drawable.gfairall);
                break;
                case 5:
                enemy.setImageResource(R.drawable.elfwaizzardall);
                break;
                case 6:
                enemy.setImageResource(R.drawable.allk1);
                break;
                case 7:
                enemy.setImageResource(R.drawable.cooperknightall);
                break;
                case 8:
                enemy.setImageResource(R.drawable.goldenknightall);
                break;
                case 9:
                enemy.setImageResource(R.drawable.orkall);
                break;
                case 10:
                enemy.setImageResource(R.drawable.pirateorkall);
                break;
                case 11:
                enemy.setImageResource(R.drawable.expertorkall);
                break;
                case 12:
                enemy.setImageResource(R.drawable.wizardall);
                break;
                case 13:
                enemy.setImageResource(R.drawable.firewizzardall);
                break;
                case 14:
                enemy.setImageResource(R.drawable.icewizzardall);
                break;
                case 15:
                enemy.setImageResource(R.drawable.greentrollall);
                break;
                case 16:
                enemy.setImageResource(R.drawable.graytrollall);
                break;
                case 17:
                enemy.setImageResource(R.drawable.browntrollall);
                break;
                case 18:
                enemy.setImageResource(R.drawable.amazonwarriorall);
                break;
                case 19:
                enemy.setImageResource(R.drawable.rougeall);
                break;
                case 20:
                enemy.setImageResource(R.drawable.amazonarcherall);
                break;

        }
    }

    /**
     * az adott kaszthoz tartozó varázslat neveket adja vissza
     * @param id
     */
    public void addMagiceItems(int id)
    {
        items.add("Select Magice!");
        String[] nevek=userDB.getMagicName(id);
       // Log.d("nevek",nevek[1]);
                items.add(nevek[0]);
                items.add(nevek[1]);
                items.add(nevek[2]);
                items.add(nevek[3]);

        }

    /**
     * egyy gyors 20 oldaló kockadobást szimulálva aphatfinder szerepjáték
     * harcrendszerét alapúlvéve kiszámolja hogy eltalálja e a szörnyet
     * és ha igen akkor mekkora csapás mér rá.
     */
    public void attackEnemy()
    {
        magiceB.setEnabled(false);
        attack.setEnabled(false);
        int dmg=0;
        Random rand = new Random();
        int k = (rand.nextInt(20)+1);
        if(k==20)
        {

            k = (rand.nextInt(20)+1);
            k=k+dmgi;
            if(k>eaci)
            {
                dmg=(rand.nextInt(dmgi)+1)*2;
            }
            else
            {
                dmg=(rand.nextInt(dmgi)+1);
            }
        }
        else
        {
            k=k+dmgi;
            if(k>eaci)
            {
                dmg=(rand.nextInt(dmgi)+1);
            }
            else
            {
                addText(getString(R.string.next_to));
            }
        }
        int folytat=0;
        if(dmg>0)
        {
            charAnime(kaszt,"P","a");
            addText(getString(R.string.you_hit)+" and do "+dmg+" dmg");
            ehpi=ehpi-dmg;
            charAnime(ekaszt,"E","h");
            if(ehpi<=0)
            {
                folytat=1;
                youWin();
            }
            ehp.setText(Integer.toString(ehpi));
        }
        if(folytat==0){
        final Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                enemyAttack();
            }
            }, 2000);
        }
    }
    public void enemyAttack() {
        magiceB.setEnabled(true);
        attack.setEnabled(true);
        int dmg = 0;
        Random rand = new Random();
        int k = 0;
        k = (rand.nextInt(18) + 1);
        if (k % 7 == 0) {
            attackEnemyMagice();

        } else {
            k = (rand.nextInt(20) + 1);
            if (k == 20) {

                k = (rand.nextInt(20) + 1);
                k = k + edmgi;
                if (k > aci) {
                    dmg = (rand.nextInt(edmgi) + 1) * 2;
                } else {
                    dmg = (rand.nextInt(edmgi) + 1);
                }
            } else {
                k = k + edmgi;
                if (k > aci) {
                    dmg = (rand.nextInt(edmgi) + 1);
                } else {
                    addText(getString(R.string.next_toe));
                }
            }
            if (dmg > 0) {
                charAnime(ekaszt, "E", "a");
                addText(getString(R.string.enemy_hit) + " and do " + dmg + " dmg");
                charAnime(kaszt, "P", "h");
                hpi = hpi - dmg;
                if (hpi < 1) {
                    youLose();
                }
                hp.setText(Integer.toString(hpi));
            }

        }
    }
    public void attackEnemyMagice()
    {

            if (emanai < (emanai - 3)) {
                addText(getString(R.string.low_mana));
            }
                //levonjuk a manát
                emanai = emanai - 3;
                emana.setText(Integer.toString(emanai));
                //ha mi vagyunk a célpont, akkor healelődünk
        if(sound.isPlaying())
        {
            sound.reset();
        }

        try {
            sound.reset();
            AssetFileDescriptor afd;
            afd = getAssets().openFd("heal.mp3");
            sound.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            sound.prepare();
            sound.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
                    ehpi = ehpi + 3+eintei;
                    ehp.setText(Integer.toString(ehpi));
                    addText(getString(R.string.enemy_heal)+" "+(3+eintei));

        magiceB.setEnabled(true);
        attack.setEnabled(true);
    }

    /**
     *A vársgombra nyomva adott varázslatot süt el az ellenfél felé
     *
     */
    public void attackMagice() {

        int folytat=0;
        String aMagice = magice.getSelectedItem().toString();
        //ha nem választott ki  semmit akkor kap 1 üzenet
        if (aMagice.equals("Select Magice!") || aMagice.equals("")) {
            addText(getString(R.string.select_magice).toString());
        } else {
            magiceB.setEnabled(false);
            attack.setEnabled(false);
            //megkapjuka varázs tulajdonságait vesszővel elválasztva
            String theMagice = userDB.getMagicByName(aMagice);
            Log.d("varázs",theMagice);
            String[] magiceSplit = theMagice.split(",");
            //ha kevesebb manánk van mint amibe kerül a varázs kap 1 üzit
            if (manai - Integer.parseInt(magiceSplit[1])<0) {
                addText(getString(R.string.low_mana));
            } else {
                //levonjuk a manát
                manai = manai - Integer.parseInt(magiceSplit[1]);
                mana.setText(Integer.toString(manai));
                //ha mi vagyunk a célpont, akkor healelődünk
                if (magiceSplit[2].equals("0")) {
                    if(sound.isPlaying())
                    {
                        sound.reset();
                    }

                    try {
                        sound.reset();
                        AssetFileDescriptor afd;
                        afd = getAssets().openFd("heal.mp3");
                        sound.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        sound.prepare();
                        sound.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    hpi = hpi + Integer.parseInt(magiceSplit[0])+intei;
                    hp.setText(Integer.toString(hpi));
                    addText(getString(R.string.you_heal)+" "+(Integer.parseInt(magiceSplit[0])+intei));
                }
                //ha az ellenfél a célpont
                if (magiceSplit[2].equals("1")) {
                    Random rand = new Random();
                    //ha fizikai sebzést okoz
                    if (magiceSplit[3].equals("1")) {

                        int y = (rand.nextInt(20) + 1);
                        if (kaszt == 3) {
                            y = y + dexi;
                        } else {
                            y = y + stri;
                        }
                        //ha  túl ütöm a pajzsát akkor dmg
                        if (y > eaci) {
                            charAnime(kaszt,"P","a");
                            charAnime(ekaszt,"E","h");
                            ehpi = ehpi - (Integer.parseInt(magiceSplit[0])+dmgi);
                            ehp.setText(Integer.toString(ehpi));
                            addText(getString(R.string.you_cast_magice)+(Integer.parseInt(magiceSplit[0])+dmgi));
                            if(ehpi<=0)
                            {
                                folytat=1;
                                youWin();
                            }
                        }
                        else
                        {
                            addText(getString(R.string.next_to));
                        }
                    }
                    //ha varázs sebzést okoz
                    if (magiceSplit[3].equals("0")) {

                        int y = (rand.nextInt(20) + 1);

                            y = y + intei;

                        //ha  túl ütöm a pajzsát akkor dmg
                        if (y > emci) {
                            if(sound.isPlaying())
                            {
                                sound.reset();
                            }

                            try {
                                sound.reset();
                                AssetFileDescriptor afd;
                                afd = getAssets().openFd("spell.wav");
                                sound.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                                sound.prepare();
                                sound.start();
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            charAnime(kaszt,"P","a");
                            charAnime(ekaszt,"E","h");
                                    ehpi = ehpi - (Integer.parseInt(magiceSplit[0])+intei);
                            ehp.setText(Integer.toString(ehpi));
                            addText(getString(R.string.you_cast_magice)+(Integer.parseInt(magiceSplit[0])+intei));
                            if(ehpi<=0)
                            {
                                folytat=1;
                                youWin();
                            }
                        }
                        else
                        {
                            addText(getString(R.string.next_to));
                        }
                    }

                }
                if(folytat==0){
                    final Handler mHandler = new Handler();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            enemyAttack();
                        }
                    }, 2000);
                }
            }
        }
    }

    /**
     * adott szöveget adja hozzá a lenti listanézethez.
     * @param szoveg
     */
    public void addText(String szoveg) {
        initialList.add(szoveg);
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, initialList);
        mAdapter.notifyDataSetChanged();
        display_events.setAdapter(mAdapter);
        display_events.setSelection(mAdapter.getCount()-1);
    }
    public void youWin()
    {
        Random rand = new Random();
        int k = (rand.nextInt(400)+1);
        userDB.chesFound(ids,k);
        int z = (rand.nextInt(60))+1*lvli;
       int ret= userDB.addXP(ids,z);
       if(ret==0) {
           Toast.makeText(Battle.this, getString(R.string.you_win) + " you get " + k + " Trefu and " + z + " xp", Toast.LENGTH_LONG).show();
       }
       else
       {
           Toast.makeText(Battle.this, getString(R.string.you_win) + " you get " + k + " Trefu and " + z + " xp"+" LVL UP! you get 5 point skillpoint", Toast.LENGTH_LONG).show();
       }
        Intent intent2 = null;
        intent2 = new Intent(Battle.this, mainScreen.class);
        intent2.putExtra("datas", datas);
        startActivity(intent2);
        finish();
    }
    public void youLose()
    {
        userDB.deleteChar(ids);
        Toast.makeText(Battle.this, R.string.you_lose, Toast.LENGTH_LONG).show();
        Intent intent2 = null;
        intent2 = new Intent(Battle.this, CharList.class);
        intent2.putExtra("datas", youID);
        startActivity(intent2);
        finish();
    }

    /**
     * változtatja az animációt ahhoz képest hogy mi történik
     * @param id melyik dolgot használja
     * @param who ki fogja használni
     * @param action mit csinál
     */
    public void charAnime(final int id, String who, String action) {
        if (who.equals("P")) {
            switch (action) {
                case "a":
                    if(sound.isPlaying())
                    {
                        sound.reset();
                    }

                    try {
                        sound.reset();
                        AssetFileDescriptor afd;
                        afd = getAssets().openFd("attack.wav");
                        sound.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        sound.prepare();
                        sound.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    switch (id) {
                        case 1:
                            youChar.setImageResource(R.drawable.k1attak);
                            break;
                        case 2:
                            youChar.setImageResource(R.drawable.rougeattack);
                            break;
                        case 3:
                            youChar.setImageResource(R.drawable.archerfight);
                            break;
                        case 4:
                            youChar.setImageResource(R.drawable.orkattack);
                            break;
                        case 5:
                            youChar.setImageResource(R.drawable.wizzardattack);
                            break;
                    }
                    break;
                case "h":
                    if(sound.isPlaying())
                    {
                        sound.reset();
                    }

                    try {
                        sound.reset();
                        AssetFileDescriptor afd;
                        afd = getAssets().openFd("hurt1.wav");
                        sound.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        sound.prepare();
                        sound.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    switch (id) {
                        case 1:
                            youChar.setImageResource(R.drawable.k1hurt);
                            break;
                        case 2:
                            youChar.setImageResource(R.drawable.rougehurt);
                            break;
                        case 3:
                            youChar.setImageResource(R.drawable.archerhurt);
                            break;
                        case 4:
                            youChar.setImageResource(R.drawable.orkhurt);
                            break;
                        case 5:
                            youChar.setImageResource(R.drawable.wizzardhurt);
                            break;
                    }
                    break;
            }
            final Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (id) {
                        case 1:
                            youChar.setImageResource(R.drawable.allk1);
                            break;
                        case 2:
                            youChar.setImageResource(R.drawable.rougeall);
                            break;
                        case 3:
                            youChar.setImageResource(R.drawable.archerall);
                            break;
                        case 4:
                            youChar.setImageResource(R.drawable.orkall);
                            break;
                        case 5:
                            youChar.setImageResource(R.drawable.wizardall);
                            break;
                    }
                }
            }, 500);

        }
        if (who.equals("E")) {
            switch (action) {
                case "a":
                    if(sound.isPlaying())
                    {
                        sound.reset();
                    }

                    try {
                        sound.reset();
                        AssetFileDescriptor afd;
                        afd = getAssets().openFd("attack.wav");
                        sound.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        sound.prepare();
                        sound.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    switch (id) {
                        case 0:
                            enemy.setImageResource(R.drawable.fairatttack);
                            break;
                        case 1:
                            enemy.setImageResource(R.drawable.archerfight);
                            break;
                        case 2:
                            enemy.setImageResource(R.drawable.bfairattack);
                            break;
                        case 3:
                            enemy.setImageResource(R.drawable.elfwarriorattack);
                            break;
                        case 4:
                            enemy.setImageResource(R.drawable.gfairattaxk);
                            break;
                        case 5:
                            enemy.setImageResource(R.drawable.elfwaizzardattack);
                            break;
                        case 6:
                            enemy.setImageResource(R.drawable.k1attak);
                            break;
                        case 7:
                            enemy.setImageResource(R.drawable.cooperknightattack);
                            break;
                        case 8:
                            enemy.setImageResource(R.drawable.goldenknightattack);
                            break;
                        case 9:
                            enemy.setImageResource(R.drawable.orkattack);
                            break;
                        case 10:
                            enemy.setImageResource(R.drawable.pirateorkattack);
                            break;
                        case 11:
                            enemy.setImageResource(R.drawable.expertorkattack);
                            break;
                        case 12:
                            enemy.setImageResource(R.drawable.wizzardattack);
                            break;
                        case 13:
                            enemy.setImageResource(R.drawable.firewizzardattack);
                            break;
                        case 14:
                            enemy.setImageResource(R.drawable.icewizzardattack);
                            break;
                        case 15:
                            enemy.setImageResource(R.drawable.greentrollattack);
                            break;
                        case 16:
                            enemy.setImageResource(R.drawable.graytrollattack);
                            break;
                        case 17:
                            enemy.setImageResource(R.drawable.browntrollattack);
                            break;
                        case 18:
                            enemy.setImageResource(R.drawable.amazonwarriorattack);
                            break;
                        case 19:
                            enemy.setImageResource(R.drawable.rougeattack);
                            break;
                        case 20:
                            enemy.setImageResource(R.drawable.amazonarcherattack);
                            break;

                    }
                    break;
                case "h":
                    if(sound.isPlaying())
                    {
                        sound.reset();
                    }

                    try {
                        sound.reset();
                        AssetFileDescriptor afd;
                        afd = getAssets().openFd("hurt2.wav");
                        sound.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        sound.prepare();
                        sound.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    switch (id) {
                        case 0:
                            enemy.setImageResource(R.drawable.fairhurt);
                            break;
                        case 1:
                            enemy.setImageResource(R.drawable.archerhurt);
                            break;
                        case 2:
                            enemy.setImageResource(R.drawable.bfairhurt);
                            break;
                        case 3:
                            enemy.setImageResource(R.drawable.elfwarriorhurt);
                            break;
                        case 4:
                            enemy.setImageResource(R.drawable.gfairhurt);
                            break;
                        case 5:
                            enemy.setImageResource(R.drawable.elfwaizzardhurt);
                            break;
                        case 6:
                            enemy.setImageResource(R.drawable.k1hurt);
                            break;
                        case 7:
                            enemy.setImageResource(R.drawable.cooperknighthurt);
                            break;
                        case 8:
                            enemy.setImageResource(R.drawable.goldenknighthurt);
                            break;
                        case 9:
                            enemy.setImageResource(R.drawable.orkhurt);
                            break;
                        case 10:
                            enemy.setImageResource(R.drawable.pirateorkhurt);
                            break;
                        case 11:
                            enemy.setImageResource(R.drawable.expertorkhurt);
                            break;
                        case 12:
                            enemy.setImageResource(R.drawable.wizzardhurt);
                            break;
                        case 13:
                            enemy.setImageResource(R.drawable.firewizzardhurt);
                            break;
                        case 14:
                            enemy.setImageResource(R.drawable.icewizzardhurt);
                            break;
                        case 15:
                            enemy.setImageResource(R.drawable.greentrollhurt);
                            break;
                        case 16:
                            enemy.setImageResource(R.drawable.graytrollhurt);
                            break;
                        case 17:
                            enemy.setImageResource(R.drawable.browntrollhurt);
                            break;
                        case 18:
                            enemy.setImageResource(R.drawable.amazonwarriorhurt);
                            break;
                        case 19:
                            enemy.setImageResource(R.drawable.rougehurt);
                            break;
                        case 20:
                            enemy.setImageResource(R.drawable.amazonarcherhurt);
                            break;

                    }
                    break;
            }
                    final Handler mHandler = new Handler();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            switch (id) {
                                case 0:
                                    enemy.setImageResource(R.drawable.fairall);
                                    break;
                                case 1:
                                    enemy.setImageResource(R.drawable.archerall);
                                    break;
                                case 2:
                                    enemy.setImageResource(R.drawable.bfairall);
                                    break;
                                case 3:
                                    enemy.setImageResource(R.drawable.elfwarriorall);
                                    break;
                                case 4:
                                    enemy.setImageResource(R.drawable.gfairall);
                                    break;
                                case 5:
                                    enemy.setImageResource(R.drawable.elfwaizzardall);
                                    break;
                                case 6:
                                    enemy.setImageResource(R.drawable.allk1);
                                    break;
                                case 7:
                                    enemy.setImageResource(R.drawable.cooperknightall);
                                    break;
                                case 8:
                                    enemy.setImageResource(R.drawable.goldenknightall);
                                    break;
                                case 9:
                                    enemy.setImageResource(R.drawable.orkall);
                                    break;
                                case 10:
                                    enemy.setImageResource(R.drawable.pirateorkall);
                                    break;
                                case 11:
                                    enemy.setImageResource(R.drawable.expertorkall);
                                    break;
                                case 12:
                                    enemy.setImageResource(R.drawable.wizardall);
                                    break;
                                case 13:
                                    enemy.setImageResource(R.drawable.firewizzardall);
                                    break;
                                case 14:
                                    enemy.setImageResource(R.drawable.icewizzardall);
                                    break;
                                case 15:
                                    enemy.setImageResource(R.drawable.greentrollall);
                                    break;
                                case 16:
                                    enemy.setImageResource(R.drawable.graytrollall);
                                    break;
                                case 17:
                                    enemy.setImageResource(R.drawable.browntrollall);
                                    break;
                                case 18:
                                    enemy.setImageResource(R.drawable.amazonwarriorall);
                                    break;
                                case 19:
                                    enemy.setImageResource(R.drawable.rougeall);
                                    break;
                                case 20:
                                    enemy.setImageResource(R.drawable.amazonarcherall);
                                    break;

                            }
                        }
                    }, 500);


        }
    }
}
