package com.drapapp.qlsv_db;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DanhSach extends AppCompatActivity
{
    ListView listView;
    DataBase dataBase;
    ArrayList<SinhVien> al_sinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsach);
        listView = findViewById(R.id.listView);
        al_sinhVien = new ArrayList<>();
        dataBase = new DataBase(this);
        al_sinhVien = dataBase.readDanhSach();
        HienThi hienthi = new HienThi(this, al_sinhVien);
        listView.setAdapter(hienthi);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position,
                                           long l)
            {

                AlertDialog.Builder alert = new AlertDialog.Builder(DanhSach.this);
                alert.setMessage("Bạn muốn xóa sinh viên này ? ");
                alert.setTitle("Cảnh báo");
                alert.setCancelable(false);
                alert.setPositiveButton("Đúng", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dataBase.delSinhVien(al_sinhVien.get(position).getMaSinhVien());
                        al_sinhVien.remove(position);
                        HienThi hienthi = new HienThi(DanhSach.this, al_sinhVien);
                        hienthi.notifyDataSetChanged();
                        finish();

                    }
                });
                alert.setNegativeButton("Không", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
                return false;
            }
        });
    }



}

