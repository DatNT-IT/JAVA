import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class QLSV extends JFrame {
    private JPanel JPL;
    private JLabel lblmasv;
    private JLabel lblqlsv;
    private JTextField txtmasv;
    private JTextField txttensv;
    private JTextField txtemail;
    private JTextField txtsdt;
    private JRadioButton rdonam;
    private JRadioButton rdonu;
    private JTextArea txtAdiachi;
    private JTable tblqlsv;
    private JButton btnnew;
    private JButton btndelete;
    private JButton btnsave;
    private JButton btnupdate;
    private JLabel lblanhimg;
    private JButton btnexit;
    private JButton btnanhimgButton;
    String lor[] = {"Mã SV", "Họ tên", "email", "số dt", "giới tính", "địa chỉ", "hình"};
    SQLServerDataSource ds = new SQLServerDataSource();
    Connection con;
    DefaultTableModel dtb;

    public QLSV() {

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
        tblqlsv.setModel(dtb);
hienthi();
        btnnew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resec();
            }
        });
        tblqlsv.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tblqlsv.getSelectedRow();
                if (index==-1){
                    return;
                }
                txtmasv.setText(String.valueOf(dtb.getValueAt(index,0)));
                txttensv.setText(String.valueOf(dtb.getValueAt(index,1)));
                txtemail.setText(String.valueOf(dtb.getValueAt(index,2)));
                txtsdt.setText(String.valueOf(dtb.getValueAt(index,3)));
                String gtinh = String.valueOf(dtb.getValueAt(index,4));
                if (gtinh.trim().equalsIgnoreCase("nam")){
                    rdonam.setSelected(true);
                }else {
                    rdonu.setSelected(true);
                }
                txtAdiachi.setText(String.valueOf(dtb.getValueAt(index,5)));
                String anh = String.valueOf(dtb.getValueAt(index, 6));
                btnanhimgButton.setIcon(  new ImageIcon(anh));

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
                if(result == JOptionPane.YES_OPTION){
                try {
                    con = ds.getConnection();
                    String sql = "INSERT INTO dbo.students\n" +
                            "(\n" +
                            "    masv,\n" +
                            "    hoten,\n" +
                            "    email,\n" +
                            "    sdt,\n" +
                            "    gioitinh,\n" +
                            "    diachi,\n" +
                            "    hinh\n" +
                            ")\n" +
                            "VALUES\n" +
                            "( ?,?,?,?,?,?,?\n" +
                            "    )\n";
                    PreparedStatement pstat = con.prepareStatement(sql);
                    pstat.setString(1, txtmasv.getText());
                    pstat.setString(2, txtmasv.getText());
                    pstat.setString(3, txtemail.getText());
                    pstat.setString(4, txtsdt.getText());
                    String gtinh = rdonam.isSelected()==true?"nam":"nữ";
                    pstat.setString(5, gtinh);
                    pstat.setString(6, txtAdiachi.getText());
                    pstat.setString(7, String.valueOf(btnanhimgButton.getIcon()));
                    int rset = pstat.executeUpdate();
                    if (rset>0) {
                        hienthi();
                        JOptionPane.showMessageDialog(null, "save thành công");
                    }

                } catch (
                        Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null,"save thất bại");
                }}

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
                if(result == JOptionPane.YES_OPTION){
                try {

                    String sql = "DELETE FROM dbo.students WHERE masv =?";
                    PreparedStatement pstat = con.prepareStatement(sql);
                    pstat.setString(1, txtmasv.getText());
                    if (pstat.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "xóa thành công ");
                        hienthi();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "xóa thất bại");
                }}
            }
        });
        btnupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              check();
                int result = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc muốn update sinh viên này",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){

                try  {

                    String sql = "UPDATE dbo.students SET hoten=?,email=?,sdt=?,gioitinh=?,diachi=?,hinh=? WHERE masv=?";
                    PreparedStatement pstat = con.prepareStatement(sql);
                    pstat.setString(1,txttensv.getText() );
                    pstat.setString(2,txtemail.getText() );
                    pstat.setString(3,txtsdt.getText() );
                    String gtinh = rdonam.isSelected()==true?"nam":"nữ";
                    pstat.setString(4,gtinh);
                    pstat.setString(5,txtAdiachi.getText() );
                    pstat.setString(6,String.valueOf(btnanhimgButton.getIcon()) );
                    pstat.setString(7,txtmasv.getText() );
if (pstat.executeUpdate()>0) {
    JOptionPane.showMessageDialog(null, "update thành công");
    hienthi();
}else {
    JOptionPane.showMessageDialog(null, "update thất bại");
}
                } catch (
                        Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "update thất bại");

            }}}
        });
        btnexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChonChuongTrinh().setVisible(true);
                dispose();//đóng ctrinh
            }
        });
        btnanhimgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnanhimgButton.setIcon(image());
            }
        });
    }
    public ImageIcon image() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return new ImageIcon(String.valueOf(chooser.getSelectedFile()));
        }
        return null;

    }
    public void hienthi() {
        try {
            con = ds.getConnection();
            String sql = " SELECT * FROM dbo.students ";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            dtb.setRowCount(0);
            while (rs.next()) {
                dtb.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7)});
            }
            if (dtb.getRowCount() > 0) {
                int index = 0;
                tblqlsv.setRowSelectionInterval(index, index);
                txtmasv.setText(String.valueOf(dtb.getValueAt(index, 0)));
                txttensv.setText(String.valueOf(dtb.getValueAt(index, 1)));
                txtemail.setText(String.valueOf(dtb.getValueAt(index, 2)));
                txtsdt.setText(String.valueOf(dtb.getValueAt(index,3)));
                String gtinh = String.valueOf(dtb.getValueAt(index,4));
                if (gtinh.equalsIgnoreCase("nam")){
                    rdonam.setSelected(true);
                }else {
                    rdonu.setSelected(true);
                }
                txtAdiachi.setText(String.valueOf(dtb.getValueAt(index, 5)));
                String anh = String.valueOf(dtb.getValueAt(index, 6));
                btnanhimgButton.setIcon(  new ImageIcon(anh));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void resec(){
        txtmasv.setText("");
        txttensv.setText("");
        txtemail.setText("");
        txtsdt.setText("");
        txtAdiachi.setText("");
       rdonam.setSelected(true);
        btnanhimgButton.setText("");

    }
    public void check() {
        if (txtmasv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhập mã");
            txtmasv.requestFocus();
            return;

        } else if (txttensv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhập điểm tên");
            txttensv.requestFocus();
            return;

        } else if (txtemail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhập điểm email");
            txtemail.requestFocus();
            return;

        } else if (txtsdt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhập điểm sdt");
            txtsdt.requestFocus();
            return;

        }else if (txtAdiachi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhập điểm địa chỉ");
            txtAdiachi.requestFocus();
            return;
        }else {
            String email = "\\w+@gmail.com";
            String sdt = "0\\d{9}";
            if (!txtemail.getText().matches(email)){
                JOptionPane.showMessageDialog(null,"nhập lại email đúng định dạng");
                txtemail.requestFocus();
                return;
            }else if (!txtsdt.getText().matches(sdt)){
                JOptionPane.showMessageDialog(null,"nhập lại sdt đúng định dạng");
                txtsdt.requestFocus();
                return;
            }
        }



    }


}
