package Connects.KhoSach;

import Connects.Connect;
import model.ChiTietSach;
import model.DocGia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataSach {
    private static Connect connect = new Connect();
    private static List<ChiTietSach> list = new ArrayList<>();

    public static List<ChiTietSach> getListSach() {
        list.clear();

        String sql = "select MACTS,SOTRANG,mota,SOLUONG,TENLS,tentg,TENNXB,lantaiban,duocmuon,TENSACH,NGAYNHAP,TINHTRANG,giabia from CHITIETSACH JOIN\n" +
                "    VITRI V on V.MAVT = CHITIETSACH.MAVT JOIN NXB N on N.MANXB = CHITIETSACH.MANXB JOIN LOAISACH L on L.MALS = CHITIETSACH.MALS JOIN\n" +
                "    tacgia t on t.matg = CHITIETSACH.MATG where CHITIETSACH.xoa=1";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new ChiTietSach(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<ChiTietSach> getListSachXoa() {
        list.clear();
        String sql = "select MACTS,SOTRANG,mota,SOLUONG,TENLS,tentg,TENNXB,lantaiban,duocmuon,TENSACH,NGAYNHAP,TINHTRANG,giabia from CHITIETSACH JOIN\n" +
                "    VITRI V on V.MAVT = CHITIETSACH.MAVT JOIN NXB N on N.MANXB = CHITIETSACH.MANXB JOIN LOAISACH L on L.MALS = CHITIETSACH.MALS JOIN\n" +
                "    tacgia t on t.matg = CHITIETSACH.MATG where CHITIETSACH.xoa=0";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new ChiTietSach(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }
}
