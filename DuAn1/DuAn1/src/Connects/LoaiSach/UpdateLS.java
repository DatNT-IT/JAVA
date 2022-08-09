package Connects.LoaiSach;

import Connects.Connect;
import model.ChiTietTacGia;
import model.LoaiSach;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateLS extends AbstractTableModel {
    private Connect connect = new Connect();
    private List<LoaiSach> list;
    private final Class[] classes = {Integer.class, String.class};
    private final String low[] = {"mã ls", "tên ls"};

    public UpdateLS(List<LoaiSach> list) {
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
        LoaiSach loaiSach = list.get(rowIndex);//lấy dữ liệu ô thì phải biết n thuộc dòng bn
        switch (columnIndex) {
            case 0:
                return loaiSach.getMaLoaiSach();//cột 1 là mã
            case 1:
                return loaiSach.getTenLoaiSach();//cột 2 là tên

            default:
                return null;//không thằng nào đc chọn
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        LoaiSach loaiSach = list.get(rowIndex);//lấy dòng đc chọn
        switch (columnIndex) {//dữ liệu trên cột nào của dòng chọn
            case 0:
                loaiSach.setMaLoaiSach((Integer) aValue);
                System.out.println(loaiSach.getMaLoaiSach());

                break;
            case 1:
                loaiSach.setTenLoaiSach((String) aValue);
                System.out.println(loaiSach.getTenLoaiSach());
                String sql = "update loaisach set TENls=? where MAls=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql);
                    ps.setString(1, loaiSach.getTenLoaiSach());
                    ps.setInt(2, loaiSach.getMaLoaiSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công tên");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại tên");
                }
                break;


        }


    }
}
