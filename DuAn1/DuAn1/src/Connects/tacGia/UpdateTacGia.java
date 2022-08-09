package Connects.tacGia;

import Connects.Connect;
import model.ChiTietTacGia;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateTacGia extends AbstractTableModel {
    private Connect connect = new Connect();
    private List<ChiTietTacGia> list;
    private final Class[] classes = {Integer.class, String.class, String.class};
    private final String low[] = {"mã tg", "tên tg", "địa chỉ tg"};

    public UpdateTacGia(List<ChiTietTacGia> list) {
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
        ChiTietTacGia tacGia = list.get(rowIndex);//lấy dữ liệu ô thì phải biết n thuộc dòng bn
        switch (columnIndex) {
            case 0:
                return tacGia.getMaTG();//cột 1 là mã
            case 1:
                return tacGia.getTenTG();//cột 2 là tên
            case 2:
                return tacGia.getDiaChi();//cột 3 là địa chỉ
            default:
                return null;//không thằng nào đc chọn
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ChiTietTacGia tacGia = list.get(rowIndex);//lấy dòng đc chọn
        switch (columnIndex) {//dữ liệu trên cột nào của dòng chọn
            case 0:
                tacGia.setMaTG((Integer) aValue);
                System.out.println(tacGia.getMaTG());

                break;
            case 1:
                tacGia.setTenTG((String) aValue);
                System.out.println(tacGia.getMaTG());
                String sql = "update tacgia set TENtg=? where MAtg=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql);
                    ps.setString(1, tacGia.getTenTG());
                    ps.setInt(2, tacGia.getMaTG());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công tên");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại tên");
                }
                break;
            case 2:
                tacGia.setDiaChi((String) aValue);
                System.out.println(tacGia.getDiaChi());
                String sql1 = "update tacgia set diachitg=? where MAtg=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql1);
                    ps.setString(1, tacGia.getDiaChi());
                    ps.setInt(2, tacGia.getMaTG());


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
