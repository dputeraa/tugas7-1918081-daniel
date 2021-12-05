package com.example.menuangkringan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_kampus";

    private static final String tb_menu = "tb_menu";

    private static final String tb_menu_id = "id";
    private static final String tb_menu_nama = "nama";
    private static final String tb_menu_harga = "harga";

    private static final String CREATE_TABLE_MENU = "CREATE TABLE " +
            tb_menu +"("
            + tb_menu_id + " INTEGER PRIMARY KEY ,"
            + tb_menu_nama + " TEXT ,"
            + tb_menu_harga + " TEXT " + ")";

    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MENU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CreateMenu(Menu data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_menu_id, data.get_id());
        values.put(tb_menu_nama, data.get_nama());
        values.put(tb_menu_harga, data.get_harga());
        db.insert(tb_menu, null, values);
        db.close();
    }

    public List<Menu> ReadMenu() {
        List<Menu> listMhs = new ArrayList<Menu>();
        String selectQuery = "SELECT  * FROM " + tb_menu;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Menu data = new Menu();
                data.set_id(cursor.getString(0));
                data.set_nama(cursor.getString(1));
                data.set_harga(cursor.getString(2));
                listMhs.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listMhs;
    }


    public int UpdateMenu (Menu data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_menu_nama, data.get_nama());
        values.put(tb_menu_harga, data.get_harga());

        return db.update(tb_menu, values, tb_menu_id + " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }

    public void DeleteMenu(Menu data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_menu,tb_menu_id+ " = ?",
                new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}
