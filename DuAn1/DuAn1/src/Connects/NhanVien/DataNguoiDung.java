package Connects.NhanVien;

import Connects.Connect;
import model.LoaiSach;
import model.NguoiDung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataNguoiDung {
    private static Connect connect = new Connect();
    private static List<NguoiDung> list = new ArrayList<>();

    public static List<NguoiDung> getListND() {
        list.clear();
        String sql = "select tennd,matkhau,email,mand,gioitinh,vaitro,mapin from nguoidung where xoa=1";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new NguoiDung(rs.getString(1), "******", rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), "******"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<NguoiDung> getListNDXoa() {
        list.clear();
        String sql = "select tennd,matkhau,email,mand,gioitinh,vaitro,mapin from nguoidung where xoa=0";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new NguoiDung(rs.getString(1), "******", rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), "******"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
