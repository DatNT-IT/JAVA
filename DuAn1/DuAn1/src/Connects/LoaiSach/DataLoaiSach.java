package Connects.LoaiSach;

import Connects.Connect;
import model.ChiTietTacGia;
import model.LoaiSach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataLoaiSach {
    private static Connect connect = new Connect();
    private static List<LoaiSach> list = new ArrayList<>();

    public static List<LoaiSach> getListLS() {
        list.clear();
        String sql = "select maLS,tenLS from loaisach where xoa=1";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new LoaiSach(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<LoaiSach> getListLSXoa() {
        list.clear();
        String sql = "select maLS,tenLS from loaisach where xoa=0";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new LoaiSach(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
