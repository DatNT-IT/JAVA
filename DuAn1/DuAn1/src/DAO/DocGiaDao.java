package DAO;

import Connects.Connect;
import model.DocGia;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DocGiaDao {
    Connect con = new Connect();

    //thêm
    public void add(DocGia docGia) {

        String sql = " insert into DOCGIA (tendg, gioitinh, ngaysinh, diachi, sdt, socccd, xoa)  values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, docGia.getTenDG());
            ps.setInt(2, docGia.getGioiTinh());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = formatter.parse(docGia.getNgaySinh());
                String strDate = formatter.format(date);
                System.out.println("Date: " + strDate);
                ps.setString(3, String.valueOf(strDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ps.setString(4, docGia.getDiaChi());
            ps.setString(5, docGia.getSDT());
            ps.setString(6, docGia.getSoCCCD());
            ps.setInt(7, 1);
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
        String sqlmuon = "delete  phieuMuon where MAdg=?";
        String sqltra = "delete  phieutra where MAdg=?";
        String sql = "delete docgia where MAdg = ?";
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

    //xoá tạm
    public void deleteTam(String id) {

        String sql = "update docgia set xoa=0 where MAdg=?";
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
    public void update(DocGia docGia) {

        String sql = "update docgia set TENdg=?, gioitinh=?, ngaysinh=?, sdt=?, socccd=?,diachi=? where MAdg=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, docGia.getTenDG());
            ps.setInt(2, docGia.getGioiTinh());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = formatter.parse(docGia.getNgaySinh());
                String strDate = formatter.format(date);
                ps.setString(3, String.valueOf(strDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            ps.setString(4, docGia.getSDT());
            ps.setString(5, docGia.getSoCCCD());
            ps.setString(6, docGia.getDiaChi());
            ps.setInt(7, docGia.getMaDG());


            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "cập nhật thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "cập nhật thất bại");
        }
    }

    //thêm lại đã xoá
    public void updateDaXoa(String id) {

        String sql = "update DocGia set xoa=1 where MAdg=?";
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
