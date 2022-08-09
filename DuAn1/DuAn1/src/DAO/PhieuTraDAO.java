package DAO;

import Connects.Connect;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhieuTraDAO {
    Connect con = new Connect();

    public void addPhieu(String ma) {

        String sql = "insert into phieutra(MAdg, xoa) values (?,?)";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, ma);
            ps.setInt(2, 1);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "thêm thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "thêm thất bại");
        }
    }

    //xoá vĩnh viễn ls
    public void deletePhieu(String id) {

        String sql = "delete phieutra where MAtra = ?";
        String sql1 = "delete chiTietPHIEUtra where MAtra = ?";
        try {
            PreparedStatement ps1 = con.con().prepareStatement(sql1);
            ps1.setString(1, id);
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "xoá thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "xoá thất bại");
        }
    }

    //xoá tạm ls
    public void deleteTamPhieu(String id) {

        String sql = "update phieutra set xoa=0 where MAtra = ?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "xoá thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "xoá thất bại");
        }
    }

    //cập nhật ls
    public void updatePhieu(String ten, String ma) {

        String sql = "update phieutra set madg=?where MAtra = ?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, ma);


            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "cập nhật thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "cập nhật thất bại");
        }
    }

    //thêm lại đã xoá ls
    public void updateDaXoaPhieu(String id) {

        String sql = "update phieutra set xoa=1 where MAtra = ?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);


            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "cập nhật lại  thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "cập nhật lại thất bại");
        }
    }
}
