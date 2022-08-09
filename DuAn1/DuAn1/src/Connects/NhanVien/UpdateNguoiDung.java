package Connects.NhanVien;

import Connects.Connect;
import model.LoaiSach;
import model.NguoiDung;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateNguoiDung extends AbstractTableModel {
    private Connect connect = new Connect();
    private List<NguoiDung> list;
    //String tenNguoiDung, String matKhau, String email, int maND, int gioiTinh, int vaiTro
    private final Class[] classes = {Integer.class, String.class, String.class, String.class, Integer.class, Integer.class, String.class, Integer.class};
    private final String low[] = {"mã ", "tên ", "mật khẩu", "email", "giới tính", "vai trò", "mã pin"};

    public UpdateNguoiDung(List<NguoiDung> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();//bảng có bao dòng
    }

    @Override
    public int getColumnCount() {
        return low.length;//dòng có bao cột
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;//không được sửa ô cột đầu là mã nd
        }
        if (columnIndex == 6) {
            return false;//không được sửa ô cột đầu là mã qr
        }
        if (columnIndex == 2) {
            return false;//không được sửa ô cột đầu là mã qr
        }
        return true;//true là tất cả đc

    }

    @Override
    public String getColumnName(int column) {
        return low[column];//cột tương ứng
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];//kiểu dữ liệu tương ứng
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NguoiDung nguoiDung = list.get(rowIndex);//lấy dữ liệu ô thì phải biết n thuộc dòng bn
        switch (columnIndex) {
            //"mã ","tên ","mật khẩu","email","giới tính","vai trò"
            case 0:
                return nguoiDung.getMaND();//cột 1 là mã
            case 1:
                return nguoiDung.getTenNguoiDung();//cột 2 là tên
            case 2:
                return nguoiDung.getMatKhau();
            case 3:
                return nguoiDung.getEmail();
            case 4:
                return nguoiDung.getGioiTinh();
            case 5:
                return nguoiDung.getVaiTro();
            case 6:
                return nguoiDung.getMapin();
            default:
                return null;//không thằng nào đc chọn
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        NguoiDung nguoiDung = list.get(rowIndex);//lấy dòng đc chọn
        switch (columnIndex) {//dữ liệu trên cột nào của dòng chọn
            case 0:
                nguoiDung.setMaND((Integer) aValue);
                System.out.println(nguoiDung.getMaND());

                break;
            case 1:
                nguoiDung.setTenNguoiDung((String) aValue);
                System.out.println(nguoiDung.getTenNguoiDung());
                String sql = "update nguoidung set TENnd=? where MAnd=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql);
                    ps.setString(1, nguoiDung.getTenNguoiDung());
                    ps.setInt(2, nguoiDung.getMaND());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công tên");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại tên");
                }
                break;
            case 2:
                nguoiDung.setMatKhau((String) aValue);
                System.out.println(nguoiDung.getMatKhau());
                String sql1 = "update nguoidung set matkhau=? where MAnd=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql1);
                    ps.setString(1, nguoiDung.getMatKhau());
                    ps.setInt(2, nguoiDung.getMaND());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công mật khẩu");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại mật khẩu");
                }
                break;
            case 3:
                nguoiDung.setEmail((String) aValue);
                System.out.println(nguoiDung.getEmail());
                String checkemail = "\\w+@\\w+(\\.\\w+){1,2}";
                if (!((String) aValue).matches(checkemail)) {
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại email");
                    break;

                }
                String sql2 = "update nguoidung set email=? where MAnd=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql2);
                    ps.setString(1, nguoiDung.getEmail());
                    ps.setInt(2, nguoiDung.getMaND());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công email");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại email");
                }
                break;
            case 4:

                nguoiDung.setGioiTinh((Integer) aValue);
                System.out.println(nguoiDung.getGioiTinh());
                String sql3 = "update nguoidung set gioitinh=? where MAnd=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql3);
                    ps.setString(1, String.valueOf(nguoiDung.getGioiTinh()));
                    ps.setInt(2, nguoiDung.getMaND());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công giới tính");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại giới tính");
                }
                break;
            case 5:

                nguoiDung.setVaiTro((Integer) aValue);
                System.out.println(nguoiDung.getVaiTro());
                String sql4 = "update nguoidung set vaitro=? where MAnd=?";
                try {

                    PreparedStatement ps = connect.con().prepareStatement(sql4);
                    ps.setString(1, String.valueOf(nguoiDung.getVaiTro()));
                    ps.setInt(2, nguoiDung.getMaND());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công vai trò");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại vai trò");
                }
                break;

            case 6:

                nguoiDung.setMapin((String) aValue);
                System.out.println(nguoiDung.getMapin());
                String sql6 = "update nguoidung set mapin=? where MAnd=?";
                try {

                    PreparedStatement ps = connect.con().prepareStatement(sql6);
                    ps.setString(1, String.valueOf(nguoiDung.getMapin()));
                    ps.setInt(2, nguoiDung.getMaND());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công mã pin");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại mã pin");
                }
                break;
        }


    }
}
