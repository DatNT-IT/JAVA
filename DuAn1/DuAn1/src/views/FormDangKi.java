package views;

import DAO.NguoiDungDAO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

public class FormDangKi extends JFrame {
    NguoiDungDAO dao = new NguoiDungDAO();
    private JPanel JPL;
    private JLabel lblTile;
    private JTextField txtTen;
    private JTextField txtEmail;
    private JRadioButton rdonam;
    private JRadioButton rdonu;
    private JButton btngui;
    private JTextField txtma;
    private JButton btndangki;
    private JLabel lblTime;
    private JLabel lblTitle;
    private JButton btnThoat;
    private JPasswordField txtpass;
    private JTextField txtmapin;
    int code;
    private String checkemail = "\\w+@\\w+(\\.\\w+){1,2}";

    public FormDangKi() {
        add(JPL);
        setSize(800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        rdonam.setSelected(true);
        chuChay();
        new TieuDeNhay();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    FromDangNhap dangNhap = new FromDangNhap();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        if (lblTime.getText().equals("0")) {
            btngui.setEnabled(true);
        }

        btngui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtEmail.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được để trống email");
                    return;
                } else if (!txtEmail.getText().matches(checkemail)) {
                    JOptionPane.showMessageDialog(null, "vui lòng nhập đúng định dạng email");
                    return;
                } else {
                    if (lblTime.getText().equals("0") || lblTime.getText().length() == 0) {
                        Random random = new Random();
                        code = random.nextInt(899999) + 100000;
                        //mail nguoi gửi
                        String user = "datntph15646@fpt.edu.vn";
                        String pass = "001202025645ok@@"; // nhập pass
                        String to = txtEmail.getText();
                        String subject = "Mã Xác Nhận Đăng Kí";
                        String message = "Đây là mã của bạn " + code;
                        //properties chuẩn bị thông số cấu hình
                        Properties props = System.getProperties();
                        props.put("mail.smtp.user", "username");
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.port", "587");
                        props.put("mail.debug", "true");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.EnableSSL.enable", "true");

                        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.setProperty("mail.smtp.socketFactory.fallback", "false");
                        props.setProperty("mail.smtp.port", "465");
                        props.setProperty("mail.smtp.socketFactory.port", "465");
                        //Authentication cung cấp tài khoản kết nối mail sever(chứng thực)
                        //Session mở 1 Session dựa vào cấu hình và authentication như trên
                        Session sessiona = Session.getInstance(props,
                                new Authenticator() {
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(user, pass);
                                    }
                                });
                        try {
                            //MimeMessage xây dựng mail:người nhận,người gửi,tiêu đề,nội dung...
                            Message messagea = new MimeMessage(sessiona);
                            messagea.setFrom(new InternetAddress(user));//người gửi
                            messagea.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                            messagea.setSubject(subject);
                            messagea.setText(message);
                            //transport khi bấm n sẽ gửi mail
                            Transport.send(messagea);
                            lblTitle.setText("Chờ mã trong :");
                            JOptionPane.showMessageDialog(null, "Gửi mã thành công");
                            run();
                        } catch (Exception a) {
                            throw new RuntimeException(a);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Đợi chút rồi gửi lại email nha!");
                    }

                }
            }
        });
        btndangki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loi()) {
                    int gioitinh;
                    if (rdonam.isSelected() == true) {
                        gioitinh = 1;
                    } else {
                        gioitinh = 0;
                    }

                    dao.dangKi(txtTen.getText(), txtpass.getText(), txtEmail.getText(), gioitinh, Integer.parseInt(txtmapin.getText()));
                }
            }
        });
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new FromDangNhap();

                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
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

    private void run() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                int i = 90;
                while (i-- > 0) {

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lblTime.setText(String.valueOf(i));
                }
            }
        };
        thread.start();
    }

    public boolean loi() {
        String checkpass = "\\w{5,20}";
        String checkmapin = "\\d{6}";
        if (txtmapin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống mã pin");
            return false;
        }
        if (txtTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống họ tên");
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống email");
            return false;
        }
        if (txtpass.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "không được để trống mật khẩu");
            return false;
        }
        if (!txtpass.getText().matches(checkpass)) {
            JOptionPane.showMessageDialog(null, "pass phải có ít nhất từ 5 - 20 kí tự và không được có kí tự đặc biệt");
            return false;
        }
        if (!txtmapin.getText().matches(checkmapin)) {
            JOptionPane.showMessageDialog(null, "mã pin là số và không được quá 6 kí tự");
            return false;
        }

        if (Integer.parseInt(txtma.getText()) != code) {
            JOptionPane.showMessageDialog(null, "Mã email không đúng");
            txtma.setText("");
            return false;
        }
        return true;
    }

    public class TieuDeNhay extends Thread {
        int icout = -1;
        String arrtext[] = {"Đăng Kí", "Đăng", "Kí"};

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
}
