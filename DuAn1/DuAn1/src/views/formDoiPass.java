package views;


import service.SeviceNguoiDung;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

public class formDoiPass extends JFrame {
    SeviceNguoiDung seviceNguoiDung = new SeviceNguoiDung();
    private String pass;
    private int user, vaitro;
    private JPanel main_p;
    private JTextField txtUser;
    private JPasswordField txtPassNow;
    private JPasswordField txtPassNew;
    private JPasswordField txtComfirmPassNew;
    private JButton btnYes;
    private JButton btnHuy;
    private JLabel lblTitle;

    public formDoiPass() {
        this.setContentPane(main_p);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        pack();
        txtUser.setEnabled(false);//khong cho sửa txtuser

        // mở form sẽ lưu giá trị
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                luuText();
            }
        });

        // quá trình đóng form sẽ r form chính
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FromChinh fromChinh = new FromChinh();
                fromChinh.setUser(user);
                fromChinh.setPass(pass);
                fromChinh.setVaitro(vaitro);
                dispose();
            }
        });

        btnYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ma = Integer.parseInt(txtUser.getText());
                seviceNguoiDung.doipass(txtPassNow.getText(), pass, txtPassNew.getText(), txtComfirmPassNew.getText(), ma);

                dispose();
            }
        });
    }


    // check lỗi ở đổi mật khẩu
    private boolean loi() {
        if (!txtPassNow.getText().equals(pass)) {
            JOptionPane.showMessageDialog(null, "bạn nhập sai mật khẩu hiện tại");
            txtPassNow.setText("");
            txtPassNow.requestFocus();
            return false;
        }

        if (txtPassNew.getText().isEmpty() || txtPassNew.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Không được để trống mật khẩu mới");
            txtPassNew.setText("");
            txtPassNew.requestFocus();
            return false;
        }

        if (txtComfirmPassNew.getText().isEmpty() || txtComfirmPassNew.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Không được để trống xác nhận mật khẩu");
            txtComfirmPassNew.setText("");
            txtComfirmPassNew.requestFocus();
            return false;
        }

        if (!txtPassNew.getText().equals(txtComfirmPassNew.getText())) {
            JOptionPane.showMessageDialog(null, "Vui lòng xác nhận đúng mật khẩu mới");
            txtComfirmPassNew.setText("");
            txtComfirmPassNew.requestFocus();
            return false;
        }
        return true
                ;

    }


    // Phương thức lấy giá trị
    public void setUser(int user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setVaitro(int vaitro) {
        this.vaitro = vaitro;
    }

    // phương thức đẩy giá trị vào form
    private void luuText() {
        txtUser.setText(String.valueOf(user));
    }


//    public static void main(String[] args) {
//        new formDoiPass();
//    }
}
