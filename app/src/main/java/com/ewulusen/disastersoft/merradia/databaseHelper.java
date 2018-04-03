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

public class databaseHelper
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
    public static final String charTable = "unit_table_Merradia";
    public databaseHelper(Context paramContext)
    {
        super(paramContext, "merradiaUsers.db", null, 12);
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
     * ez a programrészlet kiszedi az ember birtokában lévő karaktereket
     * @param id
     * @return vissza adja az adott ember birtokában lévő karaktereket
     */
    public Cursor getChar(String id)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM unit_table_Merradia where OWNER='"+id+"'";
        Log.d("SQL", str1);
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
        Log.d("SQL", paramString2);
        String str1 = "SELECT * FROM users_table_Merradia where userName='"+paramString1+"' and  password='" + paramString2 + "'";
        Log.d("SQL", str1);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        if (localCursor.getCount() == 0)
        {
            addData(paramString1, paramString2);
            String str2 = "SELECT * FROM users_table_Merradia where userName='"+paramString1+"' and password='" + paramString2 + "'";
            //Log.d("SQL", str2);
            localCursor = localSQLiteDatabase.rawQuery(str2, null);
        }

        return localCursor;
    }
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users_table_Merradia (ID INTEGER PRIMARY KEY AUTOINCREMENT,  userName TEXT, password TEXT)");
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS unit_table_Merradia( ID INTEGER PRIMARY KEY AUTOINCREMENT,   OWNER TEXT, Name TEXT,  AGI TEXT,   STR TEXT,   DEF TEXT,   CON TEXT,   REF TEXT,   LUCK TEXT)");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS users_table_Merradia");
        onCreate(paramSQLiteDatabase);
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS unit_table_Merradia");
        onCreate(paramSQLiteDatabase);
    }
}
