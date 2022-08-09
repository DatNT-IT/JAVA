package DAO;

import Connects.Connect;
import model.NguoiDung;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DangNhap {
    Connect con = new Connect();
    String login[] = new String[2];

    public NguoiDung login(String user, String pass) throws SQLException {
        NguoiDung nguoiDung = new NguoiDung();
        String sql = "select * from NguoiDung where MAND = ? and matkhau = ?";
        PreparedStatement ps = con.con().prepareStatement(sql);
        ps.setString(1, user);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            nguoiDung.setMaND(rs.getInt(1));
            nguoiDung.setTenNguoiDung(rs.getString(2));
            nguoiDung.setMatKhau(rs.getString(3));
            nguoiDung.setEmail(rs.getString(4));
            nguoiDung.setVaiTro(rs.getInt(5));
            nguoiDung.setGioiTinh(rs.getInt(6));
            nguoiDung.setMapin(rs.getString(8));


            return nguoiDung;
        } else {
            JOptionPane.showMessageDialog(null, "Kiểm tra lại thông tin tài khoản", "lỗi đăng nhập", JOptionPane.WARNING_MESSAGE);
            return null;
        }

    }

    //lưu mật khẩu
    public void nho(String user, String pass) throws SQLException {
        String sql = "insert into Nhopass(maND, matKhau, vaitro) values (?,?,?)";
        PreparedStatement pm = con.con().prepareStatement(sql);
        pm.setString(1, user);
        pm.setString(2, pass);
        pm.setInt(3, 1);
        ResultSet rs = pm.executeQuery();
        if (rs.next()) {
            System.out.println("thành công");
        }
    }

    //bỏ lưu mật khẩu
    public void bo(String user, String pass) throws SQLException {
        String sql = "delete Nhopass where maND =?";
        PreparedStatement pm = con.con().prepareStatement(sql);
        pm.setString(1, user);
        pm.executeUpdate();
    }

    // hàm lấy nhân viên đã lưu vào form
    public String[] chuyen() throws SQLException {
        String sql1 = "select * from nhopass where vaitro = 1";
        PreparedStatement pmm = con.con().prepareStatement(sql1);
        ResultSet rs = pmm.executeQuery();
        if (rs.next()) {
            login[0] = rs.getString(1);
            login[1] = rs.getString(2);
        }
        return login;

    }

    public NguoiDung quetqr(String user, String mapin) throws SQLException {
        NguoiDung nguoiDung = new NguoiDung();
        String sql = "select * from NguoiDung where MAND = ? and mapin = ?";
        PreparedStatement ps = con.con().prepareStatement(sql);
        ps.setString(1, user);
        ps.setString(2, mapin);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            nguoiDung.setMaND(rs.getInt(1));
            nguoiDung.setTenNguoiDung(rs.getString(2));
            nguoiDung.setMatKhau(rs.getString(3));
            nguoiDung.setEmail(rs.getString(4));
            nguoiDung.setVaiTro(rs.getInt(5));
            nguoiDung.setGioiTinh(rs.getInt(6));
            nguoiDung.setMapin(rs.getString(8));


            return nguoiDung;
        } else {
            JOptionPane.showMessageDialog(null, "Kiểm tra lại mã pin", "lỗi đăng nhập", JOptionPane.WARNING_MESSAGE);
            return null;
        }

    }
}
