package service;


import Connects.Connect;
import DAO.NguoiDungDAO;
import model.NguoiDung;
import views.FromChinh;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeviceNguoiDung {
    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
    private String checkTen = "^[\\p{L}\\s]+$";
    private String checkemail = "\\w+@\\w+(\\.\\w+){1,2}";
    private String checkpass = "\\w{5,20}";//ít nhất 5 kí tự nhiều nhất 10 ko đc có kí tự đặc biệt

    //!email.matches(checkemail)->ko đúng định dạng email
    public boolean add(String tennguoidung, String matkhau, int vaitro, String email, int gioitinh, String mapin) {

        if (tennguoidung.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống họ tên");
            return false;
        } else if (matkhau.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống mật khẩu");
            return false;
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống email");
            return false;
        } else if (!tennguoidung.matches(checkTen)) {
            JOptionPane.showMessageDialog(null, "họ tên không được có số và kí tự đặc biệt");
            return false;
        } else if (!matkhau.matches(checkpass)) {
            JOptionPane.showMessageDialog(null, "pass phải có ít nhất từ 5 - 20 kí tự và không được có kí tự đặc biệt");
            return false;
        } else if (!email.matches(checkemail)) {
            JOptionPane.showMessageDialog(null, "không đúng định dạng email vui lòng nhập lại");
            return false;
        }
        int chon = JOptionPane.showConfirmDialog(null, "bạn có chắc muốn thêmngười này không", "xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            NguoiDung nguoiDung = new NguoiDung(tennguoidung, matkhau, email, gioitinh, vaitro, mapin);
            nguoiDungDAO.add(nguoiDung);
            return true;
        }
        return false;
    }

    ;

    public boolean delete(String idt) {

        if (idt.isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhập mã người dùng bạn muốn xoá");
            return false;
        }
        int id;
        try {
            id = Integer.parseInt(idt);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "mã phải là số");
            return false;
        }
        int chon = JOptionPane.showConfirmDialog(null, "bạn có chắc muốn xoá người này không", "xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            nguoiDungDAO.deleteTam(idt);
            return true;
        }
        return false;
    }

    public boolean update(int ma, String tennguoidung, String matkhau, int vaitro, String email, int gioitinh, String mapin) {
        if (tennguoidung.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống họ tên");
            return false;
        } else if (matkhau.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống mật khẩu");
            return false;
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống email");
            return false;
        } else if (!tennguoidung.matches(checkTen)) {
            JOptionPane.showMessageDialog(null, "họ tên không được có số và kí tự đặc biệt");
            return false;
        } else if (!matkhau.matches(checkpass)) {
            JOptionPane.showMessageDialog(null, "phải có ít nhất từ 5 - 10 kí tự và không được có kí tự đặc biệt");
            return false;
        } else if (!email.matches(checkemail)) {
            JOptionPane.showMessageDialog(null, "không đúng định dạng email vui lòng nhập lại");
            return false;
        }
        int chon = JOptionPane.showConfirmDialog(null, "bạn có chắc muốn cập nhật thông tin người này không", "xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            NguoiDung nguoiDung = new NguoiDung(tennguoidung, matkhau, email, ma, gioitinh, vaitro, mapin);
            nguoiDungDAO.update(nguoiDung);
            return true;
        }
        return false;

    }

    ;

    //quên mk
    public boolean updateQuen(String matkhau, int maND, String email) {


        nguoiDungDAO.updatePassQuen(matkhau, maND, email);
        return true;


    }

    ;

    //đổi mk
    public boolean doipass(String mkcu, String pass, String mkmoi, String mkmoilai, int ma) {
        if (!mkcu.equals(pass)) {
            JOptionPane.showMessageDialog(null, "bạn nhập sai mật khẩu hiện tại");
            return false;
        }

        if (mkmoi.isEmpty() || mkmoi.isBlank()) {
            JOptionPane.showMessageDialog(null, "Không được để trống mật khẩu mới");

            return false;
        }

        if (mkmoilai.isEmpty() || mkmoilai.isBlank()) {
            JOptionPane.showMessageDialog(null, "Không được để trống xác nhận mật khẩu");

            return false;
        }

        if (!mkmoi.equals(mkmoilai)) {
            JOptionPane.showMessageDialog(null, "Vui lòng xác nhận đúng mật khẩu mới");
            return false;
        }
        int chon = JOptionPane.showConfirmDialog(null, "bạn có thực muốn đổi mật khẩu không", "xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            nguoiDungDAO.doipass(mkmoi, ma);
            return true;
        }
        return false;
    }

    //đổi pin
    public boolean doipin(String mkcu, String pass, String pinmoi, String pinmoilai, int ma) {
        if (!mkcu.equals(pass)) {
            JOptionPane.showMessageDialog(null, "bạn nhập sai mật khẩu hiện tại");
            return false;
        }

        if (pinmoi.isEmpty() || pinmoi.isBlank()) {
            JOptionPane.showMessageDialog(null, "Không được để trống mapin mới");

            return false;
        }

        if (pinmoilai.isEmpty() || pinmoilai.isBlank()) {
            JOptionPane.showMessageDialog(null, "Không được để trống xác nhận pin");

            return false;
        }

        if (!pinmoi.equals(pinmoilai)) {
            JOptionPane.showMessageDialog(null, "Vui lòng xác nhận đúng pin mới");
            return false;
        }
        int chon = JOptionPane.showConfirmDialog(null, "bạn có thực muốn đổi pin không", "xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            nguoiDungDAO.doipin(pinmoi, ma);
            return true;
        }
        return false;
    }

    //check
    public boolean check(String user, String pass) {
        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống user");
            return false;
        } else if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống mật khẩu");
            return false;
        } else {
            int tk;
            try {
                tk = Integer.parseInt(user);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "tk phải là số");
                return false;
            }

            return true;
        }
    }

    ;

}
