package views;


import service.SeviceNguoiDung;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.annotation.processing.Messager;
import javax.sound.midi.MidiMessage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

public class formChangePass extends JFrame {
    SeviceNguoiDung seviceNguoiDung = new SeviceNguoiDung();
    int code;
    private JTextField txtEmail;
    private JButton gửiMãButton;
    private JTextField txtCode;
    private JButton btnConfirm;
    private JPanel mainPanel;
    private JPasswordField txtNewPass;
    private JPasswordField txtComfimPass;
    private JButton thoátButton;
    private JTextField txtUser;
    private JLabel lblTime;
    private JLabel lblTitle;

    public formChangePass() {
        this.setTitle("Tìm Mật Khẩu");
        this.setContentPane(mainPanel);
        this.setVisible(true);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(2);
        if (lblTime.getText().equals("0")) {
            gửiMãButton.setEnabled(true);
        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                dispose();

            }
        });
        gửiMãButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lblTime.getText().equals("0") || lblTime.getText().length() == 0) {
                    Random random = new Random();
                    code = random.nextInt(899999) + 100000;

                    //mail nguoi gửi
                    String user = "datntph15646@fpt.edu.vn";
                    String pass = "001202025645ok@@"; // nhập pass
                    String to = txtEmail.getText();
                    String subject = "Mã để đổi mật khẩu";
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
        });
        //nhấn nút đổi mk
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loi()) {
                    int ma = Integer.parseInt(txtUser.getText());
                    seviceNguoiDung.updateQuen(txtComfimPass.getText(), ma, txtEmail.getText());

                }
                dispose();
            }
        });
        thoátButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    FromDangNhap dangNhap = new FromDangNhap();
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });
    }

    private boolean loi() {
        String checkemail = "\\w+@\\w+(\\.\\w+){1,2}";
        String checkpass = "\\w{5,20}";
        if (!txtEmail.getText().matches(checkemail)) {
            JOptionPane.showMessageDialog(null, "không đúng định dạng email vui lòng nhập lại");
            return false;
        }
        if (Integer.parseInt(txtCode.getText()) != code) {
            JOptionPane.showMessageDialog(null, "Mã email không đúng");
            txtCode.setText("");
            return false;
        }
        if (txtUser.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Không được để trống mã");
            txtUser.requestFocus();
            return false;
        }
        if (txtNewPass.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được để trống mật khẩu mới");
            txtNewPass.requestFocus();
            return false;
        }
        if (!txtNewPass.getText().matches(checkpass)) {
            JOptionPane.showMessageDialog(null, "pass phải có ít nhất từ 5 - 20 kí tự và không được có kí tự đặc biệt");
            return false;
        }
        if (txtComfimPass.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng xác nhận lại mật khẩu");
            txtComfimPass.requestFocus();
            return false;
        }
        String pass = String.valueOf(txtNewPass.getPassword());
        String passNew = String.valueOf(txtComfimPass.getPassword());
        System.out.println(pass + " " + passNew);
        if (!pass.equals(passNew)) {
            JOptionPane.showMessageDialog(null, "Nhập lại mật khẩu để xác nhận");
            txtComfimPass.requestFocus();
            txtComfimPass.setText("");
            return false;
        }
        return true;
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


}
