package com.example.pm2e12277.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private  static final  int DATABASE_VERSION = 2;
    private  static final String DATABASE_NOMBRE = "BD_agenda";
    public static  final String TABLE_CONTACTOS = "Table_contactos";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACTOS + "("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pais TEXT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "nota TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CONTACTOS);
        onCreate(sqLiteDatabase);
    }
}
