package com.ewulusen.disastersoft.merradia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.sql.Timestamp;

/**
 * Created by diszterhoft.zoltan on 2018.04.03
 * Ebben a javafájlban fogom létrehozni az adatbázisokat amivel dolgozni fogunk
 */

public class DatabaseHelper
        extends SQLiteOpenHelper
{
    /**
     * Előszőr is létrhozzuk az összes változót amivel dolgozni fogunk.
     */
    public static final String DatabaseName = "merradiaUsers.db";
    public static final String Username = "userName";
    public static final String Password = "password";
    public static final String uTableName = "users_table_Merradia";
    public static final String owner = "OWNER";
    public static final String charTable = "char_table_Merradia";
    public DatabaseHelper(Context paramContext)
    {
        super(paramContext, DatabaseName, null, 16);
    }

    /**
     * ez a programrészlet kiszedi az adatbázisból az adott id-hez tartozó nevet
     * @param id
     * @return vissza adja az adott nevet
     */
    public  Cursor getName(String id)
    {
    SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
    String str1 = "SELECT userName FROM users_table_Merradia where ID='"+id+"'";
    Log.d("SQL", str1);
    Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
    return localCursor;
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
        String str1 = "SELECT * FROM char_table_Merradia where ID=id";
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        localCursor.moveToNext();
        int m=Integer.parseInt(localCursor.getString(localCursor.getColumnIndex("MONEY")).toString());
        m=m+money;
        localContentValues.put("MONEY",m);
        localSQLiteDatabase.update(charTable, localContentValues, "ID =" + id, null);
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
        Log.d("SQL", str1);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
       // printDatabaseHelper(id);
        return localCursor;
    }
    public void printDatabaseHelper(String id)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM char_table_Merradia where OWNER='"+id+"'";
        Cursor cursor = localSQLiteDatabase.rawQuery(str1, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String line="";
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    line=line+(cursor.getString(i).toString())+"->"+cursor.getColumnName(i)+",";
                }

               Log.d("tartalom",line);
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
       // Log.d("SQL", str1);
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
     * ez még nincs
     * @param paramString
     * @return
     */
    public Cursor backLogin(String paramString)
    {
        return getReadableDatabase().rawQuery("SELECT * FROM users_table_Merradia where ID=" + paramString + "", null);
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
            localContentValues.put("Owner", 0);
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
            localSQLiteDatabase.insert("char_table_Merradia", null, localContentValues);
            localContentValues.clear();
        }

        return localCursor;
    }
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users_table_Merradia (ID INTEGER PRIMARY KEY AUTOINCREMENT,  userName TEXT, password TEXT)");
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS char_table_Merradia( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Owner TEXT, Name TEXT,  AGI TEXT,   STR TEXT,   DEF TEXT,   CON TEXT," +
                "   REF TEXT,   LUCK TEXT, DEX TEXT,INTE TEXT,KASZT TEXT," +
                "POINT TEXT,LVL TEXT,MONEY TEXT)");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS users_table_Merradia");
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS char_table_Merradia");
        onCreate(paramSQLiteDatabase);
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
        localSQLiteDatabase.update(charTable, localContentValues, "ID =" + datas[8], null);
        localContentValues.clear();
    }
    public void deleteChar(String id)
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        localSQLiteDatabase.delete(charTable, "ID=" + id, null);
    }
}
