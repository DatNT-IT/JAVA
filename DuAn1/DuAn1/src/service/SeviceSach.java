package service;

import DAO.SachDAO;
import model.ChiTietSach;
import model.DocGia;
import model.NguoiDung;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeviceSach {
    SachDAO sachDAO = new SachDAO();

    public boolean addsach(String tensach, String ngaynhap, int giabia, int sotrang, int lantaiban, int soluong, int chomuon, String tinhtrang, String tg, String nxb, String theloai, String vitri) {
        if (tensach.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống tên sách");
            return false;
        } else if (tinhtrang.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống tình trạng");
            return false;
        }

        int chon = JOptionPane.showConfirmDialog(null, "bạn có chắc muốn thêm sách này không", "xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            ChiTietSach sach = new ChiTietSach(sotrang, vitri, soluong, theloai, tg, nxb, lantaiban, chomuon, tensach, ngaynhap, tinhtrang, giabia);
            sachDAO.add(sach);
            return true;
        }
        return false;
    }

    ;

    public boolean deletesach(String id) {
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhập mã sách bạn muốn xoá");
            return false;
        }
        int chon = JOptionPane.showConfirmDialog(null, "bạn có chắc muốn xoá sách này không", "xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            sachDAO.deleteTam(id);
            return true;
        }
        return false;
    }

    public boolean update(int ma, String tensach, String ngaynhap, int giabia, int sotrang, int lantaiban, int soluong, int chomuon, String tinhtrang, String tg, String nxb, String theloai, String vitri) {
        if (tensach.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống tên sách");
            return false;
        }
        if (tinhtrang.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống tình trạng sách");
            return false;
        }

        int chon = JOptionPane.showConfirmDialog(null, "bạn có chắc muốn cập nhật thông tin sách này không", "xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            //String tenDG, String diaChi, String ngaySinh, String soCCCD, String SDT, int maDG, int gioiTinh

            ChiTietSach sach = new ChiTietSach(ma, sotrang, vitri, soluong, theloai, tg, nxb, lantaiban, chomuon, tensach, ngaynhap, tinhtrang, giabia);
            sachDAO.update(sach);
            return true;
        }
        return false;

    }

    ;

}
