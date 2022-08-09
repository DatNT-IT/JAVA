package Connects.KhoSach;

import Connects.Connect;
import model.ChiTietSach;
import model.DocGia;
import model.NXBSach;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UpdateSach extends AbstractTableModel {
    private Connect connect = new Connect();
    private List<ChiTietSach> list;
    private final Class[] classes = {Integer.class, String.class, String.class, String.class, Integer.class, Integer.class, String.class, String.class, Integer.class, Integer.class, String.class, String.class, Integer.class};
    private final String low[] = {"mã sách", "tên sách", "tác giả", "thể loại", "tái bản lần", "giá bìa", "NXB", "ngày nhập", "số lượng", "số trang", "vị trí", "tình trạng", "cho phép mượn"};

    public UpdateSach(List<ChiTietSach> list) {
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
        if (columnIndex == 7) {
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
        ChiTietSach sach = list.get(rowIndex);//lấy dữ liệu ô thì phải biết n thuộc dòng bn

        switch (columnIndex) {
            case 0:
                return sach.getMaSach();//cột 1 là mã
            case 1:
                return sach.getTenSach();//cột 2 là tên
            case 2:

                return sach.getTenTG();//cột 3 là địa chỉ
            case 3:
                return sach.getTenLS();
            case 4:
                return sach.getLanTaiBan();
            case 5:
                return sach.getGiaBia();
            case 6:
                return sach.getTenNXB();
            case 7:
                return sach.getNgayNhap();
            case 8:
                return sach.getSoLuong();
            case 9:
                return sach.getSoTrang();
            case 10:
                return sach.getTenVT();
            case 11:
                return sach.getTinhTrang();
            case 12:
                return sach.getDuocPhepMuon();

            default:
                return null;//không thằng nào đc chọn
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ChiTietSach sach = list.get(rowIndex);//lấy dòng đc chọn
        switch (columnIndex) {//dữ liệu trên cột nào của dòng chọn
            case 0:
                sach.setMaSach((Integer) aValue);
                System.out.println(sach.getMaSach());

                break;
            case 1:
                sach.setTenSach((String) aValue);
                System.out.println(sach.getTenSach());
                String sql = "update chitietsach set TENsach=? where MActs=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql);
                    ps.setString(1, sach.getTenSach());
                    ps.setInt(2, sach.getMaSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công tên");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại tên");
                }
                break;
            case 2:
                sach.setTenTG((String) aValue);
                System.out.println(sach.getTenTG());
                int matg = 0;
                String sqltg = "SELECT * FROM tacgia WHERE tentg like N'%" + sach.getTenTG() + "%' ";

                try {
                    Connect con = new Connect();
                    PreparedStatement ps = con.con().prepareStatement(sqltg);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        matg = rs.getInt(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "thêm thất bại");
                    return;
                }
                String sql1 = "update chitietsach set matg=? where MActs=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql1);
                    ps.setInt(1, matg);
                    ps.setInt(2, sach.getMaSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công tác giả");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại tác giả");
                }
                break;
            case 3:
                sach.setTenLS((String) aValue);
                System.out.println(sach.getTenLS());
                int matl = 0;
                String sqlls = "SELECT * FROM LOAISACH WHERE tenls like N'%" + sach.getTenLS() + "%' ";

                try {
                    Connect con = new Connect();
                    PreparedStatement ps = con.con().prepareStatement(sqlls);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        matl = rs.getInt(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "thêm thất bại");
                    return;
                }
                String sql2 = "update chitietsach set mals=? where MActs=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql2);
                    ps.setInt(1, matl);
                    ps.setInt(2, sach.getMaSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công ls");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại ls");
                }
                break;
            case 4:
                sach.setLanTaiBan((Integer) aValue);
                System.out.println(sach.getLanTaiBan());
                String sql3 = "update chitietsach set lantaiban=? where MActs=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql3);
                    ps.setInt(1, sach.getLanTaiBan());
                    ps.setInt(2, sach.getMaSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công lần tái bản");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại lần tái bản");
                }
                break;
            case 5:
                sach.setGiaBia((Integer) aValue);
                System.out.println(sach.getGiaBia());
                String sql4 = "update chitietsach set giabia=? where MActs=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql4);
                    ps.setInt(1, sach.getGiaBia());
                    ps.setInt(2, sach.getMaSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công giá bìa");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại giá bìa");
                }
                break;
            case 6:
                sach.setTenNXB((String) aValue);
                System.out.println(sach.getTenNXB());
                int manxb = 0;
                String sqlnxb = "SELECT * FROM nxb WHERE tennxb like N'%" + sach.getTenNXB() + "%' ";

                try {
                    Connect con = new Connect();
                    PreparedStatement ps = con.con().prepareStatement(sqlnxb);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        manxb = rs.getInt(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "thêm thất bại");
                    return;
                }
                String sql5 = "update chitietsach set manxb=? where MActs=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql5);
                    ps.setInt(1, manxb);
                    ps.setInt(2, sach.getMaSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công nxb");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại nxb");
                }
                break;
            case 7:
                sach.setNgayNhap((String) aValue);
                System.out.println(sach.getNgayNhap());
                String sql6 = "update chitietsach set ngaynhap=? where MActs=?";

                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql6);
                    ps.setString(1, sach.getNgayNhap());
                    ps.setInt(2, sach.getMaSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công ngày nhập");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại ngày nhập");
                }
                break;
            case 8:
                sach.setSoLuong((Integer) aValue);
                System.out.println(sach.getSoLuong());
                String sql7 = "update chitietsach set soluong=? where MActs=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql7);
                    ps.setInt(1, sach.getSoLuong());
                    ps.setInt(2, sach.getMaSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công số lượng");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại số lượng");
                }
                break;
            case 9:
                sach.setSoTrang((Integer) aValue);
                System.out.println(sach.getSoTrang());
                String sql8 = "update chitietsach set sotrang=? where MActs=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql8);
                    ps.setInt(1, sach.getSoTrang());
                    ps.setInt(2, sach.getMaSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công số trang");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại số trang");
                }
                break;
            case 10:
                sach.setTenVT((String) aValue);
                System.out.println(sach.getTenVT());
                int mavt = 0;
                String sqlvt = "SELECT * FROM VITRI WHERE mota like N'%" + "tủ sách dân chí, hàng 1,ô 2" + "%' ";

                try {
                    Connect con = new Connect();
                    PreparedStatement ps = con.con().prepareStatement(sqlvt);
                    // ps.setString(1,"tủ sách dân chí, hàng 1,ô 2");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        mavt = rs.getInt(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "thêm thất bại");
                    return;
                }
                String sql9 = "update chitietsach set mavt=? where MActs=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql9);
                    ps.setInt(1, mavt);
                    ps.setInt(2, sach.getMaSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công vị trí");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại vị trí");
                }
                break;

            case 11:
                sach.setTinhTrang((String) aValue);
                System.out.println(sach.getTinhTrang());
                String sql10 = "update chitietsach set tinhtrang=? where MActs=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql10);
                    ps.setString(1, sach.getTinhTrang());
                    ps.setInt(2, sach.getMaSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công tình trạng");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại tình trạng");
                }
                break;
            case 12:
                sach.setDuocPhepMuon((Integer) aValue);
                System.out.println(sach.getDuocPhepMuon());
                String sql11 = "update chitietsach set duocmuon=? where MActs=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql11);
                    ps.setInt(1, sach.getDuocPhepMuon());
                    ps.setInt(2, sach.getMaSach());


                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "cập nhật thành công cho mượn");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "cập nhật thất bại cho mượn");
                }
                break;
        }

    }
}
