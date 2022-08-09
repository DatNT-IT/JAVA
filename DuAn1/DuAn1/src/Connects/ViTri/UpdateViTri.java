package Connects.ViTri;

import Connects.Connect;
import model.LoaiSach;
import model.ViTri;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateViTri extends AbstractTableModel {
    private Connect connect = new Connect();
    private List<ViTri> list;
    private final Class[] classes = {Integer.class, String.class, String.class, String.class, String.class};
    private final String low[] = {"mã vị trí", "hàng sách", "ô sách", "tủ sách", "mô tả"};

    public UpdateViTri(List<ViTri> list) {
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
            return false;//không được sửa ô cột đầu là mã
        }
        if (columnIndex == 4) {
            return false;//không được sửa ô cột đầu là mã
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
        ViTri viTri = list.get(rowIndex);//lấy dữ liệu ô thì phải biết n thuộc dòng bn
        switch (columnIndex) {
            case 0:
                return viTri.getMaVT();//cột 1 là mã
            case 1:
                return viTri.getHangSach();//cột 2 là tên
            case 2:
                return viTri.getoSach();//cột 2 là tên

            case 3:
                return viTri.getTuSach();//cột 2 là tên
            case 4:
                return viTri.getMoTa();//cột 2 là tên

            default:
                return null;//không thằng nào đc chọn
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ViTri viTri = list.get(rowIndex);//lấy dòng đc chọn
        switch (columnIndex) {//dữ liệu trên cột nào của dòng chọn
            case 0:
                viTri.setMaVT((Integer) aValue);
                System.out.println(viTri.getMaVT());

                break;
            case 1:
                viTri.setHangSach((String) aValue);
                System.out.println(viTri.getHangSach());
                String sql = "update vitri set hangsach=? where MAvt=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql);
                    ps.setString(1, viTri.getHangSach());
                    ps.setInt(2, viTri.getMaVT());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công hàng sách");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại hàng sách");
                }
                break;
            case 2:
                viTri.setoSach((String) aValue);
                System.out.println(viTri.getoSach());
                String sql1 = "update vitri set osach=? where MAvt=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql1);
                    ps.setString(1, viTri.getoSach());
                    ps.setInt(2, viTri.getMaVT());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công ô sách");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại ô sách");
                }
                break;
            case 3:
                viTri.setTuSach((String) aValue);
                System.out.println(viTri.getTuSach());
                String sql2 = "update vitri set tusach=? where MAvt=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql2);
                    ps.setString(1, viTri.getTuSach());
                    ps.setInt(2, viTri.getMaVT());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công tủ sách");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại tủ sách");
                }
                break;
            case 4:
                viTri.setMoTa((String) aValue);
                System.out.println(viTri.getMoTa());
                String sql3 = "update vitri set mota=? where MAvt=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql3);
                    ps.setString(1, viTri.getMoTa());
                    ps.setInt(2, viTri.getMaVT());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công mô tả sách");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại mô tả sách");
                }
                break;

        }


    }
}
