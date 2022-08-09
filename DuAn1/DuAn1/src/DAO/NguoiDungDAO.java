package DAO;

import Connects.Connect;
import model.NguoiDung;

import javax.swing.*;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NguoiDungDAO {
    Connect con = new Connect();

    //dăng kí
    public void dangKi(String ten, String matKhau, String email, int gioiTinh, int mapin) {

        String sql = " insert into NGUOIDUNG (TENND, MATKHAU, EMAIL, VAITRO, gioiTinh,mapin,xoa)  values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, matKhau);
            ps.setString(3, email);
            ps.setInt(4, 1);
            ps.setInt(5, gioiTinh);
            ps.setInt(6, mapin);
            ps.setInt(7, 1);


            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "đăng kí thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "đăng kí thất bại");
        }
    }

    //thêm
    public void add(NguoiDung nguoiDung) {

        String sql = " insert into NGUOIDUNG (TENND, MATKHAU, EMAIL, VAITRO, gioiTinh,xoa,mapin)  values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, nguoiDung.getTenNguoiDung());
            ps.setString(2, nguoiDung.getMatKhau());
            ps.setString(3, nguoiDung.getEmail());
            ps.setInt(4, Integer.parseInt(String.valueOf(nguoiDung.getVaiTro())));
            ps.setInt(5, Integer.parseInt(String.valueOf(nguoiDung.getGioiTinh())));
            ps.setInt(6, 1);
            ps.setString(8, nguoiDung.getMapin());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "thêm thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "thêm thất bại");
        }
    }

    //xoá vĩnh viễn
    public void delete(String id) {
        String sqlnd = "delete  chiTietPHIEUtra where MAND=?";
        String sql = "delete NGUOIDUNG where MAND = ?";
        try {
            PreparedStatement ps1 = con.con().prepareStatement(sqlnd);
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

    //xoá tạm
    public void deleteTam(String id) {

        String sql = "update NGUOIDUNG set xoa=0 where MAND=?";
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

    //cập nhật
    public void update(NguoiDung nguoiDung) {

        String sql = "update NGUOIDUNG set TENND=?, MATKHAU=?, EMAIL=?, VAITRO=?, gioiTinh=? ,mapin=?where MAND=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, nguoiDung.getTenNguoiDung());
            ps.setString(2, nguoiDung.getMatKhau());
            ps.setString(3, nguoiDung.getEmail());
            ps.setInt(4, Integer.parseInt(String.valueOf(nguoiDung.getVaiTro())));
            ps.setInt(5, Integer.parseInt(String.valueOf(nguoiDung.getGioiTinh())));
            ps.setInt(7, Integer.parseInt(String.valueOf(nguoiDung.getMaND())));
            ps.setInt(6, Integer.parseInt(String.valueOf(nguoiDung.getMapin())));
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "cập nhật thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "cập nhật thất bại");
        }
    }

    //cập nhật đã xoá
    public void updateDaXoa(String id) {

        String sql = "update NGUOIDUNG set xoa=1 where MAND=?";
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

    //quên mk
    public void updatePassQuen(String mkMoi, int maND, String email) {
        String sql = "update NGUOIDUNG set matkhau = ? WHERE mand = ? and email = ?";
        try {


            PreparedStatement pm = con.con().prepareStatement(sql);
            pm.setInt(2, maND);
            pm.setString(1, mkMoi);
            pm.setString(3, email);

            if (pm.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Quên Mật Khẩu Thành Công");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Quên Mật Khẩu Thất bại vui lòng kiểm tra lại thông tin");
        }

    }

    //đổi mk
    public void doipass(String mkMoi, int maND) {
        String sql = "update NGUOIDUNG set matkhau = ? WHERE mand = ? ";
        try {


            PreparedStatement pm = con.con().prepareStatement(sql);
            pm.setInt(2, maND);
            pm.setString(1, mkMoi);


            if (pm.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Đổi Mật Khẩu Thành Công");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đổi Mật Khẩu thất bại");
        }

    }

    //đổi pin
    public void doipin(String pin, int maND) {
        String sql = "update NGUOIDUNG set mapin = ? WHERE mand = ? ";
        try {


            PreparedStatement pm = con.con().prepareStatement(sql);
            pm.setInt(2, maND);
            pm.setString(1, pin);


            if (pm.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Đổi pin Thành Công");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đổi pin thất bại");
        }

    }
}

