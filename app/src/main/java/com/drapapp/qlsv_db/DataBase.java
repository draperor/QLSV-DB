package com.drapapp.qlsv_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper
{
    private static final String DB_NAME = "danhsach";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "mydanhsach";
    private static final String ID_COL = "id";
    private static final String MSV_COL = "masinhvien";
    private static final String HOTEN_COL = "hoten";
    private static final String CHUYENNGANH_COL = "chuyennganh";
    private static final String DIEM_COL = "diem";
    private static final String CHUCVU_COL = "chucvu";

    public DataBase(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db ) {
        String query =" CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MSV_COL + " TEXT,"
                + HOTEN_COL + " TEXT,"
                + CHUYENNGANH_COL + " TEXT,"
                + CHUCVU_COL + " TEXT,"
                + DIEM_COL + " TEXT)";
        db.execSQL(query);

    }



    public void addSinhVien(String msv,String hoTen, String chuyenNganh, String chucVu,
                            String diem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MSV_COL,msv);
        values.put(HOTEN_COL, hoTen);
        values.put(CHUYENNGANH_COL, chuyenNganh);
        values.put(CHUCVU_COL, chucVu);
        values.put(DIEM_COL, diem);
        db.insert(TABLE_NAME,null,values);
        db.close();


    }
    public void delSinhVien(String msv) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"masinhvien=?",new String[]{msv});
        db.close();
    }

    public ArrayList<SinhVien> readDanhSach() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        ArrayList<SinhVien> al_sinhvien = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                al_sinhvien.add(new SinhVien(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getDouble(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return al_sinhvien;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
