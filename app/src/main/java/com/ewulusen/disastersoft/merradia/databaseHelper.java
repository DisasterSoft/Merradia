package com.ewulusen.disastersoft.merradia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.sql.Timestamp;

/**
 * Created by diszterhoft.zoltan on 2018.04.03
 * Ebben a javafájlban fogom létrehozni az adatbázisokat amivel dolgozni fogunk
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * Előszőr is létrhozzuk az összes változót amivel dolgozni fogunk.
     */
    public static final String DatabaseName = "merradiaUsers.db";
    public static final String uTableName = "users_table_Merradia";
    public static final String mTableName = "magice_table_Merradia";
    public static final String cTableName = "char_table_Merradia";
    public DatabaseHelper(Context paramContext)

    {
        super(paramContext, DatabaseName, null, 23);
    }

    /**
     * ez a programrészlet kiszedi az adatbázisból az adott id-hez tartozó nevet
     * @param id
     * @return vissza adja az adott nevet
     */
    public  Cursor getName(String id)
    {
    SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
    String str1 = "SELECT Name FROM "+cTableName+" where ID='"+id+"'";
  //  Log.d("SQL", str1);
    Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
    return localCursor;
    }
    public  Cursor getLvL(String id)
    {
    SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
    String str1 = "SELECT LVL FROM "+cTableName+" where ID='"+id+"'";
  //  Log.d("SQL", str1);
    Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
    return localCursor;
    }
    /**
     * hozzá ad egy sort a felhasználó táblához
     * @param paramString1-email
     * @param paramString2-password
     * @return boolen
     */
    public boolean addData(String paramString1, String paramString2)
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("userName", paramString1);
        localContentValues.put("password", paramString2);
        return localSQLiteDatabase.insert("users_table_Merradia", null, localContentValues) != -1L;
    }
    /**
     * Bejelentkezés/vagy regisztrál egy felhasználót
     * @param paramString1-email
     * @param paramString2-jelszó
     * @return Cursor
     */
    public Cursor login(String paramString1, String paramString2)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
       // Log.d("SQL", paramString2);
        String str1 = "SELECT * FROM users_table_Merradia where userName='"+paramString1+"' and  password='" + paramString2 + "'";
        //Log.d("SQL", str1);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        if (localCursor.getCount() == 0)
        {
            addData(paramString1, paramString2);
            String str2 = "SELECT * FROM users_table_Merradia where userName='"+paramString1+"' and password='" + paramString2 + "'";
            //Log.d("SQL", str2);
            localCursor = localSQLiteDatabase.rawQuery(str2, null);
            ContentValues localContentValues = new ContentValues();
            localContentValues.put("Owner", 1);
            localContentValues.put("Name","teszt");
            localContentValues.put("STR", 0);
            localContentValues.put("AGI", 0);
            localContentValues.put("DEF", 0);
            localContentValues.put("DEX", 0);
            localContentValues.put("INTE", 0);
            localContentValues.put("CON", 0);
            localContentValues.put("REF", 0);
            localContentValues.put("LUCK", 0);
            localContentValues.put("KASZT", 4);
            localContentValues.put("POINT", 32);
            localContentValues.put("LVL", 1);
            localContentValues.put("MONEY", 100);
            localContentValues.put("XP",0);
            localSQLiteDatabase.insert(cTableName, null, localContentValues);
            localContentValues.clear();
        }

        return localCursor;
    }
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+uTableName+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,  userName TEXT, password TEXT)");
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+mTableName+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,  Name TEXT, DMG TEXT, MANA TEXT, TYPE TEXT, TARGET TEXT,KASZT TEXT)");
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+cTableName+"( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Owner TEXT, Name TEXT,  AGI TEXT,   STR TEXT,   DEF TEXT,   CON TEXT," +
                "   REF TEXT,   LUCK TEXT, DEX TEXT,INTE TEXT,KASZT TEXT," +
                "POINT TEXT,LVL TEXT DEFAULT '1',MONEY TEXT,XP TEXT DEFAULT '0',DMG TEXT DEFAULT '0',AC TEXT DEFAULT '0',MAC TEXT DEFAULT '0')");
        fillMagice(paramSQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS "+uTableName);
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS "+cTableName);
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS "+mTableName);
        onCreate(paramSQLiteDatabase);
    }
    /**
     * Ha talált valaki egy ládát a labirintusba ez hozzá adja a karaktere pénzösszegéhez
     * a talált pénzt, 2 paraméterrel a karakter id-je és a kapott pénz összege
     * @param id
     * @param money
     */
    public void chesFound(String id, int money)
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        String str1 = "SELECT * FROM char_table_Merradia where ID='"+id+"'";
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        localCursor.moveToNext();
        int m=Integer.parseInt(localCursor.getString(localCursor.getColumnIndex("MONEY")).toString());
        m=m+money;
        localContentValues.put("MONEY",m);
        localSQLiteDatabase.update(cTableName, localContentValues, "ID =" + id, null);
        localContentValues.clear();
    }
    /**
     * ez a programrészlet kiszedi az ember birtokában lévő karaktereket
     * @param id
     * @return vissza adja az adott ember birtokában lévő karaktereket
     */
    public Cursor getCharacters(String id)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM char_table_Merradia where OWNER='"+id+"'";
       //Log.d("SQL", str1);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        // printDatabaseHelper(id);
        return localCursor;
    }
    public void printDatabaseHelper(String id)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM char_table_Merradia where ID='"+id+"'";
        Cursor cursor = localSQLiteDatabase.rawQuery(str1, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String line="";
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    line=line+(cursor.getString(i).toString())+"->"+cursor.getColumnName(i)+",";
                }

              //  Log.d("tartalom",line);
                cursor.moveToNext();
            }
        }

    }

    /**
     * egy adott id-jű charakterrel tér vissza
     * @param id
     * @return
     */
    public Cursor getChar(String id)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM char_table_Merradia where ID='"+id+"'";
        printDatabaseHelper(id);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        return localCursor;
    }


    /**
     * arrayként megkapott adatokat menti
     * 0=names,1=stri,2=agii,3=defi,4=dexi,5=intei,6=coni,7=refi,8=lucki,9=kaszt,10=id,11=point,12=lvl,13=money
     * @param datas
     */
    public void saveData(String datas[])
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("Owner", datas[10]);
        localContentValues.put("Name", datas[0]);
        localContentValues.put("STR", datas[1]);
        localContentValues.put("AGI", datas[2]);
        localContentValues.put("DEF", datas[3]);
        localContentValues.put("DEX", datas[4]);
        localContentValues.put("INTE", datas[5]);
        localContentValues.put("CON", datas[6]);
        localContentValues.put("REF", datas[7]);
        localContentValues.put("LUCK", datas[8]);
        localContentValues.put("KASZT", datas[9]);
        localContentValues.put("POINT", datas[11]);
        localContentValues.put("LVL", datas[12]);
        localContentValues.put("MONEY", datas[13]);
        localSQLiteDatabase.insert("char_table_Merradia", null, localContentValues);
        localContentValues.clear();
    }
    /**
     * arrayként megkapott adatokkal frissíti a charakterek táblát
     * 0=stri,1=agii,2=defi,3=dexi,4=intei,5=coni,6=refi,7=lucki,8=id,9=point
     * @param datas
     */
    public void upgradeData(String datas[])
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("STR", datas[0]);
        localContentValues.put("AGI", datas[1]);
        localContentValues.put("DEF", datas[2]);
        localContentValues.put("DEX", datas[3]);
        localContentValues.put("INTE", datas[4]);
        localContentValues.put("CON", datas[5]);
        localContentValues.put("REF", datas[6]);
        localContentValues.put("LUCK", datas[7]);
        localContentValues.put("POINT", datas[9]);
        localSQLiteDatabase.update(cTableName, localContentValues, "ID =" + datas[8], null);
        localContentValues.clear();
    }
    public void upgradeDataSeller1(String datas[])
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("STR", datas[0]);
        localContentValues.put("AGI", datas[1]);
        localContentValues.put("DEF", datas[2]);
        localContentValues.put("DEX", datas[3]);
        localContentValues.put("INTE", datas[4]);
        localContentValues.put("CON", datas[5]);
        localContentValues.put("REF", datas[6]);
        localContentValues.put("LUCK", datas[7]);
        localContentValues.put("MONEY", datas[8]);
        localSQLiteDatabase.update(cTableName, localContentValues, "ID =" + datas[9], null);
        localContentValues.clear();
    }
    public void upgradeDataSeller2(String datas[])
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("DMG", datas[0]);
        localContentValues.put("MONEY", datas[1]);
        localSQLiteDatabase.update(cTableName, localContentValues, "ID =" + datas[2], null);
        localContentValues.clear();
    }
    public void upgradeDataSeller3(String datas[])
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("AC", datas[0]);
        localContentValues.put("MAC", datas[1]);
        localContentValues.put("MONEY", datas[2]);
        localSQLiteDatabase.update(cTableName, localContentValues, "ID =" + datas[3], null);
        localContentValues.clear();
    }
    public void deleteChar(String id)
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        localSQLiteDatabase.delete(cTableName, "ID=" + id, null);
    }
    /**
     * feltölti a magice táblát
     * Target 0=me, 1=enemy
     * Type 0=magice 1=phisical
     * KASZT 0=all 1....
     */
    public void fillMagice(SQLiteDatabase paramSQLiteDatabase)
    {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("Name", "First Aid");
        localContentValues.put("DMG","3");
        localContentValues.put("MANA", 5);
        localContentValues.put("TARGET", 0);
        localContentValues.put("TYPE", 0);
        localContentValues.put("KASZT", 0);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Shield Slam");
        localContentValues.put("DMG","2");
        localContentValues.put("MANA", 3);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 1);
        localContentValues.put("KASZT", 1);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Justice hit");
        localContentValues.put("DMG","6");
        localContentValues.put("MANA", 10);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 1);
        localContentValues.put("KASZT", 1);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Heal");
        localContentValues.put("DMG","5");
        localContentValues.put("MANA", 10);
        localContentValues.put("TARGET", 0);
        localContentValues.put("TYPE", 0);
        localContentValues.put("KASZT", 1);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Precizion Shot");
        localContentValues.put("DMG","7");
        localContentValues.put("MANA", 5);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 1);
        localContentValues.put("KASZT", 3);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Double Arrow");
        localContentValues.put("DMG","9");
        localContentValues.put("MANA", 10);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 1);
        localContentValues.put("KASZT", 3);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Shadow Arrow");
        localContentValues.put("DMG","7");
        localContentValues.put("MANA", 8);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 0);
        localContentValues.put("KASZT", 3);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Back Stab");
        localContentValues.put("DMG","4");
        localContentValues.put("MANA", 5);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 1);
        localContentValues.put("KASZT", 2);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Dancing Blade");
        localContentValues.put("DMG","8");
        localContentValues.put("MANA", 10);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 1);
        localContentValues.put("KASZT", 2);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Arcane Stab");
        localContentValues.put("DMG","6");
        localContentValues.put("MANA", 12);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 0);
        localContentValues.put("KASZT", 2);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Big Slam");
        localContentValues.put("DMG","8");
        localContentValues.put("MANA", 3);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 1);
        localContentValues.put("KASZT", 4);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Stomp");
        localContentValues.put("DMG","7");
        localContentValues.put("MANA", 7);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 1);
        localContentValues.put("KASZT", 4);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Blood Magice");
        localContentValues.put("DMG","4");
        localContentValues.put("MANA", 10);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 0);
        localContentValues.put("KASZT", 4);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Fire bolt");
        localContentValues.put("DMG","7");
        localContentValues.put("MANA", 7);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 0);
        localContentValues.put("KASZT", 5);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Ice Arrow");
        localContentValues.put("DMG","5");
        localContentValues.put("MANA", 10);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 0);
        localContentValues.put("KASZT", 5);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
        localContentValues.put("Name", "Thunder");
        localContentValues.put("DMG","12");
        localContentValues.put("MANA", 20);
        localContentValues.put("TARGET", 1);
        localContentValues.put("TYPE", 0);
        localContentValues.put("KASZT", 5);
        paramSQLiteDatabase.insert(mTableName, null, localContentValues);
        localContentValues.clear();
    }

    /**
     * vissz adjaegy adott kaszt összes varázs képességét
     * @param id
     * @return
     */
    public String[] getMagicName(int id)
    {
        String[] nevek;
        nevek = new String[4];
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT Name FROM "+mTableName+" where KASZT='"+id+"' or KASZT='0'";
        // Log.d("SQL", str1);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        int i=0;
        while(localCursor.moveToNext())
        {

            nevek[i]=localCursor.getString(localCursor.getColumnIndex("Name")).toString();
           // Log.d("nevekDB",nevek[i]);
            i++;
        }
        return nevek;
    } 
    /**
     * vissz ad egy adott varázs képességét neve alpján vesszővel elválasztva
     * @param name a varázslat neve
     * @return
     */
    public String getMagicByName(String name)
    {
        String magice="";
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM "+mTableName+" where Name='"+name+"'";
        // Log.d("SQL", str1);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
      localCursor.moveToNext();

            magice=localCursor.getString(localCursor.getColumnIndex("DMG")).toString();
            magice=magice+","+localCursor.getString(localCursor.getColumnIndex("MANA")).toString();
            magice=magice+","+localCursor.getString(localCursor.getColumnIndex("TARGET")).toString();
            magice=magice+","+localCursor.getString(localCursor.getColumnIndex("TYPE")).toString();

        return magice;
    }
    /**
     * csata után oda adja a csatáért járó xp-t+ lvl up és 5 pont elosztható 0 ad vissza ha nem történt lvl up
     * @param id azaonosító
     * @param xp mennyi xp-t kap
     */
    public int addXP(String id, int xp)
    {
        int ret=0;
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        String str1 = "SELECT * FROM "+cTableName+" where ID='"+id+"'";
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        localCursor.moveToFirst();
       String mxp=localCursor.getString(localCursor.getColumnIndex("XP")).toString();
        int lvl=Integer.parseInt(localCursor.getString(localCursor.getColumnIndex("LVL")).toString());
        int lvl2=lvl;
        localCursor.close();
      // Log.d("xpelőtt",mxp+"");
     //  Log.d("id",id+"");
        mxp=Integer.toString(Integer.parseInt(mxp)+xp);
      /*  Log.d("xp",mxp+"");
        Log.d("xpszámol",(Integer.parseInt(mxp)/(lvl*100))+"");
        Log.d("lvl",lvl+"");*/
        if(Integer.parseInt(mxp)/(lvl*100)>0)
            {
                mxp=Integer.toString(Integer.parseInt(mxp)%(lvl*100));
                //Log.d("bejöttxp",mxp+"");
                lvl++;
                localContentValues.put("LVL",Integer.toString(lvl));
                localContentValues.put("POINT","5");
                //Toast.makeText(this,R.string.lvlup, Toast.LENGTH_SHORT).show();
            }
            if(lvl==lvl2)
            {
                ret=0;
            }
            else
            {
                ret=1;
            }
        localContentValues.put("XP",mxp);
        localSQLiteDatabase.update(cTableName, localContentValues, "ID =" + id, null);
        localContentValues.clear();
    return ret;
    }
}
