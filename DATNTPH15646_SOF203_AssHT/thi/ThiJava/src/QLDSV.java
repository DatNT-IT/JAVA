import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class QLDSV extends JFrame {
    private JPanel JPL;
    private JLabel lblmasvtim;
    private JTextField txtmasvtim;
    private JButton btntimkiem;
    private JLabel lblqldsv;
    private JLabel lblhoten;
    private JTextField txthoten;
    private JLabel lblmasv;
    private JTextField txtmasv;
    private JLabel lblanh;
    private JTextField txtanh;
    private JLabel lbltin;
    private JTextField txttin;
    private JLabel lblgdtc;
    private JTextField txtgdtc;
    private JLabel lbldiemtb;
    private JButton btnnew;
    private JButton btnsave;
    private JButton btndelete;
    private JButton btnupdate;
    private JButton btnlon;
    private JButton btnlonlon;
    private JButton btnnho;
    private JButton btnnhonho;
    private JTable tblqldsv;
    private JButton exitButton;
    String lor[] = {"Mã SV", "Họ tên", "Tiếng anh", "Tin học", "GDTC", "Điểm TB"};
    SQLServerDataSource ds = new SQLServerDataSource();
    Connection con;


    DefaultTableModel dtb;
     int vitri;

    public QLDSV() {

        add(JPL);
        setTitle("Quản lý điểm sinh viên");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ds.setUser("sa");
        ds.setPassword("123");
        ds.setServerName("DESKTOP-PFMTEUO\\SQLEXPRESS");
        ds.setPortNumber(1433);
        ds.setDatabaseName("ASSJAVA3");

        dtb = new DefaultTableModel(lor, 0);
        dtb.setRowCount(0);
        tblqldsv.setModel(dtb);

        hienthi();

        btntimkiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtmasvtim.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "bạn hãy nhập mã sv cần tìm");
                } else {
                    try {
                        String sql = "SELECT grade.masv,hoten,tienganh,tinhoc,gdtc FROM dbo.students JOIN dbo.grade ON grade.masv = students.masv WHERE students.masv =?";
                        PreparedStatement pstat = con.prepareStatement(sql);
                        pstat.setString(1, txtmasvtim.getText());
                        ResultSet rs = pstat.executeQuery();
                        dtb.setRowCount(0);
                        if (rs.next()) {
                            dtb.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                                    (rs.getFloat(3) + rs.getFloat(4) + rs.getFloat(5)) / 3});
                            if (dtb.getRowCount() > 0) {
                                int index = 0;
                                tblqldsv.setRowSelectionInterval(index, index);
                                txtmasv.setText(String.valueOf(dtb.getValueAt(index, 0)));
                                txthoten.setText(String.valueOf(dtb.getValueAt(index, 1)));
                                txtanh.setText(String.valueOf(dtb.getValueAt(index, 2)));
                                txttin.setText(String.valueOf(dtb.getValueAt(index, 3)));
                                txtgdtc.setText(String.valueOf(dtb.getValueAt(index, 4)));
                                lbldiemtb.setText(String.valueOf(dtb.getValueAt(index, 5)));
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "không tìm thấy sv");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "lỗi");
                    }
                }
            }
        });
        tblqldsv.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tblqldsv.getSelectedRow();
                if (index == -1) {
                    return;
                }
                txtmasv.setText(String.valueOf(dtb.getValueAt(index, 0)));
                txthoten.setText(String.valueOf(dtb.getValueAt(index, 1)));
                txtanh.setText(String.valueOf(dtb.getValueAt(index, 2)));
                txttin.setText(String.valueOf(dtb.getValueAt(index, 3)));
                txtgdtc.setText(String.valueOf(dtb.getValueAt(index, 4)));
                lbldiemtb.setText(String.valueOf(dtb.getValueAt(index, 5)));


            }
        });
        btnnew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resec();
            }
        });
        btnsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
                int result = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc muốn save sinh viên này",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION) {
                    try {
                        String sql = "INSERT INTO dbo.grade\n" +
                                "(\n" +
                                "\n" +
                                "    masv,\n" +
                                "    tienganh,\n" +
                                "    tinhoc,\n" +
                                "    gdtc\n" +
                                ")\n" +
                                "VALUES\n" +
                                "(  ?,?,?,?\n" +
                                "    )";
                        PreparedStatement pstat = con.prepareStatement(sql);
                        pstat.setString(1, txtmasv.getText());
                        pstat.setDouble(2, Double.parseDouble(txtanh.getText()));
                        pstat.setDouble(3, Double.parseDouble(txttin.getText()));
                        pstat.setDouble(4, Double.parseDouble(txtgdtc.getText()));
                        if (pstat.executeUpdate()>0) {
                            JOptionPane.showMessageDialog(null, "save thành công");
                            hienthi();
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "save thất bại");
                    };
                }
            }
        });
        btndelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtmasv.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "nhập mã muốn xóa");
                    return;
                }
                int result = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc muốn xóa sinh viên này",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION) {
                    try {

                        String sql = "DELETE FROM dbo.grade WHERE masv =?";
                        PreparedStatement pstat = con.prepareStatement(sql);
                        pstat.setString(1, txtmasv.getText());
                        if (pstat.executeUpdate() > 0) {
                            JOptionPane.showMessageDialog(null, "xóa thành công ");
                            hienthi();
                        }
                        txtmasvtim.setText("");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "xóa thất bại");
                    }
                }
            }
        });
        btnupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
                txtmasvtim.setText("");
                int result = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc muốn update sinh viên này",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    try  {


                        String sql = "UPDATE dbo.grade SET tienganh=?,tinhoc=?,gdtc=? WHERE masv=?";
                        PreparedStatement pstat = con.prepareStatement(sql);
                        pstat.setString(4,txtmasv.getText() );
                        pstat.setFloat(1, Float.parseFloat(txtanh.getText()));
                        pstat.setFloat(2, Float.parseFloat(txttin.getText()));
                        pstat.setFloat(3, Float.parseFloat(txtgdtc.getText()));
                        if (pstat.executeUpdate()>0) {
                            JOptionPane.showMessageDialog(null, "update thành công");
                            hienthi();
                        }
                    } catch (
                            Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "update thất bại");
                    }
                }

            }
            });

        btnnho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            vitri =0;
                tblqldsv.setRowSelectionInterval(vitri, vitri);
                txtmasv.setText(String.valueOf(dtb.getValueAt(vitri, 0)));
                txthoten.setText(String.valueOf(dtb.getValueAt(vitri, 1)));
                txtanh.setText(String.valueOf(dtb.getValueAt(vitri, 2)));
                txttin.setText(String.valueOf(dtb.getValueAt(vitri, 3)));
                txtgdtc.setText(String.valueOf(dtb.getValueAt(vitri, 4)));
                lbldiemtb.setText(String.valueOf(dtb.getValueAt(vitri, 5)));

            }
        });
        btnlon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vitri =dtb.getRowCount()-1;
                tblqldsv.setRowSelectionInterval(vitri, vitri);
                txtmasv.setText(String.valueOf(dtb.getValueAt(vitri, 0)));
                txthoten.setText(String.valueOf(dtb.getValueAt(vitri, 1)));
                txtanh.setText(String.valueOf(dtb.getValueAt(vitri, 2)));
                txttin.setText(String.valueOf(dtb.getValueAt(vitri, 3)));
                txtgdtc.setText(String.valueOf(dtb.getValueAt(vitri, 4)));
                lbldiemtb.setText(String.valueOf(dtb.getValueAt(vitri, 5)));
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChonChuongTrinh().setVisible(true);
                dispose();//đóng ctrinh
            }
        });
        btnlonlon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                vitri++;
                if (vitri==dtb.getRowCount()){
                    vitri=0;
                }
                tblqldsv.setRowSelectionInterval(vitri, vitri);
                txtmasv.setText(String.valueOf(dtb.getValueAt(vitri, 0)));
                txthoten.setText(String.valueOf(dtb.getValueAt(vitri, 1)));
                txtanh.setText(String.valueOf(dtb.getValueAt(vitri, 2)));
                txttin.setText(String.valueOf(dtb.getValueAt(vitri, 3)));
                txtgdtc.setText(String.valueOf(dtb.getValueAt(vitri, 4)));
                lbldiemtb.setText(String.valueOf(dtb.getValueAt(vitri, 5)));
            }
        });
        btnnhonho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vitri --;
                if (vitri==-1){
                    vitri=tblqldsv.getRowCount()-1;
                }
                tblqldsv.setRowSelectionInterval(vitri, vitri);
                txtmasv.setText(String.valueOf(dtb.getValueAt(vitri, 0)));
                txthoten.setText(String.valueOf(dtb.getValueAt(vitri, 1)));
                txtanh.setText(String.valueOf(dtb.getValueAt(vitri, 2)));
                txttin.setText(String.valueOf(dtb.getValueAt(vitri, 3)));
                txtgdtc.setText(String.valueOf(dtb.getValueAt(vitri, 4)));
                lbldiemtb.setText(String.valueOf(dtb.getValueAt(vitri, 5)));
            }
        });


        btndelete.addMouseListener(new MouseAdapter() {
        });
    }

    public void check() {
        if (txtanh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhập điểm anh");
            txtanh.requestFocus();
            return;

        } else if (txttin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhập điểm tin");
            txttin.requestFocus();
            return;

        } else if (txtgdtc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhập điểm gdtc");
            txtgdtc.requestFocus();
            return;

        } else {
            double diemanh, diemtin, diemgdtc;
            try {
                diemanh = Double.parseDouble(txtanh.getText());
                diemtin = Double.parseDouble(txttin.getText());
                diemgdtc = Double.parseDouble(txtgdtc.getText());
                if (diemanh > 10)
                    if (0 < diemanh || diemanh < 10) {
                        JOptionPane.showMessageDialog(null, "nhập điểm anh trong khoảng 0-10");
                        txtanh.requestFocus();
                        return;
                    } else if (0 < diemtin || diemtin < 10) {
                        JOptionPane.showMessageDialog(null, "nhập điểm tin trong khoảng 0-10");
                        txttin.requestFocus();
                        return;
                    } else if (0 < diemgdtc || diemgdtc < 10) {
                        JOptionPane.showMessageDialog(null, "nhập điểm gdtc trong khoảng 0-10");
                        txtgdtc.requestFocus();
                        return;
                    }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "nhập điểm phải là số");
                return;
            }

        }
    }

    public void hienthi() {
        try {
            con = ds.getConnection();
            String sql = " SELECT TOP(3) grade.masv,hoten,tienganh,tinhoc,gdtc,((tienganh+tinhoc+gdtc)/3)AS dtb FROM dbo.students JOIN dbo.grade ON grade.masv = students.masv\n" +
                    "ORDER BY ((tienganh+tinhoc+gdtc)/3) DESC";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            dtb.setRowCount(0);
            while (rs.next()) {
                dtb.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        (rs.getFloat(3) + rs.getFloat(4) + rs.getFloat(5)) / 3});
            }
            if (dtb.getRowCount() > 0) {
                int index = 0;
                tblqldsv.setRowSelectionInterval(index, index);
                txtmasv.setText(String.valueOf(dtb.getValueAt(index, 0)));
                txthoten.setText(String.valueOf(dtb.getValueAt(index, 1)));
                txtanh.setText(String.valueOf(dtb.getValueAt(index, 2)));
                txttin.setText(String.valueOf(dtb.getValueAt(index, 3)));
                txtgdtc.setText(String.valueOf(dtb.getValueAt(index, 4)));
                lbldiemtb.setText(String.valueOf(dtb.getValueAt(index, 5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resec() {
        txtmasv.setText("");
        txthoten.setText("");
        txtanh.setText("");
        txttin.setText("");
        txtgdtc.setText("");
        txtmasvtim.setText("");
        lbldiemtb.setText("");
    }

}
