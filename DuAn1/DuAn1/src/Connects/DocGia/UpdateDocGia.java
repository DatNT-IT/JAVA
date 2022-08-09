package Connects.DocGia;

import Connects.Connect;
import model.ChiTietTacGia;
import model.DocGia;
import views.FromChinh;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateDocGia extends AbstractTableModel {
    private Connect connect = new Connect();
    private List<DocGia> list;
    private final Class[] classes = {Integer.class, String.class, Integer.class, String.class, String.class, String.class, String.class};
    private final String low[] = {"mã độc giả", "tên độc giả", "Giới Tính", "ngày sinh", "số CCCD", "SĐT", "địa chỉ"};

    public UpdateDocGia(List<DocGia> list) {
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
        DocGia docGia = list.get(rowIndex);
        if (columnIndex == 0) {
            return false;//không được sửa ô cột đầu là mã
        }
//        if (columnIndex==1){
//            new FromChinh();
//            System.out.println(docGia.getTenDG());
//        }//hiện têndg dòng cột đang chọn
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
        DocGia docGia = list.get(rowIndex);//lấy dữ liệu ô thì phải biết n thuộc dòng bn
        switch (columnIndex) {
            case 0:
                return docGia.getMaDG();//cột 1 là mã
            case 1:
                return docGia.getTenDG();//cột 2 là tên
            case 2:
                return docGia.getGioiTinh();//cột 3 là địa chỉ
            case 3:
                return docGia.getNgaySinh();
            case 4:
                return docGia.getSoCCCD();
            case 5:
                return docGia.getSDT();
            case 6:
                return docGia.getDiaChi();
            default:
                return null;//không thằng nào đc chọn
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        DocGia docGia = list.get(rowIndex);//lấy dòng đc chọn
        switch (columnIndex) {//dữ liệu trên cột nào của dòng chọn
            case 0:
                docGia.setMaDG((Integer) aValue);
                System.out.println(docGia.getMaDG());

                break;
            case 1:
                docGia.setTenDG((String) aValue);
                System.out.println(docGia.getTenDG());
                String sql = "update docgia set TENdg=? where MAdg=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql);
                    ps.setString(1, docGia.getTenDG());
                    ps.setInt(2, docGia.getMaDG());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công tên");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại tên");
                }
                break;
            case 2:
                docGia.setGioiTinh((Integer) aValue);
                System.out.println(docGia.getGioiTinh());
                String sql1 = "update docgia set gioitinh=? where MAdg=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql1);
                    ps.setInt(1, docGia.getGioiTinh());
                    ps.setInt(2, docGia.getMaDG());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công giới tính");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại giới tính");
                }
                break;
            case 3:
                docGia.setNgaySinh((String) aValue);
                System.out.println(docGia.getNgaySinh());
                String sql2 = "update docgia set ngaysinh=? where MAdg=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql2);
                    ps.setString(1, docGia.getNgaySinh());
                    ps.setInt(2, docGia.getMaDG());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công ngày sinh");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại ngày sinh");
                }
                break;
            case 4:
                docGia.setSoCCCD((String) aValue);
                System.out.println(docGia.getSoCCCD());
                String sql3 = "update docgia set socccd=? where MAdg=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql3);
                    ps.setString(1, docGia.getSoCCCD());
                    ps.setInt(2, docGia.getMaDG());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công cccd");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại cccd");
                }
                break;
            case 5:
                docGia.setSDT((String) aValue);
                System.out.println(docGia.getSDT());
                String sql4 = "update docgia set sdt=? where MAdg=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql4);
                    ps.setString(1, docGia.getSDT());
                    ps.setInt(2, docGia.getMaDG());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công sdt");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại sdt");
                }
                break;
            case 6:
                docGia.setDiaChi((String) aValue);
                System.out.println(docGia.getDiaChi());
                String sql5 = "update docgia set diachi=? where MAdg=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql5);
                    ps.setString(1, docGia.getDiaChi());
                    ps.setInt(2, docGia.getMaDG());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công địa chỉ");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại địa chỉ");
                }
                break;

        }


    }
}
