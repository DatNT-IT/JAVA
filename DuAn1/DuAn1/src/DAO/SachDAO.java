package DAO;

import Connects.Connect;
import model.ChiTietSach;
import model.DocGia;
import model.NguoiDung;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SachDAO {
    Connect con = new Connect();

    //thêm
    public void add(ChiTietSach sach) {
        int matg = 0, matl = 0, manxb = 0, mavt = 0;
        //ktra tg
        String sqltg = "SELECT * FROM tacgia WHERE tentg like N'%" + sach.getTenTG() + "%' ";

        try {
            Connect con = new Connect();
            PreparedStatement ps = con.con().prepareStatement(sqltg);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                matg = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "thêm thất bại");
            return;
        }

        //loại sách
        String sqlls = "SELECT * FROM LOAISACH WHERE tenls like N'%" + sach.getTenLS() + "%' ";

        try {
            Connect con = new Connect();
            PreparedStatement ps = con.con().prepareStatement(sqlls);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                matl = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "thêm thất bại");
            return;
        }
        //nxb
        String sqlnxb = "SELECT * FROM nxb WHERE tennxb like N'%" + sach.getTenNXB() + "%' ";

        try {
            Connect con = new Connect();
            PreparedStatement ps = con.con().prepareStatement(sqlnxb);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                manxb = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "thêm thất bại");
            return;
        }
        //vị trí
        String sql = "SELECT * FROM VITRI WHERE mota like N'%" + sach.getTenVT() + "%' ";

        try {
            Connect con = new Connect();
            PreparedStatement ps = con.con().prepareStatement(sql);
            // ps.setString(1,"tủ sách dân chí, hàng 1,ô 2");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mavt = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "thêm thất bại");
            return;
        }
//thêm vào bảng sách
        String sqladd = " insert into CHITIETSACH(tensach, sotrang, mavt, ngaynhap, soluong, giabia, tinhtrang, mals, matg, manxb, lantaiban, duocmuon, xoa) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement psadd = con.con().prepareStatement(sqladd);
            psadd.setString(1, sach.getTenSach());
            psadd.setInt(2, sach.getSoTrang());
            psadd.setInt(3, mavt);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = formatter.parse(sach.getNgayNhap());
                String strDate = formatter.format(date);
                psadd.setString(4, String.valueOf(strDate));
            } catch (ParseException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "lỗi ngày");
            }
            psadd.setInt(5, sach.getSoLuong());
            psadd.setString(6, String.valueOf(sach.getGiaBia()));
            psadd.setString(7, sach.getTinhTrang());
            psadd.setInt(8, matl);
            psadd.setInt(9, matg);
            psadd.setInt(10, manxb);
            psadd.setInt(11, sach.getLanTaiBan());
            psadd.setInt(12, sach.getDuocPhepMuon());
            psadd.setInt(13, 1);
            if (psadd.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "thêm thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "thêm thất bại");
        }
    }

    //xoá vĩnh viễn
    public void delete(String id) {
        String sqlmuon = "delete  chiTietPHIEUMUON where MActs=?";
        String sqltra = "delete  chiTietPHIEUtra where MActs=?";
        String sql = "delete chitietsach where MActs = ?";
        try {
            PreparedStatement ps0 = con.con().prepareStatement(sqlmuon);
            ps0.setString(1, id);
            PreparedStatement ps1 = con.con().prepareStatement(sqltra);
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

    public void deleteTam(String id) {

        String sql = "update chitietsach set xoa=0 where MActs =?";
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

    public void update(ChiTietSach sach) {

        String sql = "update chitietsach  set TENsach=?, matg=?, mals=?, lantaiban=?, giabia=?,manxb=?,ngaynhap=?,soluong=?,sotrang=?,mavt=?,tinhtrang=?,duocmuon=? where MActs=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
//            ps.setString(1,sach.getTenSach());
//            ps.setInt(2,sach.getMaTG());
//            ps.setInt(3,sach.getMaLS());
//            ps.setInt(4,sach.getLanTaiBan());
//            ps.setInt(5,sach.getGiaBia());
//            ps.setInt(6,sach.getMaNXB());
//            ps.setString(7,sach.getNgayNhap());
//            ps.setInt(8,sach.getSoLuong());
//            ps.setInt(9,sach.getSoTrang());
//            ps.setInt(10,sach.getMaVT());
            ps.setString(11, sach.getTinhTrang());
            ps.setInt(12, sach.getDuocPhepMuon());
            ps.setInt(13, sach.getMaSach());

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

        String sql = "update chitietsach set xoa=1 where MActs=?";
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
