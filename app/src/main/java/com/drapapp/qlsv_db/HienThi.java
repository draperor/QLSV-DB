    package com.drapapp.qlsv_db;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.TextView;
    import android.widget.Toast;

    import java.util.ArrayList;

    public class HienThi extends BaseAdapter
    {
        private Context context;
        private ArrayList<SinhVien> al_sinhVien;

        public HienThi(Context context, ArrayList<SinhVien> al_sinhVien)
        {
            this.context=context;
            this.al_sinhVien=al_sinhVien;
        }

        @Override
        public int getCount()
        {
            return al_sinhVien.size();
        }

        @Override
        public Object getItem(int i)
        {
            return al_sinhVien.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return i;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            if (view == null)
            {
                view = LayoutInflater.from(context)
                        .inflate(R.layout.hienthi, viewGroup,false);
            }
            SinhVien sv = (SinhVien) getItem(i);


            TextView tv_maSinhVien = (TextView) view.findViewById(R.id.tv_maSinhVien);
            TextView tv_hoTen = (TextView) view.findViewById(R.id.tv_hoTen);
            TextView tv_chuyenNganh = (TextView) view.findViewById(R.id.tv_chuyenNganh);
            TextView tv_diem = (TextView) view.findViewById(R.id.tv_diem);
            TextView tv_chucVu = (TextView) view.findViewById(R.id.tv_chucVu);
            TextView tv_xepLoai = (TextView) view.findViewById(R.id.tv_xepLoai);


            tv_maSinhVien.setText(sv.getMaSinhVien());
            tv_hoTen.setText("Họ tên: "+sv.getHoTen());
            tv_chuyenNganh.setText("Chuyên ngành: "+sv.getChuyenNganh());
            tv_diem.setText("Điểm: "+sv.getDiem());
            tv_chucVu.setText("Chức vụ: "+sv.getChucVu());
            tv_xepLoai.setText("Xếp loại: "+sv.phanLoaiSV());


            return view;
        }
    }
