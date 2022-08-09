package service;

import DAO.DocGiaDao;
import DAO.NguoiDungDAO;
import model.DocGia;
import model.NguoiDung;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeviceDocGia {
    DocGiaDao docGiaDao = new DocGiaDao();
    private String checkTen = "^[\\p{L}\\s]+$";
    String checkSDT = "0[0-9]{9}";
    String checkCCCD = "[0-9]{12}";
    String strDate;

    //!email.matches(checkemail)->ko đúng định dạng email
    public boolean add(String ten, String sdt, int gioitinh, String cccd, String diachi, String ngaySinh) {
        if (!sdt.matches(checkSDT)) {
            JOptionPane.showMessageDialog(null, "số điện thoại phải là số và bắt đầu 0 (không được quá 10 số)");
            return false;
        } else if (!cccd.matches(checkCCCD)) {
            JOptionPane.showMessageDialog(null, "CCCD phải là số và không được quá 12 số");
            return false;
        }
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống họ tên");
            return false;
        } else if (diachi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống địa chỉ");
            return false;
        } else if (!ten.matches(checkTen)) {
            JOptionPane.showMessageDialog(null, "họ tên không được có số và kí tự đặc biệt");
            return false;
        }

        int chon = JOptionPane.showConfirmDialog(null, "bạn có chắc muốn thêm người này không", "xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            //String tenDG, String diaChi, String ngaySinh, String soCCCD, String SDT, int gioiTinh
            DocGia docGia = new DocGia(ten, diachi, ngaySinh, cccd, sdt, gioitinh);
            docGiaDao.add(docGia);
            return true;
        }
        return false;
    }

    ;

    public boolean delete(String id) {
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhập mã người dùng bạn muốn xoá");
            return false;
        }
        int chon = JOptionPane.showConfirmDialog(null, "bạn có chắc muốn xoá người này không", "xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            docGiaDao.deleteTam(id);
            return true;
        }
        return false;
    }

    public boolean update(int ma, String ten, String sdt, int gioitinh, String cccd, String diachi, String ngaySinh) {
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống họ tên");
            return false;
        } else if (diachi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống địa chỉ");
            return false;
        } else if (!ten.matches(checkTen)) {
            JOptionPane.showMessageDialog(null, "họ tên không được có số và kí tự đặc biệt");
            return false;
        }
        if (!sdt.matches(checkSDT)) {
            JOptionPane.showMessageDialog(null, "số điện thoại phải là số và bắt đầu 0 (không được quá 10 số)");
            return false;
        } else if (!cccd.matches(checkCCCD)) {
            JOptionPane.showMessageDialog(null, "CCCD phải là số và không được quá 12 số");
            return false;
        }


        int chon = JOptionPane.showConfirmDialog(null, "bạn có chắc muốn cập nhật thông tin người này không", "xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            //String tenDG, String diaChi, String ngaySinh, String soCCCD, String SDT, int maDG, int gioiTinh

            DocGia docGia = new DocGia(ten, diachi, strDate, cccd, sdt, ma, gioitinh);
            docGiaDao.update(docGia);
            return true;
        }
        return false;

    }

    ;


}
