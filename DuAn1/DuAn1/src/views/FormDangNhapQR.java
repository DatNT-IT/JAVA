package views;

import DAO.DangNhap;
import javazoom.jl.player.Player;
import model.NguoiDung;
import service.SeviceNguoiDung;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.sql.SQLException;

public class FormDangNhapQR extends JFrame {
    DangNhap dangNhap = new DangNhap();
    NguoiDung nguoiDung = new NguoiDung();
    SeviceNguoiDung seviceNguoiDung = new SeviceNguoiDung();
    private JPanel JPL;
    private JTextField txtdangnhap;
    private JPasswordField txtmapin;
    private JButton btndangnhap;
    private JButton btnthoat;
    private JLabel iconpass;
    private String user;
    int dem = 0;
    private int check = 1;

    public FormDangNhapQR() {
        add(JPL);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        txtmapin.setEchoChar('*');
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    new FromDangNhap();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btndangnhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtmapin.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được để trống mã pin");
                    return;
                }
                int tk, mapin1;
                try {
                    tk = Integer.parseInt(user);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "vui lòng quét lại mã");
                    try {
                        new FromDangNhap();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    return;
                }
                try {
                    mapin1 = Integer.parseInt(txtmapin.getText());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "mã pin phải  phải là số");
                    return;
                }
                try {
                    nguoiDung = dangNhap.quetqr(txtdangnhap.getText(), String.valueOf(txtmapin.getPassword()));
                    if (dem >= 5) {
                        new FromDangNhap();
                    }
                    if (nguoiDung == null) {
                        dem++;
                        return;
                    }

                    JOptionPane.showMessageDialog(null, "Chào bạn đến với thư viện sách ");
                    FromChinh fromChinh = new FromChinh();
                    fromChinh.setUser(Integer.parseInt(txtdangnhap.getText()));
                    fromChinh.setPass(nguoiDung.getMatKhau());
                    fromChinh.setVaitro(nguoiDung.getVaiTro());
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
            }
        });
        btnthoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new FromDangNhap();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        iconpass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (check == 1) {
                    txtmapin.setEchoChar((char) 0);
                    check++;
                    return;

                }
                txtmapin.setEchoChar('*');
                check = 1;
                return;
            }
        });
    }

    public void setUser(String user) {
        this.user = user;
        txtdangnhap.setText(user);
    }

    public static void main(String[] args) {
        new FormDangNhapQR();
    }
}
