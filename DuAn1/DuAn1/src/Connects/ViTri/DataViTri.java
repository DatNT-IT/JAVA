package Connects.ViTri;

import Connects.Connect;
import model.LoaiSach;
import model.ViTri;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataViTri {
    private static Connect connect = new Connect();
    private static List<ViTri> list = new ArrayList<>();

    public static List<ViTri> getListVT() {
        list.clear();
        String sql = "select mavt,hangsach,osach,tusach,mota from vitri where xoa=1";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new ViTri(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<ViTri> getListVTXoa() {
        list.clear();
        String sql = "select mavt,hangsach,osach,tusach,mota from vitri where xoa=0";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new ViTri(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
