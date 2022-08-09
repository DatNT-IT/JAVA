package Connects.tacGia;

import Connects.Connect;
import model.ChiTietTacGia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataTacGia {
    private static Connect connect = new Connect();
    private static List<ChiTietTacGia> list = new ArrayList<>();

    public static List<ChiTietTacGia> getListTG() {
        list.clear();
        String sql = "select matg,tentg,diachitg from tacgia where xoa=1";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new ChiTietTacGia(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<ChiTietTacGia> getListTGXoa() {
        list.clear();
        String sql = "select matg,tentg,diachitg from tacgia where xoa=0";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new ChiTietTacGia(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


}
