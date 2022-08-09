package Connects.NXB;

import Connects.Connect;
import model.ChiTietTacGia;
import model.NXBSach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataNXB {
    private static Connect connect = new Connect();
    private static List<NXBSach> list = new ArrayList<>();

    public static List<NXBSach> getListNXB() {
        list.clear();
        String sql = "select manxb,tennxb,diachinxb from nxb where xoa=1";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new NXBSach(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<NXBSach> getListNXBXoa() {
        list.clear();
        String sql = "select maxnb,tennxb,diachinxb from nxb where xoa=0";

        try {
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new NXBSach(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


}
