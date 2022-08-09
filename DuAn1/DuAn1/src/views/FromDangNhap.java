package views;

import DAO.DangNhap;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import giongnoi.PlayQR;
import giongnoi.PlayQRCode;
import javazoom.jl.player.Player;
import model.NguoiDung;
import service.SeviceNguoiDung;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.sql.SQLException;

public class FromDangNhap extends JFrame {
    NguoiDung nguoiDung;
    SeviceNguoiDung seviceNguoiDung = new SeviceNguoiDung();
    private JPanel JPL;
    private JTextField txtdangnhap;
    private JPasswordField txtpass;
    private JButton btndangnhap;
    private JCheckBox ckbnhomk;
    private JButton btnthoat;
    private JLabel lblquenpass;
    private JButton btnDangKi;
    private JLabel lblQR;
    private JButton newButton;
    private JLabel lblTile;
    private JButton btnquet;
    private JLabel iconpass;
    private JComboBox cbbChon;
    private int check = 1;
    DangNhap dangNhap = new DangNhap();
    int dem = 0;

    public FromDangNhap() throws SQLException {

        add(JPL);
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        chuChay();
        new TieuDeNhay();
        txtpass.setEchoChar('*');

        btndangnhap.setMnemonic(KeyEvent.VK_ENTER);
        //nhớ mk
        try {
            if (dangNhap.chuyen()[0] == null) {
                txtdangnhap.setText("");
                txtpass.setText("");
            } else {
                txtdangnhap.setText(dangNhap.chuyen()[0]);
                txtpass.setText(dangNhap.chuyen()[1]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        btnthoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        lblquenpass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new formChangePass();

            }
        });
        btndangnhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seviceNguoiDung.check(txtdangnhap.getText(), txtpass.getText()) == true) {
                    try {
                        nguoiDung = dangNhap.login(txtdangnhap.getText(), String.valueOf(txtpass.getPassword()));
                        if (dem >= 5) {
                            System.out.println("5");
                            int i = JOptionPane.showConfirmDialog(null, "Bạn có muốn quên mật khẩu không?", "Thông Báo", JOptionPane.YES_NO_OPTION);
                            if (i == JOptionPane.YES_OPTION) {
                                new formChangePass();
                                dispose();
                            }
                        }
                        if (nguoiDung == null) {
                            dem++;
                            return;
                        }


                        JOptionPane.showMessageDialog(null, "Chào bạn đến với thư viện sách ");
                        FromChinh fromChinh = new FromChinh();
                        fromChinh.setUser(Integer.parseInt(txtdangnhap.getText()));
                        fromChinh.setPass(txtpass.getText());
                        fromChinh.setVaitro(nguoiDung.getVaiTro());

                        dispose();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


        //gõ phím enter
        btndangnhap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        nguoiDung = dangNhap.login(txtdangnhap.getText(), String.valueOf(txtpass.getPassword()));
                        if (nguoiDung == null) {
                            dem++;
                        }
                        if (dem >= 5) {
                            int i = JOptionPane.showConfirmDialog(null, "Bạn có phải là chủ của tk" + txtdangnhap.getText() + " không?", "Thông Báo", JOptionPane.YES_NO_OPTION);
                            if (i == JOptionPane.YES_OPTION) {
                                new formChangePass();
                                dispose();
                            }
                        }

                        JOptionPane.showMessageDialog(null, "Chào bạn đến với thư viện sách ");
                        FromChinh fromChinh = new FromChinh();
                        fromChinh.setUser(Integer.parseInt(txtdangnhap.getText()));
                        fromChinh.setPass(txtpass.getText());
                        fromChinh.setVaitro(nguoiDung.getVaiTro());
                        System.out.println(nguoiDung.getTenNguoiDung() + nguoiDung.getVaiTro());
                        dispose();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnDangKi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormDangKi();
                dispose();
            }
        });
        ckbnhomk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {


                    if (ckbnhomk.isSelected() == true) {
                        int i = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu mật khẩu không?", "Thông Báo", JOptionPane.OK_CANCEL_OPTION);
                        if (JOptionPane.YES_OPTION == i) {
                            dangNhap.login(txtdangnhap.getText(), String.valueOf(txtpass.getPassword()));
                            try {
                                dangNhap.nho(txtdangnhap.getText(), String.valueOf(txtpass.getPassword()));
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            try {
                                dangNhap.bo(txtdangnhap.getText(), String.valueOf(txtpass.getPassword()));
                                ckbnhomk.setSelected(false);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }

                    } else {
                        int i1 = JOptionPane.showConfirmDialog(null, "Bạn có muốn bỏ lưu mật khẩu không?", "Thông Báo", JOptionPane.OK_CANCEL_OPTION);
                        if (JOptionPane.YES_OPTION == i1) {
                            dangNhap.login(txtdangnhap.getText(), String.valueOf(txtpass.getPassword()));
                            try {
                                dangNhap.bo(txtdangnhap.getText(), String.valueOf(txtpass.getPassword()));
                                ckbnhomk.setSelected(false);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            try {
                                dangNhap.nho(txtdangnhap.getText(), String.valueOf(txtpass.getPassword()));
                                ckbnhomk.setSelected(true);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtdangnhap.setText("");
                txtpass.setText("");
            }
        });
        btnquet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PlayQRCode.main(new String[0]);
                dispose();


            }
        });


        iconpass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (check == 1) {
                    txtpass.setEchoChar((char) 0);
                    check++;
                    return;

                }
                txtpass.setEchoChar('*');
                check = 1;
                return;
            }
        });
    }

    public void chuChay() {
        Thread thread = new Thread() {
            public void run() {
                String txt = lblTile.getText();
                while (true) {
                    txt = txt.substring(1, txt.length()) + txt.charAt(0);
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lblTile.setText(txt);
                }
            }

        };
        thread.start();
    }

    public class TieuDeNhay extends Thread {
        int icout = -1;
        String arrtext[] = {"Đăng nhập", "Đăng", "nhập"};

        public TieuDeNhay() {
            start();
        }

        public void run() {
            while (true) {
                try {
                    icout++;
                    if (icout == arrtext.length) {
                        icout = 0;
                    }
                    setTitle(arrtext[icout]);


                    sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    //public static void main(String[] args) {
//        new FromDangNhap().setVisible(true);
//    }
}
