package Connects.NXB;

import Connects.Connect;
import model.LoaiSach;
import model.NXBSach;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateNXB extends AbstractTableModel {
    private Connect connect = new Connect();
    private List<NXBSach> list;
    private final Class[] classes = {Integer.class, String.class, String.class};
    private final String low[] = {"mã NXB", "tên NXB", "địa chỉ"};

    public UpdateNXB(List<NXBSach> list) {
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
        NXBSach nxbSach = list.get(rowIndex);//lấy dữ liệu ô thì phải biết n thuộc dòng bn
        switch (columnIndex) {
            case 0:
                return nxbSach.getMaNXB();//cột 1 là mã
            case 1:
                return nxbSach.getTenNXB();//cột 2 là tên
            case 2:
                return nxbSach.getDiaChiNXB();//cột 3 là tên
            default:
                return null;//không thằng nào đc chọn
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        NXBSach nxbSach = list.get(rowIndex);//lấy dòng đc chọn
        switch (columnIndex) {//dữ liệu trên cột nào của dòng chọn
            case 0:
                nxbSach.setMaNXB((Integer) aValue);
                System.out.println(nxbSach.getMaNXB());

                break;
            case 1:
                nxbSach.setTenNXB((String) aValue);
                System.out.println(nxbSach.getTenNXB());
                String sql = "update nxb set TENnxb=? where MAnxb=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql);
                    ps.setString(1, nxbSach.getTenNXB());
                    ps.setInt(2, nxbSach.getMaNXB());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công tên");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại tên");
                }
                break;
            case 2:
                nxbSach.setDiaChiNXB((String) aValue);
                System.out.println(nxbSach.getDiaChiNXB());
                String sql1 = "update nxb set diachinxb=? where MAnxb=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql1);
                    ps.setString(1, nxbSach.getDiaChiNXB());
                    ps.setInt(2, nxbSach.getMaNXB());


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
