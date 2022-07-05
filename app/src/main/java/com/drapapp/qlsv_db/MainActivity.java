package com.drapapp.qlsv_db;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{
    ImageView avt;
    EditText edit_hoTen, edit_chuyenNganh, edit_diem, edit_mSV;
    TextView tv_xepLoai;
    CheckBox cb_chucVu;
    RadioGroup rg_chucVu;
    RadioButton rb_lopTruong, rb_lopPho, rb_biThu;
    Button btn_add, btn_show, btn_return, btn_chonAnh;
    DataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Nhập thông tin sinh viên");

        avt = findViewById(R.id.avt);
        edit_mSV = findViewById(R.id.edit_mSV);
        edit_hoTen = findViewById(R.id.edit_hoTen);
        edit_chuyenNganh = findViewById(R.id.edit_chuyenNganh);
        edit_diem = findViewById(R.id.edit_diem);
        tv_xepLoai = findViewById(R.id.tv_xepLoai);
        cb_chucVu = findViewById(R.id.cb_chucVu);
        rg_chucVu = findViewById(R.id.rg_chucVu);
        rb_lopTruong = findViewById(R.id.rb_lopTruong);
        rb_lopPho = findViewById(R.id.rb_lopPho);
        rb_biThu = findViewById(R.id.rb_biThu);
        btn_add = findViewById(R.id.btn_add);
        btn_return = findViewById(R.id.btn_return);
        btn_chonAnh = findViewById(R.id.btn_chonAnh);
        btn_show = findViewById(R.id.btn_show);

        dataBase = new DataBase(MainActivity.this);


        btn_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String msv = edit_mSV.getText().toString();
                String hoTen = edit_hoTen.getText().toString();
                String chuyenNganh = edit_chuyenNganh.getText().toString();
                String diem = edit_diem.getText().toString();
                String chucVu = chucVu();

                if (msv.isEmpty() || hoTen.isEmpty() || chuyenNganh.isEmpty() || diem.isEmpty() ||
                chucVu.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin",
                            Toast.LENGTH_SHORT).show();
                }


                dataBase.addSinhVien(msv,hoTen, chuyenNganh, chucVu, diem);
                Toast.makeText(MainActivity.this, "SAVED", Toast.LENGTH_SHORT).show();
                edit_mSV.setText("");
                edit_hoTen.setText("");
                edit_chuyenNganh.setText("");
                edit_diem.setText("");
                cb_chucVu.setVisibility(View.VISIBLE);

            }

            private String chucVu()
            {
                String chucVu;
                if (cb_chucVu.isChecked())
                    chucVu =
                            ((RadioButton) findViewById(rg_chucVu.getCheckedRadioButtonId()))
                                    .getText().toString();
                else chucVu = "Không";
                return chucVu;
            }

        });



        edit_mSV.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String str = charSequence.toString();
                if (str.length()>0 && str.contains(" ")) {
                    edit_mSV.setError("Không được có dấu cách");
                    edit_mSV.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        btn_show.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MainActivity.this, DanhSach.class);
                startActivity(i);
            }
        });

        cb_chucVu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b)
                {
                    rg_chucVu.setVisibility(View.VISIBLE);
                    rb_lopTruong.setChecked(true);
                } else
                {
                    rg_chucVu.setVisibility(View.INVISIBLE);
                    rg_chucVu.clearCheck();
                }
            }
        });
     }
}
