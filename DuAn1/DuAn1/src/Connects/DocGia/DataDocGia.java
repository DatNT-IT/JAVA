package Connects.DocGia;

import Connects.Connect;
import model.ChiTietTacGia;
import model.DocGia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataDocGia {
    private static Connect connect = new Connect();
    private static List<DocGia> list = new ArrayList<>();

    public static List<DocGia> getListDG() {
        list.clear();
        String sql = "select tendg,diachi,ngaysinh,socccd,sdt,madg,gioitinh from docgia where xoa=1";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new DocGia(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<DocGia> getListDGXoa() {
        list.clear();
        String sql = "select tendg,diachi,ngaysinh,socccd,sdt,madg,gioitinh from docgia where xoa=0";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new DocGia(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


}
