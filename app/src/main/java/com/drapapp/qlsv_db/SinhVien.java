package com.drapapp.qlsv_db;


import java.io.Serializable;

public class SinhVien implements Serializable
{
    protected String hoTen;
    protected String chuyenNganh;
    protected double diem;
    protected String chucVu;
    protected String phanLoai;
    protected String maSinhVien;

    public String getPhanLoai()
    {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai)
    {
        this.phanLoai = phanLoai;
    }

    public String getMaSinhVien()
    {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien)
    {
        this.maSinhVien = maSinhVien;
    }

    public SinhVien(String maSinhVien, String hoTen, String chuyenNganh, String chucVu, Double diem) {
        this.maSinhVien=maSinhVien;
        this.hoTen=hoTen;
        this.chuyenNganh=chuyenNganh;
        this.chucVu=chucVu;
        this.diem = diem;
    }

    public SinhVien(){

    }

    public String getHoTen()
    {
        return hoTen;
    }

    public void setHoTen(String hoTen)
    {
        this.hoTen = hoTen;
    }

    public String getChuyenNganh()
    {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh)
    {
        this.chuyenNganh = chuyenNganh;
    }

    public double getDiem()
    {
        return diem;
    }

    public void setDiem(double diem)
    {
        this.diem = diem;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String phanLoaiSV(){
        double diemThuong=0,diemTong=0;

        if (chucVu=="Lớp Trưởng")
            diemThuong=0.5;
        else if (chucVu=="Lớp Phó")
            diemThuong=0.3;
        else if (chucVu=="Bí Thư")
            diemThuong=0.2;

        diemTong=diem+diemThuong;

        if (diemTong>=9)
            phanLoai= "Xuất Sắc";
        else if (diemTong>=8)
            phanLoai="Giỏi";
        else if (diemTong>=7)
            phanLoai="Khá";
        else if (diemTong>=5)
            phanLoai="Trung Bình";
        else phanLoai="Yếu";

        return  phanLoai;
    }
}
